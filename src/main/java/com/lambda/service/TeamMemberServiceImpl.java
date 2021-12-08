package com.lambda.service;


import com.google.gson.Gson;
import com.lambda.model.Position;
import com.lambda.model.TeamMember;
import com.lambda.model.TeamMemberMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamMemberServiceImpl {

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

    }

    public void processTeamMemberList() {
        init();

        teamMemberList.stream()
                .collect(Collectors.groupingBy(TeamMember::getPosition))
                .forEach((key,value) -> {
                    TeamMemberMapper teamMemberMapper = new TeamMemberMapper();
                    teamMemberMapper.setPosition(key);
                    teamMemberMapper.setMaxIncome(value.stream().map(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour()).max(Float::compare).get());
                    teamMemberMapper.setTotalIncome(value.stream().map(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour()).reduce(0f, Float::sum));
                    teamMemberMapper.setNames(value.stream().filter(t1 -> t1.getWorkedHours() * t1.getSalaryPerHour() == teamMemberMapper.getMaxIncome()).map(t1 -> t1.getName()).collect(Collectors.toList()));
                    System.out.println(key + " " + value);
                    teamMemberMapperList.add(teamMemberMapper);
                });

        System.out.println("Finish \n" + teamMemberMapperList);
    }
}
