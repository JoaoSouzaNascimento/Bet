
### `Fixture`

```md
# Classe `Fixture`

A classe `Fixture` representa uma partida de futebol, com detalhes como ID, data e status.

## Atributos

- `id`: Identificador da partida.
- `date`: Data da partida.
- `status`: Status da partida.

## Métodos

### `String getId()`
Retorna o ID da partida.

### `void setId(String id)`
Define o ID da partida.

### `String getDate()`
Retorna a data da partida.

### `void setDate(String date)`
Define a data da partida.

### `Status getStatus()`
Retorna o status da partida.

### `void setStatus(Status status)`
Define o status da partida.

## Exemplo de Uso

```java
public class TesteFixture {
    public static void main(String[] args) {
        Fixture fixture = new Fixture();
        fixture.setId("12345");
        fixture.setDate("2024-08-27");
        
        // Supondo que Status é uma classe já definida
        Status status = new Status();
        fixture.setStatus(status);

        System.out.println("ID da partida: " + fixture.getId());
        System.out.println("Data da partida: " + fixture.getDate());
        System.out.println("Status da partida: " + fixture.getStatus());
    }
}
