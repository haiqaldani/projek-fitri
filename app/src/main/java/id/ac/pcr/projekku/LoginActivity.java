package id.ac.pcr.projekku;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import id.ac.pcr.projekku.api.APIRequestData;
import id.ac.pcr.projekku.api.RetroServer;
import id.ac.pcr.projekku.databinding.ActivityLoginBinding;
import id.ac.pcr.projekku.model.UserModel;
import id.ac.pcr.projekku.preference.SharedPrefManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private ProgressDialog loading;
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sharedPrefManager = new SharedPrefManager(this);

        binding.login.setOnClickListener( v -> {
            if (!binding.username.getText().toString().isEmpty() && !binding.password.getText().toString().isEmpty()) {
                sendLoginUser(binding.username.getText().toString(), binding.password.getText().toString());
            } else {
                Toast.makeText(this, "Please fill the data correctly", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void sendLoginUser(String username, String password) {
        loading = ProgressDialog.show(this, null, "Harap Tunggu...", true, false);

        APIRequestData apiRequestData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<UserModel> call = apiRequestData.loginUser(username, password);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(@NotNull Call<UserModel> call, @NotNull Response<UserModel> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("Success")) {
                        Toast.makeText(LoginActivity.this, "Berhasil login!", Toast.LENGTH_SHORT).show();
                        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_ID_PENGGUNA, response.body().getId_pengguna());
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_LEVEL, response.body().getLevel());
                        sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, response.body().getEmail());
                        startActivity(new Intent(LoginActivity.this, dashboard.class));
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Gagal login. Harap periksa kembali data Anda.", Toast.LENGTH_SHORT).show();
                    }
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(@NotNull Call<UserModel> call, @NotNull Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }
}