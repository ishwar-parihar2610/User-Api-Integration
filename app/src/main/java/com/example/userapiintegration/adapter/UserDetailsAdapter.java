package com.example.userapiintegration.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userapiintegration.R;
import com.example.userapiintegration.Response.UserDetailResponse;
import com.example.userapiintegration.databinding.UserRecycleViewBinding;
import com.example.userapiintegration.model.userDetail;

import java.util.ArrayList;

public class UserDetailsAdapter extends RecyclerView.Adapter<UserDetailsAdapter.viewHolder> {
    ArrayList<userDetail> userDetailArrayList;
    LayoutInflater inflater;

    public UserDetailsAdapter(ArrayList<userDetail> userDetailArrayList) {
        this.userDetailArrayList = userDetailArrayList;
    }

    @NonNull
    @Override
    public UserDetailsAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater==null){
            inflater=LayoutInflater.from(parent.getContext());
        }
        return new viewHolder(DataBindingUtil.inflate(inflater,R.layout.user_recycle_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserDetailsAdapter.viewHolder holder, int position) {
    userDetail userDetail= userDetailArrayList.get(position);
    holder.binding.userName.setText("Name : "+userDetail.getUsername());
    holder.binding.userEmail.setText("Email :"+userDetail.getEmail());
    holder.binding.userId.setText("Id :"+userDetail.getId());
    }

    @Override
    public int getItemCount() {
        return userDetailArrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        UserRecycleViewBinding binding;
        public viewHolder(UserRecycleViewBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
