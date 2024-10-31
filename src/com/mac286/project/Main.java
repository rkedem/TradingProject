package com.mac286.project;

public class Main {
    public static void main(String[] args) {
        // Array of risk factors for testing
        float[] riskFactors = {0.5f, 1f, 2f, 5f, 10f};

        // Path to the data folder
        String path = "C:\\Users\\rusha\\IdeaProjects\\TradingProject\\data\\";

        // Symbol to test (matching the file name in your data folder)
        String symbol = "AAL";

        // Test each risk factor for the selected symbol
        System.out.println("Testing Symbol: " + symbol);
        for (float riskFactor : riskFactors) {
            SymbolTester tester = new SymbolTester(symbol, riskFactor);
            tester.loadData();  // Load rows 76-96 only
            if (tester.test()) {
                System.out.println("Trades generated successfully for " + symbol + " with Risk Factor " + riskFactor + ":");
                var trades = tester.getTrades();
                for (Trade trade : trades) {
                    System.out.println(trade);
                }
            } else {
                System.out.println("Failed to generate trades for " + symbol);
            }
        }
    }
}