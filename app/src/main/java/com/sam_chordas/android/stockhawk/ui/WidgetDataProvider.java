package com.sam_chordas.android.stockhawk.ui;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.sam_chordas.android.stockhawk.data.StockItem;
import com.sam_chordas.android.stockhawk.data.WidgetDatabase;
import com.sam_chordas.android.stockhawk.rest.QuoteCursorAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * WidgetDataProvider acts as the adapter for the collection view widget,
 * providing RemoteViews to the widget in the getViewAt method.
 */
public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    private static final String TAG = "WidgetDataProvider";

    List<String> mCollection = new ArrayList<>();
    Context mContext = null;

    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        initData();
    }

    @Override
    public void onDataSetChanged() {
        initData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return mCollection.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                android.R.layout.simple_list_item_1);
        view.setTextViewText(android.R.id.text1, mCollection.get(position));
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    private void initData() {
        mCollection.clear();
        ArrayList<StockItem> stockArr = WidgetDatabase.stockArr;
        int n = stockArr.size();
        for (int i = 0; i < n; i++) {
            StockItem stock = stockArr.get(i);
            String sp = "    ";
            if (stock.symbol.length() == 3) {
                sp = sp + " ";
            } else if (stock.symbol.length() == 2) {
                sp = sp + "  ";
            }
            String wdStr = stock.symbol + sp + stock.bidPrice + "   " + stock.changePercent;
            mCollection.add(wdStr);
        }
    }

}
