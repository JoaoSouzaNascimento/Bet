package security;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.mindrot.jbcrypt.BCrypt;

import dao.ConexaoBdSingleton;

public class BCriptPassword {

	public void validacaoSenha(String passwordTeste, String passwordSalvar) {
        if (!passwordTeste.equals(passwordSalvar)) {
            JOptionPane.showMessageDialog(null, "Senhas informadas não são iguais");
		}
	}
        
        // Criptografar a senha
	public String criptografarSenha(String passwordSalvar) {
		return BCrypt.hashpw(passwordSalvar, BCrypt.gensalt());
		}
       
    // Verificar se a senha digitada corresponde à senha criptografada

	 public boolean verificarSenha(String senhaDigitada, String senhaCriptografada) {
	 	
	     if (BCrypt.checkpw(senhaDigitada, senhaCriptografada)) {
	         System.out.println("As senhas correspondem.");
	         return true;
	     } else {
	     	JOptionPane.showMessageDialog(null,"As senhas não correspondem.");
	         return false;
	     }
	}
	
    // Método para autenticar um usuário
    public String autenticarUser(String user_name, String password) {
    	
		try {
			PreparedStatement ps = ConexaoBdSingleton
				.getInstance()
				.getConexao()
				.prepareStatement("SELECT password_cripto FROM usuarios WHERE user_name = ?");

            ps.setString(1, user_name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String senhaCriptografada = rs.getString("password_cripto");
                if (verificarSenha(password, senhaCriptografada)) {
                    return senhaCriptografada;
                } else {
                    JOptionPane.showMessageDialog(null, "As senhas não correspondem.");
                    return "Senha incorreta.";
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                return "Usuário não encontrado.";
            }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return "Erro ao autenticar o usuário.";
	    }
	}    
}
