package workshop5;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Fork[] forks = new Fork[5];
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork(i + 1);
        }
        Thinker[] thinkers = new Thinker[5];
        for (int i = 0; i < thinkers.length; i++) {
            if (i == thinkers.length - 1) {
                thinkers[i] = new Thinker(i + 1, forks[forks.length - 1], forks[0]);
            } else {
                thinkers[i] = new Thinker(i + 1, forks[i], forks[i + 1]);
            }
        }
        ArrayList<Thinker> philosophers = new ArrayList<>(Arrays.asList(thinkers));
        for (Thinker p : philosophers) {
            new Thread(p).start();
        }
    }
}