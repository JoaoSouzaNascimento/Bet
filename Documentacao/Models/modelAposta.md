# Classe `Aposta`

A classe `Aposta` representa uma aposta feita por um usuário no sistema. Ela contém informações sobre o valor da aposta, o status, a data e o usuário associado.

## Atributos

- `Integer id`: Identificador único da aposta.
- `UUID userId`: Identificador do usuário que fez a aposta.
- `BigDecimal amount`: Valor da aposta.
- `Boolean status`: Status da aposta (por exemplo, pendente, completa).
- `LocalDate date`: Data em que a aposta foi feita.

## Construtores

### `Aposta(UUID userID, BigDecimal amount, LocalDate date)`

Construtor para criar uma nova instância de `Aposta` com usuário, valor e data.

#### Parâmetros:

- `UUID userID`: ID do usuário que fez a aposta.
- `BigDecimal amount`: Valor da aposta.
- `LocalDate date`: Data da aposta.

### `Aposta(Integer id, UUID userID, BigDecimal amount, List<Palpite> palpites, Boolean status, LocalDate date)`

Construtor para criar uma nova instância de `Aposta` com todos os atributos, incluindo ID, status e palpites.

#### Parâmetros:

- `Integer id`: ID da aposta.
- `UUID userID`: ID do usuário.
- `BigDecimal amount`: Valor da aposta.
- `List<Palpite> palpites`: Lista de palpites associados à aposta.
- `Boolean status`: Status da aposta.
- `LocalDate date`: Data da aposta.

### `Aposta(UUID userID, BigDecimal amount, List<Palpite> palpites, String timeZone)`

Construtor para criar uma nova instância de `Aposta` com usuário, valor, palpites e timezone.

#### Parâmetros:

- `UUID userID`: ID do usuário.
- `BigDecimal amount`: Valor da aposta.
- `List<Palpite> palpites`: Lista de palpites associados à aposta.
- `String timeZone`: Timezone para determinar a data da aposta. 

## Métodos

### `Integer getId()`
Retorna o ID da aposta.

### `void setId(Integer id)`
Define o ID da aposta.

### `UUID getUserId()`
Retorna o ID do usuário que fez a aposta.

### `void setUserId(UUID userId)`
Define o ID do usuário associado à aposta.

### `BigDecimal getAmount()`
Retorna o valor da aposta.

### `void setAmount(BigDecimal amount)`
Define o valor da aposta.

### `Boolean getStatus()`
Retorna o status da aposta.

### `void setStatus(Boolean status)`
Define o status da aposta.

### `LocalDate getDate()`
Retorna a data em que a aposta foi feita.

### `void setDate(LocalDate date)`
Define a data da aposta.
