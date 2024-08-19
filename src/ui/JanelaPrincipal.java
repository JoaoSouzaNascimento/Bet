package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JanelaPrincipal {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    JanelaPrincipal window = new JanelaPrincipal();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public JanelaPrincipal() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Casa de Apostas");
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(44, 62, 80)); // Cor de fundo do frame
        frame.setMinimumSize(new Dimension(400, 300)); // Tamanho mínimo da janela
        
        // Configurando o layout principal
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Painel para o título
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(34, 49, 63));
        titlePanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        frame.getContentPane().add(titlePanel, gbc);
        
        JLabel lblTitle = new JLabel("Casa de Apostas");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);
        
        // Botão Login
        JButton btnLogin = createStyledButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        frame.getContentPane().add(btnLogin, gbc);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JanelaLogin.main(null);
                frame.dispose(); // Fecha a janela principal quando a janela de login é aberta
            }
        });
        
        // Botão Cadastro
        JButton btnCadastro = createStyledButton("Cadastro");
        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.getContentPane().add(btnCadastro, gbc);
        btnCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JanelaCadastro.main(null);
            }
        });

        // Botão Sair
        JButton btnSair = createStyledButton("Sair");
        gbc.gridx = 2;
        gbc.gridy = 1;
        frame.getContentPane().add(btnSair, gbc);
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fecha a aplicação
            }
        });

        // Botão Ver Times
        JButton btnVerTimes = createStyledButton("Ver Times");
        gbc.gridx = 0;
        gbc.gridy = 2;
        frame.getContentPane().add(btnVerTimes, gbc);
        btnVerTimes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Função Ver Times não implementada");
            }
        });

        // Botão Ver Apostas
        JButton btnVerApostas = createStyledButton("Ver Apostas");
        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.getContentPane().add(btnVerApostas, gbc);
        btnVerApostas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Função Ver Apostas não implementada");
            }
        });

        // Botão Perfil do Usuário
        JButton btnPerfilUsuario = createStyledButton("Perfil do Usuário");
        gbc.gridx = 2;
        gbc.gridy = 2;
        frame.getContentPane().add(btnPerfilUsuario, gbc);
        btnPerfilUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Função Perfil do Usuário não implementada");
            }
        });
    }

    // Função para criar botões estilizados com efeitos de hover
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 40));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(41, 128, 185));
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        // Animação hover - cor muda quando o mouse está sobre o botão
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(31, 97, 141));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(41, 128, 185));
            }
        });

        return button;
    }
}
