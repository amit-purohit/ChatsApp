package com.amit.chatsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amit.chatsapp.Models.Message;
import com.amit.chatsapp.R;
import com.amit.chatsapp.databinding.ItemReceivedBinding;
import com.amit.chatsapp.databinding.ItemSentBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Message> messages;
    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;

    public MessagesAdapter(Context context, ArrayList<Message> messages)
    {
        this.context = context;
        this.messages = messages;

    }


    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {

        if(viewType == ITEM_SENT)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.item_sent, parent, false);
            return new SentViewHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_received, parent, false);
            return new ReceiverViewHolder(view);
        }

//        return null;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);
        if(FirebaseAuth.getInstance().getUid().equals(message.getSenderId()))
            return ITEM_SENT;
        else
            return ITEM_RECEIVE;
//        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if(holder.getClass() == SentViewHolder.class){
            SentViewHolder viewHolder = (SentViewHolder)holder;
            viewHolder.binding.message.setText(message.getMessage());
        }else
        {
            ReceiverViewHolder viewHolder = (ReceiverViewHolder)holder;
            viewHolder.binding.message.setText(message.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    //Sent ViewHolder
    public class SentViewHolder extends RecyclerView.ViewHolder{
        ItemSentBinding binding;
        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSentBinding.bind(itemView);
        }
    }

    //Receiver View Holder
    public class ReceiverViewHolder extends RecyclerView.ViewHolder{
        ItemReceivedBinding binding;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemReceivedBinding.bind(itemView);
        }
    }



}
