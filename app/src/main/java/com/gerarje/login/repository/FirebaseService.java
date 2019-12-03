package com.gerarje.login.repository;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.gerarje.login.view.WelcomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseService {

    private static final String TAG = "FirebaseService";

    //create account with email and password in Firebase
    public void createUserWithEmailAndPassword(String email, String password, final Activity activity
            , FirebaseAuth firebaseAuth){

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(activity, "Cuenta creada exitosamente",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activity, WelcomeActivity.class);
                            activity.startActivity(intent);
                        }else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(activity, "No se pudo crear la cuenta",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //sing in with email and password from Firebase
    public void signInWithEmailAndPassword(String email, String password, final Activity activity
            , FirebaseAuth firebaseAuth){

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(activity, "Inicio de sesión exitoso",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(activity, WelcomeActivity.class);
                            activity.startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(activity, "No se pudo inciar sesión",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
