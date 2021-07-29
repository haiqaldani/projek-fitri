package id.ac.pcr.projekku;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class dashboard extends AppCompatActivity {

    private CardView berita;
    private CardView kuliah;
    private CardView ganti;
    private CardView kalender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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

        ganti = (CardView) findViewById(R.id.ganti);
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
