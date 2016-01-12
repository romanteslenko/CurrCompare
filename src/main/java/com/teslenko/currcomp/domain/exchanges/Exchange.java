package com.teslenko.currcomp.domain.exchanges;

public class Exchange {
    private String domain;
    private String name;
    private String partnerURL;
    private String ratesURL;
    private String status;
    private int id;

    public String getDomain() {
        return domain;
    }

    public String getName() {
        return name;
    }

    public String getPartnerURL() {
        return partnerURL;
    }

    public String getRatesURL() {
        return ratesURL;
    }

    public String getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPartnerURL(String partnerURL) {
        this.partnerURL = partnerURL;
    }

    public void setRatesURL(String ratesURL) {
        this.ratesURL = ratesURL;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "domain='" + domain + '\'' +
                ", name='" + name + '\'' +
                ", partnerURL='" + partnerURL + '\'' +
                ", ratesURL='" + ratesURL + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }
}
