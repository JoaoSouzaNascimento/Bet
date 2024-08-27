# Classe `PartidaService`

A classe `PartidaService` fornece métodos para buscar dados de partidas de futebol e odds, e para criar e manipular objetos `Partida`.

## Atributos

- `footballService`: Instância de `FootballApiService` usada para interagir com a API de futebol.

## Construtores

### `PartidaService()`
Cria uma nova instância de `PartidaService`, inicializando o `footballService` a partir do contexto da aplicação.

## Métodos

### `FixtureData getFixtureData(FixtureResponse fixture) throws MalformedURLException`
Converte um objeto `FixtureResponse` em um objeto `FixtureData`.

#### Parâmetros:
- `fixture`: Objeto contendo os dados da partida.

#### Retorno:
- `FixtureData`: Dados da partida formatados.

#### Exceções:
- `MalformedURLException`: Se ocorrer um erro ao criar URLs a partir dos logos dos times.

### `OddsData getOddsData(OddsResponse oddsResponse)`
Extrai as odds para o "Match Winner" a partir de um objeto `OddsResponse`.

#### Parâmetros:
- `oddsResponse`: Objeto contendo as odds da partida.

#### Retorno:
- `OddsData`: Dados das odds extraídos.

### `Partida createPartida(FixtureData fixtureData, OddsData oddsData)`
Cria um objeto `Partida` a partir de `FixtureData` e `OddsData`.

#### Parâmetros:
- `fixtureData`: Dados da partida.
- `oddsData`: Dados das odds.

#### Retorno:
- `Partida`: Objeto representando a partida.

### `List<Partida> BuscarPartidasPorDia(String league, String season, String date, String timezone, String bookmaker)`
Busca partidas para um dia específico, incluindo a recuperação de dados de fixtures e odds.

#### Parâmetros:
- `league`: ID da liga.
- `season`: Temporada.
- `date`: Data.
- `timezone`: Fuso horário.
- `bookmaker`: Nome da casa de apostas.

#### Retorno:
- `List<Partida>`: Lista de partidas para o dia especificado.

#### Exceções:
- `Exception`: Se ocorrer um erro ao buscar fixtures ou odds.

### `FixtureData getFixtureDataById(String fixtureId) throws Exception`
Recupera dados de uma partida específica pelo ID da partida.

#### Parâmetros:
- `fixtureId`: ID da partida.

#### Retorno:
- `FixtureData`: Dados da partida.

#### Exceções:
- `Exception`: Se ocorrer um erro ao recuperar os dados da partida.

## Exemplo de Uso

```java
// Criar uma instância do PartidaService
PartidaService partidaService = new PartidaService();

// Buscar partidas para um dia específico
try {
    List<Partida> partidas = partidaService.BuscarPartidasPorDia("PL", "2023", "2024-08-27", "Europe/London", "BookmakerA");
    partidas.forEach(partida -> System.out.println("Partida: " + partida.getTimeDeCasaNome() + " vs " + partida.getTimeDeForaNome()));
} catch (Exception e) {
    System.err.println("Erro ao buscar partidas: " + e.getMessage());
}

// Recuperar dados de uma partida por ID
try {
    FixtureData fixtureData = partidaService.getFixtureDataById("12345");
    System.out.println("Dados da partida: " + fixtureData.getTimeDeCasaNome() + " vs " + fixtureData.getTimeDeForaNome());
} catch (Exception e) {
    System.err.println("Erro ao recuperar dados da partida: " + e.getMessage());
}
