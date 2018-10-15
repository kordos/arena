package com.example.Arena;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class ArenaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArenaApplication.class, args);
    }

	public static void mainOld() {
        CreaturesFactory creaturesFactory = new CreaturesFactory();

//        int listSize = 6;
//        List<CreatureEntity> randomCreatureList = creaturesFactory.randomCreatureList(listSize);
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
