package averagescore;

import children.Child;

public class TeenAverageScoreStrategy implements AverageScoreStrategy {
    /**
     * calculates the averageScore for teen
     * @param child the teen that it calculates for
     * @return specific teen average score
     */
    @Override
    public Double getNiceScore(final Child child) {
        double sum = 0.0;
        double nrScores = child.getNiceScoreHistory().size();
        for (int i = 0; i < nrScores; ++i) {
            sum += child.getNiceScoreHistory().get(i) * (i + 1);
        }
        return sum / ((nrScores + 1) * nrScores / 2);
    }
}
