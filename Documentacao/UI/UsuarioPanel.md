# Classe `UsuarioPanel`

A classe `UsuarioPanel` é uma janela (`JFrame`) que exibe informações do perfil do usuário, permite realizar depósitos e saques, e acessar históricos de apostas e transações. 

## Atributos

- **`lblUsername`**: `JLabel` - Rótulo que exibe o nome de usuário logado.
- **`lblEmail`**: `JLabel` - Rótulo que exibe o e-mail do usuário logado.
- **`lblBalance`**: `JLabel` - Rótulo que exibe o saldo do usuário logado.
- **`usuarioLogado`**: `Usuario` - O usuário atualmente logado na aplicação.
- **`usuarioService`**: `UsuarioService` - Serviço para gerenciar informações do usuário.
- **`transactionService`**: `TransactionService` - Serviço para gerenciar transações.
- **`apostaService`**: `ApostaService` - Serviço para gerenciar apostas.
- **`janelaPrincipal`**: `JanelaPrincipal` - Referência para a janela principal da aplicação.

## Construtores

- **`UsuarioPanel(JanelaPrincipal janelaPrincipal)`**: Construtor que inicializa o painel com a referência à `JanelaPrincipal`, e configura os serviços necessários.

## Métodos

- **`setUsuario(Usuario usuario)`**: Define o usuário logado e atualiza a interface com as informações do usuário.
  
- **`initialize()`**: Inicializa a interface gráfica do painel, configura os rótulos, botões, e layout.

- **`atualizarSaldoUI()`**: Atualiza o rótulo de saldo com o saldo do usuário logado e atualiza a `JanelaPrincipal`.

- **`createStyledLabel(String text)`**: Cria e retorna um rótulo com estilo padrão.

- **`createStyledButton(String text)`**: Cria e retorna um botão com estilo padrão.

- **`abrirTelaDeposito()`**: Abre uma janela (`JDialog`) para realizar depósitos, com validação e atualização do saldo.

- **`abrirTelaSaque()`**: Abre uma janela (`JDialog`) para realizar saques, com validação e atualização do saldo.

- **`abrirHistoricoTransacoes()`**: Abre uma janela (`JDialog`) que exibe o histórico de transações do usuário em uma tabela.

- **`abrirHistoricoApostas()`**: Abre uma janela (`JDialog`) que exibe o histórico de apostas do usuário em uma tabela.

- **`voltarParaJanelaPrincipal()`**: Atualiza o usuário e o saldo na `JanelaPrincipal`, fecha o painel do usuário e reabre a `JanelaPrincipal`.

## Exceções e Mensagens

- **`NumberFormatException`**: Tratamento para valores inválidos de depósito ou saque.
- **`ConsultaException`**: Tratamento para erros ao consultar informações no banco de dados.
- **`AtualizacaoException`**: Tratamento para erros ao atualizar informações no banco de dados.
- **`InsercaoException`**: Tratamento para erros ao inserir transações no banco de dados.


## Observações

- **Interface Gráfica**: Utiliza o `GridBagLayout` para a disposição dos componentes na janela.
- **Estilo dos Componentes**: Botões e rótulos são estilizados com cores e fontes específicas para manter a consistência visual.
- **Validação de Entrada**: Inclui validação para valores de depósito e saque para garantir que sejam positivos e corretos.
- **Atualização da `JanelaPrincipal`**: O saldo é atualizado na `JanelaPrincipal` quando há mudanças no saldo do usuário.

## Funcionalidade

- **Visualização do Perfil**: Permite visualizar o nome de usuário, e-mail e saldo atual.
- **Gestão Financeira**: Permite realizar depósitos e saques, atualizando o saldo do usuário e registrando transações.
- **Histórico de Transações**: Fornece um histórico detalhado das transações realizadas pelo usuário.
- **Histórico de Apostas**: Exibe o histórico de apostas feitas pelo usuário, incluindo detalhes das apostas e os palpites associados.
- **Navegação**: Oferece a opção de voltar para a janela principal, atualizando as informações do usuário e do saldo.



A classe `UsuarioPanel` fornece uma interface para a visualização e gestão de informações do perfil do usuário, além de funcionalidades para realizar operações financeiras e acessar históricos relevantes.

