package org.cmms.modules.hr.yggl.ygxxgl.controller;

import org.cmms.modules.hr.yggl.ygxxgl.service.IHrBasStaffService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateStaffInfoJob implements Job {
    @Autowired
    IHrBasStaffService hrBasStaffService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        hrBasStaffService.updateStaffInfo();
    }
}
