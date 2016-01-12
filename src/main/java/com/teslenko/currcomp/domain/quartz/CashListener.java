package com.teslenko.currcomp.domain.quartz;

import org.quartz.*;
import org.quartz.ee.servlet.QuartzInitializerListener;
import org.quartz.impl.StdSchedulerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class CashListener extends QuartzInitializerListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        super.contextInitialized(sce);
        ServletContext context = sce.getServletContext();
        StdSchedulerFactory factory = (StdSchedulerFactory) context.getAttribute(QUARTZ_FACTORY_KEY);
        try {
            Scheduler scheduler = factory.getScheduler();
            JobDetail jobDetail = JobBuilder.newJob(HoldCashJob.class).build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("cashMoney")
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(600).repeatForever())
                    .startNow()
                    .build();
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (SchedulerException e) {
//            e.printStackTrace();
            System.out.println(">>>>>There was an error scheduling the job." + e.getMessage());
        }
    }
}
