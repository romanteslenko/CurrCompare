package com.teslenko.currcomp.domain.chains;

import com.teslenko.currcomp.domain.rates.Rate;
import com.teslenko.currcomp.domain.rates.RatesManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class TradeChainManager {

    /**
     * Find all possible double and triple chains of rate deals
     * @param rates List of best rates
     * @return List of trade chains
     */
    public List<TradeChain> findAllChains(List<Rate> rates) {
        List<TradeChain> result = new ArrayList<>();
        RatesManager ratesManager = new RatesManager();
        Set<String> inCurrencies = ratesManager.getIncomingCurrenciesNames(rates);
        Set<String> outCurrencies = ratesManager.getOutgoingCurrenciesNames(rates);
        for (String firstCurrency : inCurrencies) {
            for (String targetCurrency : outCurrencies) {
//                if (targetCurrency.equals(firstCurrency)) {
//                    continue;
//                }
                for (String secondCurrency : inCurrencies) {
                    if (secondCurrency.equals(firstCurrency)
                            || secondCurrency.equals(targetCurrency)) {
                        continue;
                    }
                    TradeChain shortChain = new TradeChain();
                    shortChain.add(ratesManager.findRate(firstCurrency, secondCurrency, rates));
                    shortChain.add(ratesManager.findRate(secondCurrency, targetCurrency, rates));
                    if (shortChain.contains(null)) {
                        continue;
                    }
                    result.add(shortChain);
                    for (String thirdCurrency : inCurrencies) {
                        if (thirdCurrency.equals(firstCurrency)
                                || thirdCurrency.equals(secondCurrency)
                                || thirdCurrency.equals(targetCurrency)) {
                            continue;
                        }
                        TradeChain longChain = new TradeChain();
                        longChain.add(ratesManager.findRate(firstCurrency, secondCurrency, rates));
                        longChain.add(ratesManager.findRate(secondCurrency, thirdCurrency, rates));
                        longChain.add(ratesManager.findRate(thirdCurrency, targetCurrency, rates));
                        if (longChain.contains(null)) {
                            continue;
                        }
                        result.add(longChain);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Finds all trade chains grouped by start and target currency
     * and sorts them by profit.
     * @param startCurrency currency needed to be changed
     * @param targetCurrency currency formed as a result of exchange trade
     * @param chains list of trade chains
     * @return
     */
    public List<TradeChain> selectSpecialChains(String startCurrency,
                                                String targetCurrency,
                                                List<TradeChain> chains) {
        List<TradeChain> result = new ArrayList<>();
        for (TradeChain chain : chains) {
            if (startCurrency.equals(chain.getStartCurrencyName())
                    && targetCurrency.equals(chain.getTargetCurrencyName())) {
                result.add(chain);
            }
        }
        sortByProfitIncrease(result);
        return result;
    }



    /**
     * Sort list of trade chains by course profit
     * @param chains List of arbitrage deals
     */
    static void sortByProfitIncrease(List<TradeChain> chains) {
        chains.sort(new Comparator<TradeChain>() {
            @Override
            public int compare(TradeChain o1, TradeChain o2) {
                if (o1.result() - o2.result() <= 0) {
                    return 1;
                }
                return -1;
            }
        });
    }
}
