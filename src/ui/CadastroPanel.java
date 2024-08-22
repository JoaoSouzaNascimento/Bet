package ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import service.AuthService;
import exceptions.LoginException;
import model.Usuario;

public class CadastroPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JPasswordField passwordTeste;
    private JPasswordField passwordSalvar;
    private JTextField txtEmail;
    private JTextField txtUserName;
    private JTextField txtName;
    private AuthService authService = new AuthService();

    /**
     * Create the panel.
     */
    public CadastroPanel() {
        placeComponents();
    }

    /**
     * Initialize the contents of the panel.
     */
    private void placeComponents() {
        setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 45, 45, 13);
        add(lblName);

        JLabel lblUser = new JLabel("User:");
        lblUser.setBounds(10, 74, 61, 13);
        add(lblUser);

        JLabel lblEmail = new JLabel("E-mail:");
        lblEmail.setBounds(10, 109, 61, 13);
        add(lblEmail);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(10, 132, 61, 13);
        add(lblPassword);

        JLabel lblPasswordConfirm = new JLabel("Password:");
        lblPasswordConfirm.setBounds(10, 171, 61, 13);
        add(lblPasswordConfirm);

        txtName = new JTextField();
        txtName.setBounds(81, 42, 299, 19);
        add(txtName);
        txtName.setColumns(10);

        txtUserName = new JTextField();
        txtUserName.setBounds(81, 71, 299, 19);
        add(txtUserName);
        txtUserName.setColumns(10);

        txtEmail = new JTextField();
        txtEmail.setColumns(10);
        txtEmail.setBounds(81, 100, 299, 19);
        add(txtEmail);

        passwordTeste = new JPasswordField();
        passwordTeste.setBounds(81, 129, 299, 19);
        add(passwordTeste);

        passwordSalvar = new JPasswordField();
        passwordSalvar.setBounds(81, 168, 299, 19);
        add(passwordSalvar);

        JButton btnAddCadastro = new JButton("Add");
        btnAddCadastro.setMnemonic('A');
        btnAddCadastro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtName.getText();
                String nickname = txtUserName.getText();
                String email = txtEmail.getText();
                String password = new String(passwordTeste.getPassword());

                try {
                    Usuario usuario = authService.cadastro(username, nickname, email, password);
                    JOptionPane.showMessageDialog(CadastroPanel.this, "Cadastro realizado com sucesso!");

                    JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
                    janelaPrincipal.setUsuario(usuario); 
                    janelaPrincipal.setVisible(true);

                    CadastroPanel.this.getTopLevelAncestor().setVisible(false);

                } catch (LoginException | IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(CadastroPanel.this, "Erro no cadastro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnAddCadastro.setBounds(55, 251, 85, 21);
        add(btnAddCadastro);

        JButton btnClear = new JButton("Clear");
        btnClear.setMnemonic('C');
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtUserName.setText("");
                txtEmail.setText("");
                passwordTeste.setText("");
                passwordSalvar.setText("");
            }
        });
        btnClear.setBounds(233, 251, 85, 21);
        add(btnClear);
    }
}
