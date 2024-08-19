package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Transaction;

public interface TransactionDao {
    public Transaction createTransaction(Transaction transaction) throws InsercaoException;
    public Transaction updateTransaction(Transaction transaction) throws AtualizacaoException;
    public List<Transaction> listTransactions() throws ConsultaException;
    public void deleteTransaction(Transaction transaction) throws DelecaoException;
}
