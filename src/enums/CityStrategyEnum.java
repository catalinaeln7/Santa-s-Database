package enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum CityStrategyEnum {
    @JsonProperty("niceScoreCity")
    NICE_SCORE_CITY(),

    @JsonProperty("id")
    ID(),

    @JsonProperty("niceScore")
    NICE_SCORE();

    CityStrategyEnum() {
    }
}
