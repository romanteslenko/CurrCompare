package com.teslenko.currcomp.domain.cash;

public class CashRate {
    private String currencyName;
    private String askQuote;
    private String bidQuote;

    public CashRate(String currencyName, String askQuote, String bidQuote) {
        this.currencyName = currencyName;
        this.askQuote = askQuote;
        this.bidQuote = bidQuote;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getAskQuote() {
        return askQuote;
    }

    public String getBidQuote() {
        return bidQuote;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public void setAskQuote(String askQuote) {
        this.askQuote = askQuote;
    }

    public void setBidQuote(String bidQuote) {
        this.bidQuote = bidQuote;
    }

    @Override
    public String toString() {
        return "CashRate{" +
                "currencyName='" + currencyName + '\'' +
                ", askQuote='" + askQuote + '\'' +
                ", bidQuote='" + bidQuote + '\'' +
                '}';
    }
}
