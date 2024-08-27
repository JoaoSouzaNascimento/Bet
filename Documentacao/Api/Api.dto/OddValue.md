
### `OddValue`

```md
# Classe `OddValue`

A classe `OddValue` representa um valor de odd associado a uma aposta.

## Atributos

- `value`: O valor da odd.
- `odd`: O valor da odd em formato de string.

## Métodos

### `String getValue()`
Retorna o valor da odd.

### `void setValue(String value)`
Define o valor da odd.

### `String getOdd()`
Retorna a odd em formato de string.

### `void setOdd(String odd)`
Define a odd em formato de string.

## Exemplo de Uso

```java
public class TesteOddValue {
    public static void main(String[] args) {
        OddValue oddValue = new OddValue();
        oddValue.setValue("1.5");
        oddValue.setOdd("2.0");

        System.out.println("Valor: " + oddValue.getValue());
        System.out.println("Odd: " + oddValue.getOdd());
    }
}
