package com.example.Arena;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ArenaApplication {

	public static void main(String[] args) {
//		Human human = new Human(
//				1,
//				2,
//				3,
//				4,
//				5,
//				6,
//				7,
//				8
//		);
//		System.out.println(human);


        CreaturesFactory creaturesFactory = new CreaturesFactory();
//        System.out.println(creaturesFactory.generate(CreatureType.HUMAN));
//        System.out.println(creaturesFactory.generate(CreatureType.ELF));
//
//        Elf elf = (Elf)creaturesFactory.generate(CreatureType.ELF);
//        System.out.println(elf);
//
//        for (CreatureType type: CreatureType.values()) {
//            System.out.println(type);
//            System.out.println(creaturesFactory.generate(type));
//        }


        System.out.println("Random creature: " + creaturesFactory.randomCreature());
        System.out.println("Random creature: " + creaturesFactory.randomCreature());
        System.out.println("Random creature: " + creaturesFactory.randomCreature());
        System.out.println("Random creature: " + creaturesFactory.randomCreature());


        int listSize = 6;
        List<Creature> randomCreatureList = creaturesFactory.randomCreatureList(listSize);

        int generatedNumber = randomCreatureList.toArray().length;
        System.out.println("Random list of Creatures, count: " + randomCreatureList.toArray().length);
        System.out.println(randomCreatureList);
        for (int i = 0; i < generatedNumber; i++) {
            System.out.println("Generated: " + (i+1) + ": " + randomCreatureList.get(i));
        }
    }
}
