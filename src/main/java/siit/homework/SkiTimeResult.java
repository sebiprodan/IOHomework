package siit.homework;

import java.io.Serializable;

public class SkiTimeResult implements Serializable{
    private int minutes;
    private int seconds;

    public SkiTimeResult(int minutes, int seconds){
        this.minutes = minutes;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return minutes + ":" + seconds;
    }


}
