package com.fmanager.controller;

import com.fmanager.dto.TeamDto;
import com.fmanager.dto.TopUpDto;
import com.fmanager.dto.UpdateTeamDto;
import com.fmanager.service.EntityMapper;
import com.fmanager.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private EntityMapper entityMapper;

    @PostMapping("/teams")
    public Long create(@RequestBody TeamDto teamDto){
        return teamService.create(entityMapper.toEntity(teamDto)).getId();
    }

    @PutMapping("/teams")
    public TeamDto update(@RequestBody UpdateTeamDto update){
        return entityMapper.toDto(teamService.update(update));
    }

    @GetMapping("/teams/{id}")
    public TeamDto getById(@PathVariable Long id){
        return entityMapper.toDto(teamService.findById(id));
    }

    @GetMapping("/teams")
    public List<TeamDto> findAll(){
        return teamService.findAll().stream().map(entityMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/teams/{id}")
    public TeamDto delete(@PathVariable Long id){
        return entityMapper.toDto(teamService.delete(teamService.findById(id)));
    }

    @PostMapping("/teams/topup")
    public TeamDto topUp(@RequestBody TopUpDto topUpDto){
        return entityMapper.toDto(teamService.topUpBankAccount(topUpDto.getTeamId(), topUpDto.getAmount()));
    }
}
