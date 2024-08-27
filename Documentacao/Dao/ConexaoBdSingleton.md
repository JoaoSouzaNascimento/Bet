# Classe `ConexaoBdSingleton`

A classe `ConexaoBdSingleton` é responsável por gerenciar a conexão com a base de dados PostgreSQL utilizando o padrão Singleton. Ela garante que exista apenas uma instância da conexão ativa durante a execução da aplicação.

## Métodos

### `ConexaoBdSingleton() throws SQLException`
Construtor privado que inicializa a conexão com a base de dados. É chamado somente pelo método `getInstance()`.

#### Exceções:
- `SQLException`: Se ocorrer um erro ao tentar conectar-se à base de dados.

### `void connect() throws SQLException`
Estabelece uma conexão com a base de dados PostgreSQL.

#### Exceções:
- `SQLException`: Se ocorrer um erro ao tentar conectar-se à base de dados.

### `Connection getConexao() throws SQLException`
Retorna a conexão atual com a base de dados. Se a conexão estiver fechada ou for nula, cria uma nova conexão.

#### Exceções:
- `SQLException`: Se ocorrer um erro ao obter a conexão.

#### Retorno:
- `Connection`: A conexão com a base de dados.

### `static ConexaoBdSingleton getInstance() throws SQLException`
Obtém a instância única da classe `ConexaoBdSingleton`. Cria uma nova instância se ela não existir.

#### Exceções:
- `SQLException`: Se ocorrer um erro ao tentar criar a instância.

#### Retorno:
- `ConexaoBdSingleton`: A instância única da classe.

## Notas

- A URL de conexão utilizada é `"jdbc:postgresql://localhost:5434/tigrinho"`, com usuário `"postgres"` e senha `"postgres"`. É recomendável utilizar variáveis de ambiente ou arquivos de configuração para gerenciar essas informações sensíveis em um ambiente de produção.
