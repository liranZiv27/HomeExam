package com.liran.homeexam;

/**
 * Created by Liran on 15/12/2017.
 */

public class Conversion {
    private String currency;
    private String revenue;

    public Conversion(){}
    public Conversion(String currency, String revenue){
        this.currency = currency;
        this.revenue = revenue;
    }

    public String getRevenue() {
        return revenue;
    }
    public String getCurrency() {
        return currency;
    }

}