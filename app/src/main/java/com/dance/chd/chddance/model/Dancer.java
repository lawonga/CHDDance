package com.dance.chd.chddance.model;

/**
 * Created by Andy on 11/5/2016.
 */

public class Dancer {
    String name;
    int id, drawable, quantity;
    boolean premium;

    public Dancer(String name, int id, int drawable, boolean premium) {
        this.name = name;
        this.id = id;
        this.drawable = drawable;
        this.premium = premium;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            this.quantity = 0;
        } else {
            this.quantity = quantity;
        }
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
