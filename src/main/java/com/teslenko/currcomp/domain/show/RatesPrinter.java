package com.teslenko.currcomp.domain.show;

import com.teslenko.currcomp.domain.rates.Rate;

public class RatesPrinter {
    public String printRateInTableRow(Rate rate) {
        return "<tr>" +
                    "<td>" +
                        rate.getFrom() +
                    "</td>" +
                    "<td>" +
                        rate.getTo() +
                    "</td>" +
                    "<td>" +
                        rate.result() +
                    "</td>" +
                    "<td>" +
                        "<a href=\"" + rate.getExchange().getPartnerURL() + "\">" +
                        rate.getExchange().getName() +
                        "</a>" +
                    "</td>"+
                "</tr>";
    }

    public String printCurrencyNamesInSelectFrom(String currency) {
        return "<option value=\"" + currency + "\">" + currency + "</option>";
    }
}
