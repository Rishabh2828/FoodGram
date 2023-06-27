package com.shurish.foodgram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.shurish.foodgram.Models.AddToCartModel;

public class ProductDescriptionActivity extends AppCompatActivity {

    ImageView imageView;
    TextView firsttextView;
    TextView secondtextview;

    TextView productnametext;

    Button addtocart;
    ImageView increasequantity;
    ImageView decreasequantity;
    TextView quantity;

    TextView productdesc_price;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Drawable drawable = getResources().getDrawable(R.drawable.cardview_bg); // Replace with your drawable resource file
            decorView.setBackground(drawable);
        }

        imageView = findViewById(R.id.imageView3);
        firsttextView = findViewById(R.id.firsttext);
        secondtextview = findViewById(R.id.secondtext);
        productnametext = findViewById(R.id.productname);

        increasequantity = findViewById(R.id.productdesc_increase_quantity);
        decreasequantity = findViewById(R.id.productdesc_decrease_quantity);
        quantity = findViewById(R.id.productdesc_quantity);
        addtocart = findViewById(R.id.productdesc_addtocartbtn);
        productdesc_price= findViewById(R.id.productdesc_price);

        Bundle extras = getIntent().getExtras();

        // Get the image resource ID and name from the extras data
        String imageResourceId = extras.getString("image_resource_id");
        String firsttext = extras.getString("text1");
        String secondtext = extras.getString("text2");
        String productname = extras.getString("text1");
        String productprice= extras.getString("text3");



        Glide.with(this).load(imageResourceId).into(imageView);

        firsttextView.setText(firsttext);
        secondtextview.setText(secondtext);
        productnametext.setText(productname);
        productdesc_price.setText(productprice);





        int originalPrice = Integer.parseInt(productprice.trim());



        quantity.setText(String.valueOf(1));


        increasequantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity.setText(String.valueOf(Integer.parseInt(quantity.getText().toString()) + 1));

                int updatedPrice = originalPrice * (Integer.parseInt(quantity.getText().toString()));


                productdesc_price.setText(String.valueOf(updatedPrice));



            }
        });



        decreasequantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Integer.parseInt(quantity.getText().toString()) > 1) {

                    quantity.setText(String.valueOf(Integer.parseInt(quantity.getText().toString()) - 1));


                    int updatedPrice = originalPrice * (Integer.parseInt(quantity.getText().toString()));


                    productdesc_price.setText(String.valueOf(updatedPrice));
                }

            }
        });





        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int currentQuantity = Integer.parseInt(quantity.getText().toString());


                // Calculate the updated price based on the original price and quantity
                int updatedPrice = originalPrice * currentQuantity;





                String text1 = firsttext;
                String text2 = secondtext;
                String text3 = String.valueOf(updatedPrice);

                String img = imageResourceId;
                int quantity = currentQuantity;








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

                            Toast.makeText(ProductDescriptionActivity.this, "Added to cart", Toast.LENGTH_SHORT).show();

                        }

                        else {

                            Toast.makeText(ProductDescriptionActivity.this, "Not Added to cart", Toast.LENGTH_SHORT).show();
                        }

                    }
                });




            }
        });






    }
}