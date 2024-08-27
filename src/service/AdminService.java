package service;

import java.util.List;
import java.util.UUID;

import dao.LogDao;
import dao.UsuarioDao;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.AdminLog;
import model.Usuario;

public class AdminService {
	private final UsuarioDao usuarioDao;
	private final LogDao logDao;

	public AdminService(UsuarioDao usuarioDao, LogDao logDao) {
		this.usuarioDao = usuarioDao;
		this.logDao = logDao;
	}
	
	public void createLog(UUID adminId, String action) throws InsercaoException {
        logDao.createLog(new AdminLog(adminId, action));
    }

    public List<AdminLog> getTodosLogsDeUmAdmin(UUID adminId) throws ConsultaException {
        return logDao.getTodosLogsDeUmAdmin(adminId);
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
