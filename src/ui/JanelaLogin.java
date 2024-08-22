package ui;

import java.awt.EventQueue;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.mindrot.jbcrypt.BCrypt;

import dao.UsuarioDaoPostgreSQL;
import exceptions.ConsultaException;
import exceptions.InsercaoException;
import exceptions.LoginException;
import model.Usuario;
import service.AuthService;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.UUID;
import java.awt.event.ActionEvent;

public class JanelaLogin {

	private JFrame frame;
	private JTextField txtLogin;
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaLogin window = new JanelaLogin();
					window.addTestUser();
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
	public JanelaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Email: ");
		lblNewLabel.setBounds(24, 37, 85, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password: ");
		lblNewLabel_1.setBounds(24, 79, 85, 13);
		frame.getContentPane().add(lblNewLabel_1);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(130, 34, 227, 19);
		frame.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(130, 76, 227, 19);
		frame.getContentPane().add(txtPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AuthService authService = new AuthService();
				String login = txtLogin.getText();
                String password = new String(txtPassword.getPassword());
                Usuario usuario = null;
                
                try {
					usuario = authService.login(login, password);
				} catch (LoginException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao adicionar usuário\n" + e1);
					e1.printStackTrace();
				} catch (ConsultaException e1) {
					JOptionPane.showMessageDialog(null, "Erro ao adicionar usuário\n" + e1);
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(48, 171, 85, 21);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(214, 171, 85, 21);
		frame.getContentPane().add(btnExit);
	}
	private void addTestUser() {
	    // teste
	    UUID id = UUID.randomUUID(); 
	    String username = "testuser";
	    String nickname = "Testr";
	    String password = BCrypt.hashpw("123", BCrypt.gensalt()); 
	    String email = "user@.com";
	    double balance = 100.0; 
	    boolean deleted = false; 
	    String role = "user"; 

	    Usuario usuario = new Usuario(id, username, nickname, password, email, balance, deleted, role);

	    try {
	        UsuarioDaoPostgreSQL usuarioDao = new UsuarioDaoPostgreSQL();
	        usuarioDao.createUsuario(usuario); 
	        System.out.println("Usuário de teste adicionado com sucesso.");
	    } catch (InsercaoException e) {
	        e.printStackTrace();
	        System.out.println("Erro ao adicionar o usuário de teste.");
	    }
	}
}
