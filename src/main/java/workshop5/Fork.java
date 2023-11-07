package workshop5;

import java.util.concurrent.atomic.AtomicInteger;

public class Fork {
    private int forkID;
    private AtomicInteger userID;

    public Fork(int forkID){
        this.forkID = forkID;
        userID = new AtomicInteger();
    }

    public AtomicInteger getUserID() {
        return userID;
    }

    @Override
    public String toString(){
        return "№" + forkID;
    }
}