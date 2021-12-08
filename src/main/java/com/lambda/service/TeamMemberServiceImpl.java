package com.lambda.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lambda.model.Position;
import com.lambda.model.TeamMember;
import com.lambda.model.TeamMemberMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TeamMemberServiceImpl {

    private List<TeamMember> teamMemberList = new ArrayList<>();
    private List<TeamMemberMapper> teamMemberMapperList = new ArrayList<>();

    private void init() {
        readDataFromFile();
    }

    private void readDataFromFile(){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("members.json"))
        {
            //Read JSON file
            Object object = jsonParser.parse(reader);
            JSONArray teamMembersArray = (JSONArray) object;
            System.out.println(teamMembersArray);

            mapJsonArrayToObjectList(teamMembersArray);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void mapJsonArrayToObjectList(JSONArray jsonArray){
        ObjectMapper mapper = new ObjectMapper();
        try{
            teamMemberList = mapper.readValue(jsonArray.toString(),new TypeReference<List<TeamMember>>(){});
        }catch (Exception e) {
            e.printStackTrace();
        }
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
