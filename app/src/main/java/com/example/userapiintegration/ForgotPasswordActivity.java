package com.example.userapiintegration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.example.userapiintegration.Api.ApiClient;
import com.example.userapiintegration.Api.ApiInterface;
import com.example.userapiintegration.Response.updatePasswordResponse;
import com.example.userapiintegration.databinding.ActivityForgotPasswordBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForgotPasswordActivity extends AppCompatActivity {
    ActivityForgotPasswordBinding binding;
    Retrofit retrofit= ApiClient.getRETROFIT();
    ApiInterface apiInterface=retrofit.create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_forgot_password);
        binding.buttonUpdate.setOnClickListener(v->{
            updatePassword(binding.emailEdit.getText().toString(),binding.passwordEdit.getText().toString(),binding.newPasswordEdit.getText().toString());
        });
    }

    private void updatePassword(String email,String password,String newPassword){
        apiInterface.updatePassword(email,password,newPassword).enqueue(new Callback<updatePasswordResponse>() {
            @Override
            public void onResponse(Call<updatePasswordResponse> call, Response<updatePasswordResponse> response) {
                if (response.body()!=null){
                    showToast(response.body().getMessage());

                }
            }

            @Override
            public void onFailure(Call<updatePasswordResponse> call, Throwable t) {
            showToast(t.getLocalizedMessage());
            }
        });
    }
    private void showToast(String Text){
        Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
    }
}