# Classe `TransactionDaoPostgreSQL`

A classe `TransactionDaoPostgreSQL` é uma implementação da interface `TransactionDao` e fornece métodos para gerenciar transações em uma base de dados PostgreSQL.

## Métodos

### `Transaction createTransaction(Transaction transaction, Usuario usuario) throws InsercaoException`
Insere uma nova transação na tabela `TRANSACTIONS`.

#### Parâmetros:
- `transaction`: O objeto `Transaction` que representa a transação a ser criada.
- `usuario`: O objeto `Usuario` associado à transação.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir a transação.

#### Retorno:
- `Transaction`: A transação criada com o ID gerado.

### `Transaction updateTransaction(Transaction transaction) throws AtualizacaoException`
Atualiza uma transação existente na tabela `TRANSACTIONS`.

#### Parâmetros:
- `transaction`: O objeto `Transaction` que representa a transação a ser atualizada.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro ao tentar atualizar a transação.

#### Retorno:
- `Transaction`: A transação atualizada.

### `List<Transaction> listTransactions() throws ConsultaException`
Lista todas as transações na tabela `TRANSACTIONS`.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar listar as transações.

#### Retorno:
- `List<Transaction>`: Uma lista de objetos `Transaction`.

### `void deleteTransaction(Transaction transaction) throws DelecaoException`
Deleta uma transação da tabela `TRANSACTIONS`.

#### Parâmetros:
- `transaction`: O objeto `Transaction` que representa a transação a ser deletada.

#### Exceções:
- `DelecaoException`: Se ocorrer um erro ao tentar deletar a transação.

### `List<Transaction> getAllTransactionsByUser(UUID userId) throws ConsultaException`
Obtém todas as transações associadas a um usuário específico da tabela `TRANSACTIONS`.

#### Parâmetros:
- `userId`: O ID do usuário cujas transações devem ser retornadas.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar as transações do usuário.

#### Retorno:
- `List<Transaction>`: Uma lista de objetos `Transaction` associada ao usuário.

### `Transaction getTransactionById(int id) throws ConsultaException`
Obtém uma transação específica da tabela `TRANSACTIONS` com base no seu ID.

#### Parâmetros:
- `id`: O ID da transação a ser consultada.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar a transação.

#### Retorno:
- `Transaction`: A transação consultada.

## Notas

- A URL de conexão com a base de dados é gerenciada pela classe `ConexaoBdSingleton`, e a conexão é estabelecida através do método `getConexao()` dessa classe.
- O método `createTransaction` insere um novo registro na tabela `TRANSACTIONS` e retorna o ID gerado para a transação.
- O método `updateTransaction` atualiza registros na tabela `TRANSACTIONS` com base no ID da transação.
- O método `listTransactions` retorna todas as transações da tabela `TRANSACTIONS`.
- O método `getAllTransactionsByUser` retorna todas as transações associadas ao ID do usuário fornecido.
- O método `getTransactionById` retorna uma transação específica com base no ID fornecido.
- O método `deleteTransaction` deleta uma transação com base no ID fornecido.

## SQL Queries

- `INSERT_TRANSACTION`: `"INSERT INTO \"TRANSACTIONS\" (\"USER_ID\", \"TYPE\", \"STATUS\", \"AMOUNT\") VALUES (?, ?, ?, ?) RETURNING \"ID\""`
- `UPDATE_TRANSACTION`: `"UPDATE \"TRANSACTIONS\" SET \"USER_ID\" = ?, \"TYPE\" = ?, \"STATUS\" = ?, \"AMOUNT\" = ? WHERE \"ID\" = ?"`
- `SELECT_TRANSACTION_BY_ID`: `"SELECT * FROM \"TRANSACTIONS\" WHERE \"ID\" = ?"`
- `DELETE_TRANSACTION`: `"DELETE FROM \"TRANSACTIONS\" WHERE \"ID\" = ?"`
- `SELECT_ALL_TRANSACTIONS`: `"SELECT * FROM \"TRANSACTIONS\""`
- `SELECT_TRANSACTIONS_BY_USER`: `"SELECT * FROM \"TRANSACTIONS\" WHERE \"USER_ID\" = ?"`
