package com.sam_chordas.android.stockhawk.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

import java.util.ArrayList;

/**
 * Created by Felix Huang
 **/

public class WidgetDatabase {
  public static ArrayList<StockItem> stockArr = new ArrayList<StockItem>();
  public static void add (String symbol, String bidPrice, String changePercent) {
    StockItem stock = new StockItem(symbol, bidPrice, changePercent);
    stockArr.add(stock);
  }
}
