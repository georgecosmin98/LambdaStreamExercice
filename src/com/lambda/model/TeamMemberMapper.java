package com.lambda.model;

public class TeamMemberMapper {
    private String name;
    private Position position;
    private float maxIncome;
    private float totalIncome;

    public TeamMemberMapper() {
    }

    public TeamMemberMapper(String name, Position position, float maxIncome, float totalIncome) {
        this.name = name;
        this.position = position;
        this.maxIncome = maxIncome;
        this.totalIncome = totalIncome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
