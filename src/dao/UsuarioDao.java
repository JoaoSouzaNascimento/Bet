package dao;

import java.sql.SQLException;
import java.util.List;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Usuario;

public interface UsuarioDao {
	public Usuario createUsuario(Usuario usuario) throws InsercaoException;
	public Usuario getUsuarioByEmail(String email) throws ConsultaException;
	public Usuario updateUsuario(Usuario usuario) throws AtualizacaoException;
	public void deleteUsuario(String id) throws DelecaoException;
	public List<Usuario> getAllUsuarios() throws ConsultaException;
}
