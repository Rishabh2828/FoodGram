package com.shurish.foodgram;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.shurish.foodgram.Adapters.CartAdapter;
import com.shurish.foodgram.Models.AddToCartModel;
import com.shurish.foodgram.Models.OrderHistoryModel;

public class OrderHistoryAdapter extends FirestoreRecyclerAdapter<OrderHistoryModel, OrderHistoryAdapter.viewholder > {

    Context context;

    public OrderHistoryAdapter(@NonNull FirestoreRecyclerOptions<OrderHistoryModel> options, Context context) {
        super(options);
        this.context = context;
    }

    public OrderHistoryAdapter(@NonNull FirestoreRecyclerOptions<OrderHistoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull OrderHistoryModel model) {

        String docId = getSnapshots().getSnapshot(position).getId();
        holder.text1.setText(model.getText1());
        holder.text2.setText(model.getText2());
        holder.text3.setText(model.getText3());



        Glide.with(context)
                .load(model.getImg())
                .into(holder.imageView);

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext()).inflate(R.layout.orderhistorysamplelayout,parent, false));
        return new viewholder(view);
    }


    public class viewholder extends RecyclerView.ViewHolder {


        ImageView imageView;
        TextView text1;
        TextView text2;
        TextView text3;

        public viewholder(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.meal_img1);
            text1 = itemView.findViewById(R.id.meal_text11);
            text2 = itemView.findViewById(R.id.meal_text21);
            text3 = itemView.findViewById(R.id.cart_price1);
        }
    }

    }
