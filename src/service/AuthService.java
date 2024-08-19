package service;

import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import dao.UsuarioDaoPostgreSQL;
import exceptions.ConsultaException;
import exceptions.InsercaoException;
import exceptions.LoginException;
import model.Usuario;

public class AuthService {
	
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
		
		// TODO adicionar validações aq. 
		
		Usuario usuario = new Usuario(
				UUID.fromString(email), 
				username,
				nickname, 
				BCrypt.hashpw(password, BCrypt.gensalt()), 
				email);
		
		if(!BCrypt.checkpw(password, usuario.getPassword())) {
			throw new LoginException("Senha ou Usuário incorreto");
		}
		
		try {
			usuario = usuarioDao.createUsuario(usuario);
		} catch (InsercaoException e) {
			e.printStackTrace();
		}
	}
	
}
