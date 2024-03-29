package com.example.trashgg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {
    private static FirebaseAuth mAuth;
    private EditText nikName,emailEditText,passwordEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            mAuth = FirebaseAuth.getInstance();
            return insets;
        });
    }

    @Override
    public void onStart() { //פעולה הבודקת האם המשתמש כבר רשום - אם כן ישר מעבירה אותו למסך המרכזי
        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null) {
//           startActivity(new Intent(LoginActivity.this, MainActivity.class));
//       }
   }

    public void register(View view) { //פעולה המאפשרת הרשמה של משתמש חדש במערכת ורושמת אותו ב-פייר בייס
        emailEditText = findViewById(R.id.etEmail);
        passwordEditText = findViewById(R.id.etPass);
        nikName = findViewById(R.id.etName);
        mAuth.createUserWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Players p = new Players(nikName.getText().toString(),emailEditText.getText().toString(),passwordEditText.getText().toString());
                            myFirebase f = new myFirebase();
                            f.addPlayer(p);
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Register Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    };
    public void login(View view) { //פעולה המאפשרת כניסה למשתמש קיים במערכת של ה- פייר בייס
        emailEditText = findViewById(R.id.etEmail);
        passwordEditText = findViewById(R.id.etPass);
        try{
            mAuth.signInWithEmailAndPassword(
                            emailEditText.getText().toString(), passwordEditText.getText().toString())
                    .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }catch (Exception e){Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();}
    }
}


















