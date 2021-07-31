package id.ac.pcr.projekku;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import id.ac.pcr.projekku.adapter.DosenAdapter;
import id.ac.pcr.projekku.api.APIRequestData;
import id.ac.pcr.projekku.api.RetroServer;
import id.ac.pcr.projekku.databinding.ActivityJadwalDosenBinding;
import id.ac.pcr.projekku.model.IdModel;
import id.ac.pcr.projekku.model.ScheduleModel;
import id.ac.pcr.projekku.preference.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalDosen extends AppCompatActivity {

    private ActivityJadwalDosenBinding binding;
    private DosenAdapter adapter;
    private SharedPrefManager sharedPrefManager;
    private ArrayList<String> idList;
    private String idJadwal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJadwalDosenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefManager = new SharedPrefManager(JadwalDosen.this);
        idList = new ArrayList<>();

        String dosenId = sharedPrefManager.getSPId();

        setupList();
        getScheduleData(dosenId);

        binding.spinnerId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idJadwal = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        binding.btnChange.setOnClickListener( v-> {
            getIdList(idJadwal);
        });
    }

    private void getIdList(String idJadwal) {
        binding.loading.setVisibility(View.VISIBLE);

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<IdModel> call = apiRequestData.getListID(idJadwal);
        call.enqueue(new Callback<IdModel>() {
            @Override
            public void onResponse(@NotNull Call<IdModel> call, @NotNull Response<IdModel> response) {
                if (response.body() != null) {
                    Intent scheduleIntent = new Intent(JadwalDosen.this, Tambah_Ganti.class);
                    List<IdModel.IdList> idLists = response.body().getListid();
                    for (IdModel.IdList idList : idLists) {
                        scheduleIntent.putExtra("id_kelas", idList.getId_kelas());
                        scheduleIntent.putExtra("id_matkul", idList.getId_matkul());
                        scheduleIntent.putExtra("id_ruangan", idList.getId_ruangan());
                        scheduleIntent.putExtra("id_jadwal", idJadwal);
                    }

                    startActivity(scheduleIntent);
                }
                binding.loading.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(@NotNull Call<IdModel> call, @NotNull Throwable t) {
                binding.loading.setVisibility(View.GONE);
                Toast.makeText(JadwalDosen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displaySpinner(ArrayList<String> listId) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, listId);
        binding.spinnerId.setAdapter(arrayAdapter);
    }

    private void setupList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvDosen.setLayoutManager(linearLayoutManager);
        adapter = new DosenAdapter(this, new ArrayList());
        binding.rvDosen.setAdapter(adapter);
    }

    private void getScheduleData(String idDosen) {
        binding.loading.setVisibility(View.VISIBLE);

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ScheduleModel> call = apiRequestData.getDosenSchedule(idDosen);
        call.enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(@NotNull Call<ScheduleModel> call, @NotNull Response<ScheduleModel> response) {
                if (response.body() != null) {
                    binding.loading.setVisibility(View.GONE);
                    List<ScheduleModel.College> collegeList = response.body().getJadwalkuliah();
                    for (ScheduleModel.College college : collegeList) {
                        idList.add(college.getId());
                    }
                    adapter.setData(collegeList);
                    displaySpinner(idList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ScheduleModel> call, @NotNull Throwable t) {
                binding.loading.setVisibility(View.GONE);
                Toast.makeText(JadwalDosen.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}