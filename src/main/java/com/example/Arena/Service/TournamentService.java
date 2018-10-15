package com.example.Arena.Service;

import com.example.Arena.Creature;
import com.example.Arena.Data.CreatureEntity;
import com.example.Arena.Data.TournamentEntity;
import com.example.Arena.Data.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public TournamentEntity get(int id) throws MissingTournamentException {
        Optional result = tournamentRepository.findById(id);
        if (!result.isPresent()) {
            throw new MissingTournamentException("TournamentEntity does not exits");
        }

        return (TournamentEntity)result.get();
    }

    public TournamentEntity save(int capacity, int points) {
        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setCapacity(capacity);
        tournamentEntity.setPoints(points);

        return tournamentRepository.save(tournamentEntity);
    }

    public void addCreature(int tournamentId, Creature creature) throws MissingTournamentException {

        TournamentEntity tournamentEntity = get(tournamentId);

        //create entity
        CreatureEntity creatureEntity = new CreatureEntity();
        creatureEntity.setLifePoints(creature.getLifePoints());
        creatureEntity.setStrength(creature.getStrength());

        creatureEntity.setTournamentEntity(tournamentEntity);

        // add to list
        List<CreatureEntity> creatures = tournamentEntity.getCreatures();
        creatures.add(creatureEntity);

        // save
        tournamentRepository.save(tournamentEntity);
    }
}
