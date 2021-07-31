package id.ac.pcr.projekku;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import id.ac.pcr.projekku.adapter.DosenAdapter;
import id.ac.pcr.projekku.adapter.SScheduleAdapter;
import id.ac.pcr.projekku.api.APIRequestData;
import id.ac.pcr.projekku.api.RetroServer;
import id.ac.pcr.projekku.databinding.ActivityLihatGantiBinding;
import id.ac.pcr.projekku.model.ScheduleModel;
import id.ac.pcr.projekku.preference.SharedPrefManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Lihat_Ganti extends AppCompatActivity {

    private ActivityLihatGantiBinding binding;
    private SharedPrefManager sharedPrefManager;
    private SScheduleAdapter sScheduleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLihatGantiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefManager = new SharedPrefManager(Lihat_Ganti.this);
        String dosenId = sharedPrefManager.getSPId();

        setupList();
        getNewSchedule(dosenId);
    }

    private void setupList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.rvJadwalGanti.setLayoutManager(linearLayoutManager);
        sScheduleAdapter = new SScheduleAdapter(this, new ArrayList());
        binding.rvJadwalGanti.setAdapter(sScheduleAdapter);
    }

    private void getNewSchedule(String dosenId) {
        binding.loading.setVisibility(View.VISIBLE);

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ScheduleModel> call = apiRequestData.getAccSchedule(dosenId);
        call.enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(@NotNull Call<ScheduleModel> call, @NotNull Response<ScheduleModel> response) {
                if (response.body() != null) {
                    binding.loading.setVisibility(View.GONE);
                    List<ScheduleModel.College> collegeList = response.body().getJadwalkuliah();
                    sScheduleAdapter.setData(collegeList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ScheduleModel> call, @NotNull Throwable t) {
                binding.loading.setVisibility(View.GONE);
                Toast.makeText(Lihat_Ganti.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
