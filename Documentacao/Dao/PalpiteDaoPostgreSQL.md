# Classe `PalpiteDaoPostgreSQL`

A classe `PalpiteDaoPostgreSQL` é uma implementação da interface `PalpiteDao` e fornece métodos para gerenciar palpites em uma base de dados PostgreSQL.

## Métodos

### `Palpite createPalpite(Palpite palpite) throws InsercaoException`
Insere um novo palpite na tabela `BETS_MATCHES`.

#### Parâmetros:
- `palpite`: O objeto `Palpite` que representa o palpite a ser inserido.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir o palpite.

#### Retorno:
- `Palpite`: O palpite inserido.

### `void createListaDePalpites(List<Palpite> palpites) throws InsercaoException`
Insere múltiplos palpites na tabela `BETS_MATCHES` em uma única operação.

#### Parâmetros:
- `palpites`: Uma lista de objetos `Palpite` a serem inseridos.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir os palpites.

### `Palpite updatePalpite(Palpite palpite) throws AtualizacaoException`
Atualiza um palpite existente na tabela `BETS_MATCHES`.

#### Parâmetros:
- `palpite`: O objeto `Palpite` que representa o palpite a ser atualizado.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro ao tentar atualizar o palpite.

#### Retorno:
- `Palpite`: O palpite atualizado.

### `void deletePalpite(int apostaId, int partidaId) throws DelecaoException`
Deleta um palpite da tabela `BETS_MATCHES` com base no ID da aposta e ID da partida.

#### Parâmetros:
- `apostaId`: O ID da aposta do palpite a ser deletado.
- `partidaId`: O ID da partida do palpite a ser deletado.

#### Exceções:
- `DelecaoException`: Se ocorrer um erro ao tentar deletar o palpite.

### `Palpite getPalpiteById(int apostaId, int partidaId) throws ConsultaException`
Obtém um palpite específico da tabela `BETS_MATCHES` com base no ID da aposta e ID da partida.

#### Parâmetros:
- `apostaId`: O ID da aposta do palpite a ser consultado.
- `partidaId`: O ID da partida do palpite a ser consultado.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar o palpite.

#### Retorno:
- `Palpite`: O palpite consultado.

### `List<Palpite> getTodosPalpitesDeUmaAposta(int apostaId) throws ConsultaException`
Obtém todos os palpites relacionados a uma aposta específica da tabela `BETS_MATCHES`.

#### Parâmetros:
- `apostaId`: O ID da aposta cujos palpites devem ser retornados.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar os palpites.

#### Retorno:
- `List<Palpite>`: Uma lista de objetos `Palpite` relacionados à aposta.

## Notas

- A URL de conexão com a base de dados é gerenciada pela classe `ConexaoBdSingleton`, e a conexão é estabelecida através do método `getConexao()` dessa classe.
- O método `createPalpite` e `createListaDePalpites` inserem registros na tabela `BETS_MATCHES`, que deve ter os campos `BET_ID`, `MATCH_ID`, `SHOT` e `ODD`.
- O método `updatePalpit
