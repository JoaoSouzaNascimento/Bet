package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.Usuario;
//import security.BCriptPassword;
import security.BCriptPassword;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class JanelaCadastro {

	private JFrame frmCadastro;
	private JPasswordField passwordTeste;
	private JPasswordField passwordSalvar;
	private JTextField txtEmail;
	private JTextField txtUser_name;
	private JTextField txtName;
	private BCriptPassword  controlPassword = new BCriptPassword();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaCadastro window = new JanelaCadastro();
					window.frmCadastro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaCadastro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadastro = new JFrame();
		frmCadastro.setTitle("CADASTRO");
		frmCadastro.setBounds(100, 100, 608, 409);
		frmCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastro.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setBounds(10, 45, 45, 13);
		frmCadastro.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("User:");
		lblNewLabel.setBounds(10, 74, 61, 13);
		frmCadastro.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("E-mail:");
		lblNewLabel_1.setBounds(10, 109, 61, 13);
		frmCadastro.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setBounds(10, 132, 61, 13);
		frmCadastro.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Password:");
		lblNewLabel_2_1.setBounds(10, 171, 61, 13);
		frmCadastro.getContentPane().add(lblNewLabel_2_1);
		
		txtName = new JTextField();
		txtName.setBounds(81, 42, 299, 19);
		frmCadastro.getContentPane().add(txtName);
		txtName.setColumns(10);
		
		txtUser_name = new JTextField();
		txtUser_name.setBounds(81, 71, 299, 19);
		frmCadastro.getContentPane().add(txtUser_name);
		txtUser_name.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(81, 100, 299, 19);
		frmCadastro.getContentPane().add(txtEmail);
		
		passwordTeste = new JPasswordField();
		passwordTeste.setBounds(81, 129, 299, 19);
		frmCadastro.getContentPane().add(passwordTeste);
		
		passwordSalvar = new JPasswordField();
		passwordSalvar.setBounds(81, 168, 299, 19);
		frmCadastro.getContentPane().add(passwordSalvar);
		
		JButton btnAddCadastro = new JButton("Add");
		btnAddCadastro.setMnemonic('A');
		btnAddCadastro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}	
		});
		btnAddCadastro.setBounds(55, 251, 85, 21);
		frmCadastro.getContentPane().add(btnAddCadastro);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setMnemonic('C');
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Limpa o campo de texto
				txtName.setText(null);
				txtUser_name.setText("");
				txtEmail.setText(""); 	
				passwordTeste.setText("");
				passwordSalvar.setText("");
			}
		});
		btnClear.setBounds(233, 251, 85, 21);
		frmCadastro.getContentPane().add(btnClear);
		
	}
}
