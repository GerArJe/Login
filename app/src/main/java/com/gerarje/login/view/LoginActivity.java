package com.gerarje.login.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gerarje.login.repository.FirebaseService;
import com.gerarje.login.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btnLogin, btnCreateAccount;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseService firebaseService = new FirebaseService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        associateElements();
        initialize();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseService.signInWithEmailAndPassword(
                        edtUsername.getText().toString(),
                        edtPassword.getText().toString(),
                        LoginActivity.this,
                        firebaseAuth);
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseService.createUserWithEmailAndPassword(
                        edtUsername.getText().toString(),
                        edtPassword.getText().toString(),
                        LoginActivity.this,
                        firebaseAuth);
            }
        });
    }

    //Associate the layouts elements
    public void associateElements(){
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
        btnCreateAccount = findViewById(R.id.btn_createAccount);
    }

    //Initialize Firebase Instance
    public void initialize(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
            startActivity(intent);
        }
    }
}
