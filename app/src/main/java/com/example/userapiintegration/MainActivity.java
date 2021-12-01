package com.example.userapiintegration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.userapiintegration.Api.ApiClient;
import com.example.userapiintegration.Api.ApiInterface;
import com.example.userapiintegration.Response.RegisterResponse;
import com.example.userapiintegration.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    String TAG="MainActivity.this";
    Retrofit retrofit= ApiClient.getRETROFIT();
    ApiInterface apiInterface=retrofit.create(ApiInterface.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.loginTextView.setOnClickListener(v -> {startActivity(new Intent(MainActivity.this,LoginActivity.class));});
        binding.buttonSignUp.setOnClickListener(v -> {registerUser(binding.usernameEdit.getText().toString(),binding.emailEdit.getText().toString(),binding.passwordEdit.getText().toString());});
    }

    public void showToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private void registerUser(String userName,String email,String password){
        apiInterface.registerUser(userName,email,password).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.body()!=null){
                    showToast(response.body().getMessage());
                    if(response.body().getStatus().equals("1")){
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
            showToast(t.getLocalizedMessage());
            }
        });
    }

}