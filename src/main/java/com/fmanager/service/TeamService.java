package com.fmanager.service;

import com.fmanager.entity.Team;
import com.fmanager.exception.EntityNotFoundException;
import com.fmanager.exception.InvalidEntityException;
import com.fmanager.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team save(Team team) {
        return teamRepository.save(team);
    }

    public Team create(Team team) {
        if (ValidationService.validate(team)) {
            return teamRepository.save(team);
        } else {
            throw new InvalidEntityException("not acceptable team : " + team);
        }
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("cant find team by id " + id));
    }

    public Team delete(Team team) {
        teamRepository.delete(team);
        return team;
    }

    public Team topUpBankAccount(Long teamId, BigDecimal amount){
        Team team = findById(teamId);
        team.setBankAccount(team.getBankAccount().add(amount));
        save(team);
        return team;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }
}
