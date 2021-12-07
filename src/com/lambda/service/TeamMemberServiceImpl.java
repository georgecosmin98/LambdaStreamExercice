package com.lambda.service;

import com.lambda.model.Position;
import com.lambda.model.TeamMember;

import java.util.*;
import java.util.stream.Collectors;

public class TeamMemberServiceImpl {

    private List<TeamMember> teamMemberList = new ArrayList<>();

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

    public Map<Position, TeamMember> processTeamMemberList() {
        init();
        return teamMemberList.stream()
                .collect(Collectors.groupingBy(TeamMember::getPosition
                        ,
                        Collectors.collectingAndThen(
//                                Collectors.reducing((TeamMember t1, TeamMember t2) -> t1.getSalaryPerHour() * t1.getWorkedHours() > t2.getSalaryPerHour() * t2.getWorkedHours() ? t1 : t2),
                                Collectors.maxBy(Comparator.comparing((TeamMember teamMember) -> teamMember.getWorkedHours()*teamMember.getSalaryPerHour())
                                ), Optional::get)));
    }
}
