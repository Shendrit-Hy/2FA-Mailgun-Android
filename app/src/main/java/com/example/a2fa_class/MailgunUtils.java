package com.example.a2fa_class;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MailgunUtils {
    private static final String MAILGUN_API_BASE = "https://api.mailgun.net/v3/";
    private static final String MAILGUN_API = "YOUR MAILGUN API";
    private static final String MAILGUN_DOMAIN = "YOUR MAILGUN DOMAIN";



    public static String generateOTP(){
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }


    public static void sendOTP(Context context, String email, String OTP) {
        String subject = "Your OTP code";
        String message = "OTP: " + OTP;

        OkHttpClient client = new OkHttpClient();
        String url = MAILGUN_API_BASE + MAILGUN_DOMAIN + "/messages";


        RequestBody formBody = new FormBody.Builder()
                .add("from", "YOUR APP NAME <mailgun@" + MAILGUN_DOMAIN + ">")
                .add("to", email)
                .add("subject", subject)
                .add("text", message)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", Credentials.basic("api", MAILGUN_API))
                .post(formBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ((Activity) context).runOnUiThread(() -> {
                    Toast.makeText(context, "Failed to send OTP!", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ((Activity) context).runOnUiThread(() -> {
                    if (response.isSuccessful()){
                        Toast.makeText(context, "OTP sent!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context, "OTP failed to send!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }





}


