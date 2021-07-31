package id.ac.pcr.projekku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import id.ac.pcr.projekku.preference.SharedPrefManager;

public class dashboard extends AppCompatActivity {

    private CardView berita, kuliah, ganti, kalender;
    private ImageButton ibLogout;
    private SharedPrefManager sharedPrefManager;
    private TextView level,email;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        int waktu_loading = 1000;

        sharedPrefManager = new SharedPrefManager(this);

        ganti = (CardView) findViewById(R.id.ganti);

        initView();

        ibLogout = (ImageButton) findViewById(R.id.ibLogout);
        ibLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(dashboard.this, null, "Harap Tunggu...", true, false);
                sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
                sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, "");
                sharedPrefManager.saveSPString(SharedPrefManager.SP_ID_PENGGUNA, "");
                sharedPrefManager.saveSPString(SharedPrefManager.SP_LEVEL, "");
                new Handler().postDelayed(() -> {
                    startActivity(new Intent(dashboard.this, LoginActivity.class));
                    loading.dismiss();
                    finish();
                }, waktu_loading);
            }
        });

        level = (TextView) findViewById(R.id.txtuser);
        email = (TextView) findViewById(R.id.txtemail);

        level.setText(sharedPrefManager.getSPLevel());
        email.setText(sharedPrefManager.getSPEmail());

        berita = (CardView) findViewById(R.id.berita);
        berita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vBerita();
            }
        });

        kuliah = findViewById(R.id.kuliah);
        kuliah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vKuliah();
            }
        });

        ganti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vGanti();
            }
        });

        kalender = findViewById(R.id.kalender);
        kalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vKalender();
            }
        });

    }

    private void initView() {
        if (sharedPrefManager.getSPLevel().equals("Mahasiswa")) {
            ganti.setVisibility(View.GONE);
        } else {
            ganti.setVisibility(View.VISIBLE);
        }
    }

    public void vBerita(){
        Intent intent = new Intent(this, Berita.class);
        startActivity(intent);
    }

    public void vKuliah(){
        Intent intent = new Intent(this, Kuliah.class);
        startActivity(intent);
    }

    public void vGanti(){
        Intent intent = new Intent(this, Ganti.class);
        startActivity(intent);
    }

    public void vKalender(){
        Intent intent = new Intent(this, Kalender.class);
        startActivity(intent);
    }
}
