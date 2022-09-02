package averagescore;

import children.Child;
import enums.AgeCategory;

public class BabyAverageScoreStrategy implements AverageScoreStrategy {
    /**
     * calculates the averageScore for baby
     * @param child the baby that it calculates for
     * @return specific baby average score
     */
    @Override
    public Double getNiceScore(final Child child) {
        return AgeCategory.BABY_AVG_SCORE;
    }
}
