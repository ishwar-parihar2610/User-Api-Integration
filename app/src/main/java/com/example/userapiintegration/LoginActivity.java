package com.example.userapiintegration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.userapiintegration.Api.ApiClient;
import com.example.userapiintegration.Api.ApiInterface;
import com.example.userapiintegration.Response.loginResponse;
import com.example.userapiintegration.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    String Tag="LoginActivity";
    Retrofit retrofit= ApiClient.getRETROFIT();
    ApiInterface apiInterface=retrofit.create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.loginTextView.setOnClickListener(v -> {startActivity(new Intent(LoginActivity.this,MainActivity.class));});
        binding.loginButton.setOnClickListener(v -> {
            loginUser(binding.emailEdit.getText().toString(),binding.passwordEdit.getText().toString());
        });
        binding.forgotTextView.setOnClickListener(v ->{
            startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
        });


    }
    private void loginUser(String email,String password){
        apiInterface.loginUser(email,password).enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                if (response.body()!=null){
                    showToast(response.body().getMessage());
                    if (response.body().getStatus().equals("1")){
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
            showToast(t.getLocalizedMessage());
            }
        });
    }


    public void showToast(String Text){
        Toast.makeText(this, Text, Toast.LENGTH_SHORT).show();
    }


}