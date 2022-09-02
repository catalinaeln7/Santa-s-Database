package averagescore;

import children.Child;
import enums.AgeCategory;

public final class AverageScoreStrategyFactory {
    private AverageScoreStrategyFactory() { }
    /**
     * creates strategy
     * @param child its ageCategory determines which strategy should be adopted
     * @return created strategy
     */
    public static AverageScoreStrategy createAverageScoreStrategy(final Child child) {
        switch (child.getAgeCategory()) {
            case AgeCategory.BABY: return new BabyAverageScoreStrategy();
            case AgeCategory.KID: return new KidAverageScoreStrategy();
            case AgeCategory.TEEN: return new TeenAverageScoreStrategy();
            default:
        }
        throw new IllegalArgumentException("Strategy type not recognized.");
    }
}
