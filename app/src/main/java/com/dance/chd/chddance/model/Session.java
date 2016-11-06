package com.dance.chd.chddance.model;

import android.util.SparseArray;

/**
 * Created by Andy on 11/5/2016.
 */

public class Session {
    SparseArray<Integer> exoticDancerListId;
    int userId;
    double money;

    public SparseArray<Integer> getExoticDancerListId() {
        return exoticDancerListId;
    }

    public void setExoticDancerListId(SparseArray<Integer> exoticDancerListId) {
        this.exoticDancerListId = exoticDancerListId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
