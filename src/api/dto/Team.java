package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Team {
	@JsonProperty("name")
	private String name;

	@JsonProperty("logo")
	private String logo;

	@JsonProperty("winner")
	private String winner;

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
        this.winner = winner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
}