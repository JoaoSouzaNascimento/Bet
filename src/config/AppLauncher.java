package config;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import dao.UsuarioDaoPostgreSQL;
import service.UsuarioService;
import service.AuthService;
import service.TransactionService;
import service.UsuarioService;
import ui.CadastroPanel;
import ui.LoginPanel;
import model.Usuario;
import model.CargoUsuario;
import exceptions.CadastroException;
public class AppLauncher {

    private AuthService authService = new AuthService(new UsuarioDaoPostgreSQL());
    private Usuario usuarioTeste;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria a instância de AppLauncher
            AppLauncher appLauncher = new AppLauncher();

            // Inicializa os serviços
            UsuarioService usuarioService = new UsuarioService();
            TransactionService transactionService = new TransactionService();
            AppContext.setUsuarioService(usuarioService);
            AppContext.setTransactionService(transactionService);

            // Cria o usuário de teste, se não existir
            appLauncher.criarUsuarioTesteSeNaoExistir();

            // Cria o JFrame
            JFrame frame = new JFrame("Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(450, 300);
            frame.setLocationRelativeTo(null); // Centraliza a janela na tela

            LoginPanel loginPanel = new LoginPanel(frame);
            frame.setContentPane(loginPanel);

            // Torna a janela visível
            frame.setVisible(true);
        });
    }
private void criarUsuarioTesteSeNaoExistir() {
        if (usuarioTeste == null) {
            try {
                // Dados do usuário de teste
                String username = "testuser";
                String nickname = "TestUser";
                String email = "testuser@example.com";
                String password = "password123";
                CargoUsuario role = CargoUsuario.USUARIO; // Supondo que exista um CargoUsuario.USUARIO

                // Cria o usuário através do AuthService
                usuarioTeste = authService.cadastro(username, nickname, email, password, role);

                System.out.println("Usuário de teste criado: " + usuarioTeste.getUsername());
            } catch (CadastroException e) {
                System.err.println("Erro ao criar usuário de teste: " + e.getMessage());
            }
        } else {
            System.out.println("Usuário de teste já existe.");
        }
    }
}
