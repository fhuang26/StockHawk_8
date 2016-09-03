package com.sam_chordas.android.stockhawk.data;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by Felix Huang
 **/
public class StockItem {
  public String symbol;
  public String bidPrice;
  public String changePercent;
  StockItem(String symbol, String bidPrice, String changePercent) {
    this.symbol = symbol;
    this.bidPrice = bidPrice;
    this.changePercent = changePercent;
  }
}