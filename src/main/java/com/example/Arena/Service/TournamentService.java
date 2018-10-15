package com.example.Arena.Service;

import com.example.Arena.Data.Tournament;
import com.example.Arena.Data.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TournamentService {

    @Autowired
    private TournamentRepository repository;

    public Tournament get(int id) throws MissingTournamentException {
        Optional result = repository.findById(id);
        if (!result.isPresent()) {
            throw new MissingTournamentException("Tournament does not exits");
        }

        return (Tournament)result.get();
    }

    public Tournament save(int capacity, int points) {
        Tournament tournament = new Tournament();
        tournament.setCapacity(capacity);
        tournament.setPoints(points);

        return repository.save(tournament);
    }
}
