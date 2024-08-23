package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OddsResponse {
    @JsonProperty("bookmakers")
    private List<Bookmaker> bookmaker;

    public List<Bookmaker> getResponse() {
        return bookmaker;
    }

    public void setResponse(List<Bookmaker> bookmaker) {
        this.bookmaker = bookmaker;
    }
    
}

