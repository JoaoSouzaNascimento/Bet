package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Partida {
	private int id;
	private int teamHome;
	private int teamAway;
	private LocalDate data;
	private LocalTime time;
	private StatusPartida status;
	private ResultadoPartida resultado;
	private float homeWinOdd;
	private float awayWinOdd;
	private float drawOdd;
	
	public int getTeamHome() {
		return teamHome;
	}
	public void setTeamHome(int teamHome) {
		this.teamHome = teamHome;
	}
	public int getTeamAway() {
		return teamAway;
	}
	public void setTeamAway(int teamAway) {
		this.teamAway = teamAway;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public StatusPartida getStatus() {
		return status;
	}
	public void setStatus(StatusPartida status) {
		this.status = status;
	}
	public ResultadoPartida getResultado() {
		return resultado;
	}
	public void setResultado(ResultadoPartida resultado) {
		this.resultado = resultado;
	}
	public float getHomeWinOdd() {
		return homeWinOdd;
	}
	public void setHomeWinOdd(float homeWinOdd) {
		this.homeWinOdd = homeWinOdd;
	}
	public float getAwayWinOdd() {
		return awayWinOdd;
	}
	public void setAwayWinOdd(float awayWinOdd) {
		this.awayWinOdd = awayWinOdd;
	}
	public float getDrawOdd() {
		return drawOdd;
	}
	public void setDrawOdd(float drawOdd) {
		this.drawOdd = drawOdd;
	}
	public int getId() {
		return id;
	}
	
	
	public Partida(int id, int teamHome, int teamAway, LocalDate data, LocalTime time, StatusPartida status,
			ResultadoPartida resultado, float homeWinOdd, float awayWinOdd, float drawOdd) {
		super();
		this.id = id;
		this.teamHome = teamHome;
		this.teamAway = teamAway;
		this.data = data;
		this.time = time;
		this.status = status;
		this.resultado = resultado;
		this.homeWinOdd = homeWinOdd;
		this.awayWinOdd = awayWinOdd;
		this.drawOdd = drawOdd;
	}
	@Override
	public String toString() {
		return "Partida [id=" + id + ", teamHome=" + teamHome + ", teamAway=" + teamAway + ", data=" + data + ", time="
				+ time + ", status=" + status + ", resultado=" + resultado + ", homeWinOdd=" + homeWinOdd
				+ ", awayWinOdd=" + awayWinOdd + ", drawOdd=" + drawOdd + "]";
	}

	
}