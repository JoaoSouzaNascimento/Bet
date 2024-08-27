# Classe `UsuarioService`

A classe `UsuarioService` oferece métodos para gerenciar usuários, incluindo operações de atualização, edição e exclusão.

## Atributos

- `usuarioDao`: Instância de `UsuarioDaoPostgreSQL` usada para interagir com a base de dados para usuários.

## Construtores

### `UsuarioService()`
Cria uma nova instância de `UsuarioService`, inicializando `usuarioDao` com a implementação específica para PostgreSQL.

## Métodos

### `void atualizarUsuario(Usuario usuario)`
Atualiza as informações de um usuário na base de dados.

#### Parâmetros:
- `usuario`: Objeto `Usuario` com as informações a serem atualizadas.

### `Usuario getUsuarioById(UUID id) throws ConsultaException`
Recupera um usuário pelo seu ID.

#### Parâmetros:
- `id`: ID do usuário a ser recuperado.

#### Retorno:
- `Usuario`: Objeto `Usuario` correspondente ao ID fornecido.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao consultar o usuário.

### `void editarUsuarioNome(String email, String username) throws ConsultaException, AtualizacaoException`
Atualiza o nome de usuário de um usuário com base no seu email.

#### Parâmetros:
- `email`: Email do usuário cujo nome será atualizado.
- `username`: Novo nome de usuário.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao consultar o usuário.
- `AtualizacaoException`: Se ocorrer um erro ao atualizar o usuário.

### `void editarUsuarioApelido(Usuario usuario, String apelido)`
Atualiza o apelido de um usuário.

#### Parâmetros:
- `usuario`: Objeto `Usuario` cujo apelido será atualizado.
- `apelido`: Novo apelido.

### `void editarUsuarioSenha(Usuario usuario, String senhaVelha, String novaSenha, String email)`
Atualiza a senha de um usuário.

#### Parâmetros:
- `usuario`: Objeto `Usuario` cuja senha será atualizada.
- `senhaVelha`: Senha antiga do usuário.
- `novaSenha`: Nova senha para o usuário.
- `email`: Email do usuário.

### `void editarUsuarioEmail(Usuario usuario, String email)`
Atualiza o email de um usuário.

#### Parâmetros:
- `usuario`: Objeto `Usuario` cujo email será atualizado.
- `email`: Novo email para o usuário.

### `void deletarUsuario(Usuario usuario, String email)`
Marca um usuário como excluído.

#### Parâmetros:
- `usuario`: Objeto `Usuario` a ser marcado como excluído.
- `email`: Email do usuário a ser excluído.

### `void editarUsuarioEmail(UUID usuarioId, String email) throws ConsultaException, AtualizacaoException`
Atualiza o email de um usuário com base no seu ID.

#### Parâmetros:
- `usuarioId`: ID do usuário cujo email será atualizado.
- `email`: Novo email para o usuário.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao consultar o usuário.
- `AtualizacaoException`: Se ocorrer um erro ao atualizar o usuário.

## Exemplo de Uso

```java
// Criar uma instância do UsuarioService
UsuarioService usuarioService = new UsuarioService();

// Atualizar um usuário
try {
    Usuario usuario = usuarioService.getUsuarioById(UUID.randomUUID());
    usuario.setUsername("novo_nome");
    usuarioService.atualizarUsuario(usuario);
    System.out.println("Usuário atualizado com sucesso.");
} catch (ConsultaException | AtualizacaoException e) {
    System.err.println("Erro ao atualizar usuário: " + e.getMessage());
}

// Editar nome de usuário
try {
    usuarioService.editarUsuarioNome("email@example.com", "novo_nome");
    System.out.println("Nome de usuário editado com sucesso.");
} catch (ConsultaException | AtualizacaoException e) {
    System.err.println("Erro ao editar nome de usuário: " + e.getMessage());
}

// Deletar usuário
try {
    Usuario usuario = usuarioService.getUsuarioById(UUID.randomUUID());
    usuarioService.deletarUsuario(usuario, "email@example.com");
    System.out.println("Usuário deletado com sucesso.");
} catch (ConsultaException | DelecaoException e) {
    System.err.println("Erro ao deletar usuário: " + e.getMessage());
}
