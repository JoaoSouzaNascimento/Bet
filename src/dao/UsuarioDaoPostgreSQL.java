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
import model.CargoUsuario;

public class UsuarioDaoPostgreSQL implements UsuarioDao {

	private static final String SELECT_USER_BY_EMAIL = "SELECT * from \"USERS\" WHERE \"EMAIL\" = ?";
	private static final String SELECT_USER_BY_ID = "SELECT * FROM \"USERS\" WHERE \"ID\" = ?";
	private static final String INSERT_USER = "INSERT INTO \"USERS\" (\"ID\", \"USERNAME\", \"NICKNAME\", \"PASSWORD\", \"EMAIL\", \"BALANCE\", \"DELETED\", \"ROLE\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_STATUS_USER = "UPDATE \"USERS\" SET \"DELETED\" = ? WHERE \"ID\" = ?";
	private static final String SELECT_ALL_USERS = "SELECT * FROM \"USERS\"";
	private static final String SELECT_USERS_BY_STATUS = "SELECT * FROM \"USERS\" WHERE \"DELETED\" = ?";
	private static final String UPDATE_ALL_USER_FIELDS = "UPDATE \"USERS\" SET \"USERNAME\" = ?, \"NICKNAME\" = ?, \"PASSWORD\" = ?, \"EMAIL\" = ?, \"BALANCE\" = ?, \"DELETED\" = ?, \"ROLE\" = ? WHERE \"ID\" = ?";

	@Override
	public Usuario getUsuarioByEmail(String email) throws ConsultaException {
		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_EMAIL)) {

<<<<<<< HEAD
        return usuario;
    }
    
    
    @Override
    public Usuario getUsuarioById(UUID id) throws ConsultaException {
        Usuario usuario = null;
        String sql = "SELECT * FROM \"USERS\" WHERE \"ID\" = ?";
        
        System.out.println("Executando consulta para ID: " + id);

        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
        	ps.setObject(1, id);
        	
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Dados retornados do ResultSet:");
                    System.out.println("ID: " + rs.getObject("ID"));
                    System.out.println("Username: " + rs.getString("USERNAME"));
                    // Adicione prints para outros campos conforme necessário

                    usuario = new Usuario(
                        rs.getObject("ID", UUID.class),
                        rs.getString("USERNAME"),
                        rs.getString("NICKNAME"),
                        rs.getString("PASSWORD"),
                        rs.getString("EMAIL"),
                        rs.getDouble("BALANCE"),
                        rs.getBoolean("DELETED"),
                        rs.getString("POST") // Assumindo que o nome da coluna é POST, confirme com o schema
                    );
                    System.out.println("Usuário encontrado: " + usuario);
                } else {
                    System.out.println("Nenhum usuário encontrado para o ID: " + id);
                }
            }
        } catch (SQLException e) {
            throw new ConsultaException("Erro ao buscar o usuário por ID", e);
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
=======
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return extractUserFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao buscar usuário por email", e);
		}

		return null;
	}
>>>>>>> 7fc9b8447c68aae5c1f7ae2d331ae49a56f64127

	@Override
	public Usuario getUsuarioById(UUID id) throws ConsultaException {
		Usuario usuario = null;

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_ID)) {

			ps.setObject(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					return extractUserFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao buscar o usuário por ID", e);
		}
		return usuario;
	}

	@Override
	public Usuario createUsuario(Usuario usuario) throws InsercaoException {

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(INSERT_USER)) {

			ps.setObject(1, usuario.getId());
			ps.setString(2, usuario.getUsername());
			ps.setString(3, usuario.getNickname());
			ps.setString(4, usuario.getPassword());
			ps.setString(5, usuario.getEmail());
			ps.setDouble(6, usuario.getBalance());
			ps.setBoolean(7, usuario.isDeleted());
			ps.setString(8, usuario.getRole().name());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new InsercaoException("Erro ao criar usuário", e);
		}

		return usuario;
	}

	@Override
	public Usuario updateUsuario(Usuario usuario) throws AtualizacaoException {
		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(UPDATE_ALL_USER_FIELDS)) {

			ps.setString(1, usuario.getUsername());
			ps.setString(2, usuario.getNickname());
			ps.setString(3, usuario.getPassword());
			ps.setString(4, usuario.getEmail());
			ps.setDouble(5, usuario.getBalance());
			ps.setBoolean(6, usuario.isDeleted());
			ps.setString(7, usuario.getRole().name());
			ps.setObject(8, usuario.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AtualizacaoException("Erro ao atualizar usuário", e);
		}

		return usuario;
	}

	@Override
	public void deleteUsuario(UUID id) throws DelecaoException {
		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(UPDATE_STATUS_USER)) {

			ps.setBoolean(1, true);
			ps.setObject(2, id);
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

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_ALL_USERS);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Usuario usuario = extractUserFromResultSet(rs);
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

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_USERS_BY_STATUS)) {

			ps.setBoolean(1, deleted);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Usuario usuario = extractUserFromResultSet(rs);
					usuarios.add(usuario);
				}
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao buscar usuários com status deleted = " + deleted, e);
		}

		return usuarios;
	}

	private Usuario extractUserFromResultSet(ResultSet rs) throws SQLException {
		return new Usuario(rs.getObject("id", UUID.class), rs.getString("username"), rs.getString("nickname"),
				rs.getString("password"), rs.getString("email"), rs.getDouble("balance"), rs.getBoolean("deleted"),
				CargoUsuario.valueOf(rs.getString("role")));
	}

	@Override
	public void ativarUsuario(UUID id) throws AtualizacaoException {
		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(UPDATE_STATUS_USER)) {

			ps.setBoolean(1, true);
			ps.setObject(2, id);
			int rowsAffected = ps.executeUpdate();
			if (rowsAffected == 0) {
				throw new AtualizacaoException("Nenhum usuário encontrado com o ID: " + id);
			}
		} catch (SQLException e) {
			throw new AtualizacaoException("Erro ao ativar usuário", e);
		}
	}

}