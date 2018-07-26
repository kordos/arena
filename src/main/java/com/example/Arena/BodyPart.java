package com.example.Arena;

public enum BodyPart {
    HEAD(5, 3),
    LEFT_ARM(10, 1),
    RIGHT_ARM(10, 1),
    TRUNK(30, 0),
    LEFT_LEG(5, 2),
    RIGHT_LEG(5, 2);

    /**
     * Probability of hit in percentage
     */
    private int probability;

    /**
     * Bonus points for hit.
     */
    private int bonusPoints;

    BodyPart(int probability, int bonusPoints) {
        this.probability = probability;
        this.bonusPoints = bonusPoints;
    }

    public int getProbability() {
        return probability;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }
}
