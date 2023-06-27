package com.shurish.foodgram.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.shurish.foodgram.Adapters.meal_adapter;
import com.shurish.foodgram.MainActivity;
import com.shurish.foodgram.Models.meal_model;
import com.shurish.foodgram.R;

import java.util.ArrayList;

public class BriyaniActivity extends AppCompatActivity {

    RecyclerView briyani_rv;
    ImageView backbtn;
    FirebaseFirestore database;
   public ArrayList<meal_model> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_briyani);





        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            Drawable drawable = getResources().getDrawable(R.drawable.cardview_bg); // Replace with your drawable resource file
            decorView.setBackground(drawable);
        }






        briyani_rv = findViewById(R.id.briyani_rv);
        database = FirebaseFirestore.getInstance();
        backbtn = findViewById(R.id.briyani_backbtn);




        meal_adapter meal_adapter = new meal_adapter(this,list);
        briyani_rv.setAdapter(meal_adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        briyani_rv.setLayoutManager(linearLayoutManager);


        database.collection("Meals")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        list.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {



                            meal_model model3 = snapshot.toObject(meal_model.class);

                            list.add(model3);
                        }
                        meal_adapter.notifyDataSetChanged();
                    }
                });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BriyaniActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });




    }

}