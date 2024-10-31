package com.mac286.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

public class SymbolTester {
    private float riskFactor;
    private String mSymbol;
    private String dataPath = "C:\\Users\\rusha\\IdeaProjects\\TradingProject\\data\\";  // Updated path

    private Vector<Bar> mData;
    private Vector<Trade> mTrades;
    private boolean loaded = false;

    public SymbolTester(String s, float risk) {
        this.riskFactor = risk;
        this.mSymbol = s;
        this.mData = new Vector<Bar>(3000, 100);
        this.mTrades = new Vector<Trade>(200, 100);
        this.loaded = false;
    }

    public Vector<Trade> getTrades() {
        return mTrades;
    }

    public void loadData() {
        String fileName = dataPath + mSymbol + "_Daily.csv";
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();  // Skip header line
            while ((line = br.readLine()) != null) {
                Bar b = new Bar(line);
                mData.add(b);
            }
            loaded = true;
            br.close();
            fr.close();
        } catch (IOException e) {
            System.out.println("Something is wrong: " + e.getMessage());
            loaded = false;
        }
    }

    private boolean xDaysLow(int ind, int days) {
        for (int i = ind - 1; i > ind - days; i--) {
            if (mData.elementAt(i).getLow() < mData.elementAt(ind).getLow())
                return false;
        }
        return true;
    }

    private boolean xDaysHigh(int ind, int days) {
        for (int i = ind - 1; i > ind - days; i--) {
            if (mData.elementAt(i).getHigh() > mData.elementAt(ind).getHigh())
                return false;
        }
        return true;
    }

    void outcomes(Trade T, int ind) {
        for (int i = ind; i < mData.size(); i++) {
            if (T.getDir() == Direction.LONG) {
                if (mData.elementAt(i).getHigh() > T.getTarget()) {
                    if (mData.elementAt(i).getOpen() > T.getTarget()) {
                        T.close(mData.elementAt(i).getDate(), mData.elementAt(i).getOpen(), i - ind);
                        return;
                    } else {
                        T.close(mData.elementAt(i).getDate(), T.getTarget(), i - ind);
                        return;
                    }
                } else if (mData.elementAt(i).getLow() < T.getStopLoss()) {
                    if (mData.elementAt(i).getOpen() < T.getStopLoss()) {
                        T.close(mData.elementAt(i).getDate(), mData.elementAt(i).getOpen(), i - ind);
                        return;
                    } else {
                        T.close(mData.elementAt(i).getDate(), T.getStopLoss(), i - ind);
                        return;
                    }
                }
            } else {
                if (mData.elementAt(i).getLow() <= T.getTarget()) {
                    if (mData.elementAt(i).getOpen() < T.getTarget()) {
                        T.close(mData.elementAt(i).getDate(), mData.elementAt(i).getOpen(), i - ind);
                        return;
                    } else {
                        T.close(mData.elementAt(i).getDate(), T.getTarget(), i - ind);
                        return;
                    }
                } else if (mData.elementAt(i).getHigh() >= T.getStopLoss()) {
                    if (mData.elementAt(i).getOpen() > T.getStopLoss()) {
                        T.close(mData.elementAt(i).getDate(), mData.elementAt(i).getOpen(), i - ind);
                        return;
                    } else {
                        T.close(mData.elementAt(i).getDate(), T.getStopLoss(), i - ind);
                        return;
                    }
                }
            }
        }
        T.close(mData.elementAt(mData.size() - 1).getDate(), mData.elementAt(mData.size() - 1).getClose(), mData.size() - 1 - ind);
    }

    public boolean test() {
        if (!loaded) {
            loadData();
            if (!loaded) {
                System.out.println("Cannot load data");
                return false;
            }
        }

        for (int i = 20; i < mData.size() - 2; i++) {
            if (xDaysLow(i, 10)
                    && mData.elementAt(i).getLow() < mData.elementAt(i - 1).getLow()
                    && mData.elementAt(i).getHigh() > mData.elementAt(i - 1).getHigh()
                    && (mData.elementAt(i).getHigh() - mData.elementAt(i).getClose()) / (mData.elementAt(i).range()) < 0.1) {
                float entryprice = mData.elementAt(i + 1).getOpen();
                float stoploss = mData.elementAt(i).getLow() - 0.01f;
                float risk = entryprice - stoploss;
                float target = entryprice + riskFactor * risk;
                Trade T = new Trade();
                T.open(mSymbol, mData.elementAt(i + 1).getDate(), entryprice, stoploss, target, Direction.LONG);
                outcomes(T, i + 1);
                mTrades.add(T);
            } else if (xDaysHigh(i, 10)
                    && mData.elementAt(i).getHigh() > mData.elementAt(i - 1).getHigh()
                    && mData.elementAt(i).getLow() < mData.elementAt(i - 1).getLow()
                    && (mData.elementAt(i).getClose() - mData.elementAt(i).getLow()) / (mData.elementAt(i).getHigh() - mData.elementAt(i).getLow()) < 0.1) {
                float entryprice = mData.elementAt(i + 1).getOpen();
                float stoploss = mData.elementAt(i).getHigh() + 0.01f;
                float risk = stoploss - entryprice;
                float target = entryprice - riskFactor * risk;
                Trade T = new Trade();
                T.open(mSymbol, mData.elementAt(i + 1).getDate(), entryprice, stoploss, target, Direction.SHORT);
                outcomes(T, i + 1);
                mTrades.add(T);
            }
        }
        return true;
    }
}
