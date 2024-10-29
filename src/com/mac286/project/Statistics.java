package com.mac286.project;

public class Statistics {
    private int totalTrades;
    private int wins;
    private int losses;
    private float winRate;
    private float totalProfit;
    private float averageProfit;
    private float maxProfit;
    private float maxLoss;

    public void setTotalTrades(int totalTrades) {
        this.totalTrades = totalTrades;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setWinRate(float winRate) {
        this.winRate = winRate;
    }

    public void setTotalProfit(float totalProfit) {
        this.totalProfit = totalProfit;
    }

    public void setAverageProfit(float averageProfit) {
        this.averageProfit = averageProfit;
    }

    public void setMaxProfit(float maxProfit) {
        this.maxProfit = maxProfit;
    }

    public void setMaxLoss(float maxLoss) {
        this.maxLoss = maxLoss;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "totalTrades=" + totalTrades +
                ", wins=" + wins +
                ", losses=" + losses +
                ", winRate=" + winRate +
                ", totalProfit=" + totalProfit +
                ", averageProfit=" + averageProfit +
                ", maxProfit=" + maxProfit +
                ", maxLoss=" + maxLoss +
                '}';
    }
}

