package com.example.Arena.Service;

import com.example.Arena.Creature;
import com.example.Arena.Data.CreatureEntity;
import com.example.Arena.Data.CreatureRepository;
import com.example.Arena.Data.TournamentEntity;
import com.example.Arena.Data.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private CreatureRepository creatureRepository;

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

    public int addCreature(int tournamentId, Creature creature) throws MissingTournamentException {

        TournamentEntity tournamentEntity = get(tournamentId);

        //create entity
        CreatureEntity creatureEntity = new CreatureEntity();
        creatureEntity.setLifePoints(creature.getLifePoints());
        creatureEntity.setStrength(creature.getStrength());
        creatureEntity.setDexterity(creature.getDexterity());
        creatureEntity.setInitiative(creature.getInitiative());
        creatureEntity.setVelocity(creature.getVelocity());
        creatureEntity.setEndurance(creature.getEndurance());
        creatureEntity.setNumberOfAttacks(creature.getNumberOfAttacks());
        creatureEntity.setNumberOfDodges(creature.getNumberOfDodges());

        // add creature to list
        //v1 not passing id to creatureEntity object
        //tournamentEntity.addCreature(creatureEntity);
        //tournamentRepository.save(tournamentEntity);
        //v2
        creatureEntity.setTournamentEntity(tournamentEntity);
        creatureRepository.save(creatureEntity);

        return creatureEntity.getId();
    }
}
