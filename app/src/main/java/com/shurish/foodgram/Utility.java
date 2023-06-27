package com.shurish.foodgram;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Utility {
    static public CollectionReference getcollectionreference(){

        FirebaseUser currentuser = FirebaseAuth.getInstance().getCurrentUser();
       return FirebaseFirestore.getInstance().collection("My Orders")
                .document(currentuser.getUid()).collection("orders");

    }
}
