package com.shurish.foodgram;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Utility2 {


    static public CollectionReference getcollectionreference(){

        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
        return FirebaseFirestore.getInstance().collection("Order History")
                .document(currentuser.getUid()).collection("orders");

    }
}
