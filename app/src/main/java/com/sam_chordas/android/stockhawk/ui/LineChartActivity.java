package com.sam_chordas.android.stockhawk.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.sam_chordas.android.stockhawk.data.PricesOverTime;
import com.sam_chordas.android.stockhawk.R;

import java.util.ArrayList;
import java.util.List;

public class LineChartActivity extends AppCompatActivity {

    LineChart lcPrices;
    ArrayList<PricesOverTime> pricesOverTimeArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        lcPrices = (LineChart)findViewById(R.id.lcPrices);

        if(getIntent()!=null)
        {
            String stockSymbol = getIntent().getStringExtra(getString(R.string.stock_name)).toString();
            String title = stockSymbol + "   Aug 2016";
            setTitle(title);
            pricesOverTimeArrayList = new ArrayList<PricesOverTime>();
            pricesOverTimeArrayList = getIntent().getParcelableArrayListExtra("prices3");

            plotStockPrice(pricesOverTimeArrayList);
            plotVolume(pricesOverTimeArrayList);
        }

    }

    void plotStockPrice(ArrayList stockPriceArrayList)
    {
        List<Entry> entries = new ArrayList<>();
        for(int k = stockPriceArrayList.size()-1; k >= 0; k--)
        {
            PricesOverTime pricesOverTime = (PricesOverTime) stockPriceArrayList.get(k);

            float p = Float.parseFloat(pricesOverTime.getClose());
            String str = (pricesOverTime.getDate());
            String date[] = str.split("-"); // yyyy-mm-dd

            Entry entry = new Entry(Float.parseFloat(date[2]), p); // (x, y) = (date, price)
            entries.add(entry);
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        dataSet.setLineWidth((float) 2.6);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setDrawCircleHole(true);
        LineData lineData = new LineData(dataSet);
        lcPrices.setData(lineData);
        lcPrices.setDescription("Stock Price");
        lcPrices.invalidate();
        // lineChart.animateY(5000);
    }

    void plotVolume(ArrayList stockVolumeArrayList) {
        List<Entry> entries = new ArrayList<>();
        for(int k = stockVolumeArrayList.size()-1; k >= 0; k--)
        {
            PricesOverTime pricesOverTime = (PricesOverTime) stockVolumeArrayList.get(k);

            float vol = Float.parseFloat(pricesOverTime.getVolume());
            String str = (pricesOverTime.getDate());
            String date[] = str.split("-"); // yyyy-mm-dd

            Entry entry = new Entry(Float.parseFloat(date[2]), vol); // (x, y) = (date, volume)
            entries.add(entry);
        }

        LineDataSet dataSet = new LineDataSet(entries, "Label");
        dataSet.setLineWidth((float) 1.3);
        dataSet.setColor(Color.BLACK);
        dataSet.setDrawCircleHole(true);
        LineData lineData = new LineData(dataSet);
        LineChart lcVolume = (LineChart)findViewById(R.id.lcVolume);
        lcVolume.setData(lineData);
        lcVolume.setDescription("Volume");
        lcVolume.invalidate();
        // lineChart.animateY(5000);
    }
}

