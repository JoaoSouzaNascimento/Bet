package model;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;

public class Partida {
	private String id;
	private String teamHome;
	private URL teamHomeLogo;
	private String teamAway;
	private URL teamAwayLogo;
	private OffsetDateTime data;
	private String status;
	private ResultadoPartida resultado;
	private float homeWinOdd;
	private float awayWinOdd;
	private float drawOdd;

	public Partida(String id, String teamHome, URL teamHomeLogo, String teamAway, URL teamAwayLogo, OffsetDateTime data,
			String status, ResultadoPartida resultado, float homeWinOdd, float awayWinOdd, float drawOdd) {
		super();
		this.id = id;
		this.teamHome = teamHome;
		this.teamHomeLogo = teamHomeLogo;
		this.teamAway = teamAway;
		this.teamAwayLogo = teamAwayLogo;
		this.data = data;
		this.status = status;
		this.resultado = resultado;
		this.homeWinOdd = homeWinOdd;
		this.awayWinOdd = awayWinOdd;
		this.drawOdd = drawOdd;
	}

	public String getId() {
		return id;
	}

	public String getTeamHome() {
		return teamHome;
	}

	public URL getTeamHomeLogo() {
		return teamHomeLogo;
	}

	public String getTeamAway() {
		return teamAway;
	}

	public URL getTeamAwayLogo() {
		return teamAwayLogo;
	}

	public OffsetDateTime getData() {
		return data;
	}

	public String getStatus() {
		return status;
	}

	public ResultadoPartida getResultado() {
		return resultado;
	}

	public float getHomeWinOdd() {
		return homeWinOdd;
	}

	public float getAwayWinOdd() {
		return awayWinOdd;
	}

	public float getDrawOdd() {
		return drawOdd;
	}
	
	
}