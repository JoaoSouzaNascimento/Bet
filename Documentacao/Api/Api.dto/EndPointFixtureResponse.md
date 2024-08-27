
### `EndPointFixtureResponse`

```md
# Classe `EndPointFixtureResponse`

A classe `EndPointFixtureResponse` representa a resposta da API para fixtures (partidas), contendo uma lista de `FixtureResponse`.

## Atributos

- `response`: Lista de objetos `FixtureResponse`.

## Métodos

### `List<FixtureResponse> getResponse()`
Retorna a lista de `FixtureResponse`.

### `void setResponse(List<FixtureResponse> response)`
Define a lista de `FixtureResponse`.

## Exemplo de Uso

```java
public class TesteEndPointFixtureResponse {
    public static void main(String[] args) {
        EndPointFixtureResponse endPointFixtureResponse = new EndPointFixtureResponse();
        
        // Supondo que FixtureResponse é uma classe já definida
        List<FixtureResponse> fixtureResponses = new ArrayList<>();
        // Adiciona alguns responses
        endPointFixtureResponse.setResponse(fixtureResponses);

        System.out.println("Fixtures: " + endPointFixtureResponse.getResponse());
    }
}
