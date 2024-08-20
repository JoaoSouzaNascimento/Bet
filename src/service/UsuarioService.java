package service;

import java.math.BigDecimal;
import java.util.List;
import dao.ApostaDaoPostgresSQL;
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
	
	private ApostaDaoPostgresSQL ApostaDao;
	private Usuario usuario;
	private Aposta aposta;
	
	// Manipualção do objeto aposta.
	// TODO Altera para adicionar o registro da aposta e seus palpites.
	public void apostar(BigDecimal valorApostado, List<Palpite> palpites) {
		this.aposta = new Aposta(valorApostado, palpites, false);
		
		try {
			ApostaDao.createAposta(aposta);
		} catch (InsercaoException e) {
			e.printStackTrace();
		}
	}

	public void getApostaById(int apostaId) {
		try {
			this.aposta =  ApostaDao.getApostaById(apostaId);
		} catch (ConsultaException e) {
			e.printStackTrace();
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
	//TODO Implmentar lógica de Palpite,pois é necessário para manipulação.
	public void addPalpite(Palpite palpite) {
		try {
			this.aposta.addPalpites(palpite);
			
		} catch (DelecaoException e) {
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
		ApostaDao = new ApostaDaoPostgresSQL();;
		this.usuario = usuario;
		this.aposta = null;
	}
	
	
	
	
	
	
}
