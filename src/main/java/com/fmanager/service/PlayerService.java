package com.fmanager.service;

import com.fmanager.entity.Player;
import com.fmanager.entity.Team;
import com.fmanager.exception.EntityNotFoundException;
import com.fmanager.exception.InvalidEntityException;
import com.fmanager.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamService teamService;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }

    public Player save(Player player) {
        return playerRepository.save(player);
    }

    public Player create(Player player) {
        if (ValidationService.validate(player)) {
            player.setCost(new BigDecimal(player.getExperience() * 100_000 / player.getAge()));
            return playerRepository.save(player);
        } else {
            throw new InvalidEntityException("not acceptable player : " + player);
        }
    }

    public Player findById(Long id) {
        return playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("cant find player by id " + id));
    }

    public Player delete(Player player) {
        playerRepository.delete(player);
        return player;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Transactional
    public String transfer(Long playerId, Long teamId) {
        Player player = findById(playerId);
        Team nextTeam = teamService.findById(teamId);
        Team previousTeam = player.getTeam();


        if (nextTeam.equals(previousTeam)) {
            return "Same teams";
        }

        BigDecimal price = player.getCost().multiply(BigDecimal.valueOf(((double) previousTeam.getCommission() / 100D) + 1D));
        if (nextTeam.getBankAccount().compareTo(price) >= 0) {
            //Enough money to buy player
            nextTeam.setBankAccount(nextTeam.getBankAccount().subtract(price));
            previousTeam.setBankAccount(previousTeam.getBankAccount().add(price));
            player.setTeam(nextTeam);

            save(player);
            teamService.save(previousTeam);
            teamService.save(nextTeam);

            return "Success";
        } else {
            return "Team hasn't enough money to buy a player";
        }
    }
}
