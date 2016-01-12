package com.teslenko.currcomp.domain.chains;

import java.util.*;

/**
 * Created by Roman on 15.11.2015.
 */
public class ArbitrageManager {
    public List<TradeChain> findAllArbitrages(List<TradeChain> chains) {
        List<TradeChain> result = new ArrayList<>();
        for (TradeChain chain : chains) {
            if (chain.getStartCurrencyName().equals(chain.getTargetCurrencyName())) {
                result.add(chain);
            }
        }
//        TradeChainManager.sortByProfitIncrease(result);
        return result;
    }

    public List<TradeChain> selectBestArbitrages(List<TradeChain> arbitrages) {
        List<TradeChain> bestArbitrages = new ArrayList<>();
        Set<String> currencies = new HashSet<>();
        for (TradeChain chain : arbitrages) {
            currencies.add(chain.getTargetCurrencyName());
        }
        for (String currency : currencies) {
            List<TradeChain> subresult = new TradeChainManager().
                    selectSpecialChains(currency, currency, arbitrages);
            TradeChainManager.sortByProfitIncrease(subresult);
            bestArbitrages.add(subresult.get(0));
        }
        TradeChainManager.sortByProfitIncrease(bestArbitrages);
        return bestArbitrages;
    }
}
