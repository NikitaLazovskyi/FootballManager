package com.fmanager.service;

import com.fmanager.dto.Response;
import com.fmanager.dto.UpdatePlayerDto;
import com.fmanager.entity.Player;
import com.fmanager.entity.Team;
import com.fmanager.exception.EntityNotFoundException;
import com.fmanager.exception.InvalidEntityException;
import com.fmanager.exception.InvalidTransferException;
import com.fmanager.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
            player.setCost(ComputingService.computeCost(player.getDateOfBirth(), player.getStartCareer()));
            return playerRepository.save(player);
        } else {
            throw new InvalidEntityException("not acceptable player : " + player);
        }
    }

    public Player update(UpdatePlayerDto update) {
        Player foundPlayer = findById(update.getId());
        if (update.getName() != null) {
            foundPlayer.setName(update.getName());
        }
        if (update.getLastname() != null) {
            foundPlayer.setLastname(update.getLastname());
        }
        if (ValidationService.validate(foundPlayer)) {
            playerRepository.save(foundPlayer);
            return foundPlayer;
        } else {
            throw new InvalidEntityException("invalid name " + foundPlayer.getName() + " or lastname " + foundPlayer.getLastname());
        }
    }

    public void updateCostOfEachPlayer(){
        List<Player> collect = findAll();
        collect.forEach(player -> player.setCost(ComputingService.computeCost(player.getDateOfBirth(), player.getStartCareer())));
        playerRepository.saveAll(collect);
    }

    public Player findById(Long id) {
        if (id != null) {
            return playerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("cant find player by id " + id));
        } else {
            throw new IllegalArgumentException("can't be null. Player id: " + id);
        }
    }

    public Player delete(Player player) {
        playerRepository.delete(player);
        return player;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Transactional
    public Response transfer(Long playerId, Long teamId) {
        Player player = findById(playerId);
        Team nextTeam = teamService.findById(teamId);
        Team previousTeam = player.getTeam();


        if (nextTeam.equals(previousTeam)) {
            throw new InvalidTransferException(previousTeam.getName() + " and " + nextTeam.getName() + " are same");
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

            return new Response("success");
        } else {
            throw new InvalidTransferException(nextTeam.getName() + " hasn't enough money for transfer");
        }
    }
}
