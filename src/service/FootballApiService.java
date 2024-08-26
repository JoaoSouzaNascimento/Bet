package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import api.dto.Bookmaker;
import api.dto.EndPointFixtureResponse;
import api.dto.EndpointOddsResponse;
import api.dto.FixtureResponse;
import api.dto.OddsResponse;

public class FootballApiService {
	private final HttpClient client;
	private final ObjectMapper objectMapper;
	private final String apiKey;
	private final String apiHost;

	public FootballApiService(String apiKey, String apiHost) {
		this.client = HttpClient.newHttpClient();
		this.objectMapper = new ObjectMapper();
		this.apiKey = apiKey;
		this.apiHost = apiHost;
	}

	public List<FixtureResponse> getFixtures(String league, String season, String date, String timezone)
			throws Exception {
		String apiUrl = String.format(
				"https://v3.football.api-sports.io/fixtures?league=%s&season=%s&date=%s&timezone=%s", league, season,
				date, timezone);

		HttpRequest request = HttpRequest.newBuilder().uri(new URI(apiUrl)).header("x-rapidapi-key", apiKey)
				.header("x-rapidapi-host", apiHost).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() == 200) {
			EndPointFixtureResponse apiResponse = objectMapper.readValue(response.body(),
					EndPointFixtureResponse.class);
			return apiResponse.getResponse();
		} else {
			throw new RuntimeException("Erro na requisição: " + response.statusCode());
		}
	}

	public List<OddsResponse> getOddsForFixture(String fixtureId, String league, String season, String bookmaker,
			String timezone) throws Exception {
		String apiUrl = String.format(
				"https://v3.football.api-sports.io/odds?league=%s&season=%s&fixture=%s&bookmaker=%s&timezone=%s",
				league, season, fixtureId, bookmaker, timezone);

		HttpRequest request = HttpRequest.newBuilder().uri(new URI(apiUrl)).header("x-rapidapi-key", apiKey)
				.header("x-rapidapi-host", apiHost).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() == 200) {
			return objectMapper.readValue(response.body(), EndpointOddsResponse.class).getResponse();
		} else {
			throw new RuntimeException("Erro na requisição: " + response.statusCode());
		}
	}

	public FixtureResponse getFixtureById(String fixtureId) throws Exception {
		String apiUrl = String.format("https://v3.football.api-sports.io/fixtures?id=%s", fixtureId);

		HttpRequest request = HttpRequest.newBuilder().uri(new URI(apiUrl)).header("x-rapidapi-key", apiKey)
				.header("x-rapidapi-host", apiHost).GET().build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

		if (response.statusCode() == 200) {
			EndPointFixtureResponse apiResponse = objectMapper.readValue(response.body(),
					EndPointFixtureResponse.class);
			return apiResponse.getResponse().get(0);
		} else {
			throw new RuntimeException("Erro na requisição: " + response.statusCode());
		}
	}

	

}
