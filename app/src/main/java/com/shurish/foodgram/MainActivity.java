package com.shurish.foodgram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.shurish.foodgram.Activity.BriyaniActivity;
import com.shurish.foodgram.Activity.BurgerActivity;
import com.shurish.foodgram.Activity.CartActivity;
import com.shurish.foodgram.Activity.MyOrdersActivity;
import com.shurish.foodgram.Adapters.item_adapter;
import com.shurish.foodgram.Adapters.meal_adapter;
import com.shurish.foodgram.Adapters.offer_cv_adapter;
import com.shurish.foodgram.Models.Item_model;
import com.shurish.foodgram.Models.User;
import com.shurish.foodgram.Models.meal_model;
import com.shurish.foodgram.Models.offer_cv_model;

import java.util.ArrayList;
import java.util.List;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    RecyclerView offer_cv_rv;
    RecyclerView item_rv;

    RecyclerView meal_rv;

    TextView usrname;

    FirebaseFirestore database;

    SmoothBottomBar bottomBar;
    int selectedBottomBarItem = 0;

    EditText searchview;

    ArrayList<meal_model> list3 = new ArrayList<>();
    meal_adapter meal_adapter = new meal_adapter(this,list3);

    ArrayList<meal_model> matchingItems = new ArrayList<>();

    private boolean doubleBackPressed = false;

    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        offer_cv_rv = findViewById(R.id.offer_cv_rv);
        item_rv = findViewById(R.id.item_rv);
        meal_rv = findViewById(R.id.meal_rv);
        usrname = findViewById(R.id.username);

        bottomBar= findViewById(R.id.bottomBar);

        database = FirebaseFirestore.getInstance();

        searchview = findViewById(R.id.searchEditText);





        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();


        String uid = auth.getCurrentUser().getUid();

        database.collection("users")
                .document(uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user = document.toObject(User.class);
                                usrname.setText(user.getName());
                            }
                        } else {
                            // Handle the error
                        }
                    }
                });























        ArrayList<offer_cv_model> list = new ArrayList<>();












        offer_cv_adapter adapter = new offer_cv_adapter(this, list);
        offer_cv_rv.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        offer_cv_rv.setLayoutManager(linearLayoutManager);

        database.collection("CardViewOffers")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        list.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {

                            offer_cv_model model = snapshot.toObject(offer_cv_model.class);

                            list.add(model);
                        }
                        adapter.notifyDataSetChanged();
                    }
                });






        ArrayList<Item_model> list2 = new ArrayList<>();

        item_adapter item_adapter  = new item_adapter(this, list2);
        item_rv.setAdapter(item_adapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        item_rv.setLayoutManager(linearLayoutManager1);

        database.collection("Items")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        list2.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {


                            Item_model model2 = snapshot.toObject(Item_model.class);

                            list2.add(model2);
                        }
                        item_adapter.notifyDataSetChanged();
                    }
                });

















        meal_rv.setAdapter(meal_adapter);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        meal_rv.setLayoutManager(linearLayoutManager3);


        database.collection("Meals")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        list3.clear();
                        for (DocumentSnapshot snapshot : value.getDocuments()) {



                            meal_model model3 = snapshot.toObject(meal_model.class);

                            list3.add(model3);
                        }
                        meal_adapter.notifyDataSetChanged();
                    }
                });

        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                switch ( i){

                    case 0 :
                        Intent intent = new Intent(MainActivity.this, MainActivity.class );
                        startActivity(intent);
                        break;


                    case 1 :
                        Intent intent1 = new Intent(MainActivity.this, CartActivity.class );
                        startActivity(intent1);
                        break;

                    case 2 :
                        Intent intent2 = new Intent(MainActivity.this, MyOrdersActivity.class );
                        startActivity(intent2);
                        break;



                }

                return false;
            }
        });







        searchview.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                performSearch(editable.toString());


                if (editable.toString().isEmpty()) {
                    // Show the RecyclerViews when the search text is empty
                    item_rv.setVisibility(View.VISIBLE);
                    offer_cv_rv.setVisibility(View.VISIBLE);
                } else {
                    // Hide the RecyclerViews when there is a search query
                    item_rv.setVisibility(View.GONE);
                    offer_cv_rv.setVisibility(View.GONE);
                }

            }
        });
















    }


    @Override
    public void onBackPressed() {




        searchview.setText("");

        // Show the complete MainActivity
        item_rv.setVisibility(View.VISIBLE);
        offer_cv_rv.setVisibility(View.VISIBLE);
        meal_adapter.filterList(list3);




        if (doubleBackPressed) {
            super.onBackPressed();
            return;
        }

        this.doubleBackPressed = true;
        Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackPressed = false;
            }
        }, 2000); // Reset the flag after 2 seconds
    }








    public  void performSearch(String query) {



        matchingItems.clear();

//
//        BriyaniActivity briyaniActivity = new BriyaniActivity();
//        BurgerActivity burgerActivity = new BurgerActivity();
//
//        // Search for matching items in Activity A
//        ArrayList<meal_model> itemsFromActivityA = briyaniActivity.list;
//
//        for (meal_model item : itemsFromActivityA) {
//            if (item.getText1().toLowerCase().contains(query.toLowerCase())) {
//                matchingItems.add(item);
//            }
//        }
//
//        // Search for matching items in Activity B
//        ArrayList<meal_model> itemsFromActivityB = burgerActivity.list;
//        for (meal_model item : itemsFromActivityB) {
//            if (item.getText1().toLowerCase().contains(query.toLowerCase())) {
//                matchingItems.add(item);
//            }
//        }

        // Add other activities as needed...


        for (meal_model item : list3) {
            if (item.getText1().toLowerCase().contains(query.toLowerCase())) {
                matchingItems.add(item);
            }
        }





        item_rv.setVisibility(View.GONE);
        offer_cv_rv.setVisibility(View.GONE);

        meal_adapter.filterList(matchingItems);






    }






}