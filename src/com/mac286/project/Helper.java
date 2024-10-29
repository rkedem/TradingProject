package com.mac286.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class Helper {
    public static Vector<String> loadSymbols(String path, String file){
        Vector<String> symbols = new Vector<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path + "/" + file))) {
            String line;
            while ((line = br.readLine()) != null) {
                symbols.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading symbols file: " + e.getMessage());
        }
        return symbols;
    }

    public static Statistics computeStats(Vector<Trade> trades) {
        Statistics stats = new Statistics();
        if (trades == null || trades.isEmpty()) {
            return stats;
        }

        int totalTrades = trades.size();
        int wins = 0;
        int losses = 0;
        float totalProfit = 0;
        float maxProfit = Float.MIN_VALUE;
        float maxLoss = Float.MAX_VALUE;

        for (Trade trade : trades) {
            float tradeResult = trade.percentPL();
            totalProfit += tradeResult;

            if (tradeResult > 0) {
                wins++;
                maxProfit = Math.max(maxProfit, tradeResult);
            } else {
                losses++;
                maxLoss = Math.min(maxLoss, tradeResult);
            }
        }

        float winRate = (float) wins / totalTrades * 100;
        float averageProfit = totalProfit / totalTrades;

        stats.setTotalTrades(totalTrades);
        stats.setWins(wins);
        stats.setLosses(losses);
        stats.setWinRate(winRate);
        stats.setTotalProfit(totalProfit);
        stats.setAverageProfit(averageProfit);
        stats.setMaxProfit(maxProfit);
        stats.setMaxLoss(maxLoss);

        return stats;
    }
}

