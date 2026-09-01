package cn.itcast.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class QuartzTest {

    private static Scheduler scheduler = null;

    static {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public static Trigger createSimpTigger() {
        //使用构建器构建Tigger
        return TriggerBuilder.newTrigger()
                //设置Tigger的name以及group
                .withIdentity("my_job_tigger", "my_job_tigger_group")
                //tigger 开始生效时间
                .startAt(new Date(System.currentTimeMillis() + 5000))
                //调度策略
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).withRepeatCount(10))
                //tigger开始失效时间
                .endAt(new Date(System.currentTimeMillis() + 15000))
                //任务名词
                .forJob("simple_trigger_test")
                .build();
    }

    /**
     * 创建触发器
     *
     * @return
     */
    public static Trigger createCronTigger() {
        //使用构建器构建Tigger
        return TriggerBuilder.newTrigger()
                //设置Tigger的name以及group
                .withIdentity("my_job_tigger", "my_job_tigger_group")
                //tigger 开始生效时间
                .startNow()
                //调度策略 每隔5S执行一次
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                //tigger开始失效时间
                .endAt(new Date(System.currentTimeMillis() + 25000))
                //任务名词
                .forJob("MyJob_1", "JobGroup_1")
                .build();
    }

    /**
     * 创建JobDetail
     *
     * @return
     */
    private static JobDetail createJobDetail() {
        return JobBuilder.newJob(MyJob.class).withIdentity("MyJob_1", "JobGroup_1").build();
    }


    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Trigger trigger = createCronTigger();
        JobDetail jobDetail = createJobDetail();
        scheduler.scheduleJob(jobDetail, trigger);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
