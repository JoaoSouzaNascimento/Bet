# Classe `Partida`

A classe `Partida` representa uma partida de futebol no sistema de apostas, contendo informações sobre os times, data, status, resultado e as odds (probabilidades) para cada possível resultado.

## Atributos

- `String id`: Identificador único da partida.
- `String teamHome`: Nome do time da casa.
- `URL teamHomeLogo`: URL do logotipo do time da casa.
- `String teamAway`: Nome do time visitante.
- `URL teamAwayLogo`: URL do logotipo do time visitante.
- `OffsetDateTime data`: Data e hora em que a partida será ou foi realizada.
- `String status`: Status atual da partida (ex: pendente, acontecendo, terminado).
- `ResultadoPartida resultado`: Resultado final da partida (ex: vitória do time da casa, vitória do visitante, empate).
- `float homeWinOdd`: Odd (probabilidade) para a vitória do time da casa.
- `float awayWinOdd`: Odd (probabilidade) para a vitória do time visitante.
- `float drawOdd`: Odd (probabilidade) para o empate.

## Construtor

### `Partida(String id, String teamHome, URL teamHomeLogo, String teamAway, URL teamAwayLogo, OffsetDateTime data, String status, ResultadoPartida resultado, float homeWinOdd, float awayWinOdd, float drawOdd)`

Construtor para inicializar uma instância da classe `Partida` com todas as informações da partida.

#### Parâmetros:

- `String id`: ID da partida.
- `String teamHome`: Nome do time da casa.
- `URL teamHomeLogo`: Logotipo do time da casa.
- `String teamAway`: Nome do time visitante.
- `URL teamAwayLogo`: Logotipo do time visitante.
- `OffsetDateTime data`: Data e hora da partida.
- `String status`: Status da partida.
- `ResultadoPartida resultado`: Resultado final da partida.
- `float homeWinOdd`: Odd para vitória do time da casa.
- `float awayWinOdd`: Odd para vitória do time visitante.
- `float drawOdd`: Odd para empate.

## Métodos

### `String getId()`
Retorna o ID da partida.

### `String getTeamHome()`
Retorna o nome do time da casa.

### `URL getTeamHomeLogo()`
Retorna a URL do logotipo do time da casa.

### `String getTeamAway()`
Retorna o nome do time visitante.

### `URL getTeamAwayLogo()`
Retorna a URL do logotipo do time visitante.

### `OffsetDateTime getData()`
Retorna a data e hora da partida.

### `String getStatus()`
Retorna o status atual da partida.

### `ResultadoPartida getResultado()`
Retorna o resultado final da partida.

### `float getHomeWinOdd()`
Retorna a odd para a vitória do time da casa.

### `float getAwayWinOdd()`
Retorna a odd para a vitória do time visitante.

### `float getDrawOdd()`
Retorna a odd para o empate.
