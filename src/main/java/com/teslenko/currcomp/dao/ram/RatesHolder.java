package com.teslenko.currcomp.dao.ram;

import com.teslenko.currcomp.domain.rates.Rate;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Roman on 11.11.2015.
 */
public class RatesHolder {
    private List<Rate> rates;

    private static volatile RatesHolder instance;

    public static RatesHolder getInstance() {
        RatesHolder localeInstance = instance;
        if (localeInstance == null) {
            synchronized (RatesHolder.class) {
                localeInstance = instance;
                if (localeInstance == null) {
                    instance = localeInstance = new RatesHolder();
                }
            }
        }
        return localeInstance;
    }

    public RatesHolder() {
        this.rates = new CopyOnWriteArrayList<>();
        this.getRates();
    }

    public void setRates(List<Rate> rateList) {
        if (!this.rates.isEmpty()) {
            this.rates.clear();
        }
        this.rates.addAll(rateList);
    }

    public List<Rate> getRates() {
        return rates;
    }
}
