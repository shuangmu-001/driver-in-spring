package org.springframework.examples.lesson01;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


/**
 * @author wcl
 *
 * @date Create in 17:02 2019-04-30
 *
 */
public class QuartzLesson01 {
    /**
     * 任务的crud 触发失败怎么办
     *
     * @see org.quartz.Calendar
     * @see org.quartz.JobDataMap
     *
     */
    public static void main(String[] args) {
        //
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            // 主要API
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            // 具体任务
            JobDetail jobDetail = JobBuilder.newJob(HelloWorld.class)
                    // JobKey  ( name + group) 身份， job唯一标志
                    .withIdentity("myJob", "group1")
                    .build();
            // 触发器
            //  public enum TriggerState { NONE, NORMAL, PAUSED, COMPLETE, ERROR, BLOCKED }
            Trigger trigger = TriggerBuilder.newTrigger()
                    // TriggerKey  ( name + group) 身份， trigger唯一标志
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(
                            SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInSeconds(40)
                                    .repeatForever())
                    .build();
            //  注册到 scheduler上
            scheduler.scheduleJob(jobDetail, trigger);
            if(!scheduler.isStarted()) {
                scheduler.start();
            }
            Thread.sleep(1000 * 60);
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            System.out.println();
        }
        
    }

}
