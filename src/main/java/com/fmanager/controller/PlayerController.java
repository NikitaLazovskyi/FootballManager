package com.fmanager.controller;

import com.fmanager.dto.PlayerDto;
import com.fmanager.dto.Response;
import com.fmanager.dto.TransferPlayerDto;
import com.fmanager.dto.UpdatePlayerDto;
import com.fmanager.service.EntityMapper;
import com.fmanager.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private EntityMapper entityMapper;

    @PostMapping("/players")
    public Long create(@RequestBody PlayerDto playerDto){
        return playerService.create(entityMapper.toEntity(playerDto)).getId();
    }

    @PutMapping("/players")
    public PlayerDto update(@RequestBody UpdatePlayerDto update){
        return entityMapper.toDto(playerService.update(update));
    }

    @GetMapping("/players/{id}")
    public PlayerDto getById(@PathVariable Long id){
        return entityMapper.toDto(playerService.findById(id));
    }

    @GetMapping("/players")
    public List<PlayerDto> findAll(){
        return playerService.findAll().stream().map(entityMapper::toDto).collect(Collectors.toList());
    }

    @DeleteMapping("/players/{id}")
    public PlayerDto delete(@PathVariable Long id){
        return entityMapper.toDto(playerService.delete(playerService.findById(id)));
    }

    @PostMapping("/players/transfer")
    public Response transfer(@RequestBody TransferPlayerDto transferPlayerDto){
        return playerService.transfer(transferPlayerDto.getPlayerId(), transferPlayerDto.getTeamId());
    }
}
