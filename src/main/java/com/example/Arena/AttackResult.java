package com.example.Arena;

//TODO AttackIntention seams better name
public class AttackResult {
    private BodyPart bodyPart;
    private int potentialDamage;
    private int attackCount;

    public AttackResult(){}

    public AttackResult(BodyPart bodyPart, int potentialDamage, int attackCount) {
        this.bodyPart = bodyPart;
        this.potentialDamage = potentialDamage;
        this.attackCount = attackCount;
    }

    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public int getPotentialDamage() {
        return potentialDamage;
    }

    public int getAttackCount() {
        return attackCount;
    }

    public void setBodyPart(BodyPart bodyPart) {
        this.bodyPart = bodyPart;
    }

    public void setPotentialDamage(int potentialDamage) {
        this.potentialDamage = potentialDamage;
    }

    public void setAttackCount(int attackCount) {
        this.attackCount = attackCount;
    }
}
