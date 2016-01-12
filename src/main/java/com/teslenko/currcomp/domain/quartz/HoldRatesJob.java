package com.teslenko.currcomp.domain.quartz;

import com.teslenko.currcomp.dao.ram.RatesHolder;
import com.teslenko.currcomp.domain.rates.Rate;
import com.teslenko.currcomp.domain.rates.RatesCollector;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

public class HoldRatesJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Rate> rates = RatesCollector.getInstance().collectRates();
        RatesHolder.getInstance().setRates(rates);
    }
}
