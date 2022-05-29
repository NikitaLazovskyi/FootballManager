package com.fmanager.service;

import com.fmanager.dto.PlayerDto;
import com.fmanager.dto.TeamDto;
import com.fmanager.entity.Player;
import com.fmanager.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EntityMapper {

    @Autowired
    private TeamService teamService;

    public Player toEntity(PlayerDto playerDto){
        return new Player(
                playerDto.getId(),
                playerDto.getName(),
                playerDto.getLastname(),
                playerDto.getDob(),
                playerDto.getStartCareer(),
                teamService.findById(playerDto.getTeamId()),
                playerDto.getCost());
    }

    public PlayerDto toDto(Player player){
        return new PlayerDto(
                player.getId(),
                player.getName(),
                player.getLastname(),
                player.getDateOfBirth(),
                player.getStartCareer(),
                player.getTeam().getId() ,
                player.getCost());
    }

    public Team toEntity(TeamDto teamDto){
        return new Team(
                teamDto.getId(),
                teamDto.getName(),
                teamDto.getCommission(),
                teamDto.getBankAccount());
    }

    public TeamDto toDto(Team team){
        return new TeamDto(
                team.getId(),
                team.getName(),
                team.getCommission(),
                team.getBankAccount());
    }
}
