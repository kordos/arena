package com.example.Arena.Data;

import com.example.Arena.creature.Creature;
import com.example.Arena.Service.MissingTournamentException;
import com.example.Arena.Service.Tournament;
import com.example.Arena.Service.TournamentRepositoryInterface;
import com.example.Arena.util.GeneralMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TournamentDatabaseRepository implements TournamentRepositoryInterface {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    CreatureRepository creatureRepository;

    @Autowired
    GeneralMapper generalMapper;

    private TournamentEntity get(int id) throws MissingTournamentException {
        Optional result = tournamentRepository.findById(id);
        if (!result.isPresent()) {
            throw new MissingTournamentException("TournamentEntity does not exits!");
        }

        return (TournamentEntity)result.get();
    }

    @Override
    public List<Tournament> getTournaments() {
        List<Tournament> tournaments = new ArrayList<>();

        tournamentRepository
            .findAll()
            .forEach(entity -> tournaments.add(
                generalMapper.mapSimple(entity, new Tournament())
            ));

        return tournaments;
    }

    @Override
    public Tournament getTournament(int id) throws MissingTournamentException {
        Optional result = tournamentRepository.findById(id);
        if (!result.isPresent()) {
            throw new MissingTournamentException("TournamentEntity does not exits");
        }

        TournamentEntity tournamentEntity = (TournamentEntity)result.get();

        return generalMapper.mapSimple(tournamentEntity, new Tournament());
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

        CreatureEntity creatureEntity = generalMapper.mapSimple(creature, new CreatureEntity());
        creatureEntity.setType(creature.getType().name());
        creatureEntity.setTournamentEntity(tournamentEntity);

        creatureRepository.save(creatureEntity);

        return creatureEntity.getId();
    }
}
