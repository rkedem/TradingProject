package com.mac286.project;

import java.util.Vector;

public class Helper {
    // Static method that accepts a path and a file name
    // Opens the file (file symbols), reads it, and creates a Vector of strings of all symbols
    public static Vector<String> loadSymbols(String path, String file) {
        Vector<String> symbols = new Vector<>();
        // Implement the logic to open the file and read line by line
        // Add each symbol to the Vector after trimming any whitespace
        return symbols;
    }

    // Static method to compute statistics from a Vector of Trades
    public static Statistics computeStats(Vector<Trade> trades) {
        Statistics stats = new Statistics();

        // Example of computing statistics:
        // Loop through trades and calculate various statistics, such as average profit,
        // win/loss percentages, etc., and set them in the `stats` object

        // Example (pseudo code, replace with actual logic):
        // stats.averageProfit = calculateAverageProfit(trades);
        // stats.winningPercent = calculateWinningPercentage(trades);

        return stats;
    }
}
