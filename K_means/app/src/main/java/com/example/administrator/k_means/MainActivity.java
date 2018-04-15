package com.example.administrator.k_means;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private KmeansView mKmeansView;
    private Kcluster mKmean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_10points:
                mKmeansView.makeMuchPoint(10);
                return true;
            case R.id.action_50points:
                mKmeansView.makeMuchPoint(50);
                return true;
            case R.id.action_200points:
                mKmeansView.makeMuchPoint(200);
                return true;
            case R.id.action_500points:
                mKmeansView.makeMuchPoint(500);
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mKmeansView = findViewById(R.id.kmeansView);
    }
}
