package org.springframework.examples.lesson01;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author wcl
 *
 * @date Create in 17:06 2019-04-30
 *
 */
public class HelloWorld implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("hello world!");
    }
}
