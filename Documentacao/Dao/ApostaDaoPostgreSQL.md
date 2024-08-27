# Classe `ApostaDaoPostgreSQL`

A classe `ApostaDaoPostgreSQL` implementa a interface `ApostaDao` e fornece a implementação concreta para manipulação de apostas em um banco de dados PostgreSQL.

## Métodos

### `Aposta createAposta(Aposta aposta) throws InsercaoException`
Implementa a criação de uma nova aposta na base de dados.

#### Parâmetros:
- `Aposta aposta`: Objeto contendo os dados da aposta a ser criada.

#### Exceções:
- `InsercaoException`: Se ocorrer um erro durante a inserção.

#### Retorno:
- `Aposta`: Aposta criada com o ID gerado.

### `Aposta updateAposta(Aposta aposta) throws AtualizacaoException`
Implementa a atualização de uma aposta existente na base de dados.

#### Parâmetros:
- `Aposta aposta`: Objeto contendo os dados atualizados da aposta.

#### Exceções:
- `AtualizacaoException`: Se ocorrer um erro durante a atualização.

#### Retorno:
- `Aposta`: Aposta atualizada.

### `void deleteAposta(int id) throws DelecaoException`
Implementa a deleção de uma aposta da base de dados pelo ID.

#### Parâmetros:
- `int id`: ID da aposta a ser deletada.

#### Exceções:
- `DelecaoException`: Se ocorrer um erro durante a deleção.

### `Aposta getApostaById(int id) throws ConsultaException`
Implementa a recuperação de uma aposta da base de dados pelo ID.

#### Parâmetros:
- `int id`: ID da aposta a ser recuperada.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro durante a consulta ou se a aposta não for encontrada.

#### Retorno:
- `Aposta`: Aposta recuperada.

### `List<Aposta> getTodasApostasPorUsuarioId(UUID usuarioId) throws ConsultaException`
Implementa a recuperação de todas as apostas associadas a um usuário específico.

#### Parâmetros:
- `UUID usuarioId`: ID do usuário cujas apostas devem ser recuperadas.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro durante a consulta.

#### Retorno:
- `List<Aposta>`: Lista de apostas associadas ao usuário.

### `List<Aposta> getApostasPendentesPorUsuarioId(UUID usuarioId) throws ConsultaException`
Implementa a recuperação de todas as apostas pendentes associadas a um usuário específico.

#### Parâmetros:
- `UUID usuarioId`: ID do usuário cujas apostas pendentes devem ser recuperadas.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro durante a consulta.

#### Retorno:
- `List<Aposta>`: Lista de apostas pendentes associadas ao usuário.

### `List<Aposta> getApostasComStatusNulo() throws ConsultaException`
Implementa a recuperação de todas as apostas com status nulo.

#### Exceções:
- `ConsultaException`: Se ocorrer um erro durante a consulta.

#### Retorno:
- `List<Aposta>`: Lista de apostas com status nulo.

## Métodos Privados

### `Aposta extractApostaFromResultSet(ResultSet rs) throws SQLException`
Extrai os dados de uma aposta a partir de um `ResultSet`.

#### Parâmetros:
- `ResultSet rs`: `ResultSet` contendo os dados da aposta.

#### Exceções:
- `SQLException`: Se ocorrer um erro ao extrair dados do `ResultSet`.

#### Retorno:
- `Aposta`: Objeto `Aposta` com os dados extraídos.
