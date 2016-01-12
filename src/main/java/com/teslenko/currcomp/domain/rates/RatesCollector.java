package com.teslenko.currcomp.domain.rates;

import com.teslenko.currcomp.dao.db.ExchangesJdbcDao;
import com.teslenko.currcomp.domain.exchanges.Exchange;
import com.teslenko.currcomp.domain.parsers.StAXParser;
import com.teslenko.currcomp.domain.parsers.XMLParser;
import com.teslenko.currcomp.domain.parsers.XMLParsingException;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Collect rates data from all exchanges stored in database
 */
public class RatesCollector {
    private static Logger log = Logger.getLogger(RatesCollector.class.getName());

    private RatesCollector() {}

    private static class CollectorHolder {
        private static final RatesCollector instance = new RatesCollector();
    }

    public static RatesCollector getInstance() {
        return CollectorHolder.instance;
    }

    /**
     * Get list of exchanges from database and read XML files
     * though all of it by XMLParser or it implementation
     * @return List of exchanges rates
     */
    public List<Rate> collectRates() {
        List<Rate> rates = new LinkedList<>();
        List<Exchange> exchanges = new ExchangesJdbcDao().selectActiveExchanges();
        XMLParser parser = new StAXParser();
        for (Exchange exchange : exchanges) {
            try {
                rates.addAll(parser.readRatesFromXML(exchange));
            } catch (XMLParsingException e) {
                log.log(Level.INFO, exchange.getName().toUpperCase() +
                        " parsing is failed. " +
                        e.getMessage(), e);
            }
        }
        return rates;
    }
}
