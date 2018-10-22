package com.example.Arena.Data;

import com.example.Arena.Creature;
import com.example.Arena.Service.MissingTournamentException;
import com.example.Arena.Service.Tournament;
import com.example.Arena.Service.TournamentRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TournamentDatabaseRepository implements TournamentRepositoryInterface {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    CreatureRepository creatureRepository;

    public TournamentEntity get(int id) throws MissingTournamentException {
        Optional result = tournamentRepository.findById(id);
        if (!result.isPresent()) {
            throw new MissingTournamentException("TournamentEntity does not exits!");
        }

        return (TournamentEntity)result.get();
    }

    @Override
    public Tournament getTournament(int id) throws MissingTournamentException {
        Optional result = tournamentRepository.findById(id);
        if (!result.isPresent()) {
            throw new MissingTournamentException("TournamentEntity does not exits");
        }

        TournamentEntity tournamentEntity = (TournamentEntity)result.get();

        return new Tournament(
            tournamentEntity.getId(),
            tournamentEntity.getCapacity(),
            tournamentEntity.getPoints()
        );
    }

    @Override
    public int addTournament(int capacity, int points) {
        TournamentEntity tournamentEntity = new TournamentEntity();
        tournamentEntity.setCapacity(capacity);
        tournamentEntity.setPoints(points);

        tournamentRepository.save(tournamentEntity);

        return tournamentEntity.getId();
    }

    @Override
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
        creatureEntity.setType(creature.getType().name());

        // add creature to list
        //v1 not passing id to creatureEntity object
        //tournamentEntity.addCreature(creatureEntity);
        //tournamentRepository.save(tournamentEntity);
        //v2
        creatureEntity.setTournamentEntity(tournamentEntity);
        creatureRepository.save(creatureEntity);

        creatureRepository.save(creatureEntity);

        return creatureEntity.getId();
    }
}
