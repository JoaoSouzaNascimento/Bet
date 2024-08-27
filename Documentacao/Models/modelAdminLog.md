NAO IMPLEMENTADO

# Classe `AdminLog`

A classe `AdminLog` representa um registro de ação realizada por um administrador no sistema. Ela armazena informações sobre o administrador, a ação executada e o momento em que a ação foi registrada.

## Atributos

- `Integer id`: Identificador único do log de administração.
- `UUID adminId`: Identificador do administrador que realizou a ação.
- `String action`: Descrição da ação realizada pelo administrador.
- `Timestamp createdAt`: Data e hora em que o log foi criado.

## Construtores

### `AdminLog(Integer id, UUID adminId, String action, Timestamp createdAt)`

Construtor para criar uma instância completa de `AdminLog`, incluindo ID, ID do administrador, descrição da ação e timestamp.

#### Parâmetros:

- `Integer id`: ID do log.
- `UUID adminId`: ID do administrador que realizou a ação.
- `String action`: Descrição da ação realizada.
- `Timestamp createdAt`: Data e hora em que o log foi criado.

### `AdminLog(UUID adminId, String action)`

Construtor para criar uma instância de `AdminLog` sem o ID e o timestamp, que podem ser definidos posteriormente.

#### Parâmetros:

- `UUID adminId`: ID do administrador que realizou a ação.
- `String action`: Descrição da ação realizada.

## Métodos

### `Integer getId()`
Retorna o ID do log de administração.

### `void setId(Integer id)`
Define o ID do log de administração.

### `UUID getAdminId()`
Retorna o ID do administrador que realizou a ação.

### `void setAdminId(UUID adminId)`
Define o ID do administrador.

### `String getAction()`
Retorna a descrição da ação realizada.

### `void setAction(String action)`
Define a descrição da ação realizada.

### `Timestamp getCreatedAt()`
Retorna a data e hora em que o log foi criado.

### `void setCreatedAt(Timestamp createdAt)`
Define a data e hora em que o log foi criado.
