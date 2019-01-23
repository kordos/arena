package com.example.Arena.creature;

public enum CreatureType {
    HUMAN,
    ELF,
    DWARF,
    HALFING,
    ORC,
    TROLL;

    @Override
    public String toString() {
        return "My toString() in CreatureType: " + super.toString();
    }
}