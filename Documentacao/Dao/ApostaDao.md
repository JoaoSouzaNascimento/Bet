# Interface `ApostaDao`

A interface `ApostaDao` define as operações básicas para o gerenciamento de apostas na base de dados. Ela fornece métodos para criar, atualizar, deletar e consultar apostas.

## Métodos

### `Aposta createAposta(Aposta aposta) throws InsercaoException`
Cria uma nova aposta na base de dados.

#### Parâmetros:
- `Aposta aposta`: Objeto contendo os dados da aposta a ser criada.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro durante a inserção.

#### Retorno:
- `Aposta`: Aposta criada com o ID gerado.

### `Aposta updateAposta(Aposta aposta) throws AtualizacaoException`
Atualiza uma aposta existente na base de dados.

#### Parâmetros:
- `Aposta aposta`: Objeto contendo os dados atualizados da aposta.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro durante a atualização.

#### Retorno:
- `Aposta`: Aposta atualizada.

### `void deleteAposta(int id) throws DelecaoException`
Deleta uma aposta da base de dados pelo ID.

#### Parâmetros:
- `int id`: ID da aposta a ser deletada.

#### Exceções:
- `DelecaoException`: Se ocorrer um erro durante a deleção.

### `Aposta getApostaById(int id) throws ConsultaException`
Recupera uma aposta da base de dados pelo ID.

#### Parâmetros:
- `int id`: ID da aposta a ser recuperada.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro durante a consulta ou se a aposta não for encontrada.

#### Retorno:
- `Aposta`: Aposta recuperada.

### `List<Aposta> getTodasApostasPorUsuarioId(UUID usuarioId) throws ConsultaException`
Recupera todas as apostas associadas a um usuário específico.

#### Parâmetros:
- `UUID usuarioId`: ID do usuário cujas apostas devem ser recuperadas.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro durante a consulta.

#### Retorno:
- `List<Aposta>`: Lista de apostas associadas ao usuário.

### `List<Aposta> getApostasPendentesPorUsuarioId(UUID usuarioId) throws ConsultaException`
Recupera todas as apostas pendentes associadas a um usuário específico.

#### Parâmetros:
- `UUID usuarioId`: ID do usuário cujas apostas pendentes devem ser recuperadas.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro durante a consulta.

#### Retorno:
- `List<Aposta>`: Lista de apostas pendentes associadas ao usuário.

### `List<Aposta> getApostasComStatusNulo() throws ConsultaException`
Recupera todas as apostas com status nulo.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro durante a consulta.

#### Retorno:
- `List<Aposta>`: Lista de apostas com status nulo.
