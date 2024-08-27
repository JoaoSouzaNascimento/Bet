# Classe `CadastroPanel`

A classe `CadastroPanel` é um painel de cadastro que permite aos usuários criar uma nova conta. Ela fornece campos para entrada de nome, nome de usuário, e-mail e senha, além de botões para adicionar ou limpar o formulário.

## Atributos

- `passwordTeste`: Campo de senha para a senha do usuário.
- `passwordSalvar`: Campo de senha para confirmar a senha do usuário.
- `txtEmail`: Campo de texto para o e-mail do usuário.
- `txtUserName`: Campo de texto para o nome de usuário.
- `txtName`: Campo de texto para o nome completo do usuário.
- `authService`: Instância do serviço de autenticação para realizar o cadastro de usuário.

## Construtor

### `CadastroPanel(JFrame parentFrame)`

Cria um novo painel de cadastro e configura a interface gráfica.

- **Parâmetros:**
  - `parentFrame`: O `JFrame` pai que será fechado após o cadastro ser realizado com sucesso.

## Métodos

### `JLabel createStyledLabel(String text)`

Cria e retorna um `JLabel` com o texto fornecido, estilizado com fonte Arial e cor branca.

- **Parâmetros:**
  - `text`: O texto a ser exibido no `JLabel`.

- **Retorno:**
  - `JLabel` estilizado.

### `JTextField createStyledTextField()`

Cria e retorna um `JTextField` estilizado com fonte Arial e tamanho preferido.

- **Retorno:**
  - `JTextField` estilizado.

### `JPasswordField createStyledPasswordField()`

Cria e retorna um `JPasswordField` estilizado com fonte Arial e tamanho preferido.

- **Retorno:**
  - `JPasswordField` estilizado.

### `JButton createStyledButton(String text)`

Cria e retorna um `JButton` estilizado com o texto fornecido, cor de fundo e fonte Arial.

- **Parâmetros:**
  - `text`: O texto a ser exibido no `JButton`.

- **Retorno:**
  - `JButton` estilizado.

## Exemplo de Uso

```java
public class TesteCadastroPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro");
        CadastroPanel cadastroPanel = new CadastroPanel(frame);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(cadastroPanel);
        frame.pack();
        frame.setVisible(true);
    }
}

## Funcionalidade

- **Cadastro de Usuário:** 
  - Quando o botão "Add" é clicado, o painel realiza as seguintes ações:
    1. **Validação de Senha:** Verifica se as senhas fornecidas nos campos `passwordTeste` e `passwordSalvar` coincidem. Se não coincidirem, uma mensagem de erro é exibida e o cadastro não prossegue.
    2. **Cadastro:** Se as senhas coincidirem, utiliza o `AuthService` para tentar realizar o cadastro do novo usuário com os dados fornecidos (nome, nome de usuário, e-mail e senha). 
    3. **Feedback:** Se o cadastro for bem-sucedido, uma mensagem de sucesso é exibida, o `JFrame` de cadastro é fechado e a `JanelaPrincipal` é aberta com o usuário cadastrado.
    4. **Erro:** Caso ocorra algum erro durante o processo de cadastro, uma mensagem de erro é exibida com o detalhe da exceção.
- **Limpar Campos:** 
  - O botão "Clear" limpa todos os campos do formulário, permitindo que o usuário reinicie o processo de cadastro.

## Observações

- **Dependências:**
  - **`AuthService`**: Usado para realizar o cadastro do usuário.
  - **`UsuarioDaoPostgreSQL`**: Implementação do DAO utilizada pelo `AuthService` para persistência dos dados.
  - **`CargoUsuario.USUARIO`**: Definido como cargo padrão do usuário durante o cadastro.
  - **`JanelaPrincipal`**: Deve ser uma classe existente no projeto, que é aberta após o cadastro com o usuário cadastrado.
- **Estilização:**
  - O painel é estilizado com cores e fontes específicas para criar uma interface visualmente atraente e coerente.
- **Validações:**
  - Verificações básicas são realizadas para garantir que as senhas coincidam antes de permitir o cadastro.

