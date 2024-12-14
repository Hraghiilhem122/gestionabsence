package com.example.gestionabscence;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextName, editTextRegisterEmail, editTextRegisterPassword;
    private Button buttonLogin, buttonRegister;
    private TextView textSwitchToRegister, textSwitchToLogin;
    private LinearLayout loginLayout, registerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialisation des vues
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextName = findViewById(R.id.editTextName);
        editTextRegisterEmail = findViewById(R.id.editTextRegisterEmail);
        editTextRegisterPassword = findViewById(R.id.editTextRegisterPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonRegister = findViewById(R.id.buttonRegister);
        textSwitchToRegister = findViewById(R.id.textSwitchToRegister);
        textSwitchToLogin = findViewById(R.id.textSwitchToLogin);
        loginLayout = findViewById(R.id.loginLayout);
        registerLayout = findViewById(R.id.registerLayout);

        // Logic to switch to register mode
        textSwitchToRegister.setOnClickListener(v -> {
            loginLayout.setVisibility(View.GONE);
            registerLayout.setVisibility(View.VISIBLE);
        });

        // Logic to switch to login mode
        textSwitchToLogin.setOnClickListener(v -> {
            registerLayout.setVisibility(View.GONE);
            loginLayout.setVisibility(View.VISIBLE);
        });

        // Logic for login button
        buttonLogin.setOnClickListener(v -> {
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            // Handle login logic here (validation, checking credentials)
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                // Logic to check login credentials, possibly using a database
                Toast.makeText(LoginActivity.this, "Connexion réussie", Toast.LENGTH_SHORT).show();
            }
        });

        // Logic for register button
        buttonRegister.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String email = editTextRegisterEmail.getText().toString();
            String password = editTextRegisterPassword.getText().toString();
            // Handle registration logic here (saving user data)
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(LoginActivity.this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            } else {
                // Logic to save user information, possibly in a database
                Toast.makeText(LoginActivity.this, "Inscription réussie", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
