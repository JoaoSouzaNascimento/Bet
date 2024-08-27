# Classe `ApostaService`

A classe `ApostaService` oferece serviços relacionados à manipulação e validação de apostas, incluindo a criação, exclusão, e atualização de apostas, além da verificação do resultado das apostas.

## Atributos

- `apostaDao`: Instância de `ApostaDao` usada para operações de acesso a dados das apostas.
- `palpiteDao`: Instância de `PalpiteDao` usada para operações de acesso a dados dos palpites.
- `footballApiService`: Instância de `FootballApiService` usada para acessar dados de partidas de futebol.
- `partidaService`: Instância de `PartidaService` usada para obter dados das partidas.
- `usuarioDao`: Instância de `UsuarioDao` usada para operações de acesso a dados dos usuários.

## Construtores

### `ApostaService(ApostaDao apostaDao, PalpiteDao palpiteDao, FootballApiService footballApiService, PartidaService partidaService, UsuarioDao usuarioDao)`
Cria uma nova instância de `ApostaService` com os DAOs e serviços fornecidos para operações relacionadas a apostas, palpites, e dados de futebol.

#### Parâmetros:
- `apostaDao`: Instância de `ApostaDao`.
- `palpiteDao`: Instância de `PalpiteDao`.
- `footballApiService`: Instância de `FootballApiService`.
- `partidaService`: Instância de `PartidaService`.
- `usuarioDao`: Instância de `UsuarioDao`.

## Métodos

### `boolean validarApostas(String league, String timezone) throws Exception`
Valida e atualiza o status das apostas com status nulo, verificando se foram ganhas com base nos resultados das partidas.

#### Parâmetros:
- `league`: Liga para a qual as apostas são validadas.
- `timezone`: Fuso horário para a obtenção dos dados de fixtures.

#### Retorno:
- `boolean`: Retorna `true` se a validação foi bem-sucedida para todas as apostas, caso contrário `false`.

#### Exceções:
- `Exception`: Captura exceções relacionadas a consultas e atualizações de dados.

### `Aposta getApostaById(int apostaId)`
Obtém uma aposta específica pelo ID.

#### Parâmetros:
- `apostaId`: ID da aposta a ser recuperada.

#### Retorno:
- `Aposta`: A aposta com o ID especificado.

### `List<Aposta> getTodasApostas(Usuario usuario) throws ConsultaException`
Obtém todas as apostas associadas a um usuário específico.

#### Parâmetros:
- `usuario`: O usuário cujas apostas serão recuperadas.

#### Retorno:
- `List<Aposta>`: Lista de apostas associadas ao usuário.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar as apostas no banco de dados.

### `List<Aposta> getApostasPendentes(Usuario usuario)`
Obtém todas as apostas pendentes para um usuário específico.

#### Parâmetros:
- `usuario`: O usuário cujas apostas pendentes serão recuperadas.

#### Retorno:
- `List<Aposta>`: Lista de apostas pendentes.

### `List<Aposta> getApostasComStatusNulo()`
Obtém todas as apostas com status nulo (pendentes).

#### Retorno:
- `List<Aposta>`: Lista de apostas com status nulo.

### `void criarAposta(Aposta aposta, List<Palpite> palpites)`
Cria uma nova aposta e seus palpites associados, e atualiza o saldo do usuário.

#### Parâmetros:
- `aposta`: A aposta a ser criada.
- `palpites`: Lista de palpites associados à aposta.

#### Exceções:
- `Exception`: Captura exceções relacionadas à criação da aposta e atualização do saldo do usuário.

### `void excluirAposta(Aposta aposta) throws Exception`
Exclui uma aposta específica, desde que esteja aberta.

#### Parâmetros:
- `aposta`: A aposta a ser excluída.

#### Exceções:
- `Exception`: Captura exceções relacionadas à exclusão da aposta.

### `List<Palpite> getPalpitesDeUmaAposta(Aposta aposta)`
Obtém todos os palpites associados a uma aposta específica.

#### Parâmetros:
- `aposta`: A aposta cujos palpites serão recuperados.

#### Retorno:
- `List<Palpite>`: Lista de palpites associados à aposta.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar os palpites no banco de dados.

## Métodos Privados

### `List<FixtureData> getFinishedFixtures(List<FixtureResponse> fixtures) throws Exception`
Filtra e retorna as fixtures que foram finalizadas.

#### Parâmetros:
- `fixtures`: Lista de respostas de fixtures a serem filtradas.

#### Retorno:
- `List<FixtureData>`: Lista de fixtures finalizadas.

#### Exceções:
- `Exception`: Se ocorrer um erro ao processar as fixtures.

### `boolean isApostaGanha(List<Palpite> palpites, List<FixtureData> fixtureDataList)`
Determina se uma aposta foi ganha com base nos palpites e nos dados das fixtures.

#### Parâmetros:
- `palpites`: Lista de palpites associados à aposta.
- `fixtureDataList`: Lista de dados das fixtures.

#### Retorno:
- `boolean`: Retorna `true` se a aposta foi ganha, caso contrário `false`.

### `boolean isApostaAberta(Aposta aposta) throws Exception`
Verifica se uma aposta está aberta, ou seja, se todas as partidas associadas ainda não começaram.

#### Parâmetros:
- `aposta`: A aposta a ser verificada.

#### Retorno:
- `boolean`: Retorna `true` se a aposta estiver aberta, caso contrário `false`.

#### Exceções:
- `Exception`: Captura exceções relacionadas à verificação do status das partidas.

## Exemplo de Uso

```java
ApostaService apostaService = new ApostaService(apostaDao, palpiteDao, footballApiService, partidaService, usuarioDao);

// Criar uma nova aposta
Aposta aposta = new Aposta(...);
List<Palpite> palpites = Arrays.asList(...);
apostaService.criarAposta(aposta, palpites);

// Validar apostas
boolean validado = apostaService.validarApostas("LigaExemplo", "GMT");

// Obter apostas de um usuário
Usuario usuario = new Usuario(...);
List<Aposta> apostas = apostaService.getTodasApostas(usuario);

// Excluir uma aposta
Aposta apostaParaExcluir = new Aposta(...);
apostaService.excluirAposta(apostaParaExcluir);
