package service;

import java.util.List;
import java.util.UUID;

import dao.UsuarioDaoPostgreSQL;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import model.Usuario;

public class UsuarioService {

	private UsuarioDaoPostgreSQL usuarioDao;

	public UsuarioService() {
		super();
		usuarioDao = new UsuarioDaoPostgreSQL();
	}

	public void atualizarUsuario(Usuario usuario) {
		try {
			usuarioDao.updateUsuario(usuario);
		} catch (AtualizacaoException e) {
			e.printStackTrace();
		}
	}

	public void editarUsuarioNome(String email, String username) throws ConsultaException, AtualizacaoException {
		// TODO: Adicionar validações
		System.out.println("Editando usuário com ID: " + email);
		Usuario usuario = usuarioDao.getUsuarioByEmail(email); // Pode lançar ConsultaException
		if (usuario != null) {
			usuario.setUsername(username);
			usuarioDao.updateUsuario(usuario); // Atualiza o usuário no banco
		} else {
			throw new AtualizacaoException("Usuário não encontrado.");
		}
	}

	public void editarUsuarioApelido(Usuario usuario, String apelido) {
		// TODO Adicionar validações

		usuario.setNickname(apelido);

		try {
			usuarioDao.updateUsuario(usuario);
		} catch (AtualizacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editarUsuarioSenha(Usuario usuario, String senhaVelha, String novaSenha, String email) {
		// TODO Adicionar validações

		usuario.setPassword(novaSenha);

		try {
			usuarioDao.updateUsuario(usuario);
		} catch (AtualizacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editarUsuarioEmail(Usuario usuario, String email) {
		// TODO Adicionar validações

		usuario.setEmail(email);

		try {
			usuarioDao.updateUsuario(usuario);
		} catch (AtualizacaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deletarUsuario(Usuario usuario, String email) {
		// TODO Adicionar validações

		usuario.setDeleted(true);

		try {
			usuarioDao.deleteUsuario(usuario.getId());
		} catch (DelecaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void editarUsuarioEmail(UUID usuarioId, String email) throws ConsultaException, AtualizacaoException {
		// TODO: Adicionar validações para o e-mail
		System.out.println("Editando e-mail do usuário com ID: " + usuarioId);
		Usuario usuario = usuarioDao.getUsuarioById(usuarioId); // Pode lançar ConsultaException
		if (usuario != null) {
			usuario.setEmail(email);
			usuarioDao.updateUsuario(usuario); // Atualiza o usuário no banco
		} else {
			throw new AtualizacaoException("Usuário não encontrado.");
		}
	}
}
