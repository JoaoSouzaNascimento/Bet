
### `Team`

```md
# Classe `Team`

A classe `Team` representa um time, com informações sobre o nome, o logo e o vencedor.

## Atributos

- `name`: Nome do time.
- `logo`: URL do logo do time.
- `winner`: Informação se o time é o vencedor.

## Métodos

### `String getName()`
Retorna o nome do time.

### `void setName(String name)`
Define o nome do time.

### `String getLogo()`
Retorna o URL do logo do time.

### `void setLogo(String logo)`
Define o URL do logo do time.

### `String getWinner()`
Retorna a informação se o time é o vencedor.

### `void setWinner(String winner)`
Define a informação se o time é o vencedor.

## Exemplo de Uso

```java
public class TesteTeam {
    public static void main(String[] args) {
        Team team = new Team();
        team.setName("Team A");
        team.setLogo("http://example.com/logo.png");
        team.setWinner("yes");

        System.out.println("Nome do time: " + team.getName());
        System.out.println("Logo do time: " + team.getLogo());
        System.out.println("Vencedor: " + team.getWinner());
    }
}
