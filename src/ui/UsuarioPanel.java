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
        // Configuração do botão Depósito
        gbc.gridx = 1; // coluna 1
        gbc.gridy = 1; // linha 1
        getContentPane().add(btnDeposito, gbc);
        // Configuração do botão Saque
        gbc.gridx = 2; // coluna 2 (ao lado do botão de Depósito)
        gbc.gridy = 1; // mesma linha que o botão de Depósito
        getContentPane().add(btnSaque, gbc);
        // Implementar ações para os botões
        btnDeposito.addActionListener(e -> {
            // Lógica para abrir a tela de Depósito
            abrirTelaDeposito();
        });

        btnSaque.addActionListener(e -> {
            // Lógica para abrir a tela de Saque
            abrirTelaSaque();
        });

        btnUsername = new JButton();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        getContentPane().add(btnUsername, gbc);
        btnUsername.addActionListener(e -> abrirPainelEdicaoNome());
        
        txtUsername = new JLabel();
        gbc.gridx = 1;
        gbc.gridy = 2;
        getContentPane().add(txtUsername, gbc);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        getContentPane().add(lblEmail, gbc);
        
        btnEmail = new JButton();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        getContentPane().add(btnEmail, gbc);
        btnEmail.addActionListener(e -> abrirPainelEdicaoEmail());
      
        txtEmail = new JLabel();
        lblEmail.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 3;
        getContentPane().add(txtEmail, gbc);

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

        // Espaço para Grafico
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

        // Espaço para Gráfico de Transações
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
        btnBack.addActionListener(e -> {
            dispose(); 
        });
    }
    
    private void abrirPainelEdicaoEmail() {
        // Cria o JDialog
        JDialog dialog = new JDialog(this, "Editar E-mail de Usuário", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adiciona os componentes
        JLabel lblNovoEmail = new JLabel("Novo E-mail:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(lblNovoEmail, gbc);

        JTextField novoEmailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        dialog.add(novoEmailField, gbc);

        JButton editarEmailButton = new JButton("Editar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(editarEmailButton, gbc);

        editarEmailButton.addActionListener(e -> {
            String novoEmail = novoEmailField.getText();
            if (!novoEmail.isEmpty()) {
                try {
                    usuarioService.editarUsuarioEmail(usuarioLogado.getId(), novoEmail); // Método para editar o e-mail
                    btnEmail.setText(novoEmail); // Atualiza o botão/label de e-mail, se houver
                    dialog.dispose();
                } catch (AtualizacaoException ex) {
                    JOptionPane.showMessageDialog(dialog, "Erro ao atualizar o e-mail: " + ex.getMessage(), "Erro de Atualização", JOptionPane.ERROR_MESSAGE);
                } catch (ConsultaException ex) {
                    JOptionPane.showMessageDialog(dialog, "Erro ao consultar dados: " + ex.getMessage(), "Erro de Consulta", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "E-mail não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton voltarButton = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        dialog.add(voltarButton, gbc);

        voltarButton.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(this); // Centraliza o diálogo em relação à janela principal
        dialog.setVisible(true);
    }

    
    private void abrirPainelEdicaoNome() {
        // Cria o JDialog
        JDialog dialog = new JDialog(this, "Editar Nome de Usuário", true);
        dialog.setSize(300, 150);
        dialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adiciona os componentes
        JLabel lblNovoNome = new JLabel("Novo Nome:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        dialog.add(lblNovoNome, gbc);

        JTextField novoNomeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        dialog.add(novoNomeField, gbc);

        JButton editarButton = new JButton("Editar");
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(editarButton, gbc);

        editarButton.addActionListener(e -> {
            String novoNome = novoNomeField.getText();
            if (!novoNome.isEmpty()) {
                try {
                    usuarioService.editarUsuarioNome(usuarioLogado.getEmail(), novoNome);
                    btnUsername.setText(novoNome);
                    dialog.dispose();
                } catch (AtualizacaoException ex) {
                    JOptionPane.showMessageDialog(dialog, "Erro ao atualizar o nome: " + ex.getMessage(), "Erro de Atualização", JOptionPane.ERROR_MESSAGE);
                } catch (ConsultaException ex) {
                    JOptionPane.showMessageDialog(dialog, "Erro ao consultar dados: " + ex.getMessage(), "Erro de Consulta", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, "Erro inesperado: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "Nome não pode ser vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton voltarButton = new JButton("Voltar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        dialog.add(voltarButton, gbc);

        voltarButton.addActionListener(e -> dialog.dispose());

        dialog.setLocationRelativeTo(this); // Centraliza o diálogo em relação ao frame principal
        dialog.setVisible(true);
    }
    
    
    
    private void abrirTelaDeposito() {
        JDialog dialog = new JDialog(this, "Depósito", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Criar componentes
        JLabel lblSaldo = new JLabel("Saldo Atual: R$ " + usuarioLogado.getBalance());
        JLabel lblValor = new JLabel("Valor do depósito:");
        JTextField txtValor = new JTextField(15);
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        // Adicionar componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        dialog.add(lblSaldo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        dialog.add(lblValor, gbc);

        gbc.gridx = 1;
        dialog.add(txtValor, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        dialog.add(btnConfirmar, gbc);

        gbc.gridx = 1;
        dialog.add(btnCancelar, gbc);

        // Adicionar ação ao botão Confirmar
        btnConfirmar.addActionListener(e -> {
            try {
                BigDecimal valor = new BigDecimal(txtValor.getText());
                if (valor.compareTo(BigDecimal.ZERO) > 0) {
                    transactionService.depositar(usuarioLogado.getId(), valor);
                    JOptionPane.showMessageDialog(dialog, "Depósito realizado com sucesso!");
                    lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getBalance()); // Atualiza o saldo
                } else {
                    JOptionPane.showMessageDialog(dialog, "O valor deve ser positivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao realizar o depósito: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Adicionar ação ao botão Cancelar
        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    
    private void abrirTelaSaque() {
        JDialog dialog = new JDialog(this, "Saque", true);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Criar componentes
        JLabel lblSaldo = new JLabel("Saldo Atual: R$ " + usuarioLogado.getBalance());
        JLabel lblValor = new JLabel("Valor do saque:");
        JTextField txtValor = new JTextField(15);
        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        // Adicionar componentes ao painel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        dialog.add(lblSaldo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        dialog.add(lblValor, gbc);

        gbc.gridx = 1;
        dialog.add(txtValor, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        dialog.add(btnConfirmar, gbc);

        gbc.gridx = 1;
        dialog.add(btnCancelar, gbc);

        // Adicionar ação ao botão Confirmar
        btnConfirmar.addActionListener(e -> {
            try {
                BigDecimal valor = new BigDecimal(txtValor.getText());
                if (valor.compareTo(BigDecimal.ZERO) > 0) {
                    transactionService.sacar(usuarioLogado.getId(), valor);
                    JOptionPane.showMessageDialog(dialog, "Saque realizado com sucesso!");
                    lblSaldo.setText("Saldo Atual: R$ " + usuarioLogado.getBalance()); // Atualiza o saldo
                } else {
                    JOptionPane.showMessageDialog(dialog, "O valor deve ser positivo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Valor inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Erro ao realizar o saque: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Adicionar ação ao botão Cancelar
        btnCancelar.addActionListener(e -> dialog.dispose());

        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

}
