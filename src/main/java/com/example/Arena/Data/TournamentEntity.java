package com.example.Arena.Data;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name="tournament")
public class TournamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer capacity;
    private Integer points;

    @OneToMany(mappedBy="tournamentEntity", cascade = CascadeType.PERSIST)
    private Set<CreatureEntity> creatures;
}

