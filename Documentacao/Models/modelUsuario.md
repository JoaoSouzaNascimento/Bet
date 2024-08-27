# Classe `Usuario`

A classe `Usuario` representa um usuário do sistema de casa de apostas. Contém informações pessoais e dados financeiros do usuário, como saldo e nível de acesso (role). Esta classe também gerencia o estado do usuário, incluindo se ele foi marcado como deletado.

## Atributos

- `UUID id`: Identificador único do usuário.
- `String username`: Nome de usuário utilizado para login.
- `String nickname`: Apelido do usuário, usado para exibição.
- `String password`: Senha criptografada para autenticação.
- `String email`: Endereço de e-mail do usuário.
- `BigDecimal balance`: Saldo disponível do usuário para apostas.
- `boolean deleted`: Indica se o usuário foi marcado como deletado no sistema.
- `CargoUsuario role`: Representa o nível de acesso ou papel do usuário (ex: administrador, apostador).

## Construtor

### `Usuario(UUID id, String username, String nickname, String password, String email, BigDecimal balance, boolean deleted, CargoUsuario role)`

Construtor utilizado para criar uma nova instância de `Usuario` com todos os atributos especificados.

#### Parâmetros:

- `UUID id`: ID único do usuário.
- `String username`: Nome de usuário para login.
- `String nickname`: Apelido para exibição.
- `String password`: Senha criptografada.
- `String email`: E-mail do usuário.
- `BigDecimal balance`: Saldo do usuário.
- `boolean deleted`: Estado de exclusão do usuário.
- `CargoUsuario role`: Papel do usuário no sistema.

## Métodos

### `String getUsername()`
Retorna o nome de usuário.

### `void setUsername(String username)`
Define o nome de usuário.

### `String getNickname()`
Retorna o apelido do usuário.

### `void setNickname(String nickname)`
Define o apelido do usuário.

### `String getPassword()`
Retorna a senha do usuário.

### `void setPassword(String password)`
Define a senha do usuário.

### `String getEmail()`
Retorna o e-mail do usuário.

### `void setEmail(String email)`
Define o e-mail do usuário.

### `BigDecimal getBalance()`
Retorna o saldo do usuário.

### `void setBalance(BigDecimal balance)`
Define o saldo do usuário.

### `boolean isDeleted()`
Retorna se o usuário foi marcado como deletado.

### `void setDeleted(boolean deleted)`
Define se o usuário está marcado como deletado.

### `CargoUsuario getRole()`
Retorna o papel do usuário.

### `void setRole(CargoUsuario role)`
Define o papel do usuário.

### `UUID getId()`
Retorna o ID único do usuário.
