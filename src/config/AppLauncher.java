package config;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import dao.ApostaDao;
import dao.ApostaDaoPostgreSQL;
import dao.PalpiteDao;
import dao.PalpiteDaoPostgreSQL;
import dao.UsuarioDao;
import dao.UsuarioDaoPostgreSQL;
import service.ApostaService;
import service.FootballApiService;
import service.PartidaService;
import service.TransactionService;
import service.UsuarioService;
import ui.CadastroPanel;
import ui.LoginPanel;

public class AppLauncher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	
        	UsuarioService usuarioService = new UsuarioService();
        	TransactionService transactionService = new TransactionService();
            // Configura o AppContext com o UsuarioService
            AppContext.setUsuarioService(usuarioService);
        	AppContext.setTransactionService(transactionService);
        	AppContext.setFootballApiService(new FootballApiService(AppContext.API_KEY, AppContext.API_HOST));
        	AppContext.setApostaService(new ApostaService(new ApostaDaoPostgreSQL(), new PalpiteDaoPostgreSQL(), AppContext.getFootballApiService(),
        			new PartidaService(), new UsuarioDaoPostgreSQL()));
            // Cria o JFrame
            //JFrame frame = new JFrame("Cadastro");
            //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setSize(600, 400); // Tamanho da janela
        	JFrame frame = new JFrame("Login");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(450, 300);
            frame.setLocationRelativeTo(null); // Centraliza a janela na tela
            
            LoginPanel loginPanel = new LoginPanel(frame);
            frame.setContentPane(loginPanel);
            
            // Adiciona o CadastroPanel ao JFrame
            //CadastroPanel cadastroPanel = new CadastroPanel();
            //frame.getContentPane().add(cadastroPanel);

            // Torna a janela visível
            frame.setVisible(true);
        });
    }
}
