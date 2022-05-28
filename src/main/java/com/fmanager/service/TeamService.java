package com.fmanager.service;

import com.fmanager.dto.UpdatePlayerDto;
import com.fmanager.dto.UpdateTeamDto;
import com.fmanager.entity.Player;
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

    public Team update(UpdateTeamDto update) {
        Team foundTeam = findById(update.getId());
        if (update.getName() != null) {
            foundTeam.setName(update.getName());
        }
        if (update.getCommission() != null) {
            foundTeam.setCommission(update.getCommission());
        }
        if (ValidationService.validate(foundTeam)) {
            teamRepository.save(foundTeam);
            return foundTeam;
        } else {
            throw new InvalidEntityException("invalid name " + foundTeam.getName() + " or commission: " + foundTeam.getCommission() + " (must be 0 >= commission <= 10) ");
        }
    }

    public Team findById(Long id) {
        if (id != null) {
            return teamRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("can't find team by id " + id));
        } else {
            throw new IllegalArgumentException("can't be null. Team id: " + id);
        }

    }

    public Team delete(Team team) {
        teamRepository.delete(team);
        return team;
    }

    public Team topUpBankAccount(Long teamId, BigDecimal amount) {
        Team team = findById(teamId);
        team.setBankAccount(team.getBankAccount().add(amount));
        save(team);
        return team;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }
}
