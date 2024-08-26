package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import config.AppContext;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import service.TransactionService;
import service.UsuarioService;
import model.Usuario;

public class UsuarioPanel extends JFrame {
    private static final long serialVersionUID = 1L;
    private JButton btnUsername;
    private JLabel txtEmail;
    private JTextField txtBalance;
    private JLabel txtUsername;
    private Usuario usuarioLogado;
    private UsuarioService usuarioService;
    private TransactionService transactionService;
    private JButton btnEmail;
    
    public UsuarioPanel() {
        this.usuarioService = AppContext.getUsuarioService();
        this.transactionService = AppContext.getTransactionService();
        initialize();
    }

    public void setUsuario(Usuario usuario) {
        this.usuarioLogado = usuario;
        btnUsername.setText(usuario.getUsername());
        btnEmail.setText(usuario.getEmail());
        txtEmail.setText(usuario.getEmail());
        txtBalance.setText(String.valueOf(usuario.getBalance()));
        txtUsername.setText(usuario.getUsername());
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

        JPanel panel = new JPanel();
        panel.setBackground(new Color(34, 49, 63));
        panel.setLayout(new GridBagLayout());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        getContentPane().add(panel, gbc);

        JLabel lblTitle = new JLabel("Perfil do Usuário");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 30));
        lblTitle.setForeground(Color.WHITE);
        panel.add(lblTitle);

        JButton btnEdit = new JButton("Editar Informações");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        getContentPane().add(btnEdit, gbc);
        btnEdit.addActionListener(e -> {
            // Implementar ação para editar informações
        });

        JButton btnDeposito = new JButton("Depósito");
        JButton btnSaque = new JButton("Saque");
        
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre os componentes
        gbc.gridx = 1; // coluna 1
        gbc.gridy = 1; // linha 1
        getContentPane().add(btnDeposito, gbc);
        gbc.gridx = 2; // coluna 2 (ao lado do botão de Depósito)
        gbc.gridy = 1; // mesma linha que o botão de Depósito
        getContentPane().add(btnSaque, gbc);

        btnDeposito.addActionListener(e -> abrirTelaDeposito());
        btnSaque.addActionListener(e -> abrirTelaSaque());

        btnUsername = new JButton();
        if (btnUsername == null) {
            btnUsername = new JButton("Usuário"); // Fallback ou inicialização padrão
        }
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        getContentPane().add(btnUsername, gbc);
        btnUsername.addActionListener(e -> abrirPainelEdicaoNome());
        
        txtUsername = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 2;
        getContentPane().add(txtUsername, gbc);

        JPanel emailPanel = new JPanel(new GridBagLayout());
        emailPanel.setBackground(new Color(34, 49, 63));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        getContentPane().add(emailPanel, gbc);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        emailPanel.add(lblEmail, new GridBagConstraints());

        btnEmail = new JButton("Alterar Email");
        if (btnEmail == null) {
            btnEmail = new JButton("Alterar Email"); // Fallback ou inicialização padrão
        }
        emailPanel.add(btnEmail, new GridBagConstraints());
        btnEmail.addActionListener(e -> abrirPainelEdicaoEmail());

        txtEmail = new JLabel();
        emailPanel.add(txtEmail, new GridBagConstraints());

        JLabel lblBalance = new JLabel("Saldo:");
        lblBalance.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        getContentPane().add(lblBalance, gbc);

        txtBalance = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 4;
        getContentPane().add(txtBalance, gbc);
        txtBalance.setColumns(20);

        JButton btnHistoricoApostas = new JButton("Histórico de Apostas");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        getContentPane().add(btnHistoricoApostas, gbc);
        btnHistoricoApostas.addActionListener(e -> {
            // Implementar ação para mostrar o histórico de apostas
        });

        JPanel panelGrafico = new JPanel();
        panelGrafico.setBackground(new Color(52, 73, 94));
        panelGrafico.setPreferredSize(new Dimension(300, 200)); 
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        getContentPane().add(panelGrafico, gbc);

        JButton btnHistoricoTransacoes = new JButton("Histórico de Transações");
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        getContentPane().add(btnHistoricoTransacoes, gbc);
        btnHistoricoTransacoes.addActionListener(e -> {
            // Implementar ação para mostrar o histórico de transações
        });

        JPanel panelGraficoTransacoes = new JPanel();
        panelGraficoTransacoes.setBackground(new Color(52, 73, 94));
        panelGraficoTransacoes.setPreferredSize(new Dimension(300, 200)); 
        gbc.gridx = 1;
        gbc.gridy = 7;
        getContentPane().add(panelGraficoTransacoes, gbc);

        JButton btnBack = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        getContentPane().add(btnBack, gbc);
        btnBack.addActionListener(e -> dispose());
    }


    private JPanel createGraphPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(52, 73, 94));
        panel.setPreferredSize(new Dimension(300, 200));
        return panel;
    }

    private void styleButton(JButton button) {
        button.setBackground(new Color(54, 85, 115));
        button.setForeground(Color.WHITE);
        button.setBorder(new EmptyBorder(10, 20, 10, 20));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    private void abrirPainelEdicaoEmail() {
        JDialog dialog = createDialog("Editar E-mail de Usuário");
        
        JLabel lblNovoEmail = new JLabel("Novo E-mail:");
        JTextField novoEmailField = new JTextField(20);
        JButton editarEmailButton = new JButton("Editar");
        JButton voltarButton = new JButton("Voltar");

        dialog.add(lblNovoEmail, createConstraints(0, 0));
        dialog.add(novoEmailField, createConstraints(1, 0));
        dialog.add(editarEmailButton, createConstraints(0, 1));
        dialog.add(voltarButton, createConstraints(1, 1));

        editarEmailButton.addActionListener(e -> {
            String novoEmail = novoEmailField.getText();
            if (!novoEmail.isEmpty()) {
                try {
                    usuarioService.editarUsuarioEmail(usuarioLogado.getId(), novoEmail);
                    btnEmail.setText(novoEmail);
                    dialog.dispose();
                } catch (AtualizacaoException | ConsultaException ex) {
                    showErrorDialog(dialog, "Erro ao atualizar o e-mail", ex);
                } catch (Exception ex) {
                    showErrorDialog(dialog, "Erro inesperado", ex);
                }
            } else {
                showErrorDialog(dialog, "E-mail não pode ser vazio", null);
            }
        });

        voltarButton.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void abrirPainelEdicaoNome() {
        JDialog dialog = createDialog("Editar Nome de Usuário");
        
        JLabel lblNovoNome = new JLabel("Novo Nome:");
        JTextField novoNomeField = new JTextField(20);
        JButton editarButton = new JButton("Editar");
        JButton voltarButton = new JButton("Voltar");

        dialog.add(lblNovoNome, createConstraints(0, 0));
        dialog.add(novoNomeField, createConstraints(1, 0));
        dialog.add(editarButton, createConstraints(0, 1));
        dialog.add(voltarButton, createConstraints(1, 1));

        editarButton.addActionListener(e -> {
            String novoNome = novoNomeField.getText();
            if (!novoNome.isEmpty()) {
                try {
                    usuarioService.editarUsuarioNome(usuarioLogado.getEmail(), novoNome);
                    btnUsername.setText(novoNome);
                    dialog.dispose();
                } catch (AtualizacaoException | ConsultaException ex) {
                    showErrorDialog(dialog, "Erro ao atualizar o nome", ex);
                } catch (Exception ex) {
                    showErrorDialog(dialog, "Erro inesperado", ex);
                }
            } else {
                showErrorDialog(dialog, "Nome não pode ser vazio", null);
            }
        });

        voltarButton.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private JDialog createDialog(String title) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(300, 150);
        dialog.setLayout(new GridBagLayout());
        return dialog;
    }

    private GridBagConstraints createConstraints(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        return gbc;
    }

    private void showErrorDialog(JDialog dialog, String message, Exception ex) {
        JOptionPane.showMessageDialog(dialog, message + (ex != null ? ": " + ex.getMessage() : ""), "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private void abrirTelaDeposito() {
        JDialog dialog = createDialog("Depósito");

        JLabel lblSaldo = new JLabel("Saldo Atual: R$ " + usuarioLogado.getBalance());
        JLabel lblValor = new JLabel("Valor do depósito:");
        JTextField txtValor = new JTextField(15);
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        dialog.add(lblSaldo, createConstraints(0, 0));
        dialog.add(lblValor, createConstraints(0, 1));
        dialog.add(txtValor, createConstraints(1, 1));
        dialog.add(btnConfirmar, createConstraints(0, 2));
        dialog.add(btnCancelar, createConstraints(1, 2));

        btnConfirmar.addActionListener(e -> {
            try {
                BigDecimal valor = new BigDecimal(txtValor.getText());
                if (valor.compareTo(BigDecimal.ZERO) > 0) {
                    transactionService.depositar(usuarioLogado.getId(), valor);
                    JOptionPane.showMessageDialog(dialog, "Depósito realizado com sucesso!");
                    lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getBalance());
                } else {
                    showErrorDialog(dialog, "O valor deve ser positivo", null);
                }
            } catch (NumberFormatException ex) {
                showErrorDialog(dialog, "Valor inválido", ex);
            } catch (Exception ex) {
                showErrorDialog(dialog, "Erro ao realizar o depósito", ex);
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private void abrirTelaSaque() {
        JDialog dialog = createDialog("Saque");

        JLabel lblSaldo = new JLabel("Saldo Atual: R$ " + usuarioLogado.getBalance());
        JLabel lblValor = new JLabel("Valor do saque:");
        JTextField txtValor = new JTextField(15);
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        dialog.add(lblSaldo, createConstraints(0, 0));
        dialog.add(lblValor, createConstraints(0, 1));
        dialog.add(txtValor, createConstraints(1, 1));
        dialog.add(btnConfirmar, createConstraints(0, 2));
        dialog.add(btnCancelar, createConstraints(1, 2));

        btnConfirmar.addActionListener(e -> {
            try {
                BigDecimal valor = new BigDecimal(txtValor.getText());
                if (valor.compareTo(BigDecimal.ZERO) > 0) {
                    transactionService.sacar(usuarioLogado.getId(), valor);
                    JOptionPane.showMessageDialog(dialog, "Saque realizado com sucesso!");
                    lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getBalance());
                } else {
                    showErrorDialog(dialog, "O valor deve ser positivo", null);
                }
            } catch (NumberFormatException ex) {
                showErrorDialog(dialog, "Valor inválido", ex);
            } catch (Exception ex) {
                showErrorDialog(dialog, "Erro ao realizar o saque", ex);
            }
        });

        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
}
