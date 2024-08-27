
### `EndpointOddsResponse`

```md
# Classe `EndpointOddsResponse`

A classe `EndpointOddsResponse` representa a resposta da API para odds, contendo uma lista de `OddsResponse`.

## Atributos

- `response`: Lista de objetos `OddsResponse`.

## Métodos

### `List<OddsResponse> getResponse()`
Retorna a lista de `OddsResponse`.

### `void setResponse(List<OddsResponse> response)`
Define a lista de `OddsResponse`.

## Exemplo de Uso

```java
public class TesteEndpointOddsResponse {
    public static void main(String[] args) {
        EndpointOddsResponse endpointOddsResponse = new EndpointOddsResponse();
        
        // Supondo que OddsResponse é uma classe já definida
        List<OddsResponse> oddsResponses = new ArrayList<>();
        // Adiciona alguns responses
        endpointOddsResponse.setResponse(oddsResponses);

        System.out.println("Odds: " + endpointOddsResponse.getResponse());
    }
}
