# Classe `LogDaoPostgreSQL`

A classe `LogDaoPostgreSQL` é uma implementação da interface `LogDao` e fornece métodos para gerenciar logs administrativos em uma base de dados PostgreSQL.

## Métodos

### `void createLog(AdminLog log) throws InsercaoException`
Insere um novo log na tabela `ADMIN_LOGS`.

#### Parâmetros:
- `log`: O objeto `AdminLog` que representa o log a ser inserido.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir o log.

### `List<AdminLog> getTodosLogsDeUmAdmin(UUID adminId) throws ConsultaException`
Obtém todos os logs para um administrador específico, ordenados pela data do log em ordem decrescente.

#### Parâmetros:
- `adminId`: O UUID do administrador cujos logs devem ser retornados.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar os logs.

#### Retorno:
- `List<AdminLog>`: Uma lista de objetos `AdminLog` que representam os logs do administrador.

## Notas

- A URL de conexão com a base de dados é gerenciada pela classe `ConexaoBdSingleton`, e a conexão é estabelecida através do método `getConexao()` dessa classe.
- O método `createLog` insere registros na tabela `ADMIN_LOGS`, que deve ter os campos `ADMIN_ID` e `ACTION`.
- O método `getTodosLogsDeUmAdmin` faz uma consulta na tabela `ADMIN_LOGS`, ordenando os registros pela coluna `LOG_DATE` em ordem decrescente.

## SQL Queries

- `INSERT_LOG`: `"INSERT INTO \"ADMIN_LOGS\" (\"ADMIN_ID\", \"ACTION\") VALUES (?, ?)"`.
- `SELECT_LOGS_BY_ADMIN_ID`: `"SELECT * FROM \"ADMIN_LOGS\" WHERE \"ADMIN_ID\" = ? ORDER BY \"LOG_DATE\" DESC"`.
