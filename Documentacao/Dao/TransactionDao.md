# Interface `TransactionDao`

A interface `TransactionDao` define os métodos para operações relacionadas a transações na base de dados.

## Métodos

### `Transaction createTransaction(Transaction transaction, Usuario usuario) throws InsercaoException`
Cria uma nova transação na base de dados associada a um usuário.

#### Parâmetros:
- `transaction`: O objeto `Transaction` que representa a transação a ser criada.
- `usuario`: O objeto `Usuario` associado à transação.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir a transação.

#### Retorno:
- `Transaction`: A transação criada com o ID gerado.

### `Transaction updateTransaction(Transaction transaction) throws AtualizacaoException`
Atualiza uma transação existente na base de dados.

#### Parâmetros:
- `transaction`: O objeto `Transaction` que representa a transação a ser atualizada.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro ao tentar atualizar a transação.

#### Retorno:
- `Transaction`: A transação atualizada.

### `List<Transaction> listTransactions() throws ConsultaException`
Lista todas as transações na base de dados.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar listar as transações.

#### Retorno:
- `List<Transaction>`: Uma lista de objetos `Transaction`.

### `void deleteTransaction(Transaction transaction) throws DelecaoException`
Deleta uma transação da base de dados.

#### Parâmetros:
- `transaction`: O objeto `Transaction` que representa a transação a ser deletada.

#### Exceções:
- `DelecaoException`: Se ocorrer um erro ao tentar deletar a transação.

### `List<Transaction> getAllTransactionsByUser(UUID userId) throws ConsultaException`
Obtém todas as transações associadas a um usuário específico.

#### Parâmetros:
- `userId`: O ID do usuário cujas transações devem ser retornadas.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar as transações do usuário.

#### Retorno:
- `List<Transaction>`: Uma lista de objetos `Transaction` associada ao usuário.

### `Transaction getTransactionById(int id) throws ConsultaException`
Obtém uma transação específica com base no seu ID.

#### Parâmetros:
- `id`: O ID da transação a ser consultada.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar a transação.

#### Retorno:
- `Transaction`: A transação consultada.
