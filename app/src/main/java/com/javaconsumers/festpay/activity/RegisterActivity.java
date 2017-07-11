package com.javaconsumers.festpay.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.javaconsumers.festpay.R;
import com.javaconsumers.festpay.database.DatabaseManager;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail;
    private EditText editEmailConfirm;
    private EditText editPassword;
    private EditText editPasswordConfirm;
    private DatabaseManager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editEmail = (EditText) findViewById(R.id.edit_current_email);
        editEmailConfirm = (EditText) findViewById(R.id.edit_new_email);
        editPassword = (EditText) findViewById(R.id.edit_old_password);
        editPasswordConfirm = (EditText) findViewById(R.id.edit_new_password);
        findViewById(R.id.button_register_back).setOnClickListener(this);
        findViewById(R.id.button_update_account).setOnClickListener(this);
        database = new DatabaseManager(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_register_back:
                finish();
                break;
            case R.id.button_update_account:
                if (database.registerUser(editEmail.getText().toString(), editEmailConfirm.getText().toString(),
                        editPassword.getText().toString(), editPasswordConfirm.getText().toString())) {
                    Toast.makeText(this, "Registration successful.", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(this, "Registration failed.", Toast.LENGTH_SHORT).show();
                }
                finish();
                break;
        }
    }
}
