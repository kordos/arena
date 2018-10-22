package com.example.Arena.Data;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="tournament")
class TournamentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer capacity;
    private Integer points;

    @OneToMany(mappedBy="tournamentEntity", cascade = CascadeType.ALL)
    private List<CreatureEntity> creatures = new ArrayList<>();

    public void addCreature(CreatureEntity creatureEntity) {
        creatureEntity.setTournamentEntity(this);
        creatures.add(creatureEntity);
    }
}

