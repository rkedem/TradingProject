package com.mac286.project;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        float[] riskFactors = {0.5f, 1f, 2f, 5f, 10f};
        String path = "C:/ProfOmarMAC286/Spring2024/Data/";
        String stocksFile = "stocks.txt";
        String etfsFile = "ETFs.txt";

        for (float risk : riskFactors) {
            Tester stockTester = new Tester(path, stocksFile, risk);
            stockTester.run();
            Vector<Trade> stockTrades = stockTester.getTrades();
            Statistics stockStats = Helper.computeStats(stockTrades);
            System.out.println("Statistics for Stocks with Risk Factor: " + risk);
            System.out.println(stockStats.toString());
        }

        for (float risk : riskFactors) {
            Tester etfTester = new Tester(path, etfsFile, risk);
            etfTester.run();
            Vector<Trade> etfTrades = etfTester.getTrades();
            Statistics etfStats = Helper.computeStats(etfTrades);
            System.out.println("Statistics for ETFs with Risk Factor: " + risk);
            System.out.println(etfStats.toString());
        }
    }
}

