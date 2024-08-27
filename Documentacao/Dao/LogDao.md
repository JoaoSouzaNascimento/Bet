# Interface `LogDao`

A interface `LogDao` define os métodos para operações relacionadas a logs administrativos na base de dados.

## Métodos

### `void createLog(AdminLog log) throws InsercaoException`
Cria um novo log na base de dados.

#### Parâmetros:
- `log`: O objeto `AdminLog` que representa o log a ser inserido.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir o log.

### `List<AdminLog> getTodosLogsDeUmAdmin(UUID adminId) throws ConsultaException`
Obtém todos os logs relacionados a um administrador específico, ordenados pela data do log em ordem decrescente.

#### Parâmetros:
- `adminId`: O UUID do administrador cujos logs devem ser retornados.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar os logs.

#### Retorno:
- `List<AdminLog>`: Uma lista de objetos `AdminLog` que representam os logs do administrador.
