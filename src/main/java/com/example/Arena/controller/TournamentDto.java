package com.example.Arena.controller;


import lombok.Value;

@Value
public class TournamentDto {
    private Integer id;
    private Integer capacity;
    private Integer points;

    public TournamentDto(Integer id, Integer capacity, Integer points) {
        this.id = id;
        this.capacity = capacity;
        this.points = points;
    }
}

