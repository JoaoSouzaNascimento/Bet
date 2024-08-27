# Classe `OddsData`

A classe `OddsData` representa as odds (cotas) de um evento de futebol, incluindo as odds para a vitória do time da casa, a vitória do time visitante e o empate.

## Atributos

- `timeDeCasaGanhaOdd`: Odd para a vitória do time da casa.
- `timeDeForaGanhaOdd`: Odd para a vitória do time visitante.
- `empateOdd`: Odd para o empate.

## Construtores

### `OddsData(float timeDeCasaGanhaOdd, float timeDeForaGanhaOdd, float empateOdd)`
Cria uma nova instância de `OddsData`.

#### Parâmetros:
- `timeDeCasaGanhaOdd`: Odd para a vitória do time da casa.
- `timeDeForaGanhaOdd`: Odd para a vitória do time visitante.
- `empateOdd`: Odd para o empate.

## Métodos

### `float getTimeDeCasaGanhaOdd()`
Retorna a odd para a vitória do time da casa.

### `void setTimeDeCasaGanhaOdd(float timeDeCasaGanhaOdd)`
Define a odd para a vitória do time da casa.

### `float getTimeDeForaGanhaOdd()`
Retorna a odd para a vitória do time visitante.

### `void setTimeDeForaGanhaOdd(float timeDeForaGanhaOdd)`
Define a odd para a vitória do time visitante.

### `float getEmpateOdd()`
Retorna a odd para o empate.

### `void setEmpateOdd(float empateOdd)`
Define a odd para o empate.

## Exemplo de Uso

```java
public class TesteOddsData {
    public static void main(String[] args) {
        OddsData oddsData = new OddsData(1.75f, 2.10f, 3.50f);

        System.out.println("Odd para vitória do time da casa: " + oddsData.getTimeDeCasaGanhaOdd());
        System.out.println("Odd para vitória do time visitante: " + oddsData.getTimeDeForaGanhaOdd());
        System.out.println("Odd para empate: " + oddsData.getEmpateOdd());

        oddsData.setTimeDeCasaGanhaOdd(1.80f);
        oddsData.setTimeDeForaGanhaOdd(2.20f);
        oddsData.setEmpateOdd(3.40f);

        System.out.println("Novas odds:");
        System.out.println("Odd para vitória do time da casa: " + oddsData.getTimeDeCasaGanhaOdd());
        System.out.println("Odd para vitória do time visitante: " + oddsData.getTimeDeForaGanhaOdd());
        System.out.println("Odd para empate: " + oddsData.getEmpateOdd());
    }
}
