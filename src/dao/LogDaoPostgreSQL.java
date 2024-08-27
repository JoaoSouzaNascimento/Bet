
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.InsercaoException;
import model.AdminLog;

public class LogDaoPostgreSQL implements LogDao {

    private static final String INSERT_LOG = "INSERT INTO \"ADMIN_LOGS\" (\"ADMIN_ID\", \"ACTION\") VALUES (?, ?)";
    private static final String SELECT_LOGS_BY_ADMIN_ID = "SELECT * FROM \"ADMIN_LOGS\" WHERE \"ADMIN_ID\" = ? ORDER BY \"LOG_DATE\" DESC";

    @Override
    public void createLog(AdminLog log) throws InsercaoException {
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(INSERT_LOG)) {

            ps.setObject(1, log.getAdminId());
            ps.setString(2, log.getAction());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InsercaoException("Erro ao criar log", e);
        }
    }

    @Override
    public List<AdminLog> getTodosLogsDeUmAdmin(UUID adminId) throws ConsultaException {
        List<AdminLog> logs = new ArrayList<>();

        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(SELECT_LOGS_BY_ADMIN_ID)) {

            ps.setObject(1, adminId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    AdminLog log = new AdminLog(
                        rs.getInt("ID"),
                        rs.getObject("ADMIN_ID", UUID.class),
                        rs.getString("ACTION"),
                        rs.getTimestamp("LOG_DATE")
                    );
                    logs.add(log);
                }
            }
        } catch (SQLException e) {
            throw new ConsultaException("Erro ao buscar logs do admin", e);
        }

        return logs;
    }
}
