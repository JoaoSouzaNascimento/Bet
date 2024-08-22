package ui;

import java.awt.Color;
import java.awt.Dimension;
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
import model.Usuario;

public class JanelaPrincipal extends JFrame {
    private static final long serialVersionUID = 1L;
    private Usuario usuarioLogado;
    private JLabel lblUsuario; // Adiciona um rótulo para mostrar o nome do usuário

    public JanelaPrincipal() {
        initialize();
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        updateUIForLoggedUser(); 
    }

    private void initialize() {
        setTitle("Casa de Apostas");
        setBounds(100, 100, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(44, 62, 80));
        setMinimumSize(new Dimension(600, 400)); 

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(34, 49, 63));
        titlePanel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3; 
        gbc.weightx = 1; 
        getContentPane().add(titlePanel, gbc);

        JLabel lblTitle = new JLabel("Casa de Apostas");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        titlePanel.add(lblTitle);

        lblUsuario = new JLabel();
        lblUsuario.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsuario.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridwidth = 3; 
        getContentPane().add(lblUsuario, gbc);

        JButton btnCadastro = createStyledButton("Cadastro");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; 
        getContentPane().add(btnCadastro, gbc);
        btnCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CadastroPanel cadastroPanel = new CadastroPanel();
                cadastroPanel.setVisible(true);
            }
        });

        JButton btnSair = createStyledButton("Sair");
        gbc.gridx = 1;
        gbc.gridy = 2;
        getContentPane().add(btnSair, gbc);
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); 
            }
        });

        JButton btnVerTimes = createStyledButton("Ver Times");
        gbc.gridx = 0;
        gbc.gridy = 3;
        getContentPane().add(btnVerTimes, gbc);
        btnVerTimes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Função Ver Times não implementada");
            }
        });

        JButton btnVerApostas = createStyledButton("Ver Apostas");
        gbc.gridx = 1;
        gbc.gridy = 3;
        getContentPane().add(btnVerApostas, gbc);
        btnVerApostas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Função Ver Apostas não implementada");
            }
        });

        JButton btnPerfilUsuario = createStyledButton("Perfil do Usuário");
        gbc.gridx = 2;
        gbc.gridy = 3;
        getContentPane().add(btnPerfilUsuario, gbc);
        btnPerfilUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (usuarioLogado != null) {
                    UsuarioPanel usuarioPanel = new UsuarioPanel();
                    usuarioPanel.setUsuario(usuarioLogado); // Passa o usuário para a UsuarioPanel
                    usuarioPanel.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(JanelaPrincipal.this, "Usuário não está logado.");
                }
            }
        });
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 40));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(41, 128, 185));
        button.setFocusPainted(false);
        button.setBorderPainted(false);

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

    private void updateUIForLoggedUser() {
        if (usuarioLogado != null) {
            lblUsuario.setText("Usuário logado: " + usuarioLogado.getUsername());
        } else {
            lblUsuario.setText("Nenhum usuário logado");
        }
    }
}
