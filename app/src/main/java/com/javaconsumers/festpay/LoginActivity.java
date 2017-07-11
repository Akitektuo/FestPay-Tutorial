package com.javaconsumers.festpay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail;
    private EditText editPassword;
    private CheckBox checkRemember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editEmail = (EditText) findViewById(R.id.edit_login_email);
        editPassword = (EditText) findViewById(R.id.edit_old_password);
        checkRemember = (CheckBox) findViewById(R.id.check_remember);
        Button buttonLogin = (Button) findViewById(R.id.button_login);
        Button buttonRegister = (Button) findViewById(R.id.button_register);
        buttonLogin.setOnClickListener(this);
        buttonRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                //do check here
                break;
            case R.id.button_register:
                startActivity(new Intent(this, RegisterActivity.class));
                editEmail.setText("");
                editPassword.setText("");
                checkRemember.setChecked(false);
                break;
        }
    }
}
