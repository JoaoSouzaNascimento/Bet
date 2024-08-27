# Classe `AppLauncher`

A classe `AppLauncher` é responsável por iniciar a aplicação, configurando e inicializando os serviços necessários e exibindo a tela de login para o usuário.

## Atributos

- **`authService`**: `AuthService` - Serviço de autenticação usado para criar e gerenciar usuários.
- **`usuarioTeste`**: `Usuario` - Instância de um usuário de teste, criada para fins de desenvolvimento e teste.

## Métodos

### `public static void main(String[] args)`

Método principal que é executado ao iniciar a aplicação. Responsável por:

- Criar uma instância de `AppLauncher`.
- Inicializar os serviços, incluindo `UsuarioService`, `TransactionService`, `ApostaService`, e configurar o `AppContext` com essas instâncias.
- Criar um usuário de teste se ele não existir.
- Criar e configurar a janela de login (`LoginPanel`), definindo seu tamanho, operação de fechamento e visibilidade.

### `private void criarUsuarioTesteSeNaoExistir()`

Cria um usuário de teste se ele ainda não existir. Se o usuário não estiver presente, o método:

- Define os dados para o usuário de teste (nome de usuário, apelido, e-mail, senha e cargo).
- Usa o `authService` para criar o usuário.
- Imprime uma mensagem no console indicando se o usuário foi criado com sucesso ou se houve um erro.

## Observações

- **Serviços e Configuração**: A classe configura os serviços essenciais da aplicação e garante que o `AppContext` seja configurado adequadamente para fornecer os serviços necessários.

- **Usuário de Teste**: A criação do usuário de teste é uma parte importante para garantir que o sistema possa ser testado com dados pré-definidos. Este usuário é criado automaticamente se ainda não existir, facilitando a fase de desenvolvimento.

- **Interface de Usuário**: A classe é responsável por configurar e exibir a interface inicial da aplicação, especificamente a tela de login.

## Funcionalidade

- **Inicialização da Aplicação**: Inicializa os serviços e configura o contexto da aplicação.
- **Criação de Usuário de Teste**: Garante que um usuário de teste esteja disponível para uso, facilitando testes iniciais.
- **Exibição da Tela de Login**: Configura e exibe a janela de login, permitindo que o usuário acesse a aplicação.

A classe `AppLauncher` atua como ponto de entrada para a aplicação, configurando os serviços necessários e garantindo que a interface de usuário inicial esteja pronta para interações.
