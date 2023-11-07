package workshop5;

import static java.lang.Thread.sleep;

public class Thinker implements Runnable {
    private static final int EATING = 3;
    private int thinkerID;
    private Fork fLeft;
    private Fork fRight;
    private int counterEating;

    public Thinker(int thinkerID, Fork left, Fork right) {
        this.fLeft = left;
        this.fRight = right;
        this.thinkerID = thinkerID;
        counterEating = 1;
    }

    public void run() {
        try {
            while (this.counterEating < EATING) {
                // если левая вилка не используется, то присваеваем ID философа
                if (fLeft.getUserID().compareAndSet(0, thinkerID)) {
                    // если равая вилка не используется, то присваеваем ID философа
                    if (fRight.getUserID().compareAndSet(0, thinkerID)) {
                        // если обе вилки не используются
                        if (counterEating == 1) {
                            System.out.printf("Философ №%d взял вилки %s и %s (№%d ПОЕЛ: %d раз)\n",
                                    thinkerID, fLeft, fRight, thinkerID, counterEating++);
                        } else {
                            System.out.printf("Философ №%d взял вилки %s и %s (№%d ПОЕЛ: %d раза)\n",
                                    thinkerID, fLeft, fRight, thinkerID, counterEating++);
                        }
                        sleep(20);
                        // после того, как философ поел
                        System.out.printf("Философ №%d положил вилки %s и %s\n",
                                thinkerID, fLeft, fRight);
                        fRight.getUserID().compareAndSet(thinkerID, 0);
                        fLeft.getUserID().compareAndSet(thinkerID, 0);
                        sleep(20);
                    } else {
                        // если правая вилка занята
                        fLeft.getUserID().compareAndSet(thinkerID, 0);
                        toThink();
                    }
                } else {
                    // если левая вилка занята
                    toThink();
                }
            }
            System.out.printf(">>> ФИЛОСОФ №%d ПОЕЛ 3 РАЗА\n", thinkerID);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void toThink() {
        System.out.printf("Философ №%d думает\n", thinkerID);
        try {
            sleep(18);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(thinkerID);
        if (fLeft.getUserID().intValue() == thinkerID)
            sb.append("Философ: ").append(fLeft);
        if (fRight.getUserID().intValue() == thinkerID)
            sb.append("Философ: ").append(fRight);
        return sb.toString();
    }
}