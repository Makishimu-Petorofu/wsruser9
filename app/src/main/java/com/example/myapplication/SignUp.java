package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


    }
    public void onClickSignIn(View v){
        startActivity(new Intent(SignUp.this, SignIn.class));
        finish();

    }
    public void onClickReg(View v){
        EditText edEmail = (EditText)findViewById(R.id.edEmail);
        EditText edName = (EditText)findViewById(R.id.edName);
        EditText edSecName = (EditText)findViewById(R.id.edSecName);
        EditText edPass = (EditText)findViewById(R.id.edPass);
        EditText edPass1 = (EditText)findViewById(R.id.edPass1);

        final String e = edEmail.getText().toString();

        if(!(edEmail.getText().toString().equals("") && edName.getText().toString().equals("") && edSecName.getText().toString().equals("") && edPass.getText().toString().equals("")  && edPass1.getText().toString().equals(""))&& Patterns.EMAIL_ADDRESS.matcher(e).matches() && edPass.getText().toString().equals(edPass1.getText().toString())){
            registerUser();
        }
        else{
            ShowDialog("Error");
        }
    }
    public void ShowDialog(String Text){
        final AlertDialog alertDialog =  new AlertDialog.Builder(SignUp.this).setMessage(Text).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).create();
        alertDialog.show();
    }

    public void registerUser() {
       /* RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(edEmail.getText().toString());
        registerRequest.setName(edName.getText().toString());
        registerRequest.setSecName(edSecName.getText().toString());
        registerRequest.setPass(edPass.getText().toString());

        Call<RegisterResponse> registerResponseCall = ApiClient.getRegister().registerUser(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {

            @Override
            public void onResponse(CallbackResponse<RegisterResponse> response) {

            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                startActivity(new Intent(SignUp.this, SignIn.class));
            }
       });   */

    }



}