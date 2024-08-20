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
	private PalpiteDaoPostgreSQL palpiteDao;
	private Usuario usuario;
	private Aposta aposta;
	
	// Manipualção do objeto aposta.
	// TODO Altera para adicionar o registro da aposta e seus palpites.
	public void apostar(BigDecimal valorApostado) {
		this.aposta = new Aposta(valorApostado, false);
		
		try {
			apostaDao.createAposta(usuario.getId(),aposta);
			palpiteDao.createListaDePalpites(aposta.getId(), palpites);
		} catch (InsercaoException e) {
			e.printStackTrace();
		}
	}

	public void getApostaById(int apostaId) {
		
		if(apostaId != this.aposta.getId()) {
			try {
				this.aposta =  apostaDao.getApostaById(apostaId);
			} catch (ConsultaException e) {
				e.printStackTrace();
			}
		}
	}
	
	// TODO ainda falta implementar feature para resgate de todas as apostas por id do usuario;
	public void getTodasApostas() {
		try {
			//this.aposta =  ApostaDao.getAposta(apostaId);
		} catch (ConsultaException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirAposta() {
		try {
			apostaDao.deleteAposta(this.aposta.getId());
			this.aposta = null;
		} catch (DelecaoException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizarValorApostado(BigDecimal NovoValorApostado) {
		this.aposta.setAmount(NovoValorApostado);
		
		try {
			apostaDao.updateAposta(this.aposta);
		} catch (AtualizacaoException e) {
			e.printStackTrace();
		}
	}
	
	//Manipulação do objeto Palpite.
	
	public void addPalpite(Palpite palpite) {
		try {
			this.aposta.addPalpites(palpite);
			
		} catch (InsercaoException e) {
			e.printStackTrace();
		}
	}
	
	
	public void excluirPalpite(int palpitePartidaId) {
		try {
			apostaDao.deleteAposta(this.aposta.getId());
			this.aposta = null;
		} catch (DelecaoException e) {
			e.printStackTrace();
		}
	}
	
	public UsuarioService(Usuario usuario) {
		super();
		apostaDao = new ApostaDaoPostgreSQL();;
		this.usuario = usuario;
		this.aposta = null;
	}
	
	
	
	
	
	
}
