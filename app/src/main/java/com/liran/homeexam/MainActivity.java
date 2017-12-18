package com.liran.homeexam;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.appsflyer.*;
import com.appsflyer.AppsFlyerConversionListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String AF_DEV_KEY = "uddHHjCdAU3BnxDJpLyUb7";
    private List<Conversion> conversionList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ConversionAdapter pAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        pAdapter = new ConversionAdapter(conversionList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(pAdapter);

        {
        AppsFlyerConversionListener conversionDataListener =
                new AppsFlyerConversionListener() {
                    @Override
                    public void onInstallConversionDataLoaded(Map<String, String> conversionData) {
                        for (String attrName : conversionData.keySet()) {
                            Log.d("ConversionData", "attribute: " + attrName + " = " + conversionData.get(attrName));
                            Conversion conversion = new Conversion(attrName, conversionData.get(attrName));
                            conversionList.add(conversion);
                        }
                    }

                    @Override
                    public void onInstallConversionFailure(String errorMessage) {
                        Log.i("lirantest", "error getting conversion data: " + errorMessage);
                    }

                    @Override
                    public void onAppOpenAttribution(Map<String, String> map) {

                        Log.i("lirantest", "onAppOpen");
                    }

                    @Override
                    public void onAttributionFailure(String errorMessage) {
                        Log.i("lirantest", "error onAttributionFailure : " + errorMessage);
                    }
                };

        AppsFlyerLib.getInstance().setDebugLog(true);
        AppsFlyerLib.getInstance().setLogLevel(AFLogger.LogLevel.VERBOSE);
        AppsFlyerLib.getInstance().init(AF_DEV_KEY, conversionDataListener, getApplicationContext());
        AppsFlyerLib.getInstance().startTracking(this.getApplication());
    }

    FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
    {
        @Override
        public void onClick (View view){
            Map<String, Object> eventValue = new HashMap<>();
            eventValue.put(AFInAppEventParameterName.REVENUE, 200);
            eventValue.put(AFInAppEventParameterName.CONTENT_TYPE, "t-shirt");
            eventValue.put(AFInAppEventParameterName.CONTENT_ID, "1234567");
            eventValue.put(AFInAppEventParameterName.CURRENCY, "USD");
            AppsFlyerLib.getInstance().trackEvent(getApplicationContext(), AFInAppEventType.PURCHASE, eventValue);
            pAdapter.notifyDataSetChanged();
            Snackbar.make(view, "Purchased", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
});
        }
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


