package com.example.Arena.Service;

import com.example.Arena.creature.Creature;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TournamentService {

    private final TournamentRepositoryInterface repository;

    public TournamentService(TournamentRepositoryInterface repository) {
        this.repository = repository;
    }

    public List<Tournament> getTournaments() {
        return repository.getTournaments();
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
