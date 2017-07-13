package com.javaconsumers.festpay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.javaconsumers.festpay.R;
import com.javaconsumers.festpay.database.DatabaseManager;
import com.javaconsumers.festpay.util.Preference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editEmail;
    private EditText editPasswordOld;
    private EditText editPasswordNew;
    private EditText editPasswordNewConfirm;
    private DatabaseManager database;
    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editEmail = (EditText) findViewById(R.id.edit_current_email);
        editPasswordOld = (EditText) findViewById(R.id.edit_old_password);
        editPasswordNew = (EditText) findViewById(R.id.edit_new_password);
        editPasswordNewConfirm = (EditText) findViewById(R.id.edit_new_password_confirm);
        findViewById(R.id.button_update_account).setOnClickListener(this);
        findViewById(R.id.button_logout).setOnClickListener(this);
        database = new DatabaseManager(this);
        preference = new Preference(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_update_account:
                if (database.updateUser(editEmail.getText().toString(), editPasswordOld.getText().toString(),
                        editPasswordNew.getText().toString(), editPasswordNewConfirm.getText().toString())) {
                    Toast.makeText(this, "Update successful.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Update failed.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button_logout:
                preference.setPreference(Preference.KEY_REMEBER, false);
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
    }
}
