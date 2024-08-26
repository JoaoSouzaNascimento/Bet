package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Palpite;
import model.ResultadoPartida;

public class PalpiteDaoPostgreSQL implements PalpiteDao {

	private static final String INSERT_PALPITE = "INSERT INTO BETS_MATCHES (BET_ID, MATCH_ID, SHOT, ODD) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_PALPITE = "UPDATE BETS_MATCHES SET SHOT = ? WHERE BET_ID = ? AND MATCH_ID = ?";
	private static final String DELETE_PALPITE = "DELETE FROM BETS_MATCHES WHERE BET_ID = ? AND MATCH_ID = ?";
	private static final String SELECT_PALPITE_BY_MATCH_ID = "SELECT BET_ID, MATCH_ID, SHOT, ODD FROM BETS_MATCHES WHERE BET_ID = ? AND MATCH_ID = ?";
	private static final String SELECT_ALL_PALPITES = "SELECT BET_ID, MATCH_ID, SHOT, ODD FROM BETS_MATCHES WHERE BET_ID = ?";

	private Connection getConnection() throws SQLException {
		return ConexaoBdSingleton.getInstance().getConexao();
	}

	@Override
	public Palpite createPalpite(Palpite palpite) throws InsercaoException {
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(INSERT_PALPITE)) {

			stmt.setInt(1, palpite.getApostaId());
			stmt.setInt(2, palpite.getPartidaId());
			stmt.setString(3, palpite.getResultado().name());
			stmt.setBigDecimal(4, palpite.getOdd());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				return palpite;
			} else {
				throw new InsercaoException("Falha ao inserir palpite.");
			}
		} catch (SQLException e) {
			throw new InsercaoException("Erro ao inserir palpite: " + e.getMessage(), e);
		}
	}

	@Override
	public void createListaDePalpites(List<Palpite> palpites) throws InsercaoException {
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(INSERT_PALPITE)) {

			for (Palpite palpite : palpites) {
				stmt.setInt(1, palpite.getApostaId());
				stmt.setInt(2, palpite.getPartidaId());
				stmt.setString(3, palpite.getResultado().name());
				stmt.setBigDecimal(4, palpite.getOdd());
				stmt.addBatch();
			}

			int[] results = stmt.executeBatch();
			for (int result : results) {
				if (result == PreparedStatement.EXECUTE_FAILED) {
					throw new InsercaoException("Falha ao inserir um ou mais palpites.");
				}
			}

		} catch (SQLException e) {
			throw new InsercaoException("Erro ao inserir palpites: " + e.getMessage(), e);
		}
	}

	@Override
	public Palpite updatePalpite(Palpite palpite) throws AtualizacaoException {
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(UPDATE_PALPITE)) {

			stmt.setString(1, palpite.getResultado().name());
			stmt.setInt(2, palpite.getApostaId());
			stmt.setInt(3, palpite.getPartidaId());

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				return palpite;
			} else {
				throw new AtualizacaoException("Falha ao atualizar palpite.");
			}
		} catch (SQLException e) {
			throw new AtualizacaoException("Erro ao atualizar palpite: " + e.getMessage(), e);
		}
	}

	@Override
	public void deletePalpite(int apostaId, int partidaId) throws DelecaoException {
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(DELETE_PALPITE)) {

			stmt.setInt(1, apostaId);
			stmt.setInt(2, partidaId);

			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected == 0) {
				throw new DelecaoException("Falha ao deletar aposta.");
			}
		} catch (SQLException e) {
			throw new DelecaoException("Erro ao deletar aposta: " + e.getMessage(), e);
		}
	}

	@Override
	public Palpite getPalpiteById(int apostaId, int partidaId) throws ConsultaException {
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(SELECT_PALPITE_BY_MATCH_ID)) {

			stmt.setInt(1, partidaId);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					stmt.setInt(1, partidaId);
					stmt.setInt(2, partidaId);
					return new Palpite(apostaId, partidaId,
							rs.getString("SHOT") == null ? null : ResultadoPartida.valueOf(rs.getString("SHOT")),
							rs.getBigDecimal("ODD"));
				} else {
					throw new ConsultaException("Palpite não encontrado para a partida: " + partidaId);
				}
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao consultar palpite: " + e.getMessage(), e);
		}
	}

	@Override
	public List<Palpite> getTodosPalpitesDeUmaAposta(int apostaId) throws ConsultaException {
		List<Palpite> palpites = new ArrayList<>();
		try (Connection connection = getConnection();
				PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_PALPITES);
				ResultSet rs = stmt.executeQuery()) {

			while (rs.next()) {
				stmt.setInt(1, apostaId);
				Palpite palpite = new Palpite(apostaId, rs.getInt("MATCH_ID"),
						rs.getString("SHOT") == null ? null : ResultadoPartida.valueOf(rs.getString("SHOT")),
						rs.getBigDecimal("ODD"));
				palpites.add(palpite);
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao consultar todos os palpites: " + e.getMessage(), e);
		}
		return palpites;
	}

}
