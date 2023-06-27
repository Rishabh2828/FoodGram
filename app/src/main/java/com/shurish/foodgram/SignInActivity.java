package com.shurish.foodgram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class SignInActivity extends AppCompatActivity {
    FirebaseAuth auth;
    ProgressDialog dialog;

    Button submitBtn;
    Button createnewaccount;

    EditText emailBox;
    EditText passwordBox;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        auth = FirebaseAuth.getInstance();

        submitBtn = findViewById(R.id.submitBtn);
        createnewaccount = findViewById(R.id.gotosignupPageBtn);
        emailBox =findViewById(R.id.emailBox);
        passwordBox = findViewById(R.id.passwordBox);


        dialog = new ProgressDialog(this);
        dialog.setMessage("Logging in...");

        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
            finish();

        }

           submitBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {



                           String email, pass;
                           email = emailBox.getText().toString();
                           pass =passwordBox.getText().toString();



                           dialog.show();

                           auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                               @Override
                               public void onComplete(@NonNull Task<AuthResult> task) {
                                   dialog.dismiss();
                                   if(task.isSuccessful()) {
                                       startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                       finish();
                                   } else {
                                       Toast.makeText(SignInActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                   }
                               }
                           });
                       }
                   });



            createnewaccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    startActivity(new Intent(SignInActivity.this, SignUpActivity.class));
                }
            });






    }
}