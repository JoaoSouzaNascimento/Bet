# Classe `AuthService`

A classe `AuthService` fornece serviços de autenticação e cadastro de usuários, incluindo login com verificação de senha e registro de novos usuários com validações.

## Atributos

- `usuarioDao`: Instância de `UsuarioDao` usada para operações de acesso a dados dos usuários.

## Construtores

### `AuthService(UsuarioDao usuarioDao)`
Cria uma nova instância de `AuthService` com o DAO de usuário fornecido.

#### Parâmetros:
- `usuarioDao`: Instância de `UsuarioDao`.

## Métodos

### `Usuario login(String email, String password) throws LoginException, ConsultaException`
Realiza o login de um usuário verificando suas credenciais.

#### Parâmetros:
- `email`: Email do usuário para autenticação.
- `password`: Senha do usuário para autenticação.

#### Retorno:
- `Usuario`: O usuário autenticado.

#### Exceções:
- `LoginException`: Se a senha estiver incorreta ou o usuário não for encontrado.
- `ConsultaException`: Se ocorrer um erro ao consultar o usuário no banco de dados.

### `Usuario cadastro(String username, String nickname, String email, String password, CargoUsuario role) throws CadastroException`
Cadastra um novo usuário com as informações fornecidas, realizando validações dos dados de entrada.

#### Parâmetros:
- `username`: Nome de usuário para o novo cadastro.
- `nickname`: Apelido do usuário.
- `email`: Email do usuário.
- `password`: Senha do usuário.
- `role`: Cargo do usuário.

#### Retorno:
- `Usuario`: O usuário recém-cadastrado.

#### Exceções:
- `CadastroException`: Se ocorrer um erro durante o cadastro, como campos obrigatórios ausentes, email inválido, nome de usuário inválido, ou se o email já estiver em uso.

## Exemplo de Uso

```java
// Criar uma instância do AuthService
AuthService authService = new AuthService(usuarioDao);

// Realizar o login de um usuário
try {
    Usuario usuario = authService.login("user@example.com", "password123");
    System.out.println("Login bem-sucedido: " + usuario.getUsername());
} catch (LoginException | ConsultaException e) {
    System.err.println("Erro ao realizar login: " + e.getMessage());
}

// Cadastrar um novo usuário
try {
    Usuario novoUsuario = authService.cadastro("newUser", "nickname", "newuser@example.com", "password123", CargoUsuario.USUARIO);
    System.out.println("Usuário cadastrado: " + novoUsuario.getUsername());
} catch (CadastroException e) {
    System.err.println("Erro ao cadastrar usuário: " + e.getMessage());
}
