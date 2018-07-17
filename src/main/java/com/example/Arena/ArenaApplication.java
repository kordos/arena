package com.example.Arena;

public class ArenaApplication {

	public static void main(String[] args) {
		Human human = new Human(
				1,
				2,
				3,
				4,
				5,
				6,
				7,
				8
		);
		System.out.println(human);


        CreaturesFactory creaturesFactory = new CreaturesFactory();
        System.out.println(creaturesFactory.generate(CreatureType.HUMAN));
        System.out.println(creaturesFactory.generate(CreatureType.ELF));

        Elf elf = (Elf)creaturesFactory.generate(CreatureType.ELF);
        System.out.println(elf);

        for (CreatureType type: CreatureType.values()) {
            System.out.println(type);
            System.out.println(creaturesFactory.generate(type));
        }
    }
}
