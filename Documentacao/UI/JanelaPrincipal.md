# Classe `JanelaPrincipal`

A classe `JanelaPrincipal` é uma janela principal (`JFrame`) em uma aplicação gráfica Java para uma casa de apostas. Ela exibe informações do usuário, lista de partidas, carrinho de apostas, e permite a confirmação de apostas. 

## Atributos

- **`usuarioLogado`**: `Usuario` - O usuário atualmente logado na aplicação.
- **`lblUsuario`**: `JLabel` - Rótulo que exibe o nome do usuário logado.
- **`lblSaldo`**: `JLabel` - Rótulo que exibe o saldo do usuário logado.
- **`lblCarrinhoInfo`**: `JLabel` - Rótulo que exibe informações sobre o carrinho de apostas.
- **`lblMultiplicacaoOdds`**: `JLabel` - Rótulo que exibe a multiplicação das odds selecionadas.
- **`txtValorAposta`**: `JTextField` - Campo de texto para o valor da aposta.
- **`btnConfirmarAposta`**: `JButton` - Botão para confirmar a aposta.
- **`cbResultadoPartida`**: `JComboBox<ResultadoPartida>` - ComboBox para selecionar o resultado da partida.
- **`partidaService`**: `PartidaService` - Serviço para gerenciar partidas.
- **`apostaService`**: `ApostaService` - Serviço para gerenciar apostas.
- **`carrinhoApostas`**: `List<PartidaSelecionada>` - Lista de apostas selecionadas no carrinho.
- **`panelCentral`**: `JPanel` - Painel central para exibir partidas.
- **`panelCarrinho`**: `JPanel` - Painel inferior para o carrinho de apostas.
- **`panelApostasPendentes`**: `JPanel` - Painel para exibir apostas pendentes.
- **`btnPerfil`**: `JButton` - Botão para abrir o perfil do usuário.
- **`btnRefresh`**: `JButton` - Botão para atualizar as partidas.
- **`btnLogout`**: `JButton` - Botão para fazer logout.
- **`panelListaPalpites`**: `JPanel` - Painel para listar os palpites selecionados.

### Classe Interna

- **`PartidaSelecionada`**: Classe que representa uma partida selecionada no carrinho de apostas.

  - **Atributos**:
    - `partida`: `Partida` - A partida associada.
    - `oddSelecionada`: `double` - A odd selecionada.
    - `resultadoSelecionado`: `ResultadoPartida` - O resultado selecionado.

  - **Construtor**:
    - `PartidaSelecionada(Partida partida, double oddSelecionada, ResultadoPartida resultadoSelecionado)`

## Construtores

- **`JanelaPrincipal()`**: Construtor padrão que inicializa os serviços e carrega partidas.
- **`JanelaPrincipal(ApostaService apostaService)`**: Construtor que permite passar um serviço de apostas personalizado.

## Métodos

- **`setUsuario(Usuario usuario)`**: Define o usuário logado e atualiza a interface do usuário.

- **`initialize()`**: Inicializa a interface gráfica, configura painéis, botões, e outras componentes.

- **`atualizarSaldoUI()`**: Atualiza o rótulo de saldo com o saldo do usuário logado.

- **`abrirUsuarioPanel()`**: Abre o painel de perfil do usuário.

- **`carregarPartidas()`**: Carrega e exibe partidas na interface. (Nota: A implementação de exemplo deve ser substituída por uma chamada real ao serviço.)

- **`padronizarBotao(String texto)`**: Cria e retorna um botão com estilo padrão.

- **`createOddButton(double oddValue, Partida partida, String tipo, ResultadoPartida resultado)`**: Cria um botão para uma odd específica e adiciona ação para adicionar a partida ao carrinho.

- **`partidaJaNoCarrinho(Partida partida)`**: Verifica se a partida já está no carrinho.

- **`adicionarAoCarrinho(Partida partida, double oddValue, ResultadoPartida resultado)`**: Adiciona uma partida ao carrinho de apostas.

- **`removerDoCarrinho(Partida partida)`**: Remove uma partida do carrinho de apostas.

- **`atualizarCarrinho()`**: Atualiza a visualização do carrinho de apostas, incluindo informações e lista de palpites.

- **`atualizarValorRetorno()`**: Atualiza o valor de retorno estimado com base no valor da aposta e nas odds selecionadas.

- **`confirmarAposta()`**: Confirma a aposta, verifica saldo e cria a aposta.

- **`atualizarApostasPendentes(Usuario usuario)`**: Atualiza o painel de apostas pendentes com as apostas associadas ao usuário.

- **`mostrarDetalhesAposta(Aposta aposta)`**: Mostra os detalhes de uma aposta selecionada.

## Exceções e Mensagens

- **`NumberFormatException`**: Tratamento para valores inválidos de aposta.
- **`Exception`**: Tratamento genérico para erros ao criar apostas.

A classe `JanelaPrincipal` combina funcionalidades de exibição e interação, com métodos que manipulam apostas, partidas, e a interface gráfica de forma a proporcionar uma experiência de usuário completa e funcional.

## Observações

- A `JanelaPrincipal` deve ser instanciada com um objeto `Usuario`, que representa o usuário logado. Esse objeto é usado para personalizar a interface de acordo com as informações do usuário.
- A janela principal inclui funcionalidades para visualizar partidas, histórico de apostas e transações, e para realizar operações relacionadas a apostas e transações.
- A classe utiliza diversos serviços (`PartidaService`, `ApostaService`) para gerenciar e exibir informações de partidas e apostas.
- O painel central é atualizado dinamicamente com informações sobre partidas e o carrinho de apostas é gerenciado em tempo real, permitindo ao usuário confirmar apostas com base nas odds e resultados selecionados.
- É essencial tratar possíveis exceções, como `NumberFormatException`, para garantir a robustez da aplicação, especialmente ao lidar com valores de aposta e operações financeiras.
