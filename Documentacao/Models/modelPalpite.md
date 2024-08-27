# Classe `Palpite`

A classe `Palpite` representa um palpite feito em uma aposta para uma partida de futebol. Ela contém informações sobre a aposta, a partida, o resultado esperado e a odd (probabilidade) associada ao palpite.

## Atributos

- `Integer apostaId`: Identificador da aposta à qual o palpite está associado.
- `int partidaId`: Identificador da partida para a qual o palpite foi feito.
- `ResultadoPartida resultado`: Resultado esperado para a partida (ex: vitória do time da casa, vitória do visitante, empate).
- `BigDecimal odd`: Odd (probabilidade) associada ao resultado esperado.

## Construtor

### `Palpite(int partidaId, Integer apostaId, ResultadoPartida resultado, BigDecimal odd)`

Construtor para criar uma nova instância de `Palpite` com todas as informações necessárias.

#### Parâmetros:

- `int partidaId`: ID da partida para a qual o palpite foi feito.
- `Integer apostaId`: ID da aposta associada ao palpite.
- `ResultadoPartida resultado`: Resultado esperado para a partida.
- `BigDecimal odd`: Odd para o resultado esperado.

## Métodos

### `int getApostaId()`
Retorna o ID da aposta associada ao palpite.

### `void setApostaId(int apostaId)`
Define o ID da aposta associada.

### `int getPartidaId()`
Retorna o ID da partida para a qual o palpite foi feito.

### `void setPartidaId(int partidaId)`
Define o ID da partida.

### `ResultadoPartida getResultado()`
Retorna o resultado esperado para a partida.

### `void setResultado(ResultadoPartida resultado)`
Define o resultado esperado.

### `BigDecimal getOdd()`
Retorna a odd associada ao resultado esperado.

### `void setOdd(BigDecimal odd)`
Define a odd associada.
