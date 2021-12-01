package com.example.userapiintegration.Response;

import com.example.userapiintegration.model.userDetail;

import java.util.ArrayList;

public class UserDetailResponse {
    String status;
    String message;
    ArrayList<userDetail> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<userDetail> data() {
        return data;
    }

    public void setUserDetailArrayList(ArrayList<userDetail> data) {
        this.data = data;
    }

    public UserDetailResponse(String status, String message, ArrayList<userDetail> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public class viewHolder {
    }
}
