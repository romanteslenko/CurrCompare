package com.teslenko.currcomp.dao.ram;

import com.teslenko.currcomp.domain.cash.CashRate;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Roman on 11.11.2015.
 */
public class CashRatesHolder {
    private List<CashRate> cashRates;

    private static volatile CashRatesHolder instance;

    public static CashRatesHolder getInstance() {
        CashRatesHolder localeInstance = instance;
        if (localeInstance == null) {
            synchronized (CashRatesHolder.class) {
                localeInstance = instance;
                if (localeInstance == null) {
                    instance = localeInstance = new CashRatesHolder();
                }
            }
        }
        return localeInstance;
    }

    public CashRatesHolder() {
        this.cashRates = new CopyOnWriteArrayList<>();
    }

    public void setCashRates(List<CashRate> cashRatesList) {
        if (!this.cashRates.isEmpty()) {
            this.cashRates.clear();
        }
        this.cashRates.addAll(cashRatesList);
    }

    public List<CashRate> getCashRates() {
        return cashRates;
    }
}
