package com.sam_chordas.android.stockhawk.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.google.android.gms.gcm.TaskParams;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.sam_chordas.android.stockhawk.data.PricesOverTime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by sam_chordas on 10/1/15.
 */
public class StockIntentService extends IntentService {
  private OkHttpClient client = new OkHttpClient();
  public static String TAG = "StockIntentService";
  public StockIntentService(){
    super(StockIntentService.class.getName());
  }

  public StockIntentService(String name) {
    super(name);
  }

  @Override protected void onHandleIntent(Intent intent) {
    // Log.d(StockIntentService.class.getSimpleName(), "Stock Intent Service");
    if (intent.getStringExtra("job") != null &&
            intent.getStringExtra("job").equals("lineChart")) {

      String symbol = intent.getStringExtra("symbol");
      String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.historicaldata%20where%20symbol%20%3D%20%22"+symbol+"%22%20and%20startDate%20%3D%20%222016-08-09%22%20and%20endDate%20%3D%20%222016-08-28%22&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
      Request request = new Request.Builder().url(url).build();

      Response response = null;
      ArrayList<PricesOverTime> pricesOverTimeList = new ArrayList<>();
      try {
        response = client.newCall(request).execute();
//                Log.d(TAG,"response:"+response.body().string());

        String s =response.body().string();
        JSONObject jsonObject = new JSONObject(s);
        JSONArray jsonArray = jsonObject.getJSONObject("query").getJSONObject("results").getJSONArray("quote");

        for(int i=0;i<jsonArray.length();++i)
        {
          String sym = jsonArray.getJSONObject(i).getString("Symbol");
          String date = jsonArray.getJSONObject(i).getString("Date");
          String close = jsonArray.getJSONObject(i).getString("Close");
          String vol = jsonArray.getJSONObject(i).getString("Volume");
          String low = jsonArray.getJSONObject(i).getString("Low");
          String high = jsonArray.getJSONObject(i).getString("High");
          String open = jsonArray.getJSONObject(i).getString("Open");
          PricesOverTime pricesOverTime = new PricesOverTime(sym, date, low, high, open, close, vol);
          pricesOverTimeList.add(pricesOverTime);
        }
      } catch (JSONException|IOException e) {
        e.printStackTrace();
      }

      Intent intent2 = new Intent("StockIntentService");
      intent2.putParcelableArrayListExtra("prices", pricesOverTimeList);
      // Log.d("StockIntentService","size:"+stockDetailList.size());
      intent2.putExtra(getString(com.sam_chordas.android.stockhawk.R.string.stock_name), symbol);
      LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
    } else {
      StockTaskService stockTaskService = new StockTaskService(this);
      Bundle args = new Bundle();
      if (intent.getStringExtra("tag").equals("add")) {
        args.putString("symbol", intent.getStringExtra("symbol"));
      }
      // We can call OnRunTask from the intent service to force it to run immediately instead of
      // scheduling a task.
      stockTaskService.onRunTask(new TaskParams(intent.getStringExtra("tag"), args));
    }

  }
}
