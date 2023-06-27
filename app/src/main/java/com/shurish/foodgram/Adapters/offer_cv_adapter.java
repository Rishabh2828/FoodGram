package com.shurish.foodgram.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shurish.foodgram.Models.offer_cv_model;
import com.shurish.foodgram.ProductDescriptionActivity;
import com.shurish.foodgram.R;

import java.util.ArrayList;

public class offer_cv_adapter extends RecyclerView.Adapter<offer_cv_adapter.viewholder>{

    Context context;
    ArrayList<offer_cv_model> list;

    public offer_cv_adapter(Context context, ArrayList<offer_cv_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.offer_cardview_sample, parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
           offer_cv_model model = list.get(position);

           holder.textView1.setText(model.getText1());
           holder.textView2.setText(model.getText2());
           holder.button.setText(model.getTextbtn());

        Glide.with(context)
                .load(model.getImg())
                .into(holder.imageView);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDescriptionActivity.class);
                intent.putExtra("image_resource_id", model.getImg());
                intent.putExtra("text1", model.getProductname());
                intent.putExtra("text2", model.getCouponapplied());
                intent.putExtra("text3", model.getPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView1;
        TextView textView2;
        Button button;


        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.burger);
            textView1 = itemView.findViewById(R.id.freedelivery_tv);
            textView2 = itemView.findViewById(R.id.seize_tv);
            button = itemView.findViewById(R.id.ordernow_btn);
        }
    }

}
