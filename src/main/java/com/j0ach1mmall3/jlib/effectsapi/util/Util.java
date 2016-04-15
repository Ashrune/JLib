package com.j0ach1mmall3.jlib.effectsapi.util;

import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 13/03/2016
 */
public final class Util {
    /**
     * Let nobody instantiate this class
     */
    private Util() {
    }

    /**
     * Creates a Cirlce
     * @param radius The Radius
     * @param amount The amount of Locations
     * @return The Locations
     */
    public static Vector[] getCircle(double radius, int amount) {
        Vector[] vectors = new Vector[amount];
        for(int i=1;i < amount + 1;i++) {
            double angle = Math.PI * 2 * i / amount;
            double x = radius * Math.cos(angle);
            double z = radius * Math.sin(angle);
            vectors[i - 1] = new Vector(x, 0, z);
        }
        return vectors;
    }

    /**
     * Creates a Sphere
     * @param radius The Radius
     * @param amountRoot The root of the amount of Locations
     * @return The Locations
     */
    public static List<Vector[]> getSphere(double radius, int amountRoot) {
        List<Vector[]> vectorz = new ArrayList<>();
        for(int i=1;i < amountRoot + 1;i++) {
            Vector[] vectors = new Vector[amountRoot];
            for(int j=1; j < amountRoot + 1;j++) {
                double angle1 = Math.PI * 2 * i / amountRoot;
                double angle2 = Math.PI * 2 * j / amountRoot;
                double x = radius * Math.sin(angle1) * Math.cos(angle2);
                double y = radius * Math.cos(angle1);
                double z = radius * Math.sin(angle1) * Math.sin(angle2);
                vectors[j - 1] = new Vector(x, y, z);
            }
            vectorz.add(vectors);
        }
        return vectorz;
    }
}
