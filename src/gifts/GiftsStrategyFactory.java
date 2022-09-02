package gifts;

import enums.CityStrategyEnum;

public final class GiftsStrategyFactory {
    private GiftsStrategyFactory() { }
    /**
     * creates strategy
     * @param strategyEnum its determines which strategy should be adopted for sharing gifts
     * @return created strategy
     */
    public static GiftsStrategy createGiftsStrategy(final CityStrategyEnum strategyEnum) {
        switch (strategyEnum) {
            case NICE_SCORE_CITY: return new NiceScoreCityGiftsStrategy();
            case ID: return new IdGiftsStrategy();
            case NICE_SCORE: return new NiceScoreGiftsStrategy();
            default:
        }
        throw new IllegalArgumentException("Strategy type not recognized.");
    }
}
