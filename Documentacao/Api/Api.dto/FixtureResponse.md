# Classe `FixtureResponse`

A classe `FixtureResponse` representa a resposta da API para informações sobre uma partida, contendo detalhes sobre a partida e os times envolvidos.

## Atributos

- `fixture`: Objeto `Fixture` que contém detalhes sobre a partida.
- `teams`: Objeto `Teams` que contém informações sobre os times envolvidos na partida.

## Métodos

### `Fixture getFixture()`
Retorna o objeto `Fixture` que representa a partida.

### `void setFixture(Fixture fixture)`
Define o objeto `Fixture` que representa a partida.

### `Teams getTeams()`
Retorna o objeto `Teams` que contém informações sobre os times.

### `void setTeams(Teams teams)`
Define o objeto `Teams` que contém informações sobre os times.

## Exemplo de Uso

```java
public class TesteFixtureResponse {
    public static void main(String[] args) {
        FixtureResponse fixtureResponse = new FixtureResponse();
        Fixture fixture = new Fixture();
        Teams teams = new Teams();
        
        fixtureResponse.setFixture(fixture);
        fixtureResponse.setTeams(teams);

        System.out.println("Detalhes da partida: " + fixtureResponse.getFixture());
        System.out.println("Times envolvidos: " + fixtureResponse.getTeams());
    }
}
