package api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import okhttp3.*;

import java.io.IOException;

public class FootballApiIntegration {

    private static final String API_KEY = "c3228facec7d0ee8fe14fc3b6d71742d"; // Substitua pela sua chave de API
    private static final String BASE_URL = "https://v3.football.api-sports.io";

    private final OkHttpClient client;
    private final Gson gson;

    public FootballApiIntegration() {
        this.client = new OkHttpClient();
        this.gson = new Gson();
    }

    public void fetchAndCreateMatches() throws IOException {
        String url = BASE_URL + "/fixtures?season=2024&league=39"; // Exemplo: Premier League (id 39) temporada 2024

        Request request = new Request.Builder()
                .url(url)
                .addHeader("x-apisports-key", API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JsonObject jsonResponse = gson.fromJson(responseBody, JsonObject.class);
                
                // Processando o JSON e criando os eventos (partidas)
                JsonArray matches = jsonResponse.getAsJsonArray("response");
                for (int i = 0; i < matches.size(); i++) {
                    JsonObject match = matches.get(i).getAsJsonObject();
                    createMatchFromJson(match);
                }
            } else {
                System.err.println("Erro ao fazer requisição: " + response.message());
            }
        }
    }

    private void createMatchFromJson(JsonObject matchJson) {
        JsonObject fixture = matchJson.getAsJsonObject("fixture");
        JsonObject teams = matchJson.getAsJsonObject("teams");

        String matchId = fixture.get("id").getAsString();
        String date = fixture.get("date").getAsString();
        String homeTeam = teams.getAsJsonObject("home").get("name").getAsString();
        String awayTeam = teams.getAsJsonObject("away").get("name").getAsString();

        // Exemplo: Criando o evento de partida na sua aplicação
        System.out.printf("Criando partida: %s vs %s em %s (ID: %s)\n", homeTeam, awayTeam, date, matchId);
        
        // Aqui você implementaria a lógica para salvar ou manipular as partidas na sua aplicação.
    }

    public static void main(String[] args) {
        FootballApiIntegration integration = new FootballApiIntegration();
        try {
            integration.fetchAndCreateMatches();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
