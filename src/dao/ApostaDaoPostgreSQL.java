package dao;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import model.Aposta;

public class ApostaDaoPostgreSQL implements ApostaDao {

	private static final String INSERT_BET = "INSERT INTO \"BETS\" (\"USER_ID\", \"AMOUNT\", \"STATUS\", \"DATE\") VALUES (?, ?, ?, ?) RETURNING \"ID\"";
	private static final String UPDATE_BET = "UPDATE \"BETS\" SET \"AMOUNT\" = ?, \"STATUS\" = ?, \"DATE\" = ?, WHERE \"ID\" = ?";
	private static final String SELECT_BET_BY_ID = "SELECT * FROM \"BETS\" WHERE \"ID\" = ?";
	private static final String SELECT_ALL_BETS = "SELECT * FROM \"BETS\" WHERE \"USER_ID\" = ? ";
	private static final String DELETE_BET = "DELETE FROM \"BETS\" WHERE \"ID\" = ?";
	private static final String SELECT_PENDING_BETS_BY_USER_ID = "SELECT * FROM \"BETS\" WHERE \"USER_ID\" = ? AND \"STATUS\" IS NULL";

	@Override
	public Aposta createAposta(Aposta aposta) throws InsercaoException {

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(INSERT_BET)) {

			ps.setObject(1, aposta.getUserId());
			ps.setBigDecimal(2, aposta.getAmount());
			if (aposta.getStatus() == null) {
				ps.setNull(3, java.sql.Types.BOOLEAN);
			} else {
				ps.setBoolean(3, aposta.getStatus());
			}
			ps.setDate(4, java.sql.Date.valueOf(aposta.getDate()));

			try (ResultSet generatedKeys = ps.executeQuery()) {
				if (generatedKeys.next()) {
					aposta.setId(generatedKeys.getInt("ID"));
				}
			}

			if (aposta.getId() == null) {
				throw new InsercaoException("Erro ao criar aposta: ID não foi definido");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new InsercaoException("Erro ao criar aposta", e);
		}
		return aposta;
	}

	@Override
	public Aposta updateAposta(Aposta aposta) throws AtualizacaoException {

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(UPDATE_BET)) {

			ps.setInt(5, aposta.getId());
			ps.setBigDecimal(1, aposta.getAmount());
			if (aposta.getStatus() == null) {
				ps.setNull(2, java.sql.Types.BOOLEAN);
			} else {
				ps.setBoolean(2, aposta.getStatus());
			}
			ps.setDate(3, java.sql.Date.valueOf(aposta.getDate()));

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AtualizacaoException("Erro ao atualizar aposta", e);
		}
		return aposta;

	}

	@Override
	public void deleteAposta(int id) throws DelecaoException {

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(DELETE_BET)) {

			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DelecaoException("Erro ao deletar aposta", e);
		}
	}

	@Override
	public Aposta getApostaById(int id) throws ConsultaException {
		Aposta aposta = null;

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_BET_BY_ID)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				aposta = extractApostaFromResultSet(rs);
			} else {
				throw new ConsultaException("Usuario não encontrado");
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao buscar aposta por id", e);
		}

		return aposta;
	}

	@Override
	public List<Aposta> getTodasApostasPorUsuarioId(UUID usuarioId) throws ConsultaException {
		List<Aposta> apostas = new ArrayList<>();

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_ALL_BETS);) {

			ps.setObject(1, usuarioId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Aposta aposta = extractApostaFromResultSet(rs);
				apostas.add(aposta);
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao listar todas as apostas", e);
		}

		return apostas;
	}

	public List<Aposta> getApostasPendentesPorUsuarioId(UUID usuarioId) throws ConsultaException {
		List<Aposta> apostas = new ArrayList<>();

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_PENDING_BETS_BY_USER_ID);) {

			ps.setObject(1, usuarioId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Aposta aposta = extractApostaFromResultSet(rs);
				apostas.add(aposta);
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao listar apostas pendentes", e);
		}

		return apostas;
	}

	private static final String SELECT_BETS_WITH_NULL_STATUS = "SELECT * FROM \"BETS\" WHERE \"STATUS\" IS NULL";

	@Override
	public List<Aposta> getApostasComStatusNulo() throws ConsultaException {
		List<Aposta> apostas = new ArrayList<>();

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_BETS_WITH_NULL_STATUS)) {

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Aposta aposta = extractApostaFromResultSet(rs);
				apostas.add(aposta);
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao listar apostas com status nulo", e);
		}

		return apostas;
	}

	private Aposta extractApostaFromResultSet(ResultSet rs) throws SQLException {
		Integer id = rs.getInt("ID");
		UUID userId = rs.getObject("USER_ID", UUID.class);
		BigDecimal amount = rs.getBigDecimal("AMOUNT");
		Boolean status = (Boolean) rs.getObject("STATUS");
		LocalDate date = rs.getDate("DATE").toLocalDate();

		return new Aposta(id, userId, amount, null, status, date);
	}

}
