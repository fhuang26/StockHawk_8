# Stock Hawk

Today (9/3), I installed Java JDK 8 and Android studio 2.1, and update Gradle to 2.14.1 for Stock Hawk project. It can compile (build) and run on my LG 4G phone (Android API 21) as follows. If it cannot build and run on your side, can you please let me know it fails to build or run, and error message? Thank you! I also load the repo StockHawk_8 back from github, and it can build and run.

(1) When a user clicks on a stock quote in main screen, recyclerView listener catches the event
     and sends a service in StockIntentService to get stock prices over a time period from
     Yahoo finance on web. When prices over time are available, the service sends a Parcelable
     intent to a receiver in MyStocksActivity. Then the receiver sends an intent to launch
     LineChartActivity to draw Stock Price and Volume charts for this stock in Aug 2016.
     From line chart screen to go back to main screen, a user can click system back button.

(2) When a user searches for a new stock quote by floating (+) button, it does not crash for
     non-existent stock. The previous bug is fixed.
     
(3) ui/CollectionWidget provides a collecction widget. When a user clicks system home from
     the main screen of Stock Hawk, the collection widget is in home, widget.
     From the widget in home screen, a user can click "Stock Hawk" in widget to bring up
     MyStocksActivity, main screen of Stock Hawk app.

(4) android:contentDescription attribute is added to floating (+) button to add a stock and
     $ button in top action bar to be able to toggle stock change between $ and %.

(5) This app supports 3 locales : English (default), Chinese, and Arabic.
     For Arabic Egypt release, this app supports layout mirroring using both RTL attribute and
     start/end tags.

(6) When internet is not available, it shows "This app requires an internet connection", or
     the corresponding message in Chinese or Arabic locale.
     
(7) This app maintains all strings in the following 3 strings.xml under res/values.
     strings.xml : English (default)
     strings.xml (zh) : Chinese
     strings.xml (ar) : Arabic

(8) For 3 locales, English release is default.

     For Chinese release, we can do the following in onCreate() in MyStocksActivity.
       // Locale locale = new Locale("en"); // English release
       Locale locale = new Locale("zh"); // Chinese release
       // Locale locale = new Locale("ar"); // Arabic Egypt release
       
     For Arabic Egypt release, we can do the following in onCreate() in MyStocksActivity.
       // Locale locale = new Locale("en"); // English release
       // Locale locale = new Locale("zh"); // Chinese release
       Locale locale = new Locale("ar"); // Arabic Egypt release

(9) The following testing is done.
     * Swipe from left to right MSFT and then MSFT is deleted.
     * Click GOOG and it shows line chart for stock prices and volume of Google in Aug 2016.
        In line chart screen, we can press system back to go back to main screen.
     * Press (+) and enter COST. Then it gets Costco stock quote over the web and adds it.
     * Press (+) and enter FB. Then it gets Facebook stock quote over the web and adds it.
     * Press (+) and enter GOOG and it shows "This stock is already saved".
     * Click $ button in action bar and it shows stock change in $.
       Click $ button in action bar again and stock change toggles back to %.
     * Press (+) and enter xxx (not a valid stock symbol) and it does not crash.
     * Click system home and collection widget is in home, widget.
        Then click "Stock Hawk" in the widget and it brings up main screen of Stock Hawk app.
     * Click COST and it shows line chart for stock prices and volume of Costco in Aug 2016.
        In line chart screen, we can press system back to go back to main screen.
     * Click GOOG and it shows line chart for stock prices and volume of Google in Aug 2016.
        In line chart screen, we can press system back to go back to main screen.
     * Click system home and collection widget is in home screen.
        Then click "Stock Hawk" in the widget and it brings up main screen of Stock Hawk app.