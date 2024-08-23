package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fixture {
	
	@JsonProperty("id")
    private String id;
	
    @JsonProperty("date")
    private String date;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
