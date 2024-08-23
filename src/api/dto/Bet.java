package api.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bet {
    @JsonProperty("name")
    private String name;

    @JsonProperty("values")
    private List<OddValue> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<OddValue> getValues() {
        return values;
    }

    public void setValues(List<OddValue> values) {
        this.values = values;
    }
}
