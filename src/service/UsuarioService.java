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
//	Realizar uma aposta
//	Mostrar seus atributos:
//		Mostar suas apostas
//	Poder dar vários palpites em uma partida
//	Poder cancelar determinado palpite em uma aposta, desde que a partida não tenha começado.

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
