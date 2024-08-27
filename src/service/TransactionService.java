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
    
    public void createTransaction(Transaction transaction, Usuario usuario) throws InsercaoException {
        try {
            transactionDao.createTransaction(transaction, usuario);
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
        
        BigDecimal newBalance = usuario.getBalance().add(amount);
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

        try {
            createTransaction(transaction, usuario);
        } catch (InsercaoException e) {
            throw new InsercaoException("Erro ao criar transação de depósito", e);
        }
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
        
        BigDecimal newBalance = usuario.getBalance().subtract(amount);
        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
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

        try {
            createTransaction(transaction, usuario);
        } catch (InsercaoException e) {
            throw new InsercaoException("Erro ao criar transação de saque", e);
        }
    }
    
    public List<Transaction> getAllTransactionsByUser(UUID userId) throws ConsultaException {
        return transactionDao.getAllTransactionsByUser(userId);
    }
}