package averagescore;

import children.Child;

public interface AverageScoreStrategy {
    /**
     * calculates the averageScore for child
     * @param child the one that it calculates for
     */
    Double getNiceScore(Child child);
}
