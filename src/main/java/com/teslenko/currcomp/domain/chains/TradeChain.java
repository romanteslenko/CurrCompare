package com.teslenko.currcomp.domain.chains;

import com.teslenko.currcomp.domain.rates.Rate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TradeChain {
    private List<Rate> chain;

    public TradeChain() {
        this.chain = new ArrayList<>();
    }

    public void add(Rate rate) {
        this.chain.add(rate);
    }

    public boolean contains(Rate rate) {
        return this.chain.contains(rate);
    }

    public int size() {
        return this.chain.size();
    }

    public List<Rate> getRates() {
        return chain;
    }

    public String getStartCurrencyName() {
        return this.chain.get(0).getFrom();
    }

    public String getTargetCurrencyName() {
        return this.chain.get(chain.size() - 1).getTo();
    }

    public double result() {
        double[] quotes = new double[chain.size()];
        for (int i = 0; i < quotes.length; i++) {
            quotes[i] =
                    Double.valueOf(chain.get(i).getOut())
                            / Double.valueOf(chain.get(i).getIn());
        }
        double result = 1.0d;
        for (double quote : quotes) {
            result = result * quote;
        }
        return new BigDecimal(result).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    public String toString() {
        return "TradeChain{" +
                "result=" + this.result() +
                chain +
                '}';
    }
}
