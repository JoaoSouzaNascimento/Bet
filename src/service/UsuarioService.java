package service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import dao.ApostaDaoPostgreSQL;
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
	
	private ApostaDaoPostgreSQL ApostaDao;
	private Usuario usuario;
	private Aposta aposta;
	
	// Manipualção do objeto aposta.
	// TODO Altera para adicionar o registro da aposta e seus palpites.
	public void apostar(BigDecimal valorApostado, List<Palpite> palpites) {
		this.aposta = new Aposta(valorApostado, palpites, false);
		
		try {
			ApostaDao.createAposta(UUID this.usuario.getId(),aposta);
		} catch (InsercaoException e) {
			e.printStackTrace();
		}
	}

	public void getApostaById(int apostaId) {
		
		if(apostaId != this.aposta.getId()) {
			try {
				this.aposta =  ApostaDao.getApostaById(apostaId);
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
			ApostaDao.deleteAposta(this.aposta.getId());
			this.aposta = null;
		} catch (DelecaoException e) {
			e.printStackTrace();
		}
	}
	
	public void atualizarValorApostado(BigDecimal NovoValorApostado) {
		this.aposta.setAmount(NovoValorApostado);
		
		try {
			ApostaDao.updateAposta(this.aposta);
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
			ApostaDao.deleteAposta(this.aposta.getId());
			this.aposta = null;
		} catch (DelecaoException e) {
			e.printStackTrace();
		}
	}
	
	public UsuarioService(Usuario usuario) {
		super();
		ApostaDao = new ApostaDaoPostgreSQL();;
		this.usuario = usuario;
		this.aposta = null;
	}
	
	
	
	
	
	
}
