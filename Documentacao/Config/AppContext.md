# Classe `AppContext`

A classe `AppContext` é uma classe de configuração central que fornece acesso aos serviços utilizados na aplicação. Ela utiliza métodos estáticos para gerenciar as instâncias dos serviços e fornece acesso a configurações de API.

## Atributos

- **`usuarioService`**: `UsuarioService` - Serviço para gerenciar informações do usuário.
- **`transactionService`**: `TransactionService` - Serviço para gerenciar transações.
- **`apostaService`**: `ApostaService` - Serviço para gerenciar apostas.
- **`footballApiService`**: `FootballApiService` - Serviço para interagir com a API de futebol.

## Constantes

- **`API_KEY`**: `String` - Chave da API para acessar o serviço de futebol. 
- **`API_HOST`**: `String` - Host da API para acessar o serviço de futebol.

## Métodos

- **`setUsuarioService(UsuarioService service)`**: Define a instância do serviço de usuário a ser usado na aplicação.

- **`getTransactionService()`**: Retorna a instância do serviço de transações.

- **`setTransactionService(TransactionService transactionService)`**: Define a instância do serviço de transações a ser usado na aplicação.

- **`getUsuarioService()`**: Retorna a instância do serviço de usuário.

- **`getApostaService()`**: Retorna a instância do serviço de apostas.

- **`setApostaService(ApostaService service)`**: Define a instância do serviço de apostas a ser usado na aplicação.

- **`getFootballApiService()`**: Retorna a instância do serviço de API de futebol.

- **`setFootballApiService(FootballApiService service)`**: Define a instância do serviço de API de futebol a ser usado na aplicação.

## Observações

- **Gerenciamento de Serviços**: A classe `AppContext` centraliza a configuração e acesso aos serviços utilizados na aplicação, permitindo que as instâncias dos serviços sejam definidas e acessadas globalmente.

- **Configuração da API**: Contém informações necessárias para acessar a API de futebol, incluindo chave e host.

## Funcionalidade

- **Configuração Centralizada**: Fornece uma abordagem centralizada para gerenciar e acessar serviços na aplicação.
- **Acesso Global**: Permite que diferentes partes da aplicação acessem serviços configurados sem precisar passar referências explicitamente.
- **Configuração de API**: Armazena e fornece detalhes necessários para interagir com a API de futebol.

A classe `AppContext` facilita a configuração e o acesso a serviços essenciais na aplicação, melhorando a organização e a modularidade do código.
