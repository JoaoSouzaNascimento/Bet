package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.AuthService;
import exceptions.ConsultaException;
import exceptions.LoginException;
import model.Usuario;
import dao.UsuarioDaoPostgreSQL;

public class LoginPanel extends JPanel {

    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private AuthService authService;
    private JFrame parentFrame;

    public LoginPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.authService = new AuthService(new UsuarioDaoPostgreSQL());
        setLayout(new BorderLayout());
        setBackground(new Color(44, 62, 80));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblEmail = createStyledLabel("Email:");
        txtLogin = createStyledTextField();

        JLabel lblPassword = createStyledLabel("Password:");
        txtPassword = createStyledPasswordField();

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        formPanel.add(txtLogin, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblPassword, gbc);
        gbc.gridx = 1;
        formPanel.add(txtPassword, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);

        JButton btnLogin = createStyledButton("Login");
        btnLogin.addActionListener(e -> {
            try {
                Usuario usuario = authService.login(txtLogin.getText(), new String(txtPassword.getPassword()));
                JanelaPrincipal mainFrame = new JanelaPrincipal();
                mainFrame.setUsuario(usuario);
                parentFrame.setVisible(false);
                mainFrame.setVisible(true);
            } catch (LoginException | ConsultaException ex) {
                JOptionPane.showMessageDialog(parentFrame, ex.getMessage(), "Erro de Login", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnExit = createStyledButton("Exit");
        btnExit.addActionListener(e -> System.exit(0));

        JButton btnRegister = createStyledButton("Cadastrar-se");
        btnRegister.addActionListener(e -> {
            parentFrame.setContentPane(new CadastroPanel(parentFrame));
            parentFrame.revalidate(); // Atualiza a tela para mostrar o painel de cadastro
        });

        buttonPanel.add(btnLogin);
        buttonPanel.add(btnExit);
        buttonPanel.add(btnRegister);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setPreferredSize(new Dimension(200, 30));  // Limita o tamanho
        return textField;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setPreferredSize(new Dimension(200, 30));  // Limita o tamanho
        return passwordField;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(180, 40));
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(41, 128, 185));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        return button;
    }
}
