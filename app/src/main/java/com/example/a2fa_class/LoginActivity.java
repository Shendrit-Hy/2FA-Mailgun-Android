package com.example.a2fa_class;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText , passwordEditText;
    private Button loginBtn;
    private static long otpTime;
    private final String testEmail = "test@gmail.com";
    private final String testPassword = "test123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                if (email.equals(testEmail) && password.equals(testPassword)){
                    otpTime = System.currentTimeMillis();
                    String OTP = MailgunUtils.generateOTP();
                    MailgunUtils.sendOTP(LoginActivity.this,email,OTP);
                    Intent intent = new Intent(LoginActivity.this, VerifyOTPActivity.class);
                    intent.putExtra("OTP",OTP);
                    intent.putExtra("otpTime", otpTime);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(LoginActivity.this, "Wrong Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}