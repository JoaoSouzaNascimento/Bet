package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EndPointFixtureResponse {
    @JsonProperty("response")
    private List<FixtureResponse> response;

    public List<FixtureResponse> getResponse() {
        return response;
    }

    public void setResponse(List<FixtureResponse> response) {
        this.response = response;
    }
}
