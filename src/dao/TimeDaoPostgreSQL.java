package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.InsercaoException;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import model.Time;

public class TimeDaoPostgreSQL implements TimeDao {

    @Override
    public Time createTime(Time time) throws InsercaoException {
        String sql = "INSERT INTO TEAMS (ID, NAME, LOGO) VALUES (?, ?, ?)";

        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, time.getId());
            ps.setString(2, time.getName());
            ps.setString(3, time.getLogo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InsercaoException("Erro ao criar time", e);
        }

        return time;
    }

    @Override
    public Time updateTime(Time time) throws AtualizacaoException {
        String sql = "UPDATE TEAMS SET NAME = ?, LOGO = ? WHERE ID = ?";

        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, time.getName());
            ps.setString(2, time.getLogo());
            ps.setInt(3, time.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AtualizacaoException("Erro ao atualizar time", e);
        }

        return time;
    }

    @Override
    public List<Time> listTimes() throws ConsultaException {
        List<Time> times = new ArrayList<>();
        String sql = "SELECT ID, NAME, LOGO FROM TEAMS";

        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Time time = new Time(
                    rs.getInt("ID"),
                    rs.getString("NAME"),
                    rs.getString("LOGO")
                );
                times.add(time);
            }

        } catch (SQLException e) {
            throw new ConsultaException("Erro ao listar times", e);
        }

        return times;
    }

    @Override
    public void deleteTime(Time time) throws DelecaoException {
        String sql = "DELETE FROM TEAMS WHERE ID = ?";

        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, time.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DelecaoException("Nenhum time encontrado com o ID: " + time.getId());
            }
        } catch (SQLException e) {
            throw new DelecaoException("Erro ao deletar time", e);
        }
    }
}
