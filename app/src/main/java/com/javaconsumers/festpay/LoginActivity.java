package com.javaconsumers.festpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editUsername;
    private EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editUsername = (EditText) findViewById(R.id.edit_login_username);
        editPassword = (EditText) findViewById(R.id.edit_login_password);
        Button buttonLogin = (Button) findViewById(R.id.button_login);
        Button buttonRegister = (Button) findViewById(R.id.button_register);
        TextView textForgotPassword = (TextView) findViewById(R.id.text_forgot_password);
        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
        textForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_login) {
            Toast.makeText(this, "Username: " + editUsername.getText().toString() + "\nPassword: " + editPassword.getText().toString(), Toast.LENGTH_LONG).show();
            editUsername.setText("Username");
        }
        switch (view.getId()) {
            case R.id.button_login:
                Toast.makeText(this, "Username: " + editUsername.getText().toString() + "\nPassword: " + editPassword.getText().toString(), Toast.LENGTH_LONG).show();
                break;
            case R.id.button_register:
                Intent intentRegister = new Intent(this, RegisterActivity.class);
                startActivity(intentRegister);
                break;
            case R.id.text_forgot_password:
                Toast.makeText(this, "Soon enough...", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
