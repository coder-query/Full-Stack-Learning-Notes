package cn.itcast.schedule.timer;

import java.util.TimerTask;

public class TimeTask1 extends TimerTask {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 定时任务TimeTask1运行了");
    }
}
