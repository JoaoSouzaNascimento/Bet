# Classe `UsuarioDaoPostgreSQL`

A classe `UsuarioDaoPostgreSQL` é uma implementação da interface `UsuarioDao` e fornece métodos para gerenciar usuários em uma base de dados PostgreSQL.

## Métodos

### `Usuario getUsuarioByEmail(String email) throws ConsultaException`
Obtém um usuário com base no seu email.

#### Parâmetros:
- `email`: O email do usuário a ser consultado.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar o usuário.

#### Retorno:
- `Usuario`: O usuário com o email fornecido, ou `null` se não encontrado.

### `Usuario getUsuarioById(UUID id) throws ConsultaException`
Obtém um usuário específico com base no seu ID.

#### Parâmetros:
- `id`: O ID do usuário a ser consultado.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar o usuário.

#### Retorno:
- `Usuario`: O usuário com o ID fornecido, ou `null` se não encontrado.

### `Usuario createUsuario(Usuario usuario) throws InsercaoException`
Cria um novo usuário na tabela `USERS`.

#### Parâmetros:
- `usuario`: O objeto `Usuario` que representa o usuário a ser criado.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir o usuário.

#### Retorno:
- `Usuario`: O usuário criado.

### `Usuario updateUsuario(Usuario usuario) throws AtualizacaoException`
Atualiza um usuário existente na tabela `USERS`.

#### Parâmetros:
- `usuario`: O objeto `Usuario` com as novas informações a serem atualizadas.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro ao tentar atualizar o usuário.

#### Retorno:
- `Usuario`: O usuário atualizado.

### `void deleteUsuario(UUID id) throws DelecaoException`
Marca um usuário como deletado na tabela `USERS`.

#### Parâmetros:
- `id`: O ID do usuário a ser marcado como deletado.

#### Exceções:
- `DelecaoException`: Se ocorrer um erro ao tentar marcar o usuário como deletado.

### `List<Usuario> getTodosUsuarios() throws ConsultaException`
Obtém todos os usuários na tabela `USERS`.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar listar todos os usuários.

#### Retorno:
- `List<Usuario>`: Uma lista de todos os usuários.

### `List<Usuario> getTodosUsuariosAtivos() throws ConsultaException`
Obtém todos os usuários que não estão marcados como deletados.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar listar todos os usuários ativos.

#### Retorno:
- `List<Usuario>`: Uma lista de todos os usuários ativos.

### `List<Usuario> getTodosUsuariosInativos() throws ConsultaException`
Obtém todos os usuários que estão marcados como deletados.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar listar todos os usuários inativos.

#### Retorno:
- `List<Usuario>`: Uma lista de todos os usuários inativos.

### `void ativarUsuario(UUID id) throws AtualizacaoException`
Marca um usuário como ativo na tabela `USERS`.

#### Parâmetros:
- `id`: O ID do usuário a ser marcado como ativo.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro ao tentar ativar o usuário.

## Notas

- A URL de conexão com a base de dados é gerenciada pela classe `ConexaoBdSingleton`, e a conexão é estabelecida através do método `getConexao()` dessa classe.
- O método `createUsuario` insere um novo registro na tabela `USERS`.
- O método `updateUsuario` atualiza registros na tabela `USERS` com base no ID do usuário.
- O método `getTodosUsuarios` retorna todos os usuários da tabela `USERS`.
- Os métodos `getTodosUsuariosAtivos` e `getTodosUsuariosInativos` retornam usuários com base no status `deleted`.
- O método `deleteUsuario` marca um usuário como deletado, e o método `ativarUsuario` marca um usuário como ativo.

## SQL Queries

- `SELECT_USER_BY_EMAIL`: `"SELECT * from \"USERS\" WHERE \"EMAIL\" = ?"`
- `SELECT_USER_BY_ID`: `"SELECT * FROM \"USERS\" WHERE \"ID\" = ?"`
- `INSERT_USER`: `"INSERT INTO \"USERS\" (\"ID\", \"USERNAME\", \"NICKNAME\", \"PASSWORD\", \"EMAIL\", \"BALANCE\", \"DELETED\", \"ROLE\") VALUES (?, ?, ?, ?, ?, ?, ?, ?)"`
- `UPDATE_STATUS_USER`: `"UPDATE \"USERS\" SET \"DELETED\" = ? WHERE \"ID\" = ?"`
- `SELECT_ALL_USERS`: `"SELECT * FROM \"USERS\""`
- `SELECT_USERS_BY_STATUS`: `"SELECT * FROM \"USERS\" WHERE \"DELETED\" = ?"`
- `UPDATE_ALL_USER_FIELDS`: `"UPDATE \"USERS\" SET \"USERNAME\" = ?, \"NICKNAME\" = ?, \"PASSWORD\" = ?, \"EMAIL\" = ?, \"BALANCE\" = ?, \"DELETED\" = ?, \"ROLE\" = ? WHERE \"ID\" = ?"`
