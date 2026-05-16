package cn.itcast.schedule.whileSleep;

public class Scheduled1 {

    private static final long timeInterval = 5000;

    public static void main(String[] args) {

        new Thread(() -> {
            while (true) {
                // 具体任务
                System.out.println("定时任务每隔" + timeInterval + "毫秒执行一次");
                try {
                    Thread.sleep(timeInterval);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}