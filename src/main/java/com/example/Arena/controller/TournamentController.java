package com.example.Arena.controller;

import com.example.Arena.Creature;
import com.example.Arena.CreaturesFactory;
import com.example.Arena.Data.TournamentEntity;
import com.example.Arena.Service.MissingTournamentException;
import com.example.Arena.Service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@org.springframework.web.bind.annotation.RestController
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private CreaturesFactory creaturesFactory;

    @RequestMapping("/tournaments")
    public String getTournaments() {

        //TODO implement
        return "OK";
    }

    @RequestMapping(value = "/tournaments/{id}", method = RequestMethod.GET)
    public TournamentDto getTournament(@PathVariable("id") int id) throws MissingTournamentException {
        TournamentEntity tournamentEntity = tournamentService.get(id);

        return new TournamentDto(
            tournamentEntity.getId(),
            tournamentEntity.getCapacity(),
            tournamentEntity.getPoints()
        );
    }

    @RequestMapping(value = "/tournaments", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public ResponseEntity createTournament(@RequestBody @Valid CreateTournamentDto createTournamentDto) {

        TournamentEntity tournamentEntity = tournamentService.save(
            createTournamentDto.getCapacity(),
            createTournamentDto.getPoints()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/tournaments/" + tournamentEntity.getId()));

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/tournaments/{id}/creatures", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public ResponseEntity addCreatureToTournament(
            @PathVariable("id") int tournamentId,
            @RequestBody @Valid AddCreatureToTournamentDto addCreatureToTournamentDto
    ) throws MissingTournamentException {

        Creature creature = creaturesFactory.createCreature(
            addCreatureToTournamentDto.getType(),
            addCreatureToTournamentDto.getStrength(),
            addCreatureToTournamentDto.getDexterity(),
            addCreatureToTournamentDto.getInitiative(),
            addCreatureToTournamentDto.getVelocity(),
            addCreatureToTournamentDto.getEndurance(),
            addCreatureToTournamentDto.getNumberOfAttacks(),
            addCreatureToTournamentDto.getNumberOfDodges(),
            addCreatureToTournamentDto.getLifePoints()
        );

        int creatureId = tournamentService.addCreature(tournamentId, creature);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/tournaments/" + tournamentId + "/creatures/" + creatureId));
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
