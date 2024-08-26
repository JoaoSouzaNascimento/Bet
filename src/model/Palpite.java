package model;

import java.util.Objects;

public class Palpite {

	private int partidaId;
	private ResultadoPartida resultado;
	private float odd;
		
	public Palpite(int partidaId, ResultadoPartida resultado, float odd) {
		super();
		this.partidaId = partidaId;
		this.resultado = resultado;
		this.odd = odd;
	}
	
	public int getPartidaId() {
		return partidaId;
	}
	public ResultadoPartida getResultado() {
		return resultado;
	}

	public float getOdd() {
		return odd;
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
