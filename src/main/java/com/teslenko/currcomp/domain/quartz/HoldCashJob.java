package com.teslenko.currcomp.domain.quartz;

import com.teslenko.currcomp.dao.ram.CashRatesHolder;
import com.teslenko.currcomp.domain.cash.CashRate;
import com.teslenko.currcomp.domain.cash.CashRateCollector;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.util.List;

public class HoldCashJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<CashRate> cashRates = null;
        try {
            cashRates = new CashRateCollector().collectCashRates();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (cashRates != null) {
            CashRatesHolder.getInstance().setCashRates(cashRates);
        } else {

        }
    }
}
