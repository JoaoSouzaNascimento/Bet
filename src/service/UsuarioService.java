package service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import dao.ApostaDaoPostgreSQL;
import dao.PalpiteDaoPostgreSQL;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Aposta;
import model.Usuario;
import model.Palpite;

//Atribuições de um usuários:
//  Intanciado sempre que após o login de usuário
//	Realiza as ações atribuidas a aposta.
//	Realiza as ações atribuidas a transções.
//	Mostra e Manipula seus atributos.

public class UsuarioService {
	
	private ApostaDaoPostgreSQL apostaDao;
	private Usuario usuario;
	
	
	public UsuarioService(Usuario usuario) {
		super();
		apostaDao = new ApostaDaoPostgreSQL();;
		this.usuario = usuario;
		this.aposta = null;
	}
	
	
	
	
	
	
}
