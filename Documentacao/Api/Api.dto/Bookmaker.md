
### `Bookmaker`

```md
# Classe `Bookmaker`

A classe `Bookmaker` representa uma entidade de casa de apostas que contém uma lista de apostas (`bets`).

## Atributos

- `bets`: Lista de apostas oferecidas pela casa de apostas.

## Métodos

### `List<Bet> getBets()`
Retorna a lista de apostas.

### `void setBets(List<Bet> bets)`
Define a lista de apostas.

## Exemplo de Uso

```java
public class TesteBookmaker {
    public static void main(String[] args) {
        Bookmaker bookmaker = new Bookmaker();
        
        // Supondo que Bet é uma classe já definida
        List<Bet> bets = new ArrayList<>();
        // Adiciona algumas apostas
        bookmaker.setBets(bets);

        System.out.println("Apostas: " + bookmaker.getBets());
    }
}
