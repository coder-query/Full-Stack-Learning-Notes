package cn.itcast.entity;

import org.quartz.*;

public class Task {
    //任务的key
    private String key;
    //任务名称
    private String name;
    //任务组
    private String group;
    //任务描述
    private String desc;
    //任务类的Class
    private Class classType;
    //任务表达式
    private String cron;
    //触发器
    private Trigger trigger;
    //任务
    private JobDetail jobDetail;

    /**
     * 获取触发器
     *
     * @return
     */
    public Trigger getTrigger() {
        if (trigger == null) {
            trigger = TriggerBuilder.newTrigger().startNow()
                    .withIdentity(name, group).forJob(name)
                    .withSchedule(CronScheduleBuilder.cronSchedule(cron))
                    .build();
        }
        return trigger;
    }

    public JobDetail getJob() {
        if (jobDetail == null) {
            jobDetail = JobBuilder.newJob(classType).withIdentity(name).build();
        }
        return jobDetail;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Class getClassType() {
        return classType;
    }

    public void setClassType(Class classType) {
        this.classType = classType;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }
}
