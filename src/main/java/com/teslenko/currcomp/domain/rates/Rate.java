package com.teslenko.currcomp.domain.rates;

import com.teslenko.currcomp.domain.exchanges.Exchange;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rate {
    private String from;
    private String to;
    private String in;
    private String out;
    private String amount;
    private Exchange exchange;

    public double result() {
        return new BigDecimal(Double.valueOf(this.in) / Double.valueOf(this.out))
                .setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getIn() {
        return in;
    }

    public String getOut() {
        return out;
    }

    public String getAmount() {
        return amount;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setIn(String in) {
        this.in = in;
    }

    public void setOut(String out) {
        this.out = out;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", in='" + in + '\'' +
                ", out='" + out + '\'' +
                ", exchange='" + exchange.getName() + '\'' +
                '}';
    }


}
