package averagescore;

import children.Child;

public class KidAverageScoreStrategy implements AverageScoreStrategy {
    /**
     * calculates the averageScore for kid
     * @param child the kid that it calculates for
     * @return specific kid average score
     */
    @Override
    public Double getNiceScore(final Child child) {
        Double sum = 0.0;
        int nr = 0;

        for (Double score : child.getNiceScoreHistory()) {
            sum += score;
            nr++;
        }

        return sum / nr;
    }
}
