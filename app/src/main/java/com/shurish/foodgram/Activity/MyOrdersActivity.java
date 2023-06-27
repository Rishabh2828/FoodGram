package com.shurish.foodgram.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.firestore.ChangeEventListener;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.shurish.foodgram.Adapters.CartAdapter;
import com.shurish.foodgram.MainActivity;
import com.shurish.foodgram.Models.AddToCartModel;
import com.shurish.foodgram.Models.OrderHistoryModel;
import com.shurish.foodgram.OrderHistoryAdapter;
import com.shurish.foodgram.R;
import com.shurish.foodgram.Utility;

public class MyOrdersActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    OrderHistoryAdapter myorderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//            View decorView = getWindow().getDecorView();
//            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            Drawable drawable = getResources().getDrawable(R.drawable.cardview_bg); // Replace with your drawable resource file
//            decorView.setBackground(drawable);
//        }


        recyclerView = findViewById(R.id.myorder_rv);
        setupRecyclerView();


    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MyOrdersActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
        startActivity(intent);
        finish(); // Finish the current activity
    }


    void setupRecyclerView() {


        Query query = Utility.getcollectionreference().orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<OrderHistoryModel> options = new FirestoreRecyclerOptions.Builder<OrderHistoryModel>()
                .setQuery(query, OrderHistoryModel.class).build();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        myorderAdapter = new OrderHistoryAdapter(options,this);










        recyclerView.setAdapter(myorderAdapter);


        options.getSnapshots().addChangeEventListener(new ChangeEventListener() {
            @Override
            public void onChildChanged(@NonNull ChangeEventType type, @NonNull DocumentSnapshot snapshot, int newIndex, int oldIndex) {

            }

            @Override
            public void onDataChanged() {

            }

            @Override
            public void onError(@NonNull FirebaseFirestoreException e) {
                // handle error
            }
        });
    }







    @Override
    protected void onStart() {
        super.onStart();
        myorderAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        myorderAdapter.stopListening();
    }


}
