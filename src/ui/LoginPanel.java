package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dao.UsuarioDaoPostgreSQL;
import exceptions.ConsultaException;
import exceptions.LoginException;
import model.Usuario;
import service.AuthService;

public class LoginPanel extends JPanel {
    private JTextField txtLogin;
    private JPasswordField txtPassword;
    private AuthService authService;

    public LoginPanel(JFrame parentFrame) {
        // Inicializa o AuthService com o DAO apropriado
        this.authService = new AuthService(new UsuarioDaoPostgreSQL());

        initialize(parentFrame);
    }

    private void initialize(JFrame parentFrame) {
        setLayout(null);

        JLabel lblEmail = new JLabel("Email: ");
        lblEmail.setBounds(24, 37, 85, 13);
        add(lblEmail);

        JLabel lblPassword = new JLabel("Password: ");
        lblPassword.setBounds(24, 79, 85, 13);
        add(lblPassword);

        txtLogin = new JTextField();
        txtLogin.setBounds(130, 34, 227, 19);
        add(txtLogin);
        txtLogin.setColumns(10);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(130, 76, 227, 19);
        add(txtPassword);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(48, 171, 85, 21);
        add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = txtLogin.getText();
                String password = new String(txtPassword.getPassword());
                Usuario usuario = null;

                try {
                    usuario = authService.login(login, password);
                    // Redireciona para a JanelaPrincipal passando o usuário logado
                    JanelaPrincipal mainFrame = new JanelaPrincipal();
                    mainFrame.setUsuario(usuario); // Passa o usuário logado para a JanelaPrincipal
                    parentFrame.setVisible(false); // Oculta a tela de login
                    mainFrame.setVisible(true);    // Exibe a JanelaPrincipal
                } catch (LoginException | ConsultaException ex) {
                    JOptionPane.showMessageDialog(parentFrame, ex.getMessage(), "Erro de Login", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton btnExit = new JButton("Exit");
        btnExit.setBounds(214, 171, 85, 21);
        add(btnExit);

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Fecha o aplicativo
            }
        });

        // Adiciona o botão de cadastro
        JButton btnRegister = new JButton("Cadastrar-se");
        btnRegister.setBounds(130, 210, 150, 21);
        add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Oculta o painel de login e exibe o painel de cadastro
                parentFrame.setContentPane(new CadastroPanel(parentFrame));
                parentFrame.revalidate(); // Atualiza a interface gráfica para mostrar o novo painel
            }
        });
    }
}
