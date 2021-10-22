package com.amit.chatsapp;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amit.chatsapp.databinding.RowConversationBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder>{

    Context context;
    ArrayList<User> users;


    public UsersAdapter(Context context, ArrayList<User> users) {

        this.context = context;
        this.users = users;
    }


    @NonNull

    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_conversation, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  UsersAdapter.UsersViewHolder holder, int position) {
        User user = users.get(position);

        holder.binding.username.setText(user.getName());
        Glide.with(context).load(user.getProfileImage()).into(holder.binding.profile);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{

        RowConversationBinding binding;

        public UsersViewHolder(View itemView) {
            super(itemView);
            binding = RowConversationBinding.bind(itemView);
        }


    }




}
