# Interface `UsuarioDao`

A interface `UsuarioDao` define os métodos para operações relacionadas a usuários na base de dados.

## Métodos

### `Usuario createUsuario(Usuario usuario) throws InsercaoException`
Cria um novo usuário na base de dados.

#### Parâmetros:
- `usuario`: O objeto `Usuario` que representa o usuário a ser criado.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir o usuário.

#### Retorno:
- `Usuario`: O usuário criado.

### `Usuario getUsuarioByEmail(String email) throws ConsultaException`
Obtém um usuário com base no seu email.

#### Parâmetros:
- `email`: O email do usuário a ser consultado.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar o usuário.

#### Retorno:
- `Usuario`: O usuário com o email fornecido, ou `null` se não encontrado.

### `Usuario updateUsuario(Usuario usuario) throws AtualizacaoException`
Atualiza as informações de um usuário existente na base de dados.

#### Parâmetros:
- `usuario`: O objeto `Usuario` com as novas informações a serem atualizadas.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro ao tentar atualizar o usuário.

#### Retorno:
- `Usuario`: O usuário atualizado.

### `void deleteUsuario(UUID id) throws DelecaoException`
Marca um usuário como deletado na base de dados.

#### Parâmetros:
- `id`: O ID do usuário a ser marcado como deletado.

#### Exceções:
- `DelecaoException`: Se ocorrer um erro ao tentar marcar o usuário como deletado.

### `List<Usuario> getTodosUsuarios() throws ConsultaException`
Obtém todos os usuários na base de dados.

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

### `Usuario getUsuarioById(UUID id) throws ConsultaException`
Obtém um usuário específico com base no seu ID.

#### Parâmetros:
- `id`: O ID do usuário a ser consultado.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar o usuário.

#### Retorno:
- `Usuario`: O usuário com o ID fornecido.

### `void ativarUsuario(UUID id) throws AtualizacaoException`
Marca um usuário como ativo na base de dados.

#### Parâmetros:
- `id`: O ID do usuário a ser marcado como ativo.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro ao tentar ativar o usuário.
