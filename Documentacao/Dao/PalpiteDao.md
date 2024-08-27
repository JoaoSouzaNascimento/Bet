# Interface `PalpiteDao`

A interface `PalpiteDao` define os métodos para operações relacionadas a palpites na base de dados.

## Métodos

### `Palpite createPalpite(Palpite palpite) throws InsercaoException`
Cria um novo palpite na base de dados.

#### Parâmetros:
- `palpite`: O objeto `Palpite` que representa o palpite a ser inserido.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir o palpite.

#### Retorno:
- `Palpite`: O palpite inserido.

### `Palpite updatePalpite(Palpite palpite) throws AtualizacaoException`
Atualiza um palpite existente na base de dados.

#### Parâmetros:
- `palpite`: O objeto `Palpite` que representa o palpite a ser atualizado.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro ao tentar atualizar o palpite.

#### Retorno:
- `Palpite`: O palpite atualizado.

### `void deletePalpite(int apostaId, int partidaId) throws DelecaoException`
Deleta um palpite da base de dados com base no ID da aposta e ID da partida.

#### Parâmetros:
- `apostaId`: O ID da aposta do palpite a ser deletado.
- `partidaId`: O ID da partida do palpite a ser deletado.

#### Exceções:
- `DelecaoException`: Se ocorrer um erro ao tentar deletar o palpite.

### `Palpite getPalpiteById(int apostaId, int partidaId) throws ConsultaException`
Obtém um palpite específico com base no ID da aposta e ID da partida.

#### Parâmetros:
- `apostaId`: O ID da aposta do palpite a ser consultado.
- `partidaId`: O ID da partida do palpite a ser consultado.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar o palpite.

#### Retorno:
- `Palpite`: O palpite consultado.

### `List<Palpite> getTodosPalpitesDeUmaAposta(int apostaId) throws ConsultaException`
Obtém todos os palpites relacionados a uma aposta específica.

#### Parâmetros:
- `apostaId`: O ID da aposta cujos palpites devem ser retornados.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro ao tentar consultar os palpites.

#### Retorno:
- `List<Palpite>`: Uma lista de objetos `Palpite` relacionados à aposta.

### `void createListaDePalpites(List<Palpite> palpites) throws InsercaoException`
Cria múltiplos palpites de uma só vez.

#### Parâmetros:
- `palpites`: Uma lista de objetos `Palpite` a serem inseridos.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro ao tentar inserir os palpites.
