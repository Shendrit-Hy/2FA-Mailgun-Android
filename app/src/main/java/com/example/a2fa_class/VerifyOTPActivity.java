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

public class VerifyOTPActivity extends AppCompatActivity {

    private EditText otpEditText;
    private Button verifyBtn, resendBtn;
    private static long otpTimestamp;
    private String OTP;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_verify_otpactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        otpEditText = findViewById(R.id.userOTP);
        verifyBtn = findViewById(R.id.verifyBtn);
        resendBtn = findViewById(R.id.resendBtn);

        otpTimestamp = intent.getLongExtra("otpTime",30);
        OTP = intent.getStringExtra("OTP");
        email = intent.getStringExtra("email");

        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userOtp = otpEditText.getText().toString().trim();

                if(userOtp.matches("\\d{6}")){
                    if (System.currentTimeMillis() - otpTimestamp <= 30000){
                        if (userOtp.equals(OTP)){
                            Toast.makeText(getApplicationContext(), "OTP Verified!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Wrong OTP!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "OTP is invalid! Resend OTP again!", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(VerifyOTPActivity.this, "OTP needs to be 6 digits!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        resendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpTimestamp = System.currentTimeMillis();
                OTP = MailgunUtils.generateOTP();
                MailgunUtils.sendOTP(VerifyOTPActivity.this,email,OTP);
            }
        });


    }
}
