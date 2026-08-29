package cn.itcast.schedule.timer;

import java.util.TimerTask;

public class TimeTask3 extends TimerTask {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 定时任务TimeTask3运行了");
    }
}
