package com.teslenko.currcomp.domain.show;

import com.teslenko.currcomp.domain.chains.TradeChain;

public class ChainPrinter {
    public String printArbitrageChain(TradeChain chain) {
        String rows = "";
        for (int i = 1; i < chain.size(); i++) {
            rows += "<tr>" +
                    "<td>" + chain.getRates().get(i).getFrom() + " -> " +
                    chain.getRates().get(i).getTo() + "</td>" +
                    "<td>" + chain.getRates().get(i).result() + "</td>" +
                    "<td><a href=\"" + chain.getRates().get(i).getExchange().getPartnerURL() + "\">" +
                    chain.getRates().get(i).getExchange().getName() + "</a></td>" +
                    "</tr>";
        }

        return
        "<tr>" +
        "<td rowspan=\"" + chain.size() + "\">" + chain.result() + "</td>" +
        "<td>" + chain.getRates().get(0).getFrom() + " -> " + chain.getRates().get(0).getTo() + "</td>" +
                "<td>" + chain.getRates().get(0).result() + "</td>" +
                "<td><a href=\"" + chain.getRates().get(0).getExchange().getPartnerURL() + "\">" +
                chain.getRates().get(0).getExchange().getName() + "</a></td>" +
                "</tr>" +
                rows;
    }
}
