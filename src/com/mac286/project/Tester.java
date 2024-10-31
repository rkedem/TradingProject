package com.mac286.project;

import java.util.Vector;

public class Tester {
    private Vector<String> vSymbols;
    private Vector<Trade> mTrades;
    private String mPath, mFile;
    private float riskFactor;

    public Tester(String path, String file, float risk) {
        this.mPath = path;
        this.mFile = file;
        this.riskFactor = risk;
        this.vSymbols = Helper.loadSymbols(path, file);
        this.mTrades = new Vector<Trade>();
    }

    public boolean run() {
        if (vSymbols == null || vSymbols.isEmpty()) {
            System.out.println("No symbols to test.");
            return false;
        }

        for (String symbol : vSymbols) {
            SymbolTester tester = new SymbolTester(symbol, riskFactor);
            tester.loadData();
            if (tester.test()) {
                mTrades.addAll(tester.getTrades());
            }
        }
        return true;
    }

    public Vector<Trade> getTrades() {
        return mTrades;
    }

    public void reset() {
        vSymbols.clear();
        mTrades.clear();
    }
}
