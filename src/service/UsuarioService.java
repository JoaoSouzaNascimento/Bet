package service;

import java.util.List;

import dao.UsuarioDaoPostgreSQL;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import model.Usuario;

//Atribuições de um usuários:
//	Realizar uma aposta
//	Mostrar seus atributos:
//		Mostar suas apostas
//	Poder dar vários palpites em uma partida
//	Poder cancelar determinado palpite em uma aposta, desde que a partida não tenha começado.

public class UsuarioService {
	
	private UsuarioDaoPostgreSQL usuarioDao;
	
	public UsuarioService() {
		super();
		usuarioDao = new UsuarioDaoPostgreSQL();
	}
	
	public Usuario getUsuarioPeloEmail(String email) {
		try {
			return usuarioDao.getUsuarioByEmail(email);
		} catch (ConsultaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void editarUsuarioNome(Usuario usuario, String username) {
		//TODO Adicionar validações
		
		usuario.setUsername(username);
		
		try {
			usuarioDao.updateUsuario(usuario);
		} catch (AtualizacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editarUsuarioApelido(Usuario usuario, String apelido) {
		//TODO Adicionar validações
		
		usuario.setNickname(apelido);
		
		try {
			usuarioDao.updateUsuario(usuario);
		} catch (AtualizacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editarUsuarioSenha(Usuario usuario, String senhaVelha, String novaSenha, String email) {
		//TODO Adicionar validações
		
		usuario.setPassword(novaSenha);
		
		try {
			usuarioDao.updateUsuario(usuario);
		} catch (AtualizacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void editarUsuarioEmail(Usuario usuario, String email) {
		//TODO Adicionar validações
		
		usuario.setEmail(email);
		
		try {
			usuarioDao.updateUsuario(usuario);
		} catch (AtualizacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletarUsuario(Usuario usuario, String email) {
		//TODO Adicionar validações
		
		usuario.setDeleted(true);
		
		try {
			usuarioDao.deleteUsuario(usuario.getId());
		} catch (DelecaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listarTodosUsuarios() {
		try {
			usuarioDao.getTodosUsuarios();
		} catch (ConsultaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Usuario> listarTodosUsuarioAtivos(){ 
		
		try {
			return usuarioDao.getTodosUsuariosAtivos();
		} catch (ConsultaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Usuario> listarTodosUsuarioInativos(){ 
		
		try {
			return usuarioDao.getTodosUsuariosInativos();
		} catch (ConsultaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
