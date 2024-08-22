package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Usuario;

public class UsuarioPanel extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtUsername;
    private JTextField txtEmail;
    private JTextField txtBalance;
    private Usuario usuarioLogado;

    public UsuarioPanel() {
        initialize();
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        txtUsername.setText(usuario.getUsername());
        txtEmail.setText(usuario.getEmail());
        txtBalance.setText(String.valueOf(usuario.getBalance()));
    }

    private void initialize() {
        setTitle("Perfil do Usuário");
        setBounds(100, 100, 800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80));
        setMinimumSize(new Dimension(600, 400));

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 49, 63));
        panel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        getContentPane().add(panel, gbc);

        JLabel lblTitle = new JLabel("Perfil do Usuário");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        panel.add(lblTitle);

        JButton btnEdit = new JButton("Editar Informações");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        getContentPane().add(btnEdit, gbc);
        btnEdit.addActionListener(e -> {
            // Implementar ação para editar informações
        });

        JButton btnSaqueDeposito = new JButton("Saque/Depósito");
        gbc.gridx = 1;
        gbc.gridy = 1;
        getContentPane().add(btnSaqueDeposito, gbc);
        btnSaqueDeposito.addActionListener(e -> {
            // Implementar ação para saque e depósito
        });

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        getContentPane().add(lblUsername, gbc);

        txtUsername = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 2;
        getContentPane().add(txtUsername, gbc);
        txtUsername.setColumns(20);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        getContentPane().add(lblEmail, gbc);

        txtEmail = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        getContentPane().add(txtEmail, gbc);
        txtEmail.setColumns(20);

        JLabel lblBalance = new JLabel("Saldo:");
        lblBalance.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        getContentPane().add(lblBalance, gbc);

        txtBalance = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        getContentPane().add(txtBalance, gbc);
        txtBalance.setColumns(20);

        JButton btnHistoricoApostas = new JButton("Histórico de Apostas");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        getContentPane().add(btnHistoricoApostas, gbc);
        btnHistoricoApostas.addActionListener(e -> {
            // Implementar ação para mostrar o histórico de apostas
        });

        // Espaço para Grafico
        JPanel panelGrafico = new JPanel();
        panelGrafico.setBackground(new Color(52, 73, 94));
        panelGrafico.setPreferredSize(new Dimension(300, 200)); 
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        getContentPane().add(panelGrafico, gbc);

        JButton btnHistoricoTransacoes = new JButton("Histórico de Transações");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        getContentPane().add(btnHistoricoTransacoes, gbc);
        btnHistoricoTransacoes.addActionListener(e -> {
            // Implementar ação para mostrar o histórico de transações
        });

        // Espaço para Gráfico de Transações
        JPanel panelGraficoTransacoes = new JPanel();
        panelGraficoTransacoes.setBackground(new Color(52, 73, 94));
        panelGraficoTransacoes.setPreferredSize(new Dimension(300, 200)); 
        gbc.gridx = 1;
        gbc.gridy = 7;
        getContentPane().add(panelGraficoTransacoes, gbc);

        JButton btnBack = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        getContentPane().add(btnBack, gbc);
        btnBack.addActionListener(e -> {
            dispose(); 
        });
    }
}
