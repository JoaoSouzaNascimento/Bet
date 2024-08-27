package ui;

import java.awt.*;
import java.math.BigDecimal;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import service.ApostaService;
import service.TransactionService;
import service.UsuarioService;
import model.Aposta;
import model.Palpite;
import model.Transaction;
import model.Usuario;
import config.AppContext;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.InsercaoException;
import java.util.List;

public class UsuarioPanel extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblUsername;
	private JLabel lblEmail;
	private JLabel lblBalance;
	private Usuario usuarioLogado;
	private UsuarioService usuarioService;
	private TransactionService transactionService;
	private ApostaService apostaService;
	private JanelaPrincipal janelaPrincipal;
	
	
	public UsuarioPanel(JanelaPrincipal janelaPrincipal) {  // Recebe a referência ao JanelaPrincipal
        this.usuarioService = AppContext.getUsuarioService();
        
        this.transactionService = AppContext.getTransactionService();
        this.apostaService = AppContext.getApostaService();
        this.janelaPrincipal = janelaPrincipal;  // Armazena a referência
        initialize();
    }

	public void setUsuario(Usuario usuario) {
		this.usuarioLogado = usuario;
		lblUsername.setText(usuario.getUsername());
		lblEmail.setText(usuario.getEmail());
		lblBalance.setText("Saldo: R$ " + String.format("%.2f", usuario.getBalance()));
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

		// Painel de título
		JLabel lblTitle = new JLabel("Perfil do Usuário");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
		lblTitle.setForeground(Color.WHITE);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER;
		getContentPane().add(lblTitle, gbc);

		// Nome de usuário
		JLabel lblUsernameLabel = createStyledLabel("Usuário:");
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		getContentPane().add(lblUsernameLabel, gbc);

		lblUsername = createStyledLabel("");
		gbc.gridx = 1;
		getContentPane().add(lblUsername, gbc);

		// Email
		JLabel lblEmailLabel = createStyledLabel("E-mail:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		getContentPane().add(lblEmailLabel, gbc);

		lblEmail = createStyledLabel("");
		gbc.gridx = 1;
		getContentPane().add(lblEmail, gbc);

		// Saldo
		JLabel lblBalanceLabel = createStyledLabel("Saldo:");
		gbc.gridx = 0;
		gbc.gridy = 3;
		getContentPane().add(lblBalanceLabel, gbc);

		lblBalance = createStyledLabel("");
		gbc.gridx = 1;
		getContentPane().add(lblBalance, gbc);

		// Botões de Depósito e Saque
		JButton btnDeposito = createStyledButton("Depósito");
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.gridwidth = 1;
		getContentPane().add(btnDeposito, gbc);

		JButton btnSaque = createStyledButton("Saque");
		gbc.gridx = 1;
		getContentPane().add(btnSaque, gbc);

		btnDeposito.addActionListener(e -> abrirTelaDeposito());
		btnSaque.addActionListener(e -> abrirTelaSaque());

		// Botão Histórico de Apostas
		JButton btnHistoricoApostas = createStyledButton("Histórico de Apostas");
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 2;
		getContentPane().add(btnHistoricoApostas, gbc);
		btnHistoricoApostas.addActionListener(e -> abrirHistoricoApostas());

		// Botão Histórico de Transações
		JButton btnHistoricoTransacoes = createStyledButton("Histórico de Transações");
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		getContentPane().add(btnHistoricoTransacoes, gbc);
		btnHistoricoTransacoes.addActionListener(e -> abrirHistoricoTransacoes());

		// Botão Voltar
		JButton btnBack = createStyledButton("Voltar");
		gbc.gridx = 0;
		gbc.gridy = 7;
		gbc.gridwidth = 2;
		getContentPane().add(btnBack, gbc);
		btnBack.addActionListener(e -> voltarParaJanelaPrincipal());
	}
	
	private void atualizarSaldoUI() {
        lblBalance.setText("Saldo: R$ " + String.format("%.2f", usuarioLogado.getBalance()));
        janelaPrincipal.atualizarSaldoUI();  // Atualiza o saldo na JanelaPrincipal
    }


	private JLabel createStyledLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Arial", Font.PLAIN, 16));
		label.setForeground(Color.WHITE);
		return label;
	}

	private JButton createStyledButton(String text) {
		JButton button = new JButton(text);
		button.setPreferredSize(new Dimension(180, 40));
		button.setFont(new Font("Arial", Font.BOLD, 16));
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

	private void abrirTelaDeposito() {
		JDialog dialog = new JDialog(this, "Depósito", true);
		dialog.setSize(300, 150);
		dialog.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel lblSaldo = new JLabel("Saldo Atual: R$ " + usuarioLogado.getBalance());
		JLabel lblValor = new JLabel("Valor do depósito:");
		JTextField txtValor = new JTextField(15);
		JButton btnConfirmar = createStyledButton("Confirmar");
		JButton btnCancelar = createStyledButton("Cancelar");

		gbc.gridx = 0;
		gbc.gridy = 0;
		dialog.add(lblSaldo, gbc);
		gbc.gridy = 1;
		dialog.add(lblValor, gbc);
		gbc.gridx = 1;
		dialog.add(txtValor, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		dialog.add(btnConfirmar, gbc);
		gbc.gridx = 1;
		dialog.add(btnCancelar, gbc);

		btnConfirmar.addActionListener(e -> {
			try {
				BigDecimal valor = new BigDecimal(txtValor.getText());
				if (valor.compareTo(BigDecimal.ZERO) > 0) {
					transactionService.depositar(usuarioLogado.getId(), valor);
					usuarioLogado = usuarioService.getUsuarioById(usuarioLogado.getId());
					atualizarSaldoUI();
					JOptionPane.showMessageDialog(dialog, "Depósito realizado com sucesso!");
					lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getBalance());
				} else {
					JOptionPane.showMessageDialog(dialog, "O valor deve ser positivo.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(dialog, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
			} catch (ConsultaException ex) {
				JOptionPane.showMessageDialog(dialog, "Erro ao realizar o deposito: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch (AtualizacaoException ex) {
				JOptionPane.showMessageDialog(dialog, "Erro ao atualizar o saldo: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch (InsercaoException ex) {
				JOptionPane.showMessageDialog(dialog, "Erro ao inserir transação: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		btnCancelar.addActionListener(e -> dialog.dispose());

		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	private void abrirTelaSaque() {
		JDialog dialog = new JDialog(this, "Saque", true);
		dialog.setSize(300, 150);
		dialog.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		JLabel lblSaldo = new JLabel("Saldo Atual: R$ " + usuarioLogado.getBalance());
		JLabel lblValor = new JLabel("Valor do saque:");
		JTextField txtValor = new JTextField(15);
		JButton btnConfirmar = createStyledButton("Confirmar");
		JButton btnCancelar = createStyledButton("Cancelar");

		gbc.gridx = 0;
		gbc.gridy = 0;
		dialog.add(lblSaldo, gbc);
		gbc.gridy = 1;
		dialog.add(lblValor, gbc);
		gbc.gridx = 1;
		dialog.add(txtValor, gbc);
		gbc.gridx = 0;
		gbc.gridy = 2;
		dialog.add(btnConfirmar, gbc);
		gbc.gridx = 1;
		dialog.add(btnCancelar, gbc);

		btnConfirmar.addActionListener(e -> {
			try {
				BigDecimal valor = new BigDecimal(txtValor.getText());
				if (valor.compareTo(BigDecimal.ZERO) > 0) {
					transactionService.sacar(usuarioLogado.getId(), valor);
					usuarioLogado = usuarioService.getUsuarioById(usuarioLogado.getId());
					atualizarSaldoUI();
					JOptionPane.showMessageDialog(dialog, "Saque realizado com sucesso!");
					lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getBalance());
				} else {
					JOptionPane.showMessageDialog(dialog, "O valor deve ser positivo.", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(dialog, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
			} catch (ConsultaException ex) {
				JOptionPane.showMessageDialog(dialog, "Erro ao realizar o saque: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch (AtualizacaoException ex) {
				JOptionPane.showMessageDialog(dialog, "Erro ao atualizar o saldo: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			} catch (InsercaoException ex) {
				JOptionPane.showMessageDialog(dialog, "Erro ao inserir transação: " + ex.getMessage(), "Erro",
						JOptionPane.ERROR_MESSAGE);
			}

		});

		btnCancelar.addActionListener(e -> dialog.dispose());

		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
	}

	private void abrirHistoricoTransacoes() {
		try {
			List<Transaction> transactions = transactionService.getAllTransactionsByUser(usuarioLogado.getId());
			JDialog dialog = new JDialog(this, "Histórico de Transações", true);
			dialog.setSize(600, 400);
			dialog.setLayout(new BorderLayout());

			// Set the background color of the dialog
			dialog.getContentPane().setBackground(new Color(44, 62, 80));

			String[] columnNames = { "ID", "Tipo", "Status", "Valor", "Data de Criação", "Data de Atualização" };
			Object[][] data = new Object[transactions.size()][6];
			for (int i = 0; i < transactions.size(); i++) {
				Transaction transaction = transactions.get(i);
				data[i][0] = transaction.getId();
				data[i][1] = transaction.getType();

				// Check the status and set the corresponding String value
				switch (transaction.getStatus()) {
				case 0:
					data[i][2] = "Pendente";
					break;
				case 1:
					data[i][2] = "Concluída";
					break;
				case -1:
					data[i][2] = "Recusada";
					break;
				default:
					data[i][2] = "Status desconhecido";
					break;
				}

				data[i][3] = transaction.getAmount();
				data[i][4] = transaction.getCreatedAt();
				data[i][5] = transaction.getUpdatedAt();
			}

			JTable table = new JTable(data, columnNames);
			table.setFillsViewportHeight(true);
			table.setBackground(Color.WHITE);
			table.setForeground(Color.BLACK);

			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBackground(new Color(44, 62, 80));

			dialog.add(scrollPane, BorderLayout.CENTER);

			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
		} catch (ConsultaException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao recuperar transações: " + ex.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void abrirHistoricoApostas() {
		try {
			List<Aposta> apostas = apostaService.getTodasApostas(usuarioLogado);
			List<Palpite> palpite = null;
			JDialog dialog = new JDialog(this, "Histórico de Apostas", true);
			dialog.setSize(600, 400);
			dialog.setLayout(new BorderLayout());
			dialog.getContentPane().setBackground(new Color(44, 62, 80));

			String[] columnNames = { "ID", "Usuário", "Valor", "Status", "Data", "Recomposa" };
			Object[][] data = new Object[apostas.size()][6];
			for (int i = 0; i < apostas.size(); i++) {
				palpite = apostaService.getPalpitesDeUmaAposta(apostas.get(i));
				Aposta aposta = apostas.get(i);
				data[i][0] = aposta.getId();
				data[i][1] = aposta.getUserId();
				data[i][2] = aposta.getAmount();
				if (aposta.getStatus() == null) {
					data[i][3] = "Em andamento";
				} else {
					data[i][3] = aposta.getStatus() ? "Ganhou" : "Perdeu";
				}
				data[i][4] = aposta.getDate();
				data[i][5] = palpite.stream().map(p -> p.getOdd()).reduce(BigDecimal.ONE, BigDecimal::multiply);
			}

			JTable table = new JTable(data, columnNames);
			table.setFillsViewportHeight(true);
			table.setBackground(Color.WHITE);
			table.setForeground(Color.BLACK);

			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBackground(new Color(44, 62, 80));

			dialog.add(scrollPane, BorderLayout.CENTER);

			dialog.setLocationRelativeTo(this);
			dialog.setVisible(true);
		} catch (ConsultaException ex) {
			JOptionPane.showMessageDialog(this, "Erro ao recuperar apostas: " + ex.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	private void voltarParaJanelaPrincipal() {
        // Atualiza o usuário e o saldo no JanelaPrincipal
        try {
            usuarioLogado = usuarioService.getUsuarioById(usuarioLogado.getId());  // Recarrega o usuário do banco
            janelaPrincipal.setUsuario(usuarioLogado);  // Atualiza o saldo na JanelaPrincipal
        } catch (ConsultaException e) {
            JOptionPane.showMessageDialog(this, "Erro ao recarregar o saldo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        // Fecha o painel do usuário
        this.dispose();
        // Reabre a JanelaPrincipal
        janelaPrincipal.setVisible(true);
    }

}
