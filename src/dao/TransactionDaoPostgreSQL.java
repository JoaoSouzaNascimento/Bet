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

public class TransactionDaoPostgreSQL implements TransactionDao {

    @Override
    public Transaction createTransaction(Transaction transaction) throws InsercaoException {
        String sql = "INSERT INTO \"TRANSACTIONS\" (\"USER_ID\", \"TYPE\", \"STATUS\", \"AMOUNT\", \"CREATED_AT\", \"UPDATED_AT\") VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setObject(1, transaction.getUserId());
            ps.setString(2, transaction.getType());
            ps.setInt(3, transaction.getStatus());
            ps.setBigDecimal(4, transaction.getAmount());
            ps.setTimestamp(5, transaction.getCreatedAt());
            ps.setTimestamp(6, transaction.getUpdatedAt());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InsercaoException("Erro ao criar transação", e);
        }

        return transaction;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) throws AtualizacaoException {
        String sql = "UPDATE \"TRANSACTIONS\" SET \"USER_ID\" = ?, \"TYPE\" = ?, \"STATUS\" = ?, \"AMOUNT\" = ?, \"CREATED_AT\" = ?, \"UPDATED_AT\" = ? WHERE \"ID\" = ?";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
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
    public List<Transaction> listTransactions() throws ConsultaException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM \"TRANSACTIONS\"";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Transaction transaction = new Transaction(
                    rs.getInt("ID"),
                    UUID.fromString(rs.getString("USER_ID")),
                    rs.getString("TYPE"),
                    rs.getInt("STATUS"),
                    rs.getBigDecimal("AMOUNT"),
                    rs.getTimestamp("CREATED_AT"),
                    rs.getTimestamp("UPDATED_AT")
                );
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            throw new ConsultaException("Erro ao buscar todas as transações", e);
        }

        return transactions;
    }

    @Override
    public void deleteTransaction(Transaction transaction) throws DelecaoException {
        String sql = "DELETE FROM \"TRANSACTIONS\" WHERE \"ID\" = ?";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, transaction.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DelecaoException("Nenhuma transação encontrada com o ID: " + transaction.getId());
            }
        } catch (SQLException e) {
            throw new DelecaoException("Erro ao deletar transação", e);
        }
    }
}
