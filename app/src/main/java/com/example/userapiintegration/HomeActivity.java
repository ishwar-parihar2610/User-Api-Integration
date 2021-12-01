package com.example.userapiintegration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.Toast;

import com.example.userapiintegration.Api.ApiClient;
import com.example.userapiintegration.Api.ApiInterface;
import com.example.userapiintegration.Response.UserDetailResponse;
import com.example.userapiintegration.adapter.UserDetailsAdapter;
import com.example.userapiintegration.databinding.ActivityHomeBinding;
import com.example.userapiintegration.model.userDetail;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {
    ActivityHomeBinding binding;
    Retrofit retrofit= ApiClient.getRETROFIT();
    ApiInterface apiInterface=retrofit.create(ApiInterface.class);
    ArrayList<userDetail> userDetailArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_home);

        userDetailArrayList=new ArrayList<>();
        apiInterface.fetchAllUser().enqueue(new Callback<UserDetailResponse>() {
            @Override
            public void onResponse(Call<UserDetailResponse> call, Response<UserDetailResponse> response) {
                if (response.body()!=null){
                    showToast(response.body().getMessage());
                    if (response.body().getStatus().equals("1")){
                        userDetailArrayList=response.body().data();
                    }
                    UserDetailsAdapter adapter=new UserDetailsAdapter(userDetailArrayList);
                    binding.userRecycleView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<UserDetailResponse> call, Throwable t) {
            showToast(t.getLocalizedMessage());
            }
        });
    }

    private void showToast(String toast){
        Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
    }
}