package com.example.Arena;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public enum ArmourType {

    HELM(0, 2, Collections.singletonList(BodyPart.HEAD)),
    ARMOUR(0, 4, Collections.singletonList(BodyPart.TRUNK)),
    GLOVES(0, 3, Arrays.asList(BodyPart.LEFT_ARM, BodyPart.RIGHT_ARM)),
    SHIN_GUARD(0, 2, Arrays.asList(BodyPart.LEFT_LEG, BodyPart.RIGHT_LEG)),
    SHIELD(0, 1, Arrays.asList(BodyPart.values()));

    private int minProtection;
    private int maxProtection;
    private Collection<BodyPart> protectedBodyPart;

    ArmourType(int minProtection, int maxProtection, Collection<BodyPart> protectedBodyPart) {
        this.minProtection = minProtection;
        this.maxProtection = maxProtection;
        this.protectedBodyPart = protectedBodyPart;
    }

    public int getMinProtection() {
        return minProtection;
    }

    public int getMaxProtection() {
        return maxProtection;
    }

    public Collection<BodyPart> getProtectedBodyPart() {
        return protectedBodyPart;
    }
}
