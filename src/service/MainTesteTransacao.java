package service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import exceptions.InsercaoException;
import model.CargoUsuario;
import model.Transaction;
import model.Usuario;

public class MainTesteTransacao {
    public static void main(String[] args) {
        TransactionService transacaoService = new TransactionService(); 
        
        UUID usuarioId = UUID.randomUUID();
        Usuario usuario = new Usuario(usuarioId, "exemplo_usuario", "Apelido", "senha123", "usuario@example.com", 100.0, false, CargoUsuario.USUARIO);
        Transaction transaction = new Transaction(1, usuarioId, "DEPOSITO", 1,
                new BigDecimal("50.00"), new Timestamp(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()));
        try {
            transacaoService.createTransaction(transaction, usuario); 
            System.out.println("Transação de depósito criada com sucesso! ID: " + transaction.getId());
        } catch (InsercaoException e) {
            System.err.println("Erro ao criar transação de depósito: " + e.getMessage());
        }
    }
}

