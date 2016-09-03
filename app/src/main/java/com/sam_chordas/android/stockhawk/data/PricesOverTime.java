package com.sam_chordas.android.stockhawk.data;

import android.os.Parcel;
import android.os.Parcelable;


public class PricesOverTime implements Parcelable {
    String symbol;
    String date;
    String close;
    String volume;
    String low;
    String high;
    String open;

    public PricesOverTime(String symbol, String date, String low, String high, String open, String close, String volume) {
        this.symbol = symbol;
        this.date = date;
        this.close = close;
        this.volume = volume;
        this.low = low;
        this.high = high;
        this.open = open;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getLow() {
        return low;
    }
    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }
    public void setHigh(String high) {
        this.high = high;
    }

    public String getOpen() {
        return open;
    }
    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }
    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }
    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.symbol);
        dest.writeString(this.date);
        dest.writeString(this.low);
        dest.writeString(this.high);
        dest.writeString(this.open);
        dest.writeString(this.close);
        dest.writeString(this.volume);
    }

    protected PricesOverTime(Parcel in) {
        this.symbol = in.readString();
        this.date = in.readString();
        this.low = in.readString();
        this.high = in.readString();
        this.open = in.readString();
        this.close = in.readString();
        this.volume = in.readString();
    }

    public static final Creator<PricesOverTime> CREATOR = new Creator<PricesOverTime>() {
        @Override
        public PricesOverTime createFromParcel(Parcel source) {
            return new PricesOverTime(source);
        }

        @Override
        public PricesOverTime[] newArray(int size) {
            return new PricesOverTime[size];
        }
    };
}
