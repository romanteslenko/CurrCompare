package com.teslenko.currcomp.domain.show;

import com.teslenko.currcomp.domain.exchanges.Exchange;

public class ExchangesPrinter {
    public String printExchangeData(Exchange exchange) {
        String status;
        if ("active".equals(exchange.getStatus())) {
            status = "<option value=\"active\" selected>active</option>" +
                    "<option value=\"blocked\">blocked</option>";
        } else {
            status = "<option value=\"active\">active</option>" +
                    "<option value=\"blocked\" selected>blocked</option>";
        }
        return
                "<tr>" +
                        "<form action=\"/admin\" method=\"POST\">" +
                        "<td class=\"id-column\">" +
                        "   <input type=\"hidden\" name=\"id\" value=\"" + exchange.getId() + "\">" + exchange.getId() +
                        "</td>" +
                        "<td>" +
                        "   <input type=\"text\" class=\"domain-column\" name=\"domain\" value=\"" + exchange.getDomain() + "\">" +
                        "</td>" +
                        "<td>" +
                        "   <input type=\"text\" class=\"name-column\" name=\"name\" value=\"" + exchange.getName() + "\">" +
                        "</td>" +
                        "<td>" +
                        "   <input type=\"text\" class=\"partner-column\" name=\"partner-url\" value=\"" + exchange.getPartnerURL() + "\">" +
                        "</td>" +
                        "<td>" +
                        "   <input type=\"text\" class=\"xml-column\" name=\"rates-url\" value=\"" + exchange.getRatesURL() + "\">" +
                        "</td>" +
                        "<td>" +
                            "<select name=\"status\"class=\"status-column\">" +
                                status +
                            "</select>" +
                        "</td>" +
                        "<td>" +
                        "   <input type=\"submit\" value=\"Confirm\" name=\"button\">" +
                        "</td>" +
                        "<td>" +
                        "   <input type=\"submit\" value=\"Delete\" name=\"button\">" +
                        "</td>" +
                        "</form>" +
                        "</tr>";
    }
}
