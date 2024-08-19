package service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import model.Aposta;
import model.Usuario;

import model.Palpite;

public class UsuarioService {
	
	private Usuario usuario;
	private Aposta aposta;
	private List<Palpite> palpites;
	
	public void apostar(BigDecimal valorApostado, List<Palpite> palpites) {
		this.aposta = new Aposta(valorApostado, palpites, false);
	}
	
	
	
	
}
