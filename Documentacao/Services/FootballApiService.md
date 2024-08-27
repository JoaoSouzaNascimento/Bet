# Classe `FootballApiService`

A classe `FootballApiService` fornece métodos para interagir com a API de futebol, incluindo a recuperação de dados sobre partidas e odds.

## Atributos

- `client`: Instância de `HttpClient` para realizar requisições HTTP.
- `objectMapper`: Instância de `ObjectMapper` para converter respostas JSON em objetos Java.
- `apiKey`: Chave da API para autenticação.
- `apiHost`: Host da API para autenticação.

## Construtores

### `FootballApiService(String apiKey, String apiHost)`
Cria uma nova instância de `FootballApiService` com a chave da API e o host fornecidos.

#### Parâmetros:
- `apiKey`: Chave da API para autenticação.
- `apiHost`: Host da API para autenticação.

## Métodos

### `List<FixtureResponse> getFixtures(String league, String season, String date, String timezone) throws Exception`
Recupera a lista de partidas para uma liga, temporada, data e fuso horário específicos.

#### Parâmetros:
- `league`: ID da liga para buscar as partidas.
- `season`: Temporada para buscar as partidas.
- `date`: Data para buscar as partidas.
- `timezone`: Fuso horário para buscar as partidas.

#### Retorno:
- `List<FixtureResponse>`: Lista de respostas de partidas.

#### Exceções:
- `Exception`: Se ocorrer um erro na requisição ou na conversão da resposta.

### `List<OddsResponse> getOddsForFixture(String fixtureId, String league, String season, String bookmaker, String timezone) throws Exception`
Recupera as odds para uma partida específica, fornecendo o ID da partida, liga, temporada, casa de apostas e fuso horário.

#### Parâmetros:
- `fixtureId`: ID da partida para buscar as odds.
- `league`: ID da liga para buscar as odds.
- `season`: Temporada para buscar as odds.
- `bookmaker`: Nome da casa de apostas para buscar as odds.
- `timezone`: Fuso horário para buscar as odds.

#### Retorno:
- `List<OddsResponse>`: Lista de respostas de odds.

#### Exceções:
- `Exception`: Se ocorrer um erro na requisição ou na conversão da resposta.

### `FixtureResponse getFixtureById(String fixtureId) throws Exception`
Recupera os detalhes de uma partida específica pelo ID da partida.

#### Parâmetros:
- `fixtureId`: ID da partida para buscar os detalhes.

#### Retorno:
- `FixtureResponse`: Detalhes da partida.

#### Exceções:
- `Exception`: Se ocorrer um erro na requisição ou na conversão da resposta.

## Exemplo de Uso

```java
// Criar uma instância do FootballApiService
FootballApiService footballApiService = new FootballApiService("yourApiKey", "yourApiHost");

// Recuperar fixtures
try {
    List<FixtureResponse> fixtures = footballApiService.getFixtures("PL", "2023", "2024-08-27", "Europe/London");
    fixtures.forEach(fixture -> System.out.println("Partida: " + fixture.getHomeTeam() + " vs " + fixture.getAwayTeam()));
} catch (Exception e) {
    System.err.println("Erro ao recuperar fixtures: " + e.getMessage());
}

// Recuperar odds para uma partida
try {
    List<OddsResponse> odds = footballApiService.getOddsForFixture("12345", "PL", "2023", "BookmakerA", "Europe/London");
    odds.forEach(odd -> System.out.println("Odds: " + odd.getOdd()));
} catch (Exception e) {
    System.err.println("Erro ao recuperar odds: " + e.getMessage());
}

// Recuperar detalhes de uma partida por ID
try {
    FixtureResponse fixture = footballApiService.getFixtureById("12345");
    System.out.println("Detalhes da partida: " + fixture.getHomeTeam() + " vs " + fixture.getAwayTeam());
} catch (Exception e) {
    System.err.println("Erro ao recuperar detalhes da partida: " + e.getMessage());
}
