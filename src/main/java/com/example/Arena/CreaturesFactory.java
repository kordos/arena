package com.example.Arena;

public class CreaturesFactory {

    Creature generate(CreatureType type) {
        Creature creature = new Human(
                1,
                2,
                3,
                4,
                5,
                6,
                7,
                8
        );;

        switch (type) {
            case ELF:
                creature = new Elf(
                        1,
                        2,
                        3,
                        4,
                        5,
                        6,
                        7,
                        8
                );
            break;
            case DWARF:
                creature = new Dwarf(
                        1,
                        2,
                        3,
                        4,
                        5,
                        6,
                        7,
                        8
                );
            break;
            case HALFING:
                creature = new Halfing(
                        1,
                        2,
                        3,
                        4,
                        5,
                        6,
                        7,
                        8
                );
            break;
            case ORC:
                creature = new Orc(
                        1,
                        2,
                        3,
                        4,
                        5,
                        6,
                        7,
                        8
                );
            break;
            case TROLL:
                creature = new Troll(
                        1,
                        2,
                        3,
                        4,
                        5,
                        6,
                        7,
                        8
                );
            break;
            case HUMAN:
                creature = new Human(
                        1,
                        2,
                        3,
                        4,
                        5,
                        6,
                        7,
                        8
                );
                break;
        }

        return creature;
    }
}
