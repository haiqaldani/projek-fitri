package id.ac.pcr.projekku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import id.ac.pcr.projekku.preference.SharedPrefManager;

public class Ganti extends AppCompatActivity {

    private CardView tambah;
    private CardView lihat;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti);

        sharedPrefManager = new SharedPrefManager(this);

        tambah = (CardView) findViewById(R.id.tambah);

        initView();

        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vTambah();

            }
        });

        lihat = findViewById(R.id.lihat);
        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vLihat();
            }
        });
    }

    public void vTambah(){
        Intent intent = new Intent(this, JadwalDosen.class);
        startActivity(intent);
    }

    public void vLihat(){
        Intent intent = new Intent(this, Lihat_Ganti.class);
        startActivity(intent);
    }

    private void initView() {
        if (sharedPrefManager.getSPLevel().equals("Mahasiswa")) {
            tambah.setVisibility(View.GONE);
        } else {
            tambah.setVisibility(View.VISIBLE);
        }
    }
}
