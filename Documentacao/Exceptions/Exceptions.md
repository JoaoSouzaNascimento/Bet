# Classe `AtualizacaoException`

A classe `AtualizacaoException` é uma exceção personalizada lançada quando ocorre um erro ao tentar atualizar dados na base de dados.

## Extende
- `SQLException`

## Construtores

### `AtualizacaoException(String mensagem)`
Cria uma nova instância de `AtualizacaoException` com uma mensagem de erro.

#### Parâmetros:
- `mensagem`: A mensagem de erro que descreve o motivo da exceção.

### `AtualizacaoException(String mensagem, Throwable causa)`
Cria uma nova instância de `AtualizacaoException` com uma mensagem de erro e a causa.

#### Parâmetros:
- `mensagem`: A mensagem de erro que descreve o motivo da exceção.
- `causa`: A causa da exceção, geralmente uma exceção de SQL.

## Exemplo de Uso

```java
throw new AtualizacaoException("Erro ao atualizar dados", e);





Classe `CadastroException`

A classe `CadastroException` é uma exceção personalizada lançada quando ocorre um erro ao tentar cadastrar um novo usuário.

## Extende
- `Exception`

## Construtores

### `CadastroException(String mensagem)`
Cria uma nova instância de `CadastroException` com uma mensagem de erro.

#### Parâmetros:
- `mensagem`: A mensagem de erro que descreve o motivo da exceção.

### `CadastroException(String mensagem, Throwable causa)`
Cria uma nova instância de `CadastroException` com uma mensagem de erro e a causa.

#### Parâmetros:
- `mensagem`: A mensagem de erro que descreve o motivo da exceção.
- `causa`: A causa da exceção.

## Exemplo de Uso

```java
throw new CadastroException("Erro ao cadastrar usuário", e);


### `DelecaoException`

```md
# Classe `DelecaoException`

A classe `DelecaoException` é uma exceção personalizada lançada quando ocorre um erro ao tentar deletar dados na base de dados.

## Extende
- `SQLException`

## Construtores

### `DelecaoException(String mensagem)`
Cria uma nova instância de `DelecaoException` com uma mensagem de erro.

#### Parâmetros:
- `mensagem`: A mensagem de erro que descreve o motivo da exceção.

### `DelecaoException(String mensagem, Throwable causa)`
Cria uma nova instância de `DelecaoException` com uma mensagem de erro e a causa.

#### Parâmetros:
- `mensagem`: A mensagem de erro que descreve o motivo da exceção.
- `causa`: A causa da exceção, geralmente uma exceção de SQL.

## Exemplo de Uso

```java
throw new DelecaoException("Erro ao deletar dados", e);


### `InsercaoException`

```md
# Classe `InsercaoException`

A classe `InsercaoException` é uma exceção personalizada lançada quando ocorre um erro ao tentar inserir dados na base de dados.

## Extende
- `SQLException`

## Construtores

### `InsercaoException(String mensagem)`
Cria uma nova instância de `InsercaoException` com uma mensagem de erro.

#### Parâmetros:
- `mensagem`: A mensagem de erro que descreve o motivo da exceção.

### `InsercaoException(String mensagem, Throwable causa)`
Cria uma nova instância de `InsercaoException` com uma mensagem de erro e a causa.

#### Parâmetros:
- `mensagem`: A mensagem de erro que descreve o motivo da exceção.
- `causa`: A causa da exceção, geralmente uma exceção de SQL.

## Exemplo de Uso

```java
throw new InsercaoException("Erro ao inserir dados", e);


### `LoginException`

```md
# Classe `LoginException`

A classe `LoginException` é uma exceção personalizada lançada quando ocorre um erro durante o processo de login.

## Extende
- `Exception`

## Construtores

### `LoginException(String mensagem)`
Cria uma nova instância de `LoginException` com uma mensagem de erro.

#### Parâmetros:
- `mensagem`: A mensagem de erro que descreve o motivo da exceção.

### `LoginException(String mensagem, Throwable causa)`
Cria uma nova instância de `LoginException` com uma mensagem de erro e a causa.

#### Parâmetros:
- `mensagem`: A mensagem de erro que descreve o motivo da exceção.
- `causa`: A causa da exceção.

## Exemplo de Uso

```java
throw new LoginException("Erro durante o login", e);
