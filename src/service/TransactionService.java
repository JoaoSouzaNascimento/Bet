package service;

import dao.TransactionDaoPostgreSQL;
import dao.UsuarioDaoPostgreSQL;
import model.Transaction;
import model.Usuario;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.InsercaoException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

public class TransactionService {
    private final TransactionDaoPostgreSQL transactionDao;
    private final UsuarioDaoPostgreSQL usuarioDao;
    public TransactionService() {
    	super();
    	this.transactionDao = new TransactionDaoPostgreSQL();
		this.usuarioDao = new UsuarioDaoPostgreSQL();
    }
    
    public void createTransaction(Transaction transaction) throws InsercaoException {
        try {
            transactionDao.createTransaction(transaction);
        } catch (InsercaoException e) {
            throw new InsercaoException("Erro ao criar transação", e);
        }
    }

    public void depositar(UUID userId, BigDecimal amount) throws InsercaoException, AtualizacaoException, ConsultaException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do depósito deve ser positivo.");
        }

        // atualiza o saldo 
        Usuario usuario = usuarioDao.getUsuarioById(userId);
        if (usuario == null) {
            throw new ConsultaException("Usuário não encontrado.");
        }
        
        double newBalance = usuario.getBalance() + amount.doubleValue();
        usuario.setBalance(newBalance);
        usuarioDao.updateUsuario(usuario);
        
        // cria transacao
        Transaction transaction = new Transaction(
            0, // ID vai ser gerado automaticamente no banco
            userId,
            "DEPOSITO",
            1, // status 1 = concluido
            amount,
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis())
        );

        // salva transacao
        createTransaction(transaction);
    }

    public void sacar(UUID userId, BigDecimal amount) throws InsercaoException, AtualizacaoException, ConsultaException {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor do saque deve ser positivo.");
        }

        // atualiza o saldo
        Usuario usuario = usuarioDao.getUsuarioById(userId);
        if (usuario == null) {
            throw new ConsultaException("Usuário não encontrado.");
        }
        
        double newBalance = usuario.getBalance() - amount.doubleValue();
        if (newBalance < 0) {
            throw new AtualizacaoException("Saldo insuficiente.");
        }
        usuario.setBalance(newBalance);
        usuarioDao.updateUsuario(usuario);
        
        // cria transacao
        Transaction transaction = new Transaction(
            0, // ID vai ser gerado automaticamente no banco
            userId,
            "SAQUE",
            1, // status 1 = concluio
            amount,
            new Timestamp(System.currentTimeMillis()),
            new Timestamp(System.currentTimeMillis())
        );

        // salva transacao
        createTransaction(transaction);
    }
    
    public List<Transaction> getAllTransactionsByUser(UUID userId) throws ConsultaException {
        return transactionDao.getAllTransactionsByUser(userId);
    }
}