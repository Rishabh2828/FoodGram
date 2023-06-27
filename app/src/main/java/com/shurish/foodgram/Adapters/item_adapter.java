package com.shurish.foodgram.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shurish.foodgram.Activity.BriyaniActivity;
import com.shurish.foodgram.Activity.BurgerActivity;
import com.shurish.foodgram.Activity.ChickenActivity;
import com.shurish.foodgram.Activity.PizzaActivity;
import com.shurish.foodgram.Activity.RollActivity;
import com.shurish.foodgram.MainActivity;
import com.shurish.foodgram.Models.Item_model;
import com.shurish.foodgram.Models.meal_model;
import com.shurish.foodgram.R;

import java.util.ArrayList;

public class item_adapter extends RecyclerView.Adapter<item_adapter.viewholder> {
    Context context;
    ArrayList<Item_model> list;

    public item_adapter(Context context, ArrayList<Item_model> list) {
        this.context = context;
        this.list = list;
    }









    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_sample_layout, parent, false);
        return new viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        Item_model model = list.get(position);

//        holder.img.setImageResource(model.getImg());

        holder.text.setText(model.getText());

        Glide.with(context)
                .load(model.getImg())
                .into(holder.img);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (position){
                    case 0:
                        Intent intent = new Intent(context, BriyaniActivity.class);
                        context.startActivity(intent);
                        break;

                    case 1:
                        Intent intent1 = new Intent(context, RollActivity.class);
                        context.startActivity(intent1);
                        break;

                    case 2:
                        Intent intent2 = new Intent(context, ChickenActivity.class);
                        context.startActivity(intent2);
                        break;

                    case 3:
                        Intent intent3 = new Intent(context, PizzaActivity.class);
                        context.startActivity(intent3);
                        break;

                    case 4:
                        Intent intent4 = new Intent(context, BurgerActivity.class);
                        context.startActivity(intent4);
                        break;

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView text;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.item_sample_img);
            text = itemView.findViewById(R.id.item_text);
        }
    }
}
