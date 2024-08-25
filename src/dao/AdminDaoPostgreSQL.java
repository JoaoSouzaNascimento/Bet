package dao;

import model.Admin;
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
import exceptions.DelecaoException;
import exceptions.InsercaoException;

public class AdminDaoPostgreSQL implements AdminDao {

 //   private Connection conn;

   // public AdminDaoPostgreSQL(Connection conn) {
     //   this.conn = conn;
 //   }

    @Override
    public Admin getAdminByEmail(String email) throws ConsultaException{
        String sql = "SELECT * FROM \"ADMINS\" AS A INNER JOIN \"USERS\" AS U WHERE \"EMAIL\" = ?";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
        		PreparedStatement ps = conn.prepareStatement(sql)) {
           ps.setString(1, email);
           ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Admin(
                        (UUID) rs.getObject("id"),
                        rs.getString("username"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDouble("balance"),
                        rs.getBoolean("deleted"),
                        rs.getString("role"),
                        (UUID) rs.getObject("admin_id"),
                        rs.getString("action"),
                        rs.getTimestamp("log_date")
                );
            }
        } catch (SQLException e) {
        	throw new ConsultaException("Erro ao buscar administrador por email", e);
        }
        return null;
    }

    @Override
    public List<Admin> getAllAdmins() throws ConsultaException{
        List<Admin> admins = new ArrayList<>();
        String sql = "SELECT * FROM \"ADMINS\" AS A INNER JOIN \"USERS\" AS U";
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
        	 PreparedStatement ps = conn.prepareStatement(sql);
        	 ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                admins.add(new Admin(
                        (UUID) rs.getObject("id"),
                        rs.getString("username"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDouble("balance"),
                        rs.getBoolean("deleted"),
                        rs.getString("role"),
                        (UUID) rs.getObject("admin_id"),
                        rs.getString("action"),
                        rs.getTimestamp("log_date")
                ));
            }
        } catch (SQLException e) {
        	throw new ConsultaException("Erro ao buscar todos os administradores", e);
        }
        return admins;
    }

    @Override
    public boolean insertAdmin(Admin admin) throws InsercaoException{
        Connection conn = null;
        PreparedStatement ps = null;	
                	
        try {
            conn = ConexaoBdSingleton.getInstance().getConexao();
            conn.setAutoCommit(false);

            // Reutiliza o método de inserção da classe UsuariosDaoPostegreSQL
            UsuarioDaoPostgreSQL usuarioDao = new UsuarioDaoPostgreSQL();
            usuarioDao.createUsuario(admin);

            // Inserção específica para o Admin
            String sql = "INSERT INTO \"ADMINS\" (id, admin_id, action, log_date) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, admin.getId());
            ps.setObject(2, admin.getAdminId());
            ps.setString(3, admin.getAction());
            ps.setTimestamp(4, admin.getLogDate());
            ps.executeUpdate();

            conn.commit();  // Confirma a transação
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();  // Reverte a transação em caso de erro
                } catch (SQLException ex) {
                    throw new InsercaoException("Erro ao reverter a inserção do administrador", ex);
                }
            }
            throw new InsercaoException("Erro ao criar administrador", e);
        } finally {
            // Fecha os recursos
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public boolean updateAdmin(Admin admin) throws AtualizacaoException{
        Connection conn = null;
        boolean success = false;

        try {
            conn = ConexaoBdSingleton.getInstance().getConexao();
            conn.setAutoCommit(false);  // Desabilita o auto-commit para gerenciar a transação manualmente

            // Atualizar informações de usuário com o método updateUsuario
            UsuarioDaoPostgreSQL usuarioDao = new UsuarioDaoPostgreSQL();
            usuarioDao.updateUsuario(admin); // Admin extends Usuario, então isso é válido

            // Atualização na tabela "ADMINS"
            String sql = "UPDATE \"ADMINS\" SET \"ACTION\" = ?, \"LOG_DATE\" = ? WHERE \"ADMIN_ID\" = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, admin.getAction());
                ps.setTimestamp(2, admin.getLogDate());
                ps.setObject(3, admin.getAdminId());
                int updatedRows = ps.executeUpdate();
                if (updatedRows > 0) {
                    success = true;
                }
            }

            if (success) {
                conn.commit();  // Confirma a transação se tudo estiver correto
                return true;
            } else {
                conn.rollback();  // Reverte a transação se a atualização falhou
                return false;
            }
        } catch (SQLException | AtualizacaoException e) {
            if (conn != null) {
                try {
                    conn.rollback();  // Em caso de erro, reverte todas as mudanças
                } catch (SQLException ex) {
                    throw new AtualizacaoException("Erro ao reverter a atualização do administrador", ex);
                }
            }
            throw new AtualizacaoException("Erro ao atualizar o administrador", e);
        } finally {
            // Fechar a conexão
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    @Override
    public void deleteAdmin(UUID adminId) throws DelecaoException {
        String sql = "DELETE FROM \"ADMINS\" WHERE \"ADMIN_ID\" = ?";
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
        	PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, adminId);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DelecaoException("Nenhum administrador encontrado com o ADMIN_ID: " + adminId);
            }
        } catch (SQLException e) {
        	throw new DelecaoException("Erro ao deletar administrador", e);
        }
    }
    
    @Override
    public void addLog(UUID adminId, String action) throws InsercaoException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexaoBdSingleton.getInstance().getConexao();
            String sql = "INSERT INTO \"ADMIN_LOGS\" (admin_id, acao, data_hora) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, adminId);
            ps.setString(2, action);
            ps.setTimestamp(3, new Timestamp(System.currentTimeMillis())); // Define a data/hora atual
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InsercaoException("Erro ao adicionar log para o administrador", e);
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
	public List<String> getLogsDeAdmin(UUID adminId) throws ConsultaException {
	    Connection conn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    List<String> logs = new ArrayList<>();
	
	    try {
	        conn = ConexaoBdSingleton.getInstance().getConexao();
	        String sql = "SELECT \"ACTION\", \"LOG_DATE\" FROM \"ADMIN_LOGS\" WHERE admin_id = ? ORDER BY \"LOG_DATE\" DESC";
	        ps = conn.prepareStatement(sql);
	        ps.setObject(1, adminId);
	        rs = ps.executeQuery();
	
	        while (rs.next()) {
	            String acao = rs.getString("action");
	            Timestamp dataHora = rs.getTimestamp("logDate");
	            logs.add(acao + " - " + dataHora.toString());
	        }
	
	        return logs;
	    } catch (SQLException e) {
	        throw new ConsultaException("Erro ao recuperar logs do administrador", e);
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (ps != null) ps.close();
	            if (conn != null) conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
}

