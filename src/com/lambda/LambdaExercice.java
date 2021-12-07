package com.lambda;

import com.lambda.model.Position;
import com.lambda.model.TeamMember;
import com.lambda.service.TeamMemberServiceImpl;

import java.util.*;
import java.util.stream.Collectors;

public class LambdaExercice {
    public static void main(String[] args) {

        TeamMemberServiceImpl teamMemberService = new TeamMemberServiceImpl();
        System.out.println(teamMemberService.processTeamMemberList());

    }
}
