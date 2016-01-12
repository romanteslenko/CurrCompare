package com.teslenko.currcomp.domain.cash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roman on 11.11.2015.
 */
public class CashRateCollector {
    private static final String USD_URL = "http://minfin.com.ua/currency/auction/usd/buy/all/";
    private static final String EUR_URL = "http://minfin.com.ua/currency/auction/eur/buy/all/";
    private static final String RUR_URL = "http://minfin.com.ua/currency/auction/rub/buy/all/";

    public List<CashRate> collectCashRates() throws IOException {
        List<CashRate> result = new ArrayList<>();
        result.add(collectFromURL("USD", USD_URL));
        result.add(collectFromURL("EUR", EUR_URL));
        result.add(collectFromURL("RUR", RUR_URL));
        return result;
    }

    private CashRate collectFromURL(String currencyName, String adress) throws IOException {
        URL url = new URL(adress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        String askQuote = null;
        String bidQuote = null;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String previousLine = "";
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (previousLine.contains("<small class=\"au-mid-buysell--title\">")) {
                    if (askQuote == null) {
                        askQuote = currentLine.trim().substring(0, 5);
                    } else {
                        bidQuote = currentLine.trim().substring(0, 5);
                        break;
                    }
                }
                previousLine = currentLine;
            }
        }

        connection.disconnect();

        return new CashRate(currencyName, askQuote, bidQuote);
    }
}
