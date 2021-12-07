package com.lambda.model;

public class TeamMember {
    private String name;
    private float salaryPerHour;
    private int workedHours;
    private Position position;

    public TeamMember() {
    }
    public TeamMember(String name, float salaryPerHour, int workedHours, Position position) {
        this.name = name;
        this.salaryPerHour = salaryPerHour;
        this.workedHours = workedHours;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(float salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public int getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "TeamMember{" +
                "name='" + name + '\'' +
                ", salaryPerHour=" + salaryPerHour +
                ", workedHours=" + workedHours +
                ", position=" + position +
                '}';
    }
}
