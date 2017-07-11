package com.javaconsumers.festpay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail;
    private EditText editEmailConfirm;
    private EditText editPassword;
    private EditText editPasswordConfirm;

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_register_back:
                finish();
                break;
            case R.id.button_update_account:
                //add into db here
                finish();
                break;
        }
    }
}
