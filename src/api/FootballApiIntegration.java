package api;

import java.util.List;

import api.dto.Bet;
import api.dto.FixtureResponse;
import api.dto.OddValue;
import api.dto.OddsResponse;
import model.Partida;
import api.dto.Bookmaker;
import service.FootballApiService;
import service.PartidaService;

import java.util.List;
import java.util.Scanner;

public class FootballApiIntegration {
	public static void main(String[] args) {

		String apiKey = "c56b9a36c9f8d7ccacc12acca78c5c1f";
		String apiHost = "v3.football.api-sports.io";

		FootballApiService apiService = new FootballApiService(apiKey, apiHost);
		PartidaService partidaService = new PartidaService();

		List<Partida> partidas = partidaService.BuscarPartidasPorDia("71", "2024", "2024-08-26", "America/Bahia", "3");


for (Partida partida : partidas) {
    System.out.println("Fixture ID: " + partida.getId());
    System.out.println("Time de Casa Nome: " + partida.getTeamHome());
    System.out.println("Time de Casa Logo: " + partida.getTeamHomeLogo());
    System.out.println("Time de Fora Nome: " + partida.getTeamAway());
    System.out.println("Time de Fora Logo: " + partida.getTeamAwayLogo());
    System.out.println("Data: " + partida.getData());
    System.out.println("Status: " + partida.getStatus());
    System.out.println("Resultado: " + partida.getResultado());
    System.out.println("ResultadoHome: " + partida.getHomeWinOdd());
    System.out.println("ResultadoAway: " + partida.getAwayWinOdd());
    System.out.println("ResultadoDraw: " + partida.getDrawOdd());
    System.out.println("-----------------------------------------");
}

		
//        try {
//            System.out.println("Enviando requisição de fixtures...");
//            List<FixtureResponse> fixtures = apiService.getFixtures("71", "2024", "2024-08-24", "America/Bahia");
//
//            for (FixtureResponse fixture : fixtures) {
//                String fixtureId = String.valueOf(fixture.getFixture().getId());
//
//                System.out.println("Buscando odds para fixture ID: " + fixtureId);
//                List<OddsResponse> oddsResponse = apiService.getOddsForFixture(fixtureId, "71", "2024", "3", "America/Bahia");
//                
//                for(OddsResponse oddsResponse2 : oddsResponse) {
//                for (Bookmaker bookmakerResponse : oddsResponse2.getResponse()) {
//           
//                    for (Bet bet : bookmakerResponse.getBets()) {
//                        if (bet.getName().equals("Match Winner")) {
//                            for (OddValue value : bet.getValues()) {
//                                System.out.println("Resultado: " + value.getValue() + " - Odd: " + value.getOdd());
//                            }
//                        }
//                    }
//                }
//                }
//                System.out.println("-----------------------------------------");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("Fim do programa");
	}
}
