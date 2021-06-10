package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignIn extends AppCompatActivity {
    private API api;
    private Retrofit retrofit;
    private EditText email, password;
    private TextView regText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl("http://cinema.areas.su/").build();
        api = retrofit.create(API.class);
        regText = findViewById(R.id.edSignUp);
    }
    public void OnClickSignUp(View v)
    {
        Intent intent = new Intent(SignIn.this, SignUp.class);
        startActivity(intent);
    }

    public void onClickVhod(View v)
    {
        Auth();
    }

    private void Auth()
    {
        AuthParam authParam = new AuthParam();
        authParam.setEmail(email.getText().toString());
        authParam.setPassword(password.getText().toString());
        Call<AuthParam> call = api.doAuth(authParam);
        call.enqueue(new Callback<AuthParam>() {
            @Override
            public void onResponse(Call<AuthParam> call, Response<AuthParam> response)
            {
                if(response.isSuccessful())
                {
                    Intent intent = new Intent(SignIn.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    ShowDialog("Error");
                }
            }
            @Override
            public void onFailure(Call<AuthParam> call, Throwable t)
            {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void ShowDialog(String Text){
        final AlertDialog alertDialog =  new AlertDialog.Builder(SignIn.this).setMessage(Text).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create();
        alertDialog.show();
    }
}