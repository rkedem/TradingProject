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
        this.vSymbols = Helper.loadSymbols(mPath, mFile);
        this.mTrades = new Vector<>();
    }

    public boolean run() {
        if (vSymbols == null || vSymbols.isEmpty()) {
            vSymbols = Helper.loadSymbols(mPath, mFile);
            if (vSymbols == null || vSymbols.isEmpty()) {
                System.out.println("No symbols loaded. Exiting...");
                return false;
            }
        }
        if (mTrades == null) {
            mTrades = new Vector<>();
        }

        for (String symbol : vSymbols) {
            SymbolTester symbolTester = new SymbolTester(symbol, mPath, riskFactor);
            if (symbolTester.test()) {
                mTrades.addAll(symbolTester.getTrades());
            }
        }
        return true;
    }

    public Vector<Trade> getTrades() {
        return mTrades;
    }

    public void reset() {
        vSymbols = null;
        mTrades = null;
    }
}

