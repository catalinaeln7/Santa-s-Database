package enums;

import com.fasterxml.jackson.annotation.JsonProperty;


public enum ElvesType {

    @JsonProperty("yellow")
    YELLOW(),

    @JsonProperty("black")
    BLACK(),

    @JsonProperty("pink")
    PINK(),

    @JsonProperty("white")
    WHITE();

    ElvesType() {
    }
}
