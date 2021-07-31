package id.ac.pcr.projekku;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import id.ac.pcr.projekku.api.APIRequestData;
import id.ac.pcr.projekku.api.RetroServer;
import id.ac.pcr.projekku.databinding.ActivityTambahGantiBinding;
import id.ac.pcr.projekku.model.IdModel;
import id.ac.pcr.projekku.model.RuanganModel;
import id.ac.pcr.projekku.preference.SharedPrefManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Tambah_Ganti extends AppCompatActivity {

    private ActivityTambahGantiBinding binding;
    private ArrayList<String> roomList;
    private String emptyRoom;
    private Calendar date, date1;
    private ProgressDialog progressDialog;
    private String dateChanged, hoursChanged;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahGantiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefManager = new SharedPrefManager(Tambah_Ganti.this);
        roomList = new ArrayList<>();

        Intent intent = getIntent();
        String id_kelas = intent.getStringExtra("id_kelas");
        String id_matkul = intent.getStringExtra("id_matkul");
        String id_ruangan = intent.getStringExtra("id_ruangan");
        String id_jadwal = intent.getStringExtra("id_jadwal");

        Log.d("TambahGanti", id_jadwal+""+id_kelas+""+id_matkul+""+id_ruangan);

        String id_dosen = sharedPrefManager.getSPId();

        showEmptyRoom();

        binding.spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                emptyRoom = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        binding.btnSend.setOnClickListener( v-> {
            setProgressLoading();
            sendRequestSchedule(emptyRoom, dateChanged, hoursChanged, id_dosen, id_kelas, id_matkul, id_ruangan, id_jadwal);
        });
    }

    private void setProgressLoading() {
        progressDialog = new ProgressDialog(Tambah_Ganti.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setTitle("ProgressDialog");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
    }

    private void showEmptyRoom() {
        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<RuanganModel> call = apiRequestData.getEmptyRoom();
        call.enqueue(new Callback<RuanganModel>() {
            @Override
            public void onResponse(@NotNull Call<RuanganModel> call, @NotNull Response<RuanganModel> response) {
                if (response.body() != null) {
                    List<RuanganModel.Data> modelList = response.body().getData();
                    for (RuanganModel.Data data : modelList) {
                        roomList.add(data.getNama_ruangan());
                    }
                    displaySpinner(roomList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<RuanganModel> call, @NotNull Throwable t) {
                Toast.makeText(Tambah_Ganti.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void displaySpinner(ArrayList<String> roomList) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, roomList);
        binding.spinnerRoom.setAdapter(arrayAdapter);
    }

    public void showDate(View view) {
        final Calendar currentDate = Calendar.getInstance();
        date = Calendar.getInstance();
        new DatePickerDialog(this, (view1, year, month, dayOfMonth) -> {
            date.set(year, month, dayOfMonth);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            dateChanged = sdf.format(date.getTime());

            binding.etDate.setText(dateChanged);
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    public void showHour(View view) {
        final Calendar currentDate1 = Calendar.getInstance();
        date1 = Calendar.getInstance();
        new TimePickerDialog(this, (view1, hourOfDay, minute) -> {
            date1.set(Calendar.HOUR_OF_DAY, hourOfDay);
            date1.set(Calendar.MINUTE, minute);

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            hoursChanged = sdf.format(date1.getTime());

            binding.etTime.setText(hoursChanged);
        }, currentDate1.get(Calendar.HOUR_OF_DAY), currentDate1.get(Calendar.MINUTE), false).show();
    }

    private void sendRequestSchedule(String emptyRoom, String dateChanged, String hoursChanged, String id_dosen, String id_kelas, String id_matkul, String id_ruangan, String id_jadwal) {

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseBody> call = apiRequestData.changeSchedule(id_dosen, id_jadwal, id_kelas, id_matkul, id_ruangan, dateChanged, hoursChanged, emptyRoom);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NotNull Call<ResponseBody> call, @NotNull Response<ResponseBody> response) {
                if (response.body() != null) {
                    Toast.makeText(Tambah_Ganti.this, "Permintaan berhasil dikirim!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Tambah_Ganti.this, dashboard.class));
                    finish();
                } else {
                    Toast.makeText(Tambah_Ganti.this, "Gagal mengirim data.", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(@NotNull Call<ResponseBody> call, @NotNull Throwable t) {
                Toast.makeText(Tambah_Ganti.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
