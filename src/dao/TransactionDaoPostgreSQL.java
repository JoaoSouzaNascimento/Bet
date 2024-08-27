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
import model.Transaction;
import model.Usuario;

public class TransactionDaoPostgreSQL implements TransactionDao {

	private static final String INSERT_TRANSACTION = "INSERT INTO \"TRANSACTIONS\" (\"USER_ID\", \"TYPE\", \"STATUS\", \"AMOUNT\") VALUES (?, ?, ?, ?)";

	private static final String UPDATE_TRANSACTION = "UPDATE \"TRANSACTIONS\" SET \"USER_ID\" = ?, \"TYPE\" = ?, \"STATUS\" = ?, \"AMOUNT\" = ? WHERE \"ID\" = ?";

	private static final String SELECT_TRANSACTION_BY_ID = "SELECT * FROM \"TRANSACTIONS\" WHERE \"ID\" = ?";

	private static final String DELETE_TRANSACTION = "DELETE FROM \"TRANSACTIONS\" WHERE \"ID\" = ?";

	private static final String SELECT_ALL_TRANSACTIONS = "SELECT * FROM \"TRANSACTIONS\"";

	private static final String SELECT_TRANSACTIONS_BY_USER = "SELECT * FROM \"TRANSACTIONS\" WHERE \"USER_ID\" = ?";

	@Override
	public Transaction createTransaction(Transaction transaction, Usuario usuario) throws InsercaoException {

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(INSERT_TRANSACTION,
						PreparedStatement.RETURN_GENERATED_KEYS)) {

			ps.setObject(1, usuario.getId());
			ps.setString(2, transaction.getType());
			ps.setInt(3, transaction.getStatus());
			ps.setBigDecimal(4, transaction.getAmount());

			try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					transaction.setId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException e) {
			throw new InsercaoException("Erro ao criar transação no banco:" + e.getMessage());
		}

		return transaction;
	}

	@Override
	public Transaction updateTransaction(Transaction transaction) throws AtualizacaoException {
		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(UPDATE_TRANSACTION)) {

			ps.setObject(1, transaction.getUserId());
			ps.setString(2, transaction.getType());
			ps.setInt(3, transaction.getStatus());
			ps.setBigDecimal(4, transaction.getAmount());
			ps.setInt(5, transaction.getId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new AtualizacaoException("Erro ao atualizar transação", e);
		}

		return transaction;
	}

	@Override
	public Transaction getTransactionById(int id) throws ConsultaException {
		Transaction transaction = null;

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_TRANSACTION_BY_ID)) {

			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					transaction = extractTransactionFromResultSet(rs);
				}
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao buscar transação por ID", e);
		}

		return transaction;
	}

	@Override
	public List<Transaction> listTransactions() throws ConsultaException {
		List<Transaction> transactions = new ArrayList<>();

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_ALL_TRANSACTIONS);
				ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				transactions.add(extractTransactionFromResultSet(rs));
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao buscar todas as transações", e);
		}

		return transactions;
	}

	@Override
	public List<Transaction> getAllTransactionsByUser(UUID userId) throws ConsultaException {
		List<Transaction> transactions = new ArrayList<>();

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(SELECT_TRANSACTIONS_BY_USER)) {

			ps.setObject(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					transactions.add(extractTransactionFromResultSet(rs));
				}
			}
		} catch (SQLException e) {
			throw new ConsultaException("Erro ao buscar transações por usuário", e);
		}

		return transactions;
	}

	@Override
	public void deleteTransaction(Transaction transaction) throws DelecaoException {

		try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
				PreparedStatement ps = conn.prepareStatement(DELETE_TRANSACTION)) {

			ps.setInt(1, transaction.getId());
			int rowsAffected = ps.executeUpdate();

			if (rowsAffected == 0) {
				throw new DelecaoException("Nenhuma transação encontrada com o ID: " + transaction.getId());
			}
		} catch (SQLException e) {
			throw new DelecaoException("Erro ao deletar transação", e);
		}
	}

	private Transaction extractTransactionFromResultSet(ResultSet rs) throws SQLException {
		return new Transaction(rs.getInt("ID"), UUID.fromString(rs.getString("USER_ID")), rs.getString("TYPE"),
				rs.getInt("STATUS"), rs.getBigDecimal("AMOUNT"), rs.getTimestamp("CREATED_AT"),
				rs.getTimestamp("UPDATED_AT"));
	}
}
