package com.lambda.service;

import com.lambda.model.Position;
import com.lambda.model.TeamMember;
import com.lambda.model.TeamMemberMapper;

import java.util.*;
import java.util.stream.Collectors;

public class TeamMemberServiceImpl {

    private List<TeamMember> devs = new ArrayList<>();
    private List<TeamMember> testers = new ArrayList<>();
    private List<TeamMember> scrums = new ArrayList<>();
    private TeamMemberMapper devsMapper = new TeamMemberMapper();
    private TeamMemberMapper testerMapper = new TeamMemberMapper();
    private TeamMemberMapper scrumsMapper = new TeamMemberMapper();
    private List<TeamMember> teamMemberList = new ArrayList<>();
    private List<TeamMemberMapper> teamMemberMapperList = new ArrayList<>();

    private void init() {
        TeamMember teamMember1 = new TeamMember("Luka", 8.5f, 8, Position.DEVELOPER);
        TeamMember teamMember2 = new TeamMember("Andrew", 8.5f, 8, Position.DEVELOPER);
        TeamMember teamMember3 = new TeamMember("Diana", 7.3f, 8, Position.DEVELOPER);
        TeamMember teamMember4 = new TeamMember("James", 8.3f, 6, Position.TESTER);
        TeamMember teamMember5 = new TeamMember("Robert", 6.3f, 8, Position.TESTER);
        TeamMember teamMember6 = new TeamMember("Patricia", 9.3f, 8, Position.SCRUM_MASTER);
        TeamMember teamMember7 = new TeamMember("Jennifer", 8.7f, 8, Position.SCRUM_MASTER);

        teamMemberList.add(teamMember1);
        teamMemberList.add(teamMember2);
        teamMemberList.add(teamMember3);
        teamMemberList.add(teamMember4);
        teamMemberList.add(teamMember5);
        teamMemberList.add(teamMember6);
        teamMemberList.add(teamMember7);

// Third version init
        devsMapper.setPosition(Position.DEVELOPER);
        testerMapper.setPosition(Position.TESTER);
        scrumsMapper.setPosition(Position.SCRUM_MASTER);
        teamMemberMapperList.add(devsMapper);
        teamMemberMapperList.add(testerMapper);
        teamMemberMapperList.add(scrumsMapper);

        devs = teamMemberList.stream()
                .filter(t1 -> t1.getPosition() == Position.DEVELOPER)
                .collect(Collectors.toList());
        testers = teamMemberList.stream()
                .filter(t1 -> t1.getPosition().equals(Position.TESTER))
                .collect(Collectors.toList());
        scrums = teamMemberList.stream()
                .filter(t1 -> t1.getPosition().equals(Position.SCRUM_MASTER))
                .collect(Collectors.toList());
    }


    public void firstAttempt() {
        init();

//        Max income
        float devMaxIncome = devs.stream()
                .map(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour())
                .max(Float::compare).get();

        float testersMaxIncome = testers.stream()
                .map(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour())
                .max(Float::compare).get();

        float scrumsMaxIncome = scrums.stream()
                .map(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour())
                .max(Float::compare).get();

//        Total income
        float devsTotalIncome = devs.stream()
                .map(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour())
                .reduce(0f, Float::sum);

        float testersTotalIncome = testers.stream()
                .map(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour())
                .reduce(0f, Float::sum);

        float scrumsTotalIncome = scrums.stream()
                .map(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour())
                .reduce(0f, Float::sum);

//        Devs
        System.out.println("Total Income Devs: " + devsTotalIncome);
        System.out.println("Max Income Devs: " + devMaxIncome);
        System.out.println("Max Income Devs Names: " + devs.stream().filter(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour() == devMaxIncome).map(t1 -> t1.getName()).collect(Collectors.toList()));
//        Testers
        System.out.println("Total Income Testers: " + testersTotalIncome);
        System.out.println("Max Income Testers: " + testersMaxIncome);
        System.out.println("Max Income Devs Names: " + testers.stream().filter(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour() == testersMaxIncome).map(t1 -> t1.getName()).collect(Collectors.toList()));
//        Scrum Masters
        System.out.println("Total Income Scrums: " + scrumsTotalIncome);
        System.out.println("Max Income Scrums: " + scrumsMaxIncome);
        System.out.println("Max Income Devs Names: " + scrums.stream().filter(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour() == scrumsMaxIncome).map(t1 -> t1.getName()).collect(Collectors.toList()));
    }

    public void secondAttempt() {
        init();

        devs.stream().forEach(t1 -> {
            devsMapper.setTotalIncome(devsMapper.getTotalIncome() + t1.getSalaryPerHour() * t1.getWorkedHours());
            devsMapper.setPosition(t1.getPosition());
            if (t1.getSalaryPerHour() * t1.getWorkedHours() >= devsMapper.getMaxIncome()) {
                devsMapper.setMaxIncome(t1.getSalaryPerHour() * t1.getWorkedHours());
                if (devsMapper.getNames() != null && devsMapper.getNames().size() > 0) {
                    List<String> names = devsMapper.getNames();
                    names.add(t1.getName());
                    devsMapper.setNames(names);
                } else {
                    List<String> names = new ArrayList<>();
                    names.add(t1.getName());
                    devsMapper.setNames(names);
                }
            }
        });
        System.out.println(devsMapper);
    }

    public void thirdAttempt() {
        init();

        teamMemberList.stream().forEach(t1 -> {
            for (TeamMemberMapper teamMemberMapper : teamMemberMapperList) {
                if (t1.getPosition().equals(teamMemberMapper.getPosition())) {
                    teamMemberMapper.setTotalIncome(teamMemberMapper.getTotalIncome() + t1.getSalaryPerHour() * t1.getWorkedHours());
                    teamMemberMapper.setPosition(t1.getPosition());
                    if (t1.getSalaryPerHour() * t1.getWorkedHours() > teamMemberMapper.getMaxIncome()) {
                        teamMemberMapper.setMaxIncome(t1.getSalaryPerHour() * t1.getWorkedHours());
                        List<String> names = new ArrayList<>();
                        names.add(t1.getName());
                        teamMemberMapper.setNames(names);
                    } else if (t1.getSalaryPerHour() * t1.getWorkedHours() == teamMemberMapper.getMaxIncome()) {
                        teamMemberMapper.setMaxIncome(t1.getSalaryPerHour() * t1.getWorkedHours());
                        List<String> names = teamMemberMapper.getNames();
                        names.add(t1.getName());
                        teamMemberMapper.setNames(names);
                    }
                }
            }
        });

        for (TeamMemberMapper teamMemberMapper : teamMemberMapperList)
            System.out.println(teamMemberMapper);
    }
}
