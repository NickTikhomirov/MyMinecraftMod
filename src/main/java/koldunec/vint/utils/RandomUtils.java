package koldunec.vint.utils;


import koldunec.vint.vint;
import java.util.Random;

public class RandomUtils {
    public static Random random = vint.random;

    public static boolean getTrueWithChance(int arg){
        return random.nextInt(100)<arg;
    }
}
