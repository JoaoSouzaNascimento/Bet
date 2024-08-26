package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.AuthService;
import model.Usuario;
import model.CargoUsuario;
import dao.UsuarioDaoPostgreSQL;
import exceptions.CadastroException;

public class CadastroPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPasswordField passwordTeste;
    private JPasswordField passwordSalvar;
    private JTextField txtEmail;
    private JTextField txtUserName;
    private JTextField txtName;
    private AuthService authService = new AuthService(new UsuarioDaoPostgreSQL());

    public CadastroPanel(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(new Color(44, 62, 80));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblName = createStyledLabel("Name:");
        txtName = createStyledTextField();

        JLabel lblUser = createStyledLabel("User:");
        txtUserName = createStyledTextField();

        JLabel lblEmail = createStyledLabel("E-mail:");
        txtEmail = createStyledTextField();

        JLabel lblPassword = createStyledLabel("Password:");
        passwordTeste = createStyledPasswordField();

        JLabel lblPasswordConfirm = createStyledLabel("Confirm Password:");
        passwordSalvar = createStyledPasswordField();

        // Adiciona componentes no painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblName, gbc);
        gbc.gridx = 1;
        formPanel.add(txtName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblUser, gbc);
        gbc.gridx = 1;
        formPanel.add(txtUserName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblEmail, gbc);
        gbc.gridx = 1;
        formPanel.add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblPassword, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordTeste, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblPasswordConfirm, gbc);
        gbc.gridx = 1;
        formPanel.add(passwordSalvar, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttonPanel.setOpaque(false);

        JButton btnAddCadastro = createStyledButton("Add");
        btnAddCadastro.addActionListener(e -> {
            try {
                authService.cadastro(
                        txtName.getText(),
                        txtUserName.getText(),
                        txtEmail.getText(),
                        new String(passwordTeste.getPassword()),
                        CargoUsuario.USUARIO
                );
                JOptionPane.showMessageDialog(CadastroPanel.this, "Cadastro realizado com sucesso!");
                parentFrame.dispose(); // Fechar o frame após o cadastro
            } catch (CadastroException ex) {
                JOptionPane.showMessageDialog(CadastroPanel.this, "Erro no cadastro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnClear = createStyledButton("Clear");
        btnClear.addActionListener(e -> {
            txtName.setText("");
            txtUserName.setText("");
            txtEmail.setText("");
            passwordTeste.setText("");
            passwordSalvar.setText("");
        });

        buttonPanel.add(btnAddCadastro);
        buttonPanel.add(btnClear);

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
        textField.setPreferredSize(new Dimension(200, 30));  // Define limite de tamanho
        return textField;
    }

    private JPasswordField createStyledPasswordField() {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setPreferredSize(new Dimension(200, 30));  // Define limite de tamanho
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
