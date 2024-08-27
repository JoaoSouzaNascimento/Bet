# Classe `AdminService`

A classe `AdminService` fornece serviços relacionados à administração, incluindo operações sobre usuários e logs administrativos.

## Atributos

- `usuarioDao`: Instância de `UsuarioDao` usada para operações de acesso a dados dos usuários.
- `logDao`: Instância de `LogDao` usada para operações de acesso a dados dos logs administrativos.

## Construtores

### `AdminService(UsuarioDao usuarioDao, LogDao logDao)`
Cria uma nova instância de `AdminService` com os DAOs fornecidos para acesso a usuários e logs.

#### Parâmetros:
- `usuarioDao`: Instância de `UsuarioDao`.
- `logDao`: Instância de `LogDao`.

## Métodos

### `void createLog(UUID adminId, String action) throws InsercaoException`
Cria um novo log administrativo com o ID do administrador e a ação descrita.

#### Parâmetros:
- `adminId`: O ID do administrador que está realizando a ação.
- `action`: A descrição da ação realizada.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir o log no banco de dados.

### `List<AdminLog> getTodosLogsDeUmAdmin(UUID adminId) throws ConsultaException`
Obtém todos os logs associados a um administrador específico.

#### Parâmetros:
- `adminId`: O ID do administrador cujos logs serão recuperados.

#### Retorno:
- `List<AdminLog>`: Lista de logs administrativos associados ao administrador.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar os logs no banco de dados.

### `Usuario getUsuarioById(UUID id) throws ConsultaException`
Obtém um usuário específico pelo ID.

#### Parâmetros:
- `id`: O ID do usuário a ser recuperado.

#### Retorno:
- `Usuario`: O usuário com o ID especificado.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar o usuário no banco de dados.

### `List<Usuario> getTodosUsuarios() throws ConsultaException`
Obtém todos os usuários registrados.

#### Retorno:
- `List<Usuario>`: Lista de todos os usuários.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar os usuários no banco de dados.

### `void ativarUsuario(UUID id) throws AtualizacaoException`
Ativa um usuário específico, marcando-o como não deletado.

#### Parâmetros:
- `id`: O ID do usuário a ser ativado.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro ao tentar atualizar o status do usuário no banco de dados.

### `List<Usuario> listarTodosUsuarioAtivos() throws ConsultaException`
Obtém todos os usuários que estão ativos (não deletados).

#### Retorno:
- `List<Usuario>`: Lista de todos os usuários ativos.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar os usuários ativos no banco de dados.

### `List<Usuario> listarTodosUsuarioInativos() throws ConsultaException`
Obtém todos os usuários que estão inativos (deletados).

#### Retorno:
- `List<Usuario>`: Lista de todos os usuários inativos.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar os usuários inativos no banco de dados.

### `Usuario getUsuarioPeloEmail(String email) throws ConsultaException`
Obtém um usuário específico pelo email.

#### Parâmetros:
- `email`: O email do usuário a ser recuperado.

#### Retorno:
- `Usuario`: O usuário com o email especificado.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar o usuário no banco de dados.

### `void listarTodosUsuarios() throws ConsultaException`
Obtém todos os usuários registrados.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar os usuários no banco de dados.

## Exemplo de Uso

```java
AdminService adminService = new AdminService(usuarioDao, logDao);

// Criar um novo log
adminService.createLog(adminId, "Ação realizada");

// Obter todos os logs de um administrador
List<AdminLog> logs = adminService.getTodosLogsDeUmAdmin(adminId);

// Obter um usuário pelo ID
Usuario usuario = adminService.getUsuarioById(userId);

// Ativar um usuário
adminService.ativarUsuario(userId);

// Listar todos os usuários ativos
List<Usuario> usuariosAtivos = adminService.listarTodosUsuarioAtivos();

// Listar todos os usuários inativos
List<Usuario> usuariosInativos = adminService.listarTodosUsuarioInativos();
