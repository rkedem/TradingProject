package com.mac286.project;

public class Trade {
    private String entryDate, exitDate, symbol;
    private float entryPrice, exitPrice, stopLoss, Target;
    private Direction Dir;
    private int holdingPeriod;
    public boolean isOn;

    public Trade() {
        this.symbol = "";
        this.entryDate = "";
        this.entryPrice = 0;
        this.stopLoss = 0;
        this.Target = 0;
        this.holdingPeriod = 0;
        this.isOn = false;
        this.Dir = Direction.NONE;
    }

    public void open(String s, String ED, float price, float loss, float target, Direction d) {
        this.symbol = s;
        this.entryDate = ED;
        this.entryPrice = price;
        this.stopLoss = loss;
        this.Target = target;
        this.holdingPeriod = 0;
        this.isOn = true;
        this.Dir = d;
    }

    public void close(String Ed, float xPrice, int holdingP) {
        this.exitPrice = xPrice;
        this.exitDate = Ed;
        this.holdingPeriod = holdingP;
        this.isOn = false;
    }

    public float percentPL() {
        if (this.Dir == Direction.LONG) {
            return (((this.exitPrice - this.entryPrice) / this.entryPrice) * 100);
        } else if (this.Dir == Direction.SHORT) {
            return ((this.entryPrice - this.exitPrice) / this.entryPrice) * 100;
        } else {
            System.out.println("We should never be here, something is wrong!");
            return 0;
        }
    }

    public String toString() {
        return this.symbol + ", " + this.entryDate + ", " + this.entryPrice + ", " + this.stopLoss + ", " + this.Target + ", " + this.Dir + ", " + this.exitDate + ", " + this.exitPrice + ", " + this.holdingPeriod;
    }
}
