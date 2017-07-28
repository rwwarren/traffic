package com.wrixton.jobs;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.spi.JobFactory;
import org.quartz.spi.TriggerFiredBundle;

public class JobScheduler implements JobFactory {

    private final Scheduler scheduler;

    public JobScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public Job newJob(TriggerFiredBundle triggerFiredBundle, Scheduler scheduler) throws SchedulerException {
        return null;
    }
}
