package com.example.Arena.controller;

import com.example.Arena.Data.Tournament;
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

    @RequestMapping("/tournaments")
    public String getTournaments() {

        //TODO implement
        return "OK";
    }

    @RequestMapping(value = "/tournaments/{id}", method = RequestMethod.GET)
    public TournamentDto getTournament(@PathVariable("id") int id) throws MissingTournamentException {
        Tournament tournament = tournamentService.get(id);

        return new TournamentDto(
            tournament.getId(),
            tournament.getCapacity(),
            tournament.getPoints()
        );
    }

    @RequestMapping(value = "/tournaments", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8")
    public ResponseEntity createTournament(@RequestBody @Valid CreateTournamentDto createTournamentDto) {

        Tournament tournament = tournamentService.save(
            createTournamentDto.getCapacity(),
            createTournamentDto.getPoints()
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/tournaments/" + tournament.getId()));

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }
}
