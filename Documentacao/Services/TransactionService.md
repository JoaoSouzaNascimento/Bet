# Classe `TransactionService`

A classe `TransactionService` fornece métodos para criar e gerenciar transações financeiras de usuários, como depósitos e saques.

## Atributos

- `transactionDao`: Instância de `TransactionDaoPostgreSQL` usada para interagir com a base de dados para transações.
- `usuarioDao`: Instância de `UsuarioDaoPostgreSQL` usada para interagir com a base de dados para usuários.

## Construtores

### `TransactionService()`
Cria uma nova instância de `TransactionService`, inicializando `transactionDao` e `usuarioDao` com implementações específicas para PostgreSQL.

## Métodos

### `void createTransaction(Transaction transaction, Usuario usuario) throws InsercaoException`
Cria uma nova transação no banco de dados.

#### Parâmetros:
- `transaction`: Objeto `Transaction` a ser criado.
- `usuario`: Objeto `Usuario` associado à transação.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao criar a transação.

### `void depositar(UUID userId, BigDecimal amount) throws InsercaoException, AtualizacaoException, ConsultaException`
Realiza um depósito na conta de um usuário.

#### Parâmetros:
- `userId`: ID do usuário que receberá o depósito.
- `amount`: Valor do depósito.

#### Exceções:
- `IllegalArgumentException`: Se o valor do depósito for menor ou igual a zero.
- `ConsultaException`: Se o usuário não for encontrado.
- `AtualizacaoException`: Se ocorrer um erro ao atualizar o saldo do usuário.
- `InsercaoException`: Se ocorrer um erro ao criar a transação de depósito.

### `void sacar(UUID userId, BigDecimal amount) throws InsercaoException, AtualizacaoException, ConsultaException`
Realiza um saque da conta de um usuário.

#### Parâmetros:
- `userId`: ID do usuário que realizará o saque.
- `amount`: Valor do saque.

#### Exceções:
- `IllegalArgumentException`: Se o valor do saque for menor ou igual a zero.
- `ConsultaException`: Se o usuário não for encontrado.
- `AtualizacaoException`: Se ocorrer um erro ao atualizar o saldo do usuário ou se o saldo for insuficiente.
- `InsercaoException`: Se ocorrer um erro ao criar a transação de saque.

### `List<Transaction> getAllTransactionsByUser(UUID userId) throws ConsultaException`
Recupera todas as transações associadas a um usuário específico.

#### Parâmetros:
- `userId`: ID do usuário cujas transações serão recuperadas.

#### Retorno:
- `List<Transaction>`: Lista de transações associadas ao usuário.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao consultar as transações.

## Exemplo de Uso

```java
// Criar uma instância do TransactionService
TransactionService transactionService = new TransactionService();

// Realizar um depósito
try {
    transactionService.depositar(UUID.randomUUID(), BigDecimal.valueOf(100));
    System.out.println("Depósito realizado com sucesso.");
} catch (Exception e) {
    System.err.println("Erro ao realizar depósito: " + e.getMessage());
}

// Realizar um saque
try {
    transactionService.sacar(UUID.randomUUID(), BigDecimal.valueOf(50));
    System.out.println("Saque realizado com sucesso.");
} catch (Exception e) {
    System.err.println("Erro ao realizar saque: " + e.getMessage());
}

// Recuperar transações de um usuário
try {
    List<Transaction> transactions = transactionService.getAllTransactionsByUser(UUID.randomUUID());
    transactions.forEach(transaction -> System.out.println("Transação: " + transaction.getType()));
} catch (ConsultaException e) {
    System.err.println("Erro ao recuperar transações: " + e.getMessage());
}
