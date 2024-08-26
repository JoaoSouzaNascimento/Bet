package service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import api.dto.Bet;
import api.dto.Bookmaker;
import api.dto.FixtureResponse;
import api.dto.OddValue;
import api.dto.OddsResponse;
import model.Partida;
import model.ResultadoPartida;
import service.util.FixtureData;
import service.util.OddsData;

public class PartidaService {
	private final FootballApiService footballService;

	public PartidaService() {
		this.footballService = new FootballApiService("c3228facec7d0ee8fe14fc3b6d71742d", "v3.football.api-sports.io");
	}

	public FixtureData getFixtureData(FixtureResponse fixture) throws MalformedURLException {
		URL timeDeCasaLogo = URI.create(fixture.getTeams().getHome().getLogo()).toURL();
		URL timeDeForaLogo = URI.create(fixture.getTeams().getAway().getLogo()).toURL();

		String fixtureId = fixture.getFixture().getId();
		String data = fixture.getFixture().getDate();
		String timeDeForaNome = fixture.getTeams().getAway().getName();
		String timeDeCasaNome = fixture.getTeams().getHome().getName();
		String status = fixture.getFixture().getStatus().getLongName();
		boolean casaGanhou = Boolean.parseBoolean(fixture.getTeams().getHome().getWinner());
		boolean foraGanhou = Boolean.parseBoolean(fixture.getTeams().getHome().getWinner());

		return new FixtureData(fixtureId, data, timeDeCasaNome, timeDeCasaLogo, timeDeForaNome, timeDeForaLogo, status,
				casaGanhou, foraGanhou);
	}

	public OddsData getOddsData(OddsResponse oddsResponse) {
		for (Bookmaker bookmakerResponse : oddsResponse.getResponse()) {
			for (Bet bet : bookmakerResponse.getBets()) {
				if (bet.getName().equals("Match Winner")) {
					float timeDeCasaGanhaOdd = Float.parseFloat(bet.getValues().get(0).getOdd());
					float timeDeForaGanhaOdd = Float.parseFloat(bet.getValues().get(2).getOdd());
					float empateOdd = Float.parseFloat(bet.getValues().get(1).getOdd());

					return new OddsData(timeDeCasaGanhaOdd, timeDeForaGanhaOdd, empateOdd);
				}
			}
		}
		return null;
	}

	private Partida createPartida(FixtureData fixtureData, OddsData oddsData) {
		ResultadoPartida resultado = null;
		if (fixtureData.getStatus().equals("Match Finished")) {
			resultado = fixtureData.isCasaGanhou() ? ResultadoPartida.HOME_WIN
					: fixtureData.isForaGanhou() ? ResultadoPartida.AWAY_WIN : ResultadoPartida.DRAW;
		}

		return new Partida(fixtureData.getFixtureId(), fixtureData.getTimeDeCasaNome(), fixtureData.getTimeDeCasaLogo(),
				fixtureData.getTimeDeForaNome(), fixtureData.getTimeDeForaLogo(),
				OffsetDateTime.parse(fixtureData.getData()), fixtureData.getStatus(), resultado,
				oddsData.getTimeDeCasaGanhaOdd(), oddsData.getTimeDeForaGanhaOdd(), oddsData.getEmpateOdd());
	}

	public List<Partida> BuscarPartidasPorDia(String league, String season, String date, String timezone,
			String bookmaker) {
		List<Partida> partidas = new ArrayList<>();

		try {
			List<FixtureResponse> fixtures = footballService.getFixtures(league, season, date, timezone);

			for (FixtureResponse fixture : fixtures) {
				FixtureData fixtureData = getFixtureData(fixture);
				List<OddsResponse> oddsResponseLista = footballService.getOddsForFixture(fixtureData.getFixtureId(),
						league, season, bookmaker, timezone);

				for (OddsResponse oddsResponse : oddsResponseLista) {
					OddsData oddsData = getOddsData(oddsResponse);
					if (oddsData != null) {
						Partida partida = createPartida(fixtureData, oddsData);
						partidas.add(partida);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return partidas;
	}
}

//	public List<Partida> BuscarPartidasPorDia(String league, String season, String date, String timezone,
//			String bookmaker) {
//		List<Partida> partidas = new ArrayList<>();
//
//		try {
//			List<FixtureResponse> fixtures = footballService.getFixtures(league, season, date, timezone);
//
//			for (FixtureResponse fixture : fixtures) {
//				URL timeDeCasaLogo = URI.create(fixture.getTeams().getHome().getLogo()).toURL();
//				URL timeDeForaLogo = URI.create(fixture.getTeams().getAway().getLogo()).toURL();
//
//				String fixtureId = fixture.getFixture().getId();
//				String data = fixture.getFixture().getDate();
//				String timeDeForaNome = fixture.getTeams().getAway().getName();
//				String timeDeCasaNome = fixture.getTeams().getHome().getName();
//				String status = fixture.getFixture().getStatus().getLongName();
//				boolean casaGanhou = Boolean.parseBoolean(fixture.getTeams().getHome().getWinner());
//				boolean foraGanhou = Boolean.parseBoolean(fixture.getTeams().getHome().getWinner());
//
//				List<OddsResponse> oddsResponseLista = footballService.getOddsForFixture(fixtureId, league, season,
//						bookmaker, timezone);
//
//				for (OddsResponse oddsResponse : oddsResponseLista) {
//					for (Bookmaker bookmakerResponse : oddsResponse.getResponse()) {
//
//						for (Bet bet : bookmakerResponse.getBets()) {
//							if (bet.getName().equals("Match Winner")) {
//								float timeDeCasaGanhaOdd = Float.parseFloat(bet.getValues().get(0).getOdd());
//								float timeDeForaGanhaOdd = Float.parseFloat(bet.getValues().get(2).getOdd());
//								float empateOdd = Float.parseFloat(bet.getValues().get(1).getOdd());
//
//								ResultadoPartida resultado = null;
//
//								if (status.equals("Match Finished"))
//									resultado = casaGanhou ? ResultadoPartida.HOME_WIN
//											: foraGanhou ? ResultadoPartida.AWAY_WIN : ResultadoPartida.DRAW;
//
//								Partida partida = new Partida(fixtureId, timeDeCasaNome, timeDeCasaLogo, timeDeForaNome,
//										timeDeForaLogo, OffsetDateTime.parse(data), status, resultado,
//										timeDeCasaGanhaOdd, timeDeForaGanhaOdd, empateOdd);
//								partidas.add(partida);
//							}
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return partidas;
//	}

