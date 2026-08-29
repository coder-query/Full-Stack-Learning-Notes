package cn.itcast.schedule.timer;

import java.util.Timer;

public class TimeScheduled {
    private static final long timeInterval = 5000;

    public static void main(String[] args) {
        Timer timer = new Timer();
        //延时3秒，每隔5s执行一次
        timer.schedule(new TimeTask1(), 3000, 3000);

        timer.schedule(new TimeTask2(), 3000, 4000);

        timer.schedule(new TimeTask3(), 3000, 5000);
    }
}