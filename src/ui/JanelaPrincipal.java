package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;
import model.Partida;
import model.Usuario;
import service.PartidaService;

public class JanelaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private Usuario usuarioLogado;
    private JLabel lblUsuario;
    private JLabel lblSaldo;
    private JLabel lblCarrinhoInfo;
    private JLabel lblMultiplicacaoOdds;
    private PartidaService partidaService;
    private List<PartidaSelecionada> carrinhoApostas;
    private JPanel panelCentral;
    private JButton btnPerfil;

    // Classe auxiliar para armazenar a partida e a odd selecionada
    private static class PartidaSelecionada {
        Partida partida;
        double oddSelecionada;

        PartidaSelecionada(Partida partida, double oddSelecionada) {
            this.partida = partida;
            this.oddSelecionada = oddSelecionada;
        }
    }

    public JanelaPrincipal() {
        this.partidaService = new PartidaService();
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
        setBounds(100, 100, 1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Estilizando e organizando a interface
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelSuperior.setBackground(new Color(34, 49, 63));
        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Título
        JLabel lblTitle = new JLabel("Casa de Apostas", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        panelSuperior.add(lblTitle, BorderLayout.CENTER);

        JPanel panelUsuario = new JPanel();
        panelUsuario.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelUsuario.setOpaque(false);
        panelSuperior.add(panelUsuario, BorderLayout.EAST);

        // Nome do usuário e saldo
        lblUsuario = new JLabel("Usuário: ");
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsuario.setForeground(Color.WHITE);
        panelUsuario.add(lblUsuario);

        lblSaldo = new JLabel("Saldo: 0.00");
        lblSaldo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblSaldo.setForeground(Color.WHITE);
        panelUsuario.add(lblSaldo);

        // Botão Perfil do Usuário
        btnPerfil = new JButton("Perfil");
        btnPerfil.setFont(new Font("Arial", Font.PLAIN, 16));
        btnPerfil.setForeground(Color.WHITE);
        btnPerfil.setBackground(new Color(41, 128, 185));
        btnPerfil.setFocusPainted(false);
        btnPerfil.setBorderPainted(false);
        btnPerfil.setPreferredSize(new Dimension(100, 30));
        panelUsuario.add(btnPerfil);
        btnPerfil.addActionListener(e -> showUserProfile());

        // Painel inferior com botão de sair e odds do carrinho
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInferior.setBackground(new Color(34, 49, 63));
        getContentPane().add(panelInferior, BorderLayout.SOUTH);

        // Botão de sair
        JButton btnSair = createStyledButton("Sair");
        panelInferior.add(btnSair, BorderLayout.WEST);
        btnSair.addActionListener(e -> System.exit(0));

        // Informações do carrinho
        lblCarrinhoInfo = new JLabel("Carrinho de Apostas: 0 odds selecionadas");
        lblCarrinhoInfo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCarrinhoInfo.setForeground(Color.WHITE);
        panelInferior.add(lblCarrinhoInfo, BorderLayout.CENTER);

        lblMultiplicacaoOdds = new JLabel("Multiplicação de Odds: 1.0");
        lblMultiplicacaoOdds.setFont(new Font("Arial", Font.PLAIN, 16));
        lblMultiplicacaoOdds.setForeground(Color.WHITE);
        panelInferior.add(lblMultiplicacaoOdds, BorderLayout.EAST);

        // Painel central para os cartões de partidas
        panelCentral = new JPanel();
        panelCentral.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCentral.setBackground(new Color(44, 62, 80));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));  // Disposição vertical dos cartões
        getContentPane().add(new JScrollPane(panelCentral), BorderLayout.CENTER);

        // Botão de atualizar
        JButton btnAtualizar = createStyledButton("Atualizar Partidas");
        panelInferior.add(btnAtualizar, BorderLayout.SOUTH);
        btnAtualizar.addActionListener(e -> carregarPartidas());
    }

    private void carregarPartidas() {
        panelCentral.removeAll();  // Limpa os cartões antes de adicionar os novos
        List<Partida> partidas = partidaService.BuscarPartidasPorDia("71", "2024", "2024-08-25", "America/Bahia", "3");

        for (Partida partida : partidas) {
            // Cria um cartão para cada partida
            JPanel card = new JPanel(new GridLayout(2, 4));
            card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            card.setBackground(new Color(224, 224, 224));
            card.setMaximumSize(new Dimension(900, 100));  // Limite de tamanho para os cartões

            JLabel lblTimeCasa = new JLabel(partida.getTeamHome(), SwingConstants.CENTER);
            JLabel lblTimeFora = new JLabel(partida.getTeamAway(), SwingConstants.CENTER);
            JLabel lblData = new JLabel(partida.getData().toLocalDateTime().toString(), SwingConstants.CENTER);
            JLabel lblStatus = new JLabel(partida.getStatus(), SwingConstants.CENTER);

            JButton btnOddCasa = createOddButton(partida.getHomeWinOdd(), partida, "Casa");
            JButton btnOddEmpate = createOddButton(partida.getDrawOdd(), partida, "Empate");
            JButton btnOddFora = createOddButton(partida.getAwayWinOdd(), partida, "Fora");

            card.add(lblTimeCasa);
            card.add(lblData);
            card.add(lblTimeFora);
            card.add(lblStatus);
            card.add(btnOddCasa);
            card.add(btnOddEmpate);
            card.add(btnOddFora);

            panelCentral.add(card);  // Adiciona o cartão ao painel central
        }

        panelCentral.revalidate();
        panelCentral.repaint();
    }

    // Cria um botão para cada odd com a função de adicionar ao carrinho
    private JButton createOddButton(double oddValue, Partida partida, String tipo) {
        JButton btnOdd = new JButton(String.valueOf(oddValue));
        btnOdd.setBackground(new Color(41, 128, 185));
        btnOdd.setForeground(Color.WHITE);
        btnOdd.setFocusPainted(false);
        btnOdd.setBorderPainted(false);
        btnOdd.setPreferredSize(new Dimension(100, 50));
        btnOdd.setFont(new Font("Arial", Font.BOLD, 16));

        // Adiciona a funcionalidade de seleção de odds ao clicar
        btnOdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove qualquer seleção anterior dessa partida no carrinho
                removerDoCarrinho(partida);
                // Adiciona a odd selecionada ao carrinho
                adicionarAoCarrinho(partida, oddValue);
            }
        });

        return btnOdd;
    }

    private void adicionarAoCarrinho(Partida partida, double oddValue) {
        carrinhoApostas.add(new PartidaSelecionada(partida, oddValue));  // Adiciona a partida ao carrinho
        atualizarCarrinho();  // Atualiza o carrinho e calcula a multiplicação das odds
    }

    private void removerDoCarrinho(Partida partida) {
        carrinhoApostas.removeIf(selecionada -> selecionada.partida.equals(partida));  // Remove a partida do carrinho
    }

    private void atualizarCarrinho() {
        lblCarrinhoInfo.setText("Carrinho de Apostas: " + carrinhoApostas.size() + " odds selecionadas");

        // Multiplica as odds no carrinho
        double multiplicacaoOdds = carrinhoApostas.stream()
                .mapToDouble(selecionada -> selecionada.oddSelecionada)
                .reduce(1, (a, b) -> a * b);

        lblMultiplicacaoOdds.setText("Multiplicação de Odds: " + multiplicacaoOdds);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 40));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(41, 128, 185));
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(31, 97, 141));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.setBackground(new Color(41, 128, 185));
            }
        });

        return button;
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

    
    private void showUserProfile() {
        if (usuarioLogado != null) {
            UsuarioPanel usuarioPanel = new UsuarioPanel();
            usuarioPanel.setUsuario(usuarioLogado);
            usuarioPanel.setSize(400, 300);  
            usuarioPanel.setLocationRelativeTo(this);  
            usuarioPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum usuário logado.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
