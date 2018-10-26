package com.example.Arena.Service;

import com.example.Arena.Creature;

import java.util.List;

public interface TournamentRepositoryInterface {

    List<Tournament> getTournaments();

    Tournament getTournament(int id) throws MissingTournamentException;

    int addTournament(int capacity, int points);

    int addCreature(int tournamentId, Creature creature) throws MissingTournamentException;
}