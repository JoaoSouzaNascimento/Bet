package api;

import java.util.List;

import api.dto.Bet;
import api.dto.FixtureResponse;
import api.dto.OddValue;
import api.dto.OddsResponse;
import api.dto.Bookmaker;
import service.FootballApiService;

import java.util.List;
import java.util.Scanner;

public class FootballApiIntegration {
    public static void main(String[] args) {

        String apiKey = "c3228facec7d0ee8fe14fc3b6d71742d";
        String apiHost = "v3.football.api-sports.io";

        FootballApiService apiService = new FootballApiService(apiKey, apiHost);

        try {
            System.out.println("Enviando requisição de fixtures...");
            List<FixtureResponse> fixtures = apiService.getFixtures("71", "2024", "2024-08-24", "America/Bahia");

            for (FixtureResponse fixture : fixtures) {
                String fixtureId = String.valueOf(fixture.getFixture().getId());

                System.out.println("Buscando odds para fixture ID: " + fixtureId);
                List<OddsResponse> oddsResponse = apiService.getOddsForFixture(fixtureId, "71", "2024", "3", "America/Bahia");
                
                for(OddsResponse oddsResponse2 : oddsResponse) {
                for (Bookmaker bookmakerResponse : oddsResponse2.getResponse()) {
           
                    for (Bet bet : bookmakerResponse.getBets()) {
                        if (bet.getName().equals("Match Winner")) {
                            for (OddValue value : bet.getValues()) {
                                System.out.println("Resultado: " + value.getValue() + " - Odd: " + value.getOdd());
                            }
                        }
                    }
                }
                }
                System.out.println("-----------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Fim do programa");
    }
}
