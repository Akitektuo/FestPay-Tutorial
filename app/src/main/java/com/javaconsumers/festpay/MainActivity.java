package com.javaconsumers.festpay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail;
    private EditText editPasswordOld;
    private EditText editPasswordNew;
    private EditText editPasswordNewConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail = (EditText) findViewById(R.id.edit_current_email);
        editPasswordOld = (EditText) findViewById(R.id.edit_old_password);
        editPasswordNew = (EditText) findViewById(R.id.edit_new_password);
        editPasswordNewConfirm = (EditText) findViewById(R.id.edit_new_password_confirm);
        findViewById(R.id.button_update_account).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_update_account) {
            //do an update
        }
    }
}
