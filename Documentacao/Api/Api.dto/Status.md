
### `Status`

```md
# Classe `Status`

A classe `Status` representa o status de uma partida com um nome descritivo.

## Atributos

- `longName`: Nome longo do status.

## Métodos

### `String getLongName()`
Retorna o nome longo do status.

### `void setLongName(String longName)`
Define o nome longo do status.

## Exemplo de Uso

```java
public class TesteStatus {
    public static void main(String[] args) {
        Status status = new Status();
        status.setLongName("Em andamento");

        System.out.println("Status: " + status.getLongName());
    }
}
