package service;

import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import dao.UsuarioDaoPostgreSQL;
import exceptions.ConsultaException;
import exceptions.InsercaoException;
import exceptions.LoginException;
import model.Usuario;

public class AuthService {
	private UsuarioDaoPostgreSQL usuarioDao;
	
	public AuthService() {
		this.usuarioDao = new UsuarioDaoPostgreSQL();
	}
	
	public Usuario login(String email, String password) throws LoginException, ConsultaException {
		UsuarioDaoPostgreSQL usuarioDao = new UsuarioDaoPostgreSQL();
		
		
		Usuario usuario = null;
		
		try {
			usuario = usuarioDao.getUsuarioByEmail(email);
		} catch (ConsultaException e) {
			throw e;
		}
		
		if(!BCrypt.checkpw(password, usuario.getPassword())) {
			throw new LoginException("Senha ou Usuário incorreto");
		}
		
		return usuario;
	}
	
	public void cadastro(String username, String nickname, String email, String password) throws LoginException {
	    UsuarioDaoPostgreSQL usuarioDao = new UsuarioDaoPostgreSQL();
	    
	    // valida
	    if (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
	        throw new IllegalArgumentException("Nome de usuário, email e senha são obrigatórios.");
	    }
	    
	    // gera uuid
	    UUID id = UUID.randomUUID();
	    
	    // hash da senha com bcrypt
	    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	    
	    // cria objeto usuario
	    Usuario usuario = new Usuario(
	        id, 
	        username, 
	        nickname, 
	        hashedPassword, 
	        email
	    );
	    
	    // tenta criar usuario no banco
	    try {
	        usuarioDao.createUsuario(usuario);
	    } catch (InsercaoException e) {
	        e.printStackTrace();
	        throw new LoginException("Erro ao criar o usuário: " + e.getMessage());
	    }
	}
	
}
