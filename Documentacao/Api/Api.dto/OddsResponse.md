
### `OddsResponse`

```md
# Classe `OddsResponse`

A classe `OddsResponse` representa a resposta da API para odds, contendo uma lista de bookmakers.

## Atributos

- `bookmaker`: Lista de objetos `Bookmaker` que representam as odds oferecidas pelos bookmakers.

## Métodos

### `List<Bookmaker> getResponse()`
Retorna a lista de objetos `Bookmaker`.

### `void setResponse(List<Bookmaker> bookmaker)`
Define a lista de objetos `Bookmaker`.

## Exemplo de Uso

```java
public class TesteOddsResponse {
    public static void main(String[] args) {
        OddsResponse oddsResponse = new OddsResponse();
        
        // Supondo que Bookmaker é uma classe já definida
        List<Bookmaker> bookmakers = new ArrayList<>();
        oddsResponse.setResponse(bookmakers);

        System.out.println("Bookmakers: " + oddsResponse.getResponse());
    }
}
