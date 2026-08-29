package cn.itcast.schedule.timer;

import java.util.TimerTask;

public class TimeTask2 extends TimerTask {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 定时任务TimeTask2运行了");
    }
}
