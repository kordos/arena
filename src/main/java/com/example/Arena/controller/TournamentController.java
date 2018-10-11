package com.example.Arena.controller;

import com.example.Arena.Data.Tournament;
import com.example.Arena.Data.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.web.bind.annotation.RestController
public class TournamentController {

    @Autowired
    private TournamentRepository tournamentRepository;

    @RequestMapping("/tournaments")
    public String getTournaments() {

        // test save
        Tournament tournament = new Tournament();
        tournament.setPoints(10);
        tournamentRepository.save(tournament);

        return "OK";
    }

    @RequestMapping("/tournaments/{id}")
    public String getTournament() {

        // test save
        return "OK";
    }

}
