package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.math.BigDecimal;

import model.Partida;
import model.Usuario;
import model.Palpite;
import model.Aposta;
import model.ResultadoPartida;
import service.PartidaService;
import service.ApostaService;
import service.FootballApiService;
import dao.PalpiteDaoPostgreSQL;
import dao.ApostaDaoPostgreSQL;
import config.AppContext;

public class JanelaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private Usuario usuarioLogado;
    private JLabel lblUsuario;
    private JLabel lblSaldo;
    private JLabel lblCarrinhoInfo;
    private JLabel lblMultiplicacaoOdds;
    private JTextField txtValorAposta;
    private JButton btnConfirmarAposta;
    private JComboBox<ResultadoPartida> cbResultadoPartida;  // Adiciona uma combobox para o resultado
    private PartidaService partidaService;
    private ApostaService apostaService;
    private List<PartidaSelecionada> carrinhoApostas;
    private JPanel panelCentral;
    private JPanel panelCarrinho;
    private JPanel panelApostasPendentes;  // Adiciona painel para apostas pendentes
    
    
    private static class PartidaSelecionada {
        Partida partida;
        double oddSelecionada;
        ResultadoPartida resultadoSelecionado;  // Adiciona o campo para o resultado

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
        
        this.apostaService = new ApostaService(apostaDao, palpiteDao, footballApiService, partidaService);
        this.partidaService = new PartidaService();
        this.carrinhoApostas = new ArrayList<>();
        initialize();
        carregarPartidas();
    }
    public JanelaPrincipal(ApostaService apostaService) {
        this.partidaService = new PartidaService();
        this.apostaService = apostaService;
        this.carrinhoApostas = new ArrayList<>();
        initialize();
        carregarPartidas();
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

        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelSuperior.setBackground(new Color(34, 49, 63));
        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        JLabel lblTitle = new JLabel("Casa de Apostas", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        panelSuperior.add(lblTitle, BorderLayout.CENTER);

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelUsuario.setOpaque(false);
        panelSuperior.add(panelUsuario, BorderLayout.EAST);

        lblUsuario = new JLabel("Usuário: ");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsuario.setForeground(Color.WHITE);
        panelUsuario.add(lblUsuario);

        lblSaldo = new JLabel("Saldo: 0.00");
        lblSaldo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSaldo.setForeground(Color.WHITE);
        panelUsuario.add(lblSaldo);

        // Painel central com partidas
        panelCentral = new JPanel();
        panelCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCentral.setBackground(new Color(44, 62, 80));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        getContentPane().add(new JScrollPane(panelCentral), BorderLayout.CENTER);

        // Painel de Carrinho e Aposta
        panelCarrinho = new JPanel(new BorderLayout());
        panelCarrinho.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCarrinho.setBackground(new Color(34, 49, 63));
        getContentPane().add(panelCarrinho, BorderLayout.SOUTH);

        lblCarrinhoInfo = new JLabel("Carrinho de Apostas: 0 odds selecionadas");
        lblCarrinhoInfo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCarrinhoInfo.setForeground(Color.WHITE);
        panelCarrinho.add(lblCarrinhoInfo, BorderLayout.NORTH);

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
        txtValorAposta.setPreferredSize(new Dimension(100, 30));
        panelAposta.add(txtValorAposta);

        btnConfirmarAposta = new JButton("Possível retorno: 0.00");
        btnConfirmarAposta.setPreferredSize(new Dimension(200, 40));
        panelAposta.add(btnConfirmarAposta);

        // Atualiza o valor do possível retorno ao modificar o valor da aposta
        txtValorAposta.addActionListener(e -> atualizarValorRetorno());

        btnConfirmarAposta.addActionListener(e -> confirmarAposta());

        // Adiciona o painel para exibir apostas pendentes
        panelApostasPendentes = new JPanel();
        panelApostasPendentes.setLayout(new BoxLayout(panelApostasPendentes, BoxLayout.Y_AXIS));
        panelApostasPendentes.setBorder(BorderFactory.createTitledBorder("Apostas Pendentes"));
        panelApostasPendentes.setPreferredSize(new Dimension(300, 700));  // Tamanho do painel
        getContentPane().add(new JScrollPane(panelApostasPendentes), BorderLayout.EAST);
    }

    private void carregarPartidas() {
        panelCentral.removeAll();
        List<Partida> partidas = partidaService.BuscarPartidasPorDia("71", "2024", "2024-08-25", "America/Bahia", "3");

        for (Partida partida : partidas) {
            JPanel card = new JPanel(new GridLayout(3, 4));
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            card.setBackground(new Color(224, 224, 224));
            card.setMaximumSize(new Dimension(900, 120));

            JLabel lblTimeCasa = new JLabel(partida.getTeamHome(), SwingConstants.CENTER);
            JLabel lblTimeFora = new JLabel(partida.getTeamAway(), SwingConstants.CENTER);
            JLabel lblData = new JLabel(partida.getData().toLocalDateTime().toString(), SwingConstants.CENTER);
            JLabel lblStatus = new JLabel(partida.getStatus(), SwingConstants.CENTER);

            JButton btnOddCasa = createOddButton(partida.getHomeWinOdd(), partida, "Casa", ResultadoPartida.HOME_WIN);
            JButton btnOddEmpate = createOddButton(partida.getDrawOdd(), partida, "Empate", ResultadoPartida.DRAW);
            JButton btnOddFora = createOddButton(partida.getAwayWinOdd(), partida, "Fora", ResultadoPartida.AWAY_WIN);

            card.add(lblTimeCasa);
            card.add(lblData);
            card.add(lblTimeFora);
            card.add(lblStatus);
            card.add(btnOddCasa);
            card.add(btnOddEmpate);
            card.add(btnOddFora);

            panelCentral.add(card);
        }

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    private JButton createOddButton(double oddValue, Partida partida, String tipo, ResultadoPartida resultado) {
        JButton btnOdd = new JButton(String.valueOf(oddValue));
        btnOdd.setBackground(new Color(41, 128, 185));
        btnOdd.setForeground(Color.WHITE);
        btnOdd.setFocusPainted(false);
        btnOdd.setBorderPainted(false);
        btnOdd.setPreferredSize(new Dimension(100, 50));
        btnOdd.setFont(new Font("Arial", Font.BOLD, 16));

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
            double valorAposta = Double.parseDouble(txtValorAposta.getText());
            if (valorAposta <= 0) {
                JOptionPane.showMessageDialog(this, "O valor da aposta deve ser maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
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
                BigDecimal.valueOf(valorAposta),
                palpites,
                "America/Bahia"
            );

            apostaService.criarAposta(usuarioLogado.getId(), aposta, palpites);

            JOptionPane.showMessageDialog(this, "Aposta realizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            carrinhoApostas.clear();  // Limpa o carrinho após a aposta
            atualizarCarrinho();
            atualizarApostasPendentes(aposta);  // Atualiza o painel de apostas pendentes
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Valor inválido para a aposta.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao criar a aposta.", "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void atualizarApostasPendentes(Aposta aposta) {
        JLabel lblAposta = new JLabel("Aposta ID: " + aposta.getId() + " - Valor: " + aposta.getAmount());
        lblAposta.setFont(new Font("Arial", Font.PLAIN, 14));
        panelApostasPendentes.add(lblAposta);

        panelApostasPendentes.revalidate();
        panelApostasPendentes.repaint();
    }

    private void updateUIForLoggedUser() {
        if (usuarioLogado != null) {
            lblUsuario.setText("Usuário: " + usuarioLogado.getUsername());
            lblSaldo.setText("Saldo: " + String.format("%.2f", usuarioLogado.getBalance()));
        } else {
            lblUsuario.setText("Nenhum usuário logado");
            lblSaldo.setText("Saldo: 0.00");
        }
    }
}
