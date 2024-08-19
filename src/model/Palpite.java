package model;

import java.util.Objects;

public class Palpite {

	private int partidaId;
	private ResultadoPartida resultado;
		
	public Palpite(int partidaId, ResultadoPartida resultado) {
		super();
		this.partidaId = partidaId;
		this.resultado = resultado;
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

	
	@Override
	public String toString() {
		return "Palpite [partidaId=" + partidaId + ", resultado=" + resultado + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palpite other = (Palpite) obj;
		return partidaId == other.partidaId && resultado == other.resultado;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(partidaId, resultado);
	}
	
}
