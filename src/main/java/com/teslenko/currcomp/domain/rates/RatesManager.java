package com.teslenko.currcomp.domain.rates;

import com.teslenko.currcomp.dao.ram.RatesHolder;

import java.util.*;

/**
 * Contains set of methods purposed to manipulate rates: create list, select specific data, etc.
 */
public class RatesManager {

    public List<Rate> grabRatesFromHolder() {
        List<Rate> result = new LinkedList<>();
        Iterator<Rate> iterator = RatesHolder.getInstance().getRates().iterator();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    public static void sortByCurrencyName(List<Rate> rates) {
        rates.sort(new Comparator<Rate>() {
            @Override
            public int compare(Rate o1, Rate o2) {
                return o1.getFrom().hashCode() - o2.getFrom().hashCode();
            }
        });
    }

    public List<Rate> selectSpecificRates(String from, String to,
                                          List<Rate> ratesList) {
        List<Rate> result = new LinkedList<>();
        for (Rate rate : ratesList) {
            if (from.equals(rate.getFrom())
                    && to.equals(rate.getTo())) {
                result.add(rate);
            }
        }
        return result;
    }

    public List<Rate> selectBestRates(List<Rate> ratesList) {
        List<Rate> bestRates = new ArrayList<>();
        Set<String> fromSet = this.getOutgoingCurrenciesNames(ratesList);
        Set<String> toSet = this.getIncomingCurrenciesNames(ratesList);
        for (String from : fromSet) {
            for (String to : toSet) {
                List<Rate> rates = this.selectSpecificRates(from, to, ratesList);
                rates.sort(new Comparator<Rate>() {
                    @Override
                    public int compare(Rate o1, Rate o2) {
                        if (o1.result() - o2.result() <= 0) {
                            return 1;
                        }
                        return -1;
                    }
                });
                if (!rates.isEmpty()) {
                    Rate best = rates.get(0);
                    bestRates.add(best);
                }
            }
        }
        return bestRates;
    }

    public Rate findRate(String from, String to, List<Rate> rates) {
        Rate result = null;
        for (Rate item : rates) {
            if (from.equals(item.getFrom()) && to.equals(item.getTo())) {
                result = item;
                break;
            }
        }
        return result;
    }

    public Set<String> getIncomingCurrenciesNames(List<Rate> rates) {
        Set<String> result = new HashSet<>();
        for (Rate item : rates) {
            result.add(item.getTo());
        }
        return result;
    }

    public Set<String> getOutgoingCurrenciesNames(List<Rate> rates) {
        Set<String> result = new HashSet<>();
        for (Rate item : rates) {
            result.add(item.getFrom());
        }
        return result;
    }

    /*private static List<Arbitrage> selectSpecificArbitrages(String targetCurrency, List<Arbitrage> arbitrages) {
        List<Arbitrage> result = new ArrayList<>();
        for (Arbitrage arbitrage : arbitrages) {
            if (targetCurrency.equals(arbitrage.getTargetCurrency())) {
                result.add(arbitrage);
            }
        }
        sortByProfitIncrease(result);
        return result;
    }

    private static void sortByProfitIncrease(List<Arbitrage> result) {
        result.sort(new Comparator<Arbitrage>() {
            @Override
            public int compare(Arbitrage o1, Arbitrage o2) {
                if (o1.calcResult() - o2.calcResult() <= 0) {
                    return 1;
                }
                return -1;
            }
        });
    }*/

    /*private static List<Arbitrage> pickUpBestArbitrages(List<Arbitrage> arbitrages) {
        List<Arbitrage> result = new ArrayList<>();
        Set<String> targets = new HashSet<>();
        for (Arbitrage arbitrage : arbitrages) {
            targets.add(arbitrage.getTargetCurrency());
        }
        for (String target : targets) {
            List<Arbitrage> subresult = new ArrayList<>();
            for (Arbitrage arbitrage : arbitrages) {
                if (target.equals(arbitrage.getTargetCurrency())) {
                    subresult.add(arbitrage);
                }
            }
            sortByProfitIncrease(subresult);
            result.add(subresult.get(0));
        }
        return result;
    }*/

    /*private static List<Arbitrage> findAllArbitrages(List<Rate> items) {
        List<Arbitrage> result = new LinkedList<>();
        Set<String> currencies = getOutgoingCurrenciesNames(items);
        for (String targetCurrency : currencies) {
            for (String secondCurrency : currencies) {
                if (targetCurrency.equals(secondCurrency)) {
                    continue;
                }
                Arbitrage simpleArbitrage = new Arbitrage(targetCurrency);
                simpleArbitrage.add(findRate(targetCurrency, secondCurrency, items));
                simpleArbitrage.add(findRate(secondCurrency, targetCurrency, items));
                if (!simpleArbitrage.contains(null)) {
                    result.add(simpleArbitrage);
                } else {
                    for (String thirdCurrency : currencies) {
                        if (thirdCurrency.equals(targetCurrency)
                                || thirdCurrency.equals(secondCurrency)) {
                            continue;
                        }
                        Arbitrage chainArbitrage = new Arbitrage(targetCurrency);
                        chainArbitrage.add(findRate(targetCurrency, secondCurrency, items));
                        chainArbitrage.add(findRate(secondCurrency, thirdCurrency, items));
                        chainArbitrage.add(findRate(thirdCurrency, targetCurrency, items));
                        if (!chainArbitrage.contains(null)) {
                            result.add(chainArbitrage);
                        }
                    }
                }
            }
        }
        return result;
    }*/

    /*private static Rate findRate(String from, String to, List<Rate> items) {
        Rate result = null;
        for (Rate item : items) {
            if (from.equals(item.getFrom()) && to.equals(item.getTo())) {
                result = item;
                break;
            }
        }
        return result;
    }*/

    /*public static Set<String> getIncomingCurrenciesNames(List<Rate> itemsList) {
        Set<String> result = new HashSet<>();
        for (Rate item : itemsList) {
            result.add(item.getTo());
        }
        return result;
    }

    public static Set<String> getOutgoingCurrenciesNames(List<Rate> itemsList) {
        Set<String> result = new HashSet<>();
        for (Rate item : itemsList) {
            result.add(item.getFrom());
        }
        return result;
    }*/
}
