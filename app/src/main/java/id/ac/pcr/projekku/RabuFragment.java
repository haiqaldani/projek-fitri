package id.ac.pcr.projekku;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import id.ac.pcr.projekku.adapter.ScheduleAdapter;
import id.ac.pcr.projekku.api.APIRequestData;
import id.ac.pcr.projekku.api.RetroServer;
import id.ac.pcr.projekku.databinding.FragmentRabuBinding;
import id.ac.pcr.projekku.model.ScheduleModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RabuFragment extends Fragment {

    private FragmentRabuBinding binding;
    private ScheduleAdapter scheduleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRabuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupList();
        getScheduleData();

        binding.fabRefresh.setOnClickListener( v-> {
            getScheduleData();
        });
    }

    private void setupList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.rvRabu.setLayoutManager(linearLayoutManager);
        scheduleAdapter = new ScheduleAdapter(getContext(), new ArrayList());
        binding.rvRabu.setAdapter(scheduleAdapter);
    }

    private void getScheduleData() {
        binding.loading.setVisibility(View.VISIBLE);

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ScheduleModel> call = apiRequestData.getSchedule("RABU");
        call.enqueue(new Callback<ScheduleModel>() {
            @Override
            public void onResponse(@NotNull Call<ScheduleModel> call, @NotNull Response<ScheduleModel> response) {
                if (response.body() != null) {
                    binding.loading.setVisibility(View.GONE);
                    List<ScheduleModel.College> collegeList = response.body().getJadwalkuliah();
                    scheduleAdapter.setData(collegeList);
                }
            }

            @Override
            public void onFailure(@NotNull Call<ScheduleModel> call, @NotNull Throwable t) {
                binding.loading.setVisibility(View.GONE);
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
