package config;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import ui.CadastroPanel;

public class AppLauncher {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria o JFrame
            JFrame frame = new JFrame("Cadastro");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400); // Tamanho da janela
            frame.setLocationRelativeTo(null); // Centraliza a janela na tela

            // Adiciona o CadastroPanel ao JFrame
            CadastroPanel cadastroPanel = new CadastroPanel();
            frame.getContentPane().add(cadastroPanel);

            // Torna a janela visível
            frame.setVisible(true);
        });
    }
}
