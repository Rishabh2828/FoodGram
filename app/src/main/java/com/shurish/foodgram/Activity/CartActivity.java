package com.shurish.foodgram.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.firestore.ChangeEventListener;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.shurish.foodgram.Adapters.CartAdapter;
import com.shurish.foodgram.MainActivity;
import com.shurish.foodgram.Models.AddToCartModel;
import com.shurish.foodgram.R;
import com.shurish.foodgram.Utility;
import com.shurish.foodgram.Utility2;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartAdapter myorderAdapter;

    TextView totalcartprice;

    LinearLayout paynow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        totalcartprice = findViewById(R.id.totalcartprice);


        paynow = findViewById(R.id.paynow);



        recyclerView = findViewById(R.id.cart_rv);

        setupRecyclerView();




        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve all items from AddToCartModel collection
                Query query = Utility.getcollectionreference().orderBy("timestamp", Query.Direction.DESCENDING);

                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            // Create a new collection reference
//                            FirebaseFirestore db = FirebaseFirestore.getInstance();
//                            CollectionReference newCollectionRef = db.collection("NewCollection");


                            FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();

                            String userId = String.valueOf(currentuser); // Replace with the actual current user ID
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            DocumentReference userDocRef = db.collection("OrderHistory").document(userId);


                            CollectionReference historyCollectionRef = userDocRef.collection("orderslist");



                            // Iterate through each item and create a copy in the new collection
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                AddToCartModel item = document.toObject(AddToCartModel.class);
                                historyCollectionRef.add(item);
                            }


                            showOrderSuccessDialog();
                        } else {
                            // Handle error
                        }
                    }
                });
            }
        });










    }


    void setupRecyclerView() {


        Query query = Utility.getcollectionreference().orderBy("timestamp", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<AddToCartModel> options = new FirestoreRecyclerOptions.Builder<AddToCartModel>()
                .setQuery(query, AddToCartModel.class).build();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        myorderAdapter = new CartAdapter(options, this, totalcartprice);
        myorderAdapter.setTotalCartPrice(totalcartprice);










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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CartActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clear the activity stack
        startActivity(intent);



        finish(); // Finish the current activity
    }



    private void showOrderSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
        builder.setTitle("Order Placed Successfully")
                .setMessage("Thank you for your order!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        // Perform any additional actions after order placement
                    }
                })
                .setCancelable(false)
                .show();
    }








}