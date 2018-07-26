package com.example.Arena;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArenaApplication {

	public static void main(String[] args) {
        CreaturesFactory creaturesFactory = new CreaturesFactory();

//        int listSize = 6;
//        List<Creature> randomCreatureList = creaturesFactory.randomCreatureList(listSize);
//
//        int generatedNumber = randomCreatureList.toArray().length;
//        System.out.println("Random list of Creatures, count: " + randomCreatureList.toArray().length);
//        System.out.println(randomCreatureList);
//        for (int i = 0; i < generatedNumber; i++) {
//            System.out.println("Generated: " + (i+1) + ": " + randomCreatureList.get(i));
//        }


        Creature creature1 = creaturesFactory.randomCreature();
        Creature creature2 = creaturesFactory.randomCreature();

        FightService fightService = new FightService();
        fightService.fight(creature1, creature2);
    }
}
