package service;

import java.util.List;
import java.util.UUID;

import dao.UsuarioDao;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Usuario;

public class AdminService {
	private final UsuarioDao usuarioDao;

	public AdminService(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public Usuario getUsuarioById(UUID id) throws ConsultaException {
		return usuarioDao.getUsuarioById(id);
	}

	public List<Usuario> getTodosUsuarios() throws ConsultaException {
		return usuarioDao.getTodosUsuarios();
	}

	public void ativarUsuario(UUID id) throws AtualizacaoException {
		usuarioDao.ativarUsuario(id);
	}

	public List<Usuario> listarTodosUsuarioAtivos() throws ConsultaException {
		return usuarioDao.getTodosUsuariosAtivos();
	}

	public List<Usuario> listarTodosUsuarioInativos() throws ConsultaException {

		return usuarioDao.getTodosUsuariosInativos();
	}

	public Usuario getUsuarioPeloEmail(String email) throws ConsultaException {

		return usuarioDao.getUsuarioByEmail(email);
	}
	
	public void listarTodosUsuarios() throws ConsultaException {
			usuarioDao.getTodosUsuarios();
	}
}
