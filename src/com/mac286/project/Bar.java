package com.mac286.project;

public class Bar {
    private String Date;
    private float open;
    private float high;
    private float low, close, adjClose;
    private long volume;

    public Bar(String st) {
        String[] stAr = st.split(",");
        if(stAr.length != 7) {
            System.out.println("Problem in bar");
            return;
        }
        Date = stAr[0];
        open = Float.parseFloat(stAr[1]);
        high = Float.parseFloat(stAr[2]);
        low = Float.parseFloat(stAr[3]);
        close = Float.parseFloat(stAr[4]);
        adjClose = Float.parseFloat(stAr[5]);
        volume = Long.parseLong(stAr[6]);
    }

    public float range() {
        return (this.high - this.low);
    }

    public String getDate() {
        return Date;
    }

    public float getOpen() {
        return open;
    }

    public float getHigh() {
        return high;
    }

    public float getLow() {
        return low;
    }

    public float getClose() {
        return close;
    }

    public float getAdjClose() {
        return adjClose;
    }

    public long getVolume() {
        return volume;
    }

    public String toString() {
        return this.Date + ", " + open + ", " + high + ", " + low + ", "+ close + ", " + adjClose + ", " + volume;
    }
}

