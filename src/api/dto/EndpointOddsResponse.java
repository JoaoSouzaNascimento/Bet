package api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EndpointOddsResponse {
	 @JsonProperty("response")
	 private List<OddsResponse> response;

	public List<OddsResponse> getResponse() {
		return response;
	}

	public void setResponse(List<OddsResponse> response) {
		this.response = response;
	}
	 
}
