package com.dance.chd.chddance.helper;

/**
 * Created by Andy on 11/6/2016.
 */
public class PayMoreMoneyHelper {
    private double moneys = 25.00d;
    private static PayMoreMoneyHelper ourInstance = new PayMoreMoneyHelper();

    public static PayMoreMoneyHelper getInstance() {
        return ourInstance;
    }

    private PayMoreMoneyHelper() {
    }

    public double getMoneys() {
        return moneys;
    }

    public void setMoneys(double moneys) {
        this.moneys = moneys;
    }
}
