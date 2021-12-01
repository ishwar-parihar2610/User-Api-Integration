package com.example.userapiintegration.Api;

import com.example.userapiintegration.Response.RegisterResponse;
import com.example.userapiintegration.Response.UserDetailResponse;
import com.example.userapiintegration.Response.loginResponse;
import com.example.userapiintegration.Response.updatePasswordResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterResponse> registerUser(@Field("username") String userName,@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<loginResponse> loginUser(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("updatePassword.php")
    Call<updatePasswordResponse> updatePassword(@Field("email") String email,@Field("password") String password,@Field("newpassword") String newPassword);

    @GET("fetchall.php")
    Call<UserDetailResponse> fetchAllUser();



}
