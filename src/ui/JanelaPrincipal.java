
package ui;

import config.AppContext;
import dao.ApostaDaoPostgreSQL;
import dao.PalpiteDaoPostgreSQL;
import dao.UsuarioDaoPostgreSQL;
import java.awt.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import model.Aposta;
import model.Palpite;
import model.Partida;
import model.ResultadoPartida;
import model.Usuario;
import service.ApostaService;
import service.FootballApiService;
import service.PartidaService;

public class JanelaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private Usuario usuarioLogado;
    private JLabel lblUsuario;
    private JLabel lblSaldo;
    private JLabel lblCarrinhoInfo;
    private JLabel lblMultiplicacaoOdds;
    private JTextField txtValorAposta;
    private JButton btnConfirmarAposta;
    private JComboBox<ResultadoPartida> cbResultadoPartida;
    private PartidaService partidaService;
    private ApostaService apostaService;
    private List<PartidaSelecionada> carrinhoApostas;
    private JPanel panelCentral;
    private JPanel panelCarrinho;
    private JPanel panelApostasPendentes;
    private JButton btnPerfil;  // Botão de perfil do usuário
    private JButton btnRefresh;
    private JButton btnLogout;
    private JPanel panelListaPalpites; // Novo painel para lista de palpites
    
    
    private static class PartidaSelecionada {
        Partida partida;
        double oddSelecionada;
        ResultadoPartida resultadoSelecionado;

        PartidaSelecionada(Partida partida, double oddSelecionada, ResultadoPartida resultadoSelecionado) {
            this.partida = partida;
            this.oddSelecionada = oddSelecionada;
            this.resultadoSelecionado = resultadoSelecionado;
        }
    }

    public JanelaPrincipal() {
        ApostaDaoPostgreSQL apostaDao = new ApostaDaoPostgreSQL(); 
        PalpiteDaoPostgreSQL palpiteDao = new PalpiteDaoPostgreSQL(); 
        String apiKey = AppContext.API_KEY;
        String apiHost = AppContext.API_HOST;
        FootballApiService footballApiService = new FootballApiService(apiKey, apiHost);
        PartidaService partidaService = new PartidaService(); 
        
        this.apostaService = new ApostaService(apostaDao, palpiteDao, footballApiService, partidaService, new UsuarioDaoPostgreSQL());
        this.partidaService = new PartidaService();
        this.carrinhoApostas = new ArrayList<>();
        initialize();
        carregarPartidas();
        
        if (usuarioLogado != null) {
            atualizarApostasPendentes(usuarioLogado);
        }
        
    }

    public JanelaPrincipal(ApostaService apostaService) {
        this.partidaService = new PartidaService();
        this.apostaService = apostaService;
        this.carrinhoApostas = new ArrayList<>();
        initialize();
        carregarPartidas();
        if (usuarioLogado != null) {
            atualizarApostasPendentes(usuarioLogado);
        }
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        updateUIForLoggedUser();
        
    }

    private void initialize() {
        setTitle("Casa de Apostas");
        setBounds(100, 100, 1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

     // Painel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelSuperior.setBackground(new Color(34, 49, 63));
        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Painel de informações do usuário
        JPanel panelUsuario = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelUsuario.setOpaque(false);
        panelSuperior.add(panelUsuario, BorderLayout.WEST);

        lblUsuario = new JLabel("Usuário: ");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsuario.setForeground(Color.WHITE);
        panelUsuario.add(lblUsuario);

        lblSaldo = new JLabel("Saldo: 0.00");
        lblSaldo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSaldo.setForeground(Color.WHITE);
        panelUsuario.add(lblSaldo);

        // Título centralizado
        JLabel lblTitle = new JLabel("Casa de Apostas", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        panelSuperior.add(lblTitle, BorderLayout.CENTER);

        // Painel de botões
        JPanel panelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panelBotoes.setOpaque(false);
        panelSuperior.add(panelBotoes, BorderLayout.EAST);

        btnPerfil = padronizarBotao("Perfil");
        btnPerfil.setPreferredSize(new Dimension(100, 50));
        btnPerfil.addActionListener(e -> abrirUsuarioPanel());
        panelBotoes.add(btnPerfil);

        btnRefresh = padronizarBotao("Refresh");
        btnRefresh.setPreferredSize(new Dimension(100, 50));
        btnRefresh.addActionListener(e -> carregarPartidas());
        panelBotoes.add(btnRefresh);
        
        btnLogout = padronizarBotao("Logout");
        btnLogout.setPreferredSize(new Dimension(100, 50));
        btnLogout.addActionListener(e -> logout());
        panelBotoes.add(btnLogout);

        panelCentral = new JPanel();
        panelCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCentral.setBackground(new Color(44, 62, 80));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        getContentPane().add(new JScrollPane(panelCentral), BorderLayout.CENTER);

        // Panel Carrinho (principal)
        panelCarrinho = new JPanel(new BorderLayout());
        panelCarrinho.setBorder(new EmptyBorder(10, 10, 10, 10));  // Margens internas
        panelCarrinho.setBackground(new Color(34, 49, 63));  // Fundo escuro similar aos botões
        getContentPane().add(panelCarrinho, BorderLayout.SOUTH);

        // Panel Carrinho (esquerda)
        JPanel panelCarrinhoLeft = new JPanel(new BorderLayout());
        panelCarrinhoLeft.setOpaque(false);  // Torna o painel transparente para manter o fundo do panelCarrinho
        panelCarrinho.add(panelCarrinhoLeft, BorderLayout.WEST);

        // Label de Informações do Carrinho
        lblCarrinhoInfo = new JLabel("Carrinho de Apostas: 0 odds selecionadas");
        lblCarrinhoInfo.setFont(new Font("Arial", Font.BOLD, 16));  // Mantém a consistência com os botões
        lblCarrinhoInfo.setForeground(Color.WHITE);  // Cor de texto branco para maior contraste
        panelCarrinhoLeft.add(lblCarrinhoInfo, BorderLayout.NORTH);

        // Panel de Lista de Palpites (parte central)
        panelListaPalpites = new JPanel();
        panelListaPalpites.setLayout(new BoxLayout(panelListaPalpites, BoxLayout.Y_AXIS));
        panelListaPalpites.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.WHITE, 1), 
            "Palpites Selecionados", 
            TitledBorder.DEFAULT_JUSTIFICATION, 
            TitledBorder.DEFAULT_POSITION, 
            new Font("Arial", Font.BOLD, 14), 
            Color.WHITE  // Título em branco
        ));  // Borda com título branco
        
        
        panelListaPalpites.revalidate();
        panelListaPalpites.repaint();
        // Define o tamanho mínimo e preferido para o painel de palpites
        panelListaPalpites.setPreferredSize(new Dimension(300, 200)); // Ajuste a largura e altura conforme necessário
        panelListaPalpites.setMinimumSize(new Dimension(300, 100)); // Define o tamanho mínimo

        // Adiciona o painel de palpites ao JScrollPane
        JScrollPane scrollPanePalpites = new JScrollPane(panelListaPalpites);
        scrollPanePalpites.setBorder(BorderFactory.createLineBorder(Color.WHITE));  // Borda branca ao redor do scroll
        scrollPanePalpites.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER); // Sempre mostra a barra de rolagem vertical
        scrollPanePalpites.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Não mostra a barra de rolagem horizontal
        scrollPanePalpites.getViewport().setBackground(new Color(34, 49, 63));  // Fundo do viewport do scroll

        // Adiciona o JScrollPane ao painel carrinho esquerdo
        panelCarrinhoLeft.add(scrollPanePalpites, BorderLayout.CENTER);
        
        panelCarrinhoLeft.revalidate();
        panelCarrinhoLeft.repaint();
        panelListaPalpites.setBackground(new Color(34, 49, 63));  // Fundo igual ao panelCarrinho
        panelListaPalpites.setForeground(Color.WHITE);  // Cor de texto branco

        lblMultiplicacaoOdds = new JLabel("Multiplicação de Odds: 1.0");
        lblMultiplicacaoOdds.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMultiplicacaoOdds.setForeground(Color.WHITE);
        panelCarrinho.add(lblMultiplicacaoOdds, BorderLayout.CENTER);

        JPanel panelAposta = new JPanel(new FlowLayout());
        panelAposta.setBackground(new Color(34, 49, 63));
        panelCarrinho.add(panelAposta, BorderLayout.SOUTH);

        JLabel lblValorAposta = new JLabel("Valor da Aposta:");
        lblValorAposta.setForeground(Color.WHITE);
        panelAposta.add(lblValorAposta);

        txtValorAposta = new JTextField();
        txtValorAposta.setPreferredSize(new Dimension(200, 40));
        panelAposta.add(txtValorAposta);

        btnConfirmarAposta = padronizarBotao("Possível retorno: 0.00");
        panelAposta.add(btnConfirmarAposta);

        txtValorAposta.addActionListener(e -> atualizarValorRetorno());

        btnConfirmarAposta.addActionListener(e -> confirmarAposta());

        panelApostasPendentes = new JPanel();
        panelApostasPendentes.setLayout(new BoxLayout(panelApostasPendentes, BoxLayout.Y_AXIS));
        panelApostasPendentes.setBorder(BorderFactory.createTitledBorder("Apostas Pendentes"));
        panelApostasPendentes.setPreferredSize(new Dimension(300, 700));
        getContentPane().add(new JScrollPane(panelApostasPendentes), BorderLayout.EAST);
    }
    
    public void atualizarSaldoUI() {
        if (usuarioLogado != null) {
            lblSaldo.setText("Saldo: " + String.format("%.2f", usuarioLogado.getBalance()));
        }
    }
    
    private void abrirUsuarioPanel() {
        if (usuarioLogado != null) {
            UsuarioPanel usuarioPanel = new UsuarioPanel(this);  
            usuarioPanel.setUsuario(usuarioLogado);
            usuarioPanel.setVisible(true);
            this.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum usuário logado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void carregarPartidas() {
        panelCentral.removeAll();
     // TODO retirar a implementação abaixo:
        List<Partida> partidas = new ArrayList<>();

        try {
            for (int i = 0; i < 10; i++) {
                String id = String.valueOf(i);
                String teamHome = "TeamHome" + i;
                URL teamHomeLogo = new URL("http://example.com/logo" + i);
                String teamAway = "TeamAway" + i;
                URL teamAwayLogo = new URL("http://example.com/logo" + i);
                OffsetDateTime data = OffsetDateTime.now(ZoneOffset.UTC);
                String status = "Status" + i;
                ResultadoPartida resultado = ResultadoPartida.HOME_WIN; // or any other value
                float homeWinOdd = 1.0f + i;
                float awayWinOdd = 2.0f + i;
                float drawOdd = 3.0f + i;

                Partida partida = new Partida(id, teamHome, teamHomeLogo, teamAway, teamAwayLogo, data, status, resultado, homeWinOdd, awayWinOdd, drawOdd);
                partidas.add(partida);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // TODO retirar a implementação acima e descomentar a função abaixo

        //List<Partida> partidas = partidaService.BuscarPartidasPorDia("71", "2024", "2024-08-25", "America/Bahia", "3");

        for (Partida partida : partidas) {
            // Painel principal para cada partida
            JPanel card = new JPanel(new GridBagLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            card.setBackground(new Color(224, 224, 224));
            card.setMaximumSize(new Dimension(900, 150));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            // Informações dos times e data
            JLabel lblTimeCasa = new JLabel(partida.getTeamHome(), SwingConstants.CENTER);
            JLabel lblTimeFora = new JLabel(partida.getTeamAway(), SwingConstants.CENTER);
            JLabel lblData = new JLabel(partida.getData().toLocalDateTime().toString(), SwingConstants.CENTER);
            JLabel lblStatus = new JLabel(partida.getStatus(), SwingConstants.CENTER);

            // Configurações de GridBagConstraints para posicionar corretamente os componentes
            gbc.gridx = 0;
            gbc.gridy = 0;
            card.add(lblTimeCasa, gbc);

            gbc.gridx = 1;
            gbc.gridy = 0;
            card.add(lblData, gbc);

            gbc.gridx = 2;
            gbc.gridy = 0;
            card.add(lblTimeFora, gbc);

            gbc.gridx = 3;
            gbc.gridy = 0;
            card.add(lblStatus, gbc);

            // Botões de odds para a partida
            JButton btnOddCasa = createOddButton(partida.getHomeWinOdd(), partida, "Casa", ResultadoPartida.HOME_WIN);
            JButton btnOddEmpate = createOddButton(partida.getDrawOdd(), partida, "Empate", ResultadoPartida.DRAW);
            JButton btnOddFora = createOddButton(partida.getAwayWinOdd(), partida, "Fora", ResultadoPartida.AWAY_WIN);

            // Adiciona os botões no painel com espaçamento adequado
            gbc.gridx = 0;
            gbc.gridy = 1;
            card.add(btnOddCasa, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            card.add(btnOddEmpate, gbc);

            gbc.gridx = 2;
            gbc.gridy = 1;
            card.add(btnOddFora, gbc);

            panelCentral.add(card);
        }

        panelCentral.revalidate();
        panelCentral.repaint();
    }
    
    public JButton padronizarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setBackground(new Color(41, 128, 185));  // Cor de fundo
        botao.setForeground(Color.WHITE);  // Cor do texto
        botao.setFocusPainted(false);  // Remover borda de foco
        botao.setBorderPainted(false);  // Remover borda
        botao.setPreferredSize(new Dimension(200, 40));  // Tamanho do botão 
        botao.setFont(new Font("Arial", Font.BOLD, 16));  // Fonte e estilo
        return botao;
    }


    private JButton createOddButton(double oddValue, Partida partida, String tipo, ResultadoPartida resultado) {
        JButton btnOdd = padronizarBotao(String.valueOf(oddValue));

        btnOdd.setPreferredSize(new Dimension(100, 50));
        btnOdd.addActionListener(e -> {
            if (partidaJaNoCarrinho(partida)) {
                JOptionPane.showMessageDialog(JanelaPrincipal.this, 
                    "Não é possível adicionar dois palpites da mesma partida.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            } else {
                removerDoCarrinho(partida);
                adicionarAoCarrinho(partida, oddValue, resultado);
            }
        });

        return btnOdd;
    }

    private boolean partidaJaNoCarrinho(Partida partida) {
        return carrinhoApostas.stream()
                .anyMatch(selecionada -> selecionada.partida.equals(partida));
    }

    private void adicionarAoCarrinho(Partida partida, double oddValue, ResultadoPartida resultado) {
        carrinhoApostas.add(new PartidaSelecionada(partida, oddValue, resultado));
        atualizarCarrinho();
    }

    private void removerDoCarrinho(Partida partida) {
        carrinhoApostas.removeIf(selecionada -> selecionada.partida.equals(partida));
        atualizarCarrinho();
    }

    private void atualizarCarrinho() {
        lblCarrinhoInfo.setText("Carrinho de Apostas: " + carrinhoApostas.size() + " odds selecionadas");

        double multiplicacaoOdds = carrinhoApostas.stream()
                .mapToDouble(selecionada -> selecionada.oddSelecionada)
                .reduce(1, (a, b) -> a * b);

        lblMultiplicacaoOdds.setText("Multiplicação de Odds: " + multiplicacaoOdds);
        atualizarValorRetorno();

        // Atualizar lista de palpites
        panelListaPalpites.removeAll();
        for (PartidaSelecionada selecionada : carrinhoApostas) {
            JPanel painelPalpite = new JPanel(new BorderLayout());
            painelPalpite.setOpaque(false);

            JLabel lblPalpite = new JLabel(String.format("%s vs %s - Odd: %.2f - Resultado: %s",
                    selecionada.partida.getTeamHome(),
                    selecionada.partida.getTeamAway(),
                    selecionada.oddSelecionada,
                    selecionada.resultadoSelecionado));
            lblPalpite.setForeground(Color.WHITE);
            painelPalpite.add(lblPalpite, BorderLayout.CENTER);

            JButton btnExcluir = new JButton("Excluir");
            btnExcluir.addActionListener(e -> {
                removerDoCarrinho(selecionada.partida);
            });
            painelPalpite.add(btnExcluir, BorderLayout.EAST);

            panelListaPalpites.add(painelPalpite);
        }

        panelListaPalpites.revalidate();
        panelListaPalpites.repaint();
    }

    private void atualizarValorRetorno() {
        try {
            double valorAposta = Double.parseDouble(txtValorAposta.getText());
            double multiplicacaoOdds = carrinhoApostas.stream()
                    .mapToDouble(selecionada -> selecionada.oddSelecionada)
                    .reduce(1, (a, b) -> a * b);
            double retorno = valorAposta * multiplicacaoOdds;
            btnConfirmarAposta.setText("Possível retorno: " + String.format("%.2f", retorno));
        } catch (NumberFormatException e) {
            btnConfirmarAposta.setText("Possível retorno: 0.00");
        }
    }

    private void confirmarAposta() {
        try {
            BigDecimal valorAposta = new BigDecimal(txtValorAposta.getText());

            // Verificar se o valor da aposta é válido
            if (valorAposta.compareTo(BigDecimal.ZERO) <= 0) {
                JOptionPane.showMessageDialog(this, "O valor da aposta deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Converta o saldo do usuário de double para BigDecimal
            BigDecimal saldoUsuario = usuarioLogado.getBalance();

            // Verificar se o usuário tem saldo suficiente para realizar a aposta
            if (valorAposta.compareTo(saldoUsuario) > 0) {
                JOptionPane.showMessageDialog(this, "Saldo insuficiente para realizar a aposta.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Palpite> palpites = new ArrayList<>();
            Integer apostaId = null;

            for (PartidaSelecionada selecionada : carrinhoApostas) {
                int partidaId = Integer.parseInt(selecionada.partida.getId());

                Palpite palpite = new Palpite(
                    partidaId,                          
                    apostaId,                           
                    selecionada.resultadoSelecionado,   
                    BigDecimal.valueOf(selecionada.oddSelecionada)  
                );
                palpites.add(palpite);
            }

            Aposta aposta = new Aposta(
                usuarioLogado.getId(),
                valorAposta,  
                palpites,
                "America/Bahia"
            );

            apostaService.criarAposta(aposta, palpites);

            // Subtrair o valor da aposta do saldo do usuário
            usuarioLogado.setBalance(saldoUsuario.subtract(valorAposta));

            // Atualizar a interface com o novo saldo
            updateUIForLoggedUser();

            JOptionPane.showMessageDialog(this, "Aposta realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carrinhoApostas.clear();  // Limpa o carrinho após a aposta
            atualizarCarrinho();
            
            // Atualiza o painel de apostas pendentes com o usuário logado
            atualizarApostasPendentes(usuarioLogado);  
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido para a aposta.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao criar a aposta.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }


    private void atualizarApostasPendentes(Usuario usuario) {
        // Limpa o painel antes de adicionar apostas pendentes
        panelApostasPendentes.removeAll();

        // Obtém todas as apostas pendentes do banco de dados
        List<Aposta> apostasPendentes = apostaService.getApostasPendentes(usuario);

        for (Aposta aposta : apostasPendentes) {
            JPanel painelAposta = new JPanel(new BorderLayout());
            painelAposta.setOpaque(false);

            JLabel lblAposta = new JLabel("Aposta ID: " + aposta.getId() + " - Valor: " + aposta.getAmount());
            lblAposta.setFont(new Font("Arial", Font.PLAIN, 14));
            lblAposta.setForeground(new Color(41, 128, 185));
            painelAposta.add(lblAposta, BorderLayout.CENTER);

            painelAposta.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    mostrarDetalhesAposta(aposta);
                }
            });

            panelApostasPendentes.add(painelAposta);
        }

        panelApostasPendentes.revalidate();
        panelApostasPendentes.repaint();
    }

    private void mostrarDetalhesAposta(Aposta aposta) {
        List<Palpite> palpites = apostaService.getPalpitesDeUmaAposta(aposta);
        
        if (palpites != null && !palpites.isEmpty()) {
            // Cria um painel para exibir detalhes da aposta
            JPanel panelDetalhes = new JPanel();
            panelDetalhes.setLayout(new BoxLayout(panelDetalhes, BoxLayout.Y_AXIS));
            panelDetalhes.setBorder(BorderFactory.createTitledBorder("Detalhes da Aposta ID: " + aposta.getId()));
            
            for (Palpite palpite : palpites) {
                JPanel panelPalpite = new JPanel(new GridLayout(1, 3));
                
                JLabel lblResultado = new JLabel("Palpite: " + palpite.getResultado());
                JLabel lblOdd = new JLabel(" Odd: " + palpite.getOdd());
                
                panelPalpite.add(lblResultado);
                panelPalpite.add(lblOdd);
                
                panelDetalhes.add(panelPalpite);
            }

            // Cria uma caixa de diálogo para exibir os detalhes
            JOptionPane.showMessageDialog(this, panelDetalhes, "Detalhes da Aposta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum detalhe disponível para esta aposta.", "Detalhes da Aposta", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    
    private void logout() {
        // Limpar dados do usuário
        usuarioLogado = null;
        updateUIForLoggedUser();
        
        // Fechar a Janela Principal
        this.dispose();
        
        // Abrir o LoginPanel
        SwingUtilities.invokeLater(() -> {
            // Cria um novo JFrame para o LoginPanel
            JFrame loginFrame = new JFrame("Login");
            loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            loginFrame.setSize(450, 300);
            loginFrame.setLocationRelativeTo(null); // Centraliza a janela na tela
            
            // Instancia o LoginPanel com o novo JFrame como argumento
            LoginPanel loginPanel = new LoginPanel(loginFrame);
            loginFrame.setContentPane(loginPanel);
            
            // Torna a janela visível
            loginFrame.setVisible(true);
        });
    }


    private void updateUIForLoggedUser() {
        if (usuarioLogado != null) {
            atualizarApostasPendentes(usuarioLogado);
            atualizarSaldoUI();
            lblUsuario.setText("Usuário: " + usuarioLogado.getUsername());
            lblSaldo.setText("Saldo: " + String.format("%.2f", usuarioLogado.getBalance()));
        } else {
            lblUsuario.setText("Nenhum usuário logado");
            lblSaldo.setText("Saldo: 0.00");
        }
    }
}
