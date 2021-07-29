package id.ac.pcr.projekku;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import id.ac.pcr.projekku.adapter.AdapterBerita;
import id.ac.pcr.projekku.api.APIRequestData;
import id.ac.pcr.projekku.api.RetroServer;
import id.ac.pcr.projekku.model.BeritaModel;
import id.ac.pcr.projekku.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Berita extends AppCompatActivity {

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<BeritaModel> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita);

        rvData = findViewById(R.id.rv_berita);

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        retrieveBerita();
    }

    public void retrieveBerita(){
        APIRequestData ardBerita = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilBerita = ardBerita.ardRetrieveBerita();

        tampilBerita.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(@NotNull Call<ResponseModel> call, @NotNull Response<ResponseModel> response) {
                if (response.body()!= null) {
                    int kode = response.body().getKode();
                    String pesan = response.body().getPesan();

                    Toast.makeText(Berita.this, "Kode : " + kode + " | Pesan : " + pesan, Toast.LENGTH_SHORT).show();

                    listData = response.body().getData();

                    adData = new AdapterBerita(Berita.this, listData);
                    rvData.setAdapter(adData);
                    adData.notifyDataSetChanged();
                } else {
                    Toast.makeText(Berita.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NotNull Call<ResponseModel> call, @NotNull Throwable t) {
                Toast.makeText(Berita.this, "Gagal Menghubungi Server : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private ArrayList<Model> getMyList(){
//
//        ArrayList<Model> models = new ArrayList<>();
//
//        Model m = new Model();
//        m.setNama("Baak");
//        m.setSubjek("Jadwal Wisuda");
//        m.setIsi("Berikut ini terlampir ....");
//        m.setFoto(R.drawable.user);
//        models.add(m);
//
//        m = new Model();
//        m.setNama("BAAK");
//        m.setSubjek("Jadwal Mata Kuliah");
//        m.setIsi("Berikut ini terlampir ....");
//        m.setFoto(R.drawable.user);
//        models.add(m);
//
//        return models;
//    }
}