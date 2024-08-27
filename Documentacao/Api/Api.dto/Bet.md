# Classe `Bet`

A classe `Bet` representa uma aposta com seu nome e uma lista de valores associados.

## Atributos

- `name`: O nome da aposta.
- `values`: Lista de valores de odds associados à aposta.

## Métodos

### `String getName()`
Retorna o nome da aposta.

### `void setName(String name)`
Define o nome da aposta.

### `List<OddValue> getValues()`
Retorna a lista de valores de odds associados à aposta.

### `void setValues(List<OddValue> values)`
Define a lista de valores de odds associados à aposta.

## Exemplo de Uso

```java
public class TesteBet {
    public static void main(String[] args) {
        Bet bet = new Bet();
        bet.setName("Match Winner");

        // Supondo que OddValue é uma classe já definida
        List<OddValue> oddValues = new ArrayList<>();
        // Adiciona alguns valores
        bet.setValues(oddValues);

        System.out.println("Nome da aposta: " + bet.getName());
        System.out.println("Valores de odds: " + bet.getValues());
    }
}
