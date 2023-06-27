package com.shurish.foodgram.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.shurish.foodgram.Models.AddToCartModel;
import com.shurish.foodgram.Models.meal_model;
import com.shurish.foodgram.ProductDescriptionActivity;
import com.shurish.foodgram.R;
import com.shurish.foodgram.Utility;

import java.util.ArrayList;

public class meal_adapter extends RecyclerView.Adapter<meal_adapter.viewholder>{

    Context context;
    ArrayList<meal_model> list;

    public meal_adapter(Context context, ArrayList<meal_model> list) {
        this.context = context;
        this.list = list;
    }



    public void filterList(ArrayList<meal_model> filterlist) {
        // below line is to add our filtered
        // list in our course array list.
        list = filterlist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meals_sample_layout, parent, false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        meal_model model = list.get(position);

        holder.text1.setText(model.getText1());
        holder.text2.setText(model.getText2());
        holder.mealPrice.setText(model.getText3());


        Glide.with(context)
                .load(model.getImg())
                .into(holder.imageView);


        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDescriptionActivity.class);
                intent.putExtra("image_resource_id", model.getImg());
                intent.putExtra("text1", model.getText1());
                intent.putExtra("text2", model.getText2());
                intent.putExtra("text3", model.getText3());
                context.startActivity(intent);
            }
        });

        holder.text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDescriptionActivity.class);
                intent.putExtra("image_resource_id", model.getImg());
                intent.putExtra("text1", model.getText1());
                intent.putExtra("text2", model.getText2());
                intent.putExtra("text3", model.getText3());
                context.startActivity(intent);
            }
        });

        holder.text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDescriptionActivity.class);
                intent.putExtra("image_resource_id", model.getImg());
                intent.putExtra("text1", model.getText1());
                intent.putExtra("text2", model.getText2());
                intent.putExtra("text3", model.getText3());
                context.startActivity(intent);
            }
        });

        holder.mealPriceSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDescriptionActivity.class);
                intent.putExtra("image_resource_id", model.getImg());
                intent.putExtra("text1", model.getText1());
                intent.putExtra("text2", model.getText2());
                intent.putExtra("text3", model.getText3());
                context.startActivity(intent);
            }
        });

        holder.mealPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductDescriptionActivity.class);
                intent.putExtra("image_resource_id", model.getImg());
                intent.putExtra("text1", model.getText1());
                intent.putExtra("text2", model.getText2());
                intent.putExtra("text3", model.getText3());
                context.startActivity(intent);
            }
        });

        holder.addtocartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  String text1 = model.getText1();
                  String text2 = model.getText2();
                  String text3 = model.getText3();
                  String img = model.getImg();
                  int quantity = 1;




                  AddToCartModel order = new AddToCartModel();
                  order.setImg(img);
                  order.setText1(text1);
                  order.setText2(text2);
                  order.setText3(text3);
                  order.setQuantity(quantity);
                  order.setTimestamp(Timestamp.now());

                DocumentReference documentReference;
                documentReference= Utility.getcollectionreference().document();


                documentReference.set(order).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();

                        }

                        else {

                            Toast.makeText(context, "Not Added to cart", Toast.LENGTH_SHORT).show();
                        }

                    }
                });




            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView text1;
        TextView text2;
        TextView mealPriceSign;
        TextView mealPrice;
        Button addtocartbtn;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.meal_img);
            text1 = itemView.findViewById(R.id.meal_text1);
            text2 = itemView.findViewById(R.id.meal_text2);
            mealPriceSign = itemView.findViewById(R.id.meal_price_rupeeeSign);
            mealPrice = itemView.findViewById(R.id.meal_price);

            addtocartbtn = itemView.findViewById(R.id.addtocartbtn);

        }
    }
}
