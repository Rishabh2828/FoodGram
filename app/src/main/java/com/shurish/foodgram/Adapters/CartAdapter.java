package com.shurish.foodgram.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.shurish.foodgram.Activity.CartActivity;
import com.shurish.foodgram.Models.AddToCartModel;
import com.shurish.foodgram.R;
import com.shurish.foodgram.Utility;

import java.util.List;

public class CartAdapter extends FirestoreRecyclerAdapter<AddToCartModel, CartAdapter.viewholder > {


    Context context;


    public TextView totalCartPrice;


    public CartAdapter(@NonNull FirestoreRecyclerOptions<AddToCartModel> options, Context context, TextView totalCartPrice ) {
        super(options);

        this.context = context;
        this.totalCartPrice = totalCartPrice;

    }

    public CartAdapter(@NonNull FirestoreRecyclerOptions<AddToCartModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewholder holder, int position, @NonNull AddToCartModel model) {

        String docId = getSnapshots().getSnapshot(position).getId();
        holder.text1.setText(model.getText1());
        holder.text2.setText(model.getText2());
        holder.text3.setText(model.getText3());
        holder.quantity.setText(String.valueOf(model.getQuantity()));


        Glide.with(context)
                .load(model.getImg())
                .into(holder.imageView);

        holder.increasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                int quantity = model.getQuantity();
                int previousQuantity = quantity;
                quantity++;
                holder.quantity.setText(String.valueOf(quantity));

                // Update the quantity in the model
                model.setQuantity(quantity);

                int price = Integer.parseInt(model.getText3().trim());
                int oneItemPrice = price / previousQuantity;
                int newPrice = oneItemPrice * quantity;
                holder.text3.setText(String.valueOf(newPrice));
                model.setText3(String.valueOf(newPrice));







                        DocumentReference documentReference;

                documentReference= Utility.getcollectionreference().document(docId);

                documentReference.set(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){

                            // Toast.makeText(context, "", Toast.LENGTH_SHORT).show();





                        }

                        else {

                            Toast.makeText(context, "Could not added ", Toast.LENGTH_SHORT).show();
                        }

                    }
                });







            }
        });




        holder.decreasebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int quantity = model.getQuantity();
                int previousQuantity = quantity;



                if (quantity>1){

                    quantity--;
                    holder.quantity.setText(String.valueOf(quantity));

                    // Update the quantity in the model
                    model.setQuantity(quantity);


                    int price = Integer.parseInt(model.getText3().trim());
                    int oneItemPrice = price / previousQuantity;
                    int newPrice = oneItemPrice * quantity;
                    holder.text3.setText(String.valueOf(newPrice));
                    model.setText3(String.valueOf(newPrice));




                    DocumentReference documentReference;

                    documentReference= Utility.getcollectionreference().document(docId);

                    documentReference.set(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                // Toast.makeText(context, "", Toast.LENGTH_SHORT).show();


                            }

                            else {

                                Toast.makeText(context, "Could not added", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });




                } else {
                    Toast.makeText(context, "Minimum quantity should be one", Toast.LENGTH_SHORT).show();
                }








            }
        });



        holder.deletecart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the document ID of the item to be deleted
                String docId = getSnapshots().getSnapshot(position).getId();

                // Delete the item from Firestore
                Utility.getcollectionreference().document(docId)
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    // Item deleted successfully from Firestore
                                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Failed to delete item from Firestore
                                    Toast.makeText(context, "Failed to delete item", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });








    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_sample_layout,parent, false));
        return new viewholder(view);
    }













    @Override
    public void onDataChanged() {




        super.onDataChanged();

        calculateTotalPrice();



        notifyDataSetChanged(); // notify the adapter of the data set changes



    }






    public class viewholder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView text1;
        TextView text2;
        TextView text3;
        TextView cart_rupee_sign;
        Button addtocartbtn;

        ImageView decreasebtn;
        ImageView increasebtn;

        TextView quantity;

        ImageView deletecart;


        public viewholder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.meal_img);
            text1 = itemView.findViewById(R.id.meal_text1);
            text2 = itemView.findViewById(R.id.meal_text2);
            text3 = itemView.findViewById(R.id.cart_price);
            cart_rupee_sign = itemView.findViewById(R.id.cart_price_rupeeeSign);

            addtocartbtn = itemView.findViewById(R.id.addtocartbtn);

            decreasebtn = itemView.findViewById(R.id.decrease_cart);
            increasebtn = itemView.findViewById(R.id.incrase_cart);
            quantity = itemView.findViewById(R.id.quantity_cart);

            deletecart = itemView.findViewById(R.id.delete_cart);

        }




    }

    public void calculateTotalPrice() {
        int totalPrice = 0;
        for (int i = 0; i < getSnapshots().size(); i++) {
            AddToCartModel item = getSnapshots().get(i);
            int price = Integer.parseInt(item.getText3().trim());

            totalPrice += price ;
        }
        totalCartPrice.setText(String.valueOf(totalPrice));
    }


    public void setTotalCartPrice(TextView totalCartPrice) {
        this.totalCartPrice = totalCartPrice;
    }





}


