package com.example.Arena.Service;

import com.example.Arena.Creature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TournamentService {

    private final TournamentRepositoryInterface repository;

    @Autowired
    public TournamentService(TournamentRepositoryInterface repository) {
        this.repository = repository;
    }

    public Tournament getTournament(int id) throws MissingTournamentException {
        return repository.getTournament(id);
    }

    public int save(int capacity, int points) {

        return repository.addTournament(capacity, points);
    }

    public int addCreature(int tournamentId, Creature creature) throws MissingTournamentException {

        return repository.addCreature(tournamentId, creature);
    }
}
