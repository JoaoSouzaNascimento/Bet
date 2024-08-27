# Classe `FixtureData`

A classe `FixtureData` representa os dados de um evento de fixture de futebol, incluindo informações sobre as equipes, o status do jogo e a data.

## Atributos

- `fixtureId`: ID do fixture.
- `data`: Data e hora do fixture.
- `timeDeCasaNome`: Nome do time da casa.
- `timeDeCasaLogo`: URL do logo do time da casa.
- `timeDeForaNome`: Nome do time visitante.
- `timeDeForaLogo`: URL do logo do time visitante.
- `status`: Status do fixture (por exemplo, "Match Finished").
- `casaGanhou`: Indica se o time da casa ganhou.
- `foraGanhou`: Indica se o time visitante ganhou.

## Construtores

### `FixtureData(String fixtureId, String data, String timeDeCasaNome, URL timeDeCasaLogo, String timeDeForaNome, URL timeDeForaLogo, String status, boolean casaGanhou, boolean foraGanhou)`
Cria uma nova instância de `FixtureData`.

#### Parâmetros:
- `fixtureId`: ID do fixture.
- `data`: Data e hora do fixture.
- `timeDeCasaNome`: Nome do time da casa.
- `timeDeCasaLogo`: URL do logo do time da casa.
- `timeDeForaNome`: Nome do time visitante.
- `timeDeForaLogo`: URL do logo do time visitante.
- `status`: Status do fixture.
- `casaGanhou`: Indica se o time da casa ganhou.
- `foraGanhou`: Indica se o time visitante ganhou.

## Métodos

### `String getFixtureId()`
Retorna o ID do fixture.

### `void setFixtureId(String fixtureId)`
Define o ID do fixture.

### `String getData()`
Retorna a data e hora do fixture.

### `void setData(String data)`
Define a data e hora do fixture.

### `String getTimeDeCasaNome()`
Retorna o nome do time da casa.

### `void setTimeDeCasaNome(String timeDeCasaNome)`
Define o nome do time da casa.

### `URL getTimeDeCasaLogo()`
Retorna a URL do logo do time da casa.

### `void setTimeDeCasaLogo(URL timeDeCasaLogo)`
Define a URL do logo do time da casa.

### `String getTimeDeForaNome()`
Retorna o nome do time visitante.

### `void setTimeDeForaNome(String timeDeForaNome)`
Define o nome do time visitante.

### `URL getTimeDeForaLogo()`
Retorna a URL do logo do time visitante.

### `void setTimeDeForaLogo(URL timeDeForaLogo)`
Define a URL do logo do time visitante.

### `String getStatus()`
Retorna o status do fixture.

### `void setStatus(String status)`
Define o status do fixture.

### `boolean isCasaGanhou()`
Verifica se o time da casa ganhou.

### `void setCasaGanhou(boolean casaGanhou)`
Define se o time da casa ganhou.

### `boolean isForaGanhou()`
Verifica se o time visitante ganhou.

### `void setForaGanhou(boolean foraGanhou)`
Define se o time visitante ganhou.

### `String toString()`
Retorna uma representação em string do objeto `FixtureData`.

## Exemplo de Uso

```java
import java.net.URL;
import java.net.MalformedURLException;

public class TesteFixtureData {
    public static void main(String[] args) {
        try {
            URL logoCasa = new URL("http://example.com/logo_casa.png");
            URL logoFora = new URL("http://example.com/logo_fora.png");
            FixtureData fixtureData = new FixtureData(
                "12345",
                "2024-08-27T15:00:00Z",
                "Time da Casa",
                logoCasa,
                "Time de Fora",
                logoFora,
                "Match Finished",
                true,
                false
            );

            System.out.println(fixtureData);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
