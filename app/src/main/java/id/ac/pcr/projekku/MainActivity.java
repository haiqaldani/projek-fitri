package id.ac.pcr.projekku;


import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.ac.pcr.projekku.preference.SharedPrefManager;


public class MainActivity extends AppCompatActivity {

    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPrefManager = new SharedPrefManager(MainActivity.this);

        setContentView(R.layout.activity_main);
        int waktu_loading = 2500;
        new Handler().postDelayed(() -> {
            if (sharedPrefManager.getSPSudahLogin()) {
                startActivity(new Intent(MainActivity.this, dashboard.class));
            } else {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
            finish();
        }, waktu_loading);
    }
}
