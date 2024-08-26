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
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Transaction;
import model.Usuario;

public class TransactionDaoPostgreSQL implements TransactionDao {

    private static final String INSERT_TRANSACTION = 
        "INSERT INTO \"TRANSACTIONS\" (\"USER_ID\", \"TYPE\", \"STATUS\", \"AMOUNT\", \"CREATED_AT\", \"UPDATED_AT\") VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_TRANSACTION = 
        "UPDATE \"TRANSACTIONS\" SET \"USER_ID\" = ?, \"TYPE\" = ?, \"STATUS\" = ?, \"AMOUNT\" = ?, \"CREATED_AT\" = ?, \"UPDATED_AT\" = ? WHERE \"ID\" = ?";

    private static final String SELECT_TRANSACTION_BY_ID = 
        "SELECT * FROM \"TRANSACTIONS\" WHERE \"ID\" = ?";

    private static final String DELETE_TRANSACTION = 
        "DELETE FROM \"TRANSACTIONS\" WHERE \"ID\" = ?";

    private static final String SELECT_ALL_TRANSACTIONS = 
        "SELECT * FROM \"TRANSACTIONS\"";

    private static final String SELECT_TRANSACTIONS_BY_USER = 
        "SELECT * FROM \"TRANSACTIONS\" WHERE \"USER_ID\" = ?";

    @Override
    public Transaction createTransaction(Transaction transaction, Usuario usuario) throws InsercaoException {
    	System.out.println("debuggando1");
    	try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(INSERT_TRANSACTION, PreparedStatement.RETURN_GENERATED_KEYS)) {
        	System.out.println("debuggando2");
        	System.out.println("ID do Usuário: " + usuario.getId());
            
            System.out.println("Valor do Depósito: R$" + transaction.getAmount());
            ps.setObject(1, usuario.getId());  // Usando o ID do usuário diretamente
            ps.setString(2, transaction.getType());
            ps.setInt(3, transaction.getStatus());
            ps.setBigDecimal(4, transaction.getAmount());
            ps.setTimestamp(5, transaction.getCreatedAt());
            ps.setTimestamp(6, transaction.getUpdatedAt());
            System.out.println("debuggando3");
            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
            	System.out.println("debuggando4");
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        transaction.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            throw new InsercaoException("Erro ao criar transação", e);
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
            ps.setTimestamp(5, transaction.getCreatedAt());
            ps.setTimestamp(6, transaction.getUpdatedAt());
            ps.setInt(7, transaction.getId());

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
        return new Transaction(
            rs.getInt("ID"),
            UUID.fromString(rs.getString("USER_ID")),
            rs.getString("TYPE"),
            rs.getInt("STATUS"),
            rs.getBigDecimal("AMOUNT"),
            rs.getTimestamp("CREATED_AT"),
            rs.getTimestamp("UPDATED_AT")
        );
    }
}
