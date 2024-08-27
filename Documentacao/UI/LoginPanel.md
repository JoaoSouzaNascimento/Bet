## Classe `LoginPanel`

### Atributos

- **`txtLogin`** (`JTextField`): Campo de texto para o email do usuário.
- **`txtPassword`** (`JPasswordField`): Campo de senha para a senha do usuário.
- **`authService`** (`AuthService`): Serviço responsável pela autenticação do usuário.
- **`parentFrame`** (`JFrame`): Referência ao frame pai que contém o painel de login.

### Construtores

- **`LoginPanel(JFrame parentFrame)`**
  - **Parâmetros:**
    - `parentFrame` (`JFrame`): O frame pai que contém o painel de login.
  - **Descrição:**
    - Configura o layout do painel.
    - Inicializa o serviço de autenticação (`AuthService`).
    - Cria e adiciona os componentes do painel, incluindo campos de texto, botões e seus respectivos listeners.

### Métodos

- **`createStyledLabel(String text)`**: 
  - **Parâmetros:**
    - `text` (`String`): O texto a ser exibido no rótulo.
  - **Retorno:** `JLabel`
  - **Descrição:** Cria um `JLabel` estilizado com fonte e cor específicos.

- **`createStyledTextField()`**:
  - **Retorno:** `JTextField`
  - **Descrição:** Cria um `JTextField` estilizado com fonte e tamanho específico.

- **`createStyledPasswordField()`**:
  - **Retorno:** `JPasswordField`
  - **Descrição:** Cria um `JPasswordField` estilizado com fonte e tamanho específico.

- **`createStyledButton(String text)`**:
  - **Parâmetros:**
    - `text` (`String`): O texto a ser exibido no botão.
  - **Retorno:** `JButton`
  - **Descrição:** Cria um `JButton` estilizado com fonte, cor de fundo e tamanho específico.

### Funcionalidade

- **Login de Usuário:**
  - **Descrição:** Quando o botão "Login" é clicado, o painel realiza a autenticação do usuário utilizando o `authService`. Se as credenciais estiverem corretas, a `JanelaPrincipal` é aberta com o usuário autenticado. Caso contrário, é exibida uma mensagem de erro.
  
- **Encerrar Aplicação:**
  - **Descrição:** O botão "Exit" encerra a aplicação quando clicado.

- **Cadastro de Novo Usuário:**
  - **Descrição:** O botão "Cadastrar-se" altera o conteúdo do `JFrame` pai para exibir o painel de cadastro (`CadastroPanel`), permitindo que um novo usuário se registre.

### Observações

- **Dependências:**
  - **`AuthService`**: Usado para autenticar o usuário.
  - **`UsuarioDaoPostgreSQL`**: Implementação do DAO utilizada pelo `AuthService` para persistência e recuperação dos dados do usuário.
  - **`JanelaPrincipal`**: Deve ser uma classe existente no projeto, que é aberta após o login bem-sucedido.
  - **`CadastroPanel`**: Painel para registro de novos usuários, exibido ao clicar no botão "Cadastrar-se".

- **Estilização:**
  - O painel é estilizado com cores e fontes específicas para garantir uma interface visualmente atraente e coerente com o restante da aplicação.

- **Validações:**
  - O painel lida com erros de login e exibe mensagens apropriadas.
  - O botão "Cadastrar-se" permite a navegação para a tela de cadastro, mantendo a aplicação fluida e intuitiva.
