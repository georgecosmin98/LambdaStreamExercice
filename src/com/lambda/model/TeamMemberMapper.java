package com.lambda.model;

import java.util.List;

public class TeamMemberMapper {
    private List<String> names;
    private Position position;
    private float maxIncome;
    private float totalIncome;

    public TeamMemberMapper() {
    }

    public TeamMemberMapper(List<String> names, Position position, float maxIncome, float totalIncome) {
        this.names = names;
        this.position = position;
        this.maxIncome = maxIncome;
        this.totalIncome = totalIncome;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public float getMaxIncome() {
        return maxIncome;
    }

    public void setMaxIncome(float maxIncome) {
        this.maxIncome = maxIncome;
    }

    public float getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(float totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String toString() {
        return "Position: " + position +
                "\n" +
                "Names of persons: " + names +
                ", max income: " + maxIncome +
                ", total income: " + totalIncome + "\n";
    }
}
