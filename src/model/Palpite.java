package model;

import java.math.BigDecimal;
import java.util.Objects;

public class Palpite {
	
	private int apostaId;
	private int partidaId;
	private ResultadoPartida resultado;
	private BigDecimal odd;
		
	public Palpite(int partidaId, int apostaIDId,ResultadoPartida resultado, BigDecimal odd) {
		super();
		this.apostaId = apostaId;
		this.partidaId = partidaId;
		this.resultado = resultado;
		this.odd = odd;
	}

	public int getApostaId() {
		return apostaId;
	}

	public void setApostaId(int apostaId) {
		this.apostaId = apostaId;
	}

	public int getPartidaId() {
		return partidaId;
	}

	public void setPartidaId(int partidaId) {
		this.partidaId = partidaId;
	}

	public ResultadoPartida getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoPartida resultado) {
		this.resultado = resultado;
	}

	public BigDecimal getOdd() {
		return odd;
	}

	public void setOdd(BigDecimal odd) {
		this.odd = odd;
	}
	
	
}
