
### `Teams`

```md
# Classe `Teams`

A classe `Teams` representa os times envolvidos em uma partida, com informações sobre o time da casa e o time visitante.

## Atributos

- `home`: Objeto `Team` que representa o time da casa.
- `away`: Objeto `Team` que representa o time visitante.

## Métodos

### `Team getHome()`
Retorna o time da casa.

### `void setHome(Team home)`
Define o time da casa.

### `Team getAway()`
Retorna o time visitante.

### `void setAway(Team away)`
Define o time visitante.

## Exemplo de Uso

```java
public class TesteTeams {
    public static void main(String[] args) {
        Teams teams = new Teams();
        Team homeTeam = new Team();
        Team awayTeam = new Team();
        
        teams.setHome(homeTeam);
        teams.setAway(awayTeam);

        System.out.println("Time da casa: " + teams.getHome());
        System.out.println("Time visitante: " + teams.getAway());
    }
}
