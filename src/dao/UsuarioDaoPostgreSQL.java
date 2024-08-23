package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Usuario;

public class UsuarioDaoPostgreSQL implements UsuarioDao {

    @Override
    public Usuario getUsuarioByEmail(String email) throws ConsultaException {
        Usuario usuario = null;

        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement("SELECT * from USERS WHERE email = ?")) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario(
                        rs.getObject("id", UUID.class),
                        rs.getString("username"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDouble("balance"),
                        rs.getBoolean("deleted"),
                        rs.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            throw new ConsultaException("Erro ao buscar usuário por email", e);
        }

        return usuario;
    }

    @Override
    public Usuario createUsuario(Usuario usuario) throws InsercaoException {
        String sql = "INSERT INTO \"USERS\" (\"ID\", \"USERNAME\", \"NICKNAME\", \"PASSWORD\", \"EMAIL\", \"BALANCE\", \"DELETED\") VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setObject(1, usuario.getId());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getNickname());
            ps.setString(4, usuario.getPassword());
            ps.setString(5, usuario.getEmail());
            ps.setDouble(6, usuario.getBalance());
            ps.setBoolean(7, usuario.isDeleted());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InsercaoException("Erro ao criar usuário", e);
        }

        return usuario;
    }

    @Override
    public Usuario updateUsuario(Usuario usuario) throws AtualizacaoException {
        String sql = "UPDATE \"USERS\" SET \"USERNAME\" = ?, \"NICKNAME\" = ?, \"PASSWORD\" = ?, \"EMAIL\" = ?, \"BALANCE\" = ?, \"DELETED\" = ? WHERE \"ID\" = ?";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getNickname());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getEmail());
            ps.setDouble(5, usuario.getBalance());
            ps.setBoolean(6, usuario.isDeleted());
            ps.setObject(7, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AtualizacaoException("Erro ao atualizar usuário", e);
        }

        return usuario;
    }

    @Override
    public void deleteUsuario(UUID id) throws DelecaoException {
        String sql = "UPDATE \"USERS\" SET \"DELETED\" = true WHERE \"ID\" = ?";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setObject(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DelecaoException("Nenhum usuário encontrado com o ID: " + id);
            }
        } catch (SQLException e) {
            throw new DelecaoException("Erro ao deletar usuário", e);
        }
    }

    @Override
    public List<Usuario> getTodosUsuarios() throws ConsultaException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM \"USERS\"";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                    rs.getObject("id", UUID.class),
                    rs.getString("username"),
                    rs.getString("nickname"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getDouble("balance"),
                    rs.getBoolean("deleted"),
                    rs.getString("role")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new ConsultaException("Erro ao buscar todos os usuários", e);
        }

        return usuarios;
    }

    @Override
    public List<Usuario> getTodosUsuariosAtivos() throws ConsultaException {
        return getUsuariosPorStatus(false);
    }

    @Override
    public List<Usuario> getTodosUsuariosInativos() throws ConsultaException {
        return getUsuariosPorStatus(true);
    }

    private List<Usuario> getUsuariosPorStatus(boolean deleted) throws ConsultaException {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT * FROM \"USERS\" WHERE \"DELETED\" = ?";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setBoolean(1, deleted);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario(
                        rs.getObject("id", UUID.class),
                        rs.getString("username"),
                        rs.getString("nickname"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getDouble("balance"),
                        rs.getBoolean("deleted"),
                        rs.getString("role")
                    );
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            throw new ConsultaException("Erro ao buscar usuários com status deleted = " + deleted, e);
        }

        return usuarios;
    }
}