package com.teslenko.currcomp.domain.parsers;

import com.teslenko.currcomp.domain.exchanges.Exchange;
import com.teslenko.currcomp.domain.rates.Rate;

import java.util.List;

/**
 * Managing information from specific quotes XML files
 */
public interface XMLParser {

    /**
     * Read data from different rates export XML files
     * and put it into a list
     * @param exchange Certain exchange from database
     * @return List of items, which contains list of rates from certain exchange
     * @throws XMLParsingException
     */
    public List<Rate> readRatesFromXML(Exchange exchange) throws XMLParsingException;
}
