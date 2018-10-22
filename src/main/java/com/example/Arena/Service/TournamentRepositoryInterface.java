package com.example.Arena.Service;

import com.example.Arena.Creature;

public interface TournamentRepositoryInterface {

    Tournament getTournament(int id) throws MissingTournamentException;

    int addTournament(int capacity, int points);

    int addCreature(int tournamentId, Creature creature) throws MissingTournamentException;
}