# Classe `Transaction`

A classe `Transaction` representa uma transação financeira no sistema de casa de apostas, como depósitos, retiradas ou apostas. Ela armazena informações sobre o tipo, valor, status e timestamps da transação.

## Atributos

- `int id`: Identificador único da transação.
- `UUID userId`: Identificador do usuário que realizou a transação.
- `String type`: Tipo da transação (por exemplo, depósito, retirada, aposta).
- `int status`: Status da transação (ex.: pendente, completa, falha).
- `BigDecimal amount`: Valor da transação.
- `Timestamp createdAt`: Data e hora da criação da transação.
- `Timestamp updatedAt`: Data e hora da última atualização da transação.

## Construtor

### `Transaction(int id, UUID userId, String type, int status, BigDecimal amount, Timestamp createdAt, Timestamp updatedAt)`

Construtor para inicializar uma nova transação com todos os seus atributos.

#### Parâmetros:

- `int id`: ID único da transação.
- `UUID userId`: ID do usuário associado.
- `String type`: Tipo da transação.
- `int status`: Status atual da transação.
- `BigDecimal amount`: Valor da transação.
- `Timestamp createdAt`: Data de criação.
- `Timestamp updatedAt`: Data de última atualização.

## Métodos

### `int getId()`
Retorna o ID da transação.

### `void setId(int id)`
Define o ID da transação.

### `UUID getUserId()`
Retorna o ID do usuário associado à transação.

### `void setUserId(UUID userId)`
Define o ID do usuário associado.

### `String getType()`
Retorna o tipo da transação.

### `void setType(String type)`
Define o tipo da transação.

### `int getStatus()`
Retorna o status da transação.

### `void setStatus(int status)`
Define o status da transação.

### `BigDecimal getAmount()`
Retorna o valor da transação.

### `void setAmount(BigDecimal amount)`
Define o valor da transação.

### `Timestamp getCreatedAt()`
Retorna a data de criação da transação.

### `void setCreatedAt(Timestamp createdAt)`
Define a data de criação da transação.

### `Timestamp getUpdatedAt()`
Retorna a data da última atualização da transação.

### `void setUpdatedAt(Timestamp updatedAt)`
Define a data de última atualização da transação.
