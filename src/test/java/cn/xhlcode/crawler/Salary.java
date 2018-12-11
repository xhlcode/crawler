package cn.xhlcode.crawler;

import java.io.Serializable;

public class Salary implements Serializable {


    private static final long serialVersionUID = -2140234769839131214L;

    private Double basePay;
    private Double bonus;

    public Salary(Double basePay, Double bonus) {
        this.basePay = basePay;
        this.bonus = bonus;
    }

    public Double getBasePay() {
        return basePay;
    }

    public void setBasePay(Double basePay) {
        this.basePay = basePay;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }
}
