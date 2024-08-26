package ui;

import java.awt.*;
import java.math.BigDecimal;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import service.TransactionService;
import service.UsuarioService;
import model.Usuario;
import config.AppContext;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.InsercaoException;

public class UsuarioPanel extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel lblUsername;
    private JLabel lblEmail;
    private JLabel lblBalance;
    private Usuario usuarioLogado;
    private UsuarioService usuarioService;
    private TransactionService transactionService;

    public UsuarioPanel() {
        this.usuarioService = AppContext.getUsuarioService();
        this.transactionService = AppContext.getTransactionService();
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
        btnHistoricoApostas.addActionListener(e -> {
            // Implementar ação para mostrar o histórico de apostas
        });

        // Botão Histórico de Transações
        JButton btnHistoricoTransacoes = createStyledButton("Histórico de Transações");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        getContentPane().add(btnHistoricoTransacoes, gbc);
        btnHistoricoTransacoes.addActionListener(e -> {
            // Implementar ação para mostrar o histórico de transações
        });

        // Botão Voltar
        JButton btnBack = createStyledButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        getContentPane().add(btnBack, gbc);
        btnBack.addActionListener(e -> dispose());
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
                    JOptionPane.showMessageDialog(dialog, "Depósito realizado com sucesso!");
                    lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getBalance());
                } else {
                    JOptionPane.showMessageDialog(dialog, "O valor deve ser positivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
        	    JOptionPane.showMessageDialog(dialog, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        	} catch (ConsultaException ex) {
        	    JOptionPane.showMessageDialog(dialog, "Erro ao realizar o deposito: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        	} catch (AtualizacaoException ex) {
        	    JOptionPane.showMessageDialog(dialog, "Erro ao atualizar o saldo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        	} catch (InsercaoException ex) {
        	    JOptionPane.showMessageDialog(dialog, "Erro ao inserir transação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
        	        JOptionPane.showMessageDialog(dialog, "Saque realizado com sucesso!");
        	        lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getBalance());
        	    } else {
        	        JOptionPane.showMessageDialog(dialog, "O valor deve ser positivo.", "Erro", JOptionPane.ERROR_MESSAGE);
        	    }
        	} catch (NumberFormatException ex) {
        	    JOptionPane.showMessageDialog(dialog, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
        	} catch (ConsultaException ex) {
        	    JOptionPane.showMessageDialog(dialog, "Erro ao realizar o saque: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        	} catch (AtualizacaoException ex) {
        	    JOptionPane.showMessageDialog(dialog, "Erro ao atualizar o saldo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        	} catch (InsercaoException ex) {
        	    JOptionPane.showMessageDialog(dialog, "Erro ao inserir transação: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        	}


        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
