package service;

import java.math.BigDecimal;
import java.util.UUID;

import org.mindrot.jbcrypt.BCrypt;

import dao.UsuarioDao;
import dao.UsuarioDaoPostgreSQL;
import exceptions.CadastroException;
import exceptions.ConsultaException;
import exceptions.InsercaoException;
import exceptions.LoginException;
import model.CargoUsuario;
import model.Usuario;

public class AuthService {
	private UsuarioDao usuarioDao;
	
	public AuthService(UsuarioDao usuarioDao) {
		  this.usuarioDao = usuarioDao;
	}
	
	public Usuario login(String email, String password) throws LoginException, ConsultaException {
        Usuario usuario = null;
		
		try {
			usuario = usuarioDao.getUsuarioByEmail(email);
			if(usuario == null)
				throw new ConsultaException("Usuário não encontrado");
		} catch (ConsultaException e) {
			throw e;
		}
		
		if(!BCrypt.checkpw(password, usuario.getPassword())) {
			throw new LoginException("Senha ou Usuário incorreto");
		}
		
		return usuario;
	}
	
	public Usuario cadastro(String username, String nickname, String email, String password, CargoUsuario role) throws CadastroException {
	    
		if (username == null || username.isEmpty() || nickname == null || nickname.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty()) {
	        throw new CadastroException("Todos os campos são obrigatórios");
	    }

	    if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
	        throw new CadastroException("Email inválido");
	    }

	    if (!username.matches("[A-Za-zÀ-ÖØ-öø-ÿ ']*")) {
	        throw new CadastroException("Nome de usuário inválido");
	    }
	    
	    if (nickname.length() > 16) {
	        throw new CadastroException("Apelido deve ter no máximo 16 caracteres");
	    }

	    
	    try {
	        Usuario existingUser = usuarioDao.getUsuarioByEmail(email);
	        if (existingUser != null) {
	            throw new CadastroException("Email já está em uso");
	        }
	    } catch (ConsultaException e) {
	        throw new CadastroException("Erro ao verificar a existência do usuário: " + e.getMessage());
	    }
		
		Usuario usuario = new Usuario(UUID.randomUUID(), username, nickname, BCrypt.hashpw(password, BCrypt.gensalt()), email, BigDecimal.valueOf(0), false, role);
	    try {
	        usuarioDao.createUsuario(usuario);
		} catch (InsercaoException e) {
			throw new CadastroException("Erro ao criar o usuário: " + e.getMessage());
		}
	    
	    return usuario;
	}
	
}
