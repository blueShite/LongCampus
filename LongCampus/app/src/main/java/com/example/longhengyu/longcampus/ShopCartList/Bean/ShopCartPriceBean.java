package com.example.longhengyu.longcampus.ShopCartList.Bean;

import java.io.Serializable;

/**
 * Created by longhengyu on 2017/7/5.
 */

public class ShopCartPriceBean implements Serializable {

    /**
     * total : 144.5
     * pack : 5.5
     */

    private double total;
    private double pack;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getPack() {
        return pack;
    }

    public void setPack(double pack) {
        this.pack = pack;
    }
}
