package com.javaconsumers.festpay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.javaconsumers.festpay.R;
import com.javaconsumers.festpay.database.DatabaseManager;
import com.javaconsumers.festpay.database.User;
import com.javaconsumers.festpay.util.Preference;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout layoutUpdate;
    private ScrollView layoutSearch;
    private TextView textResult;
    private EditText editEmail;
    private EditText editPasswordOld;
    private EditText editPasswordNew;
    private EditText editPasswordNewConfirm;
    private EditText editSearch;
    private DatabaseManager database;
    private Preference preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutUpdate = (LinearLayout) findViewById(R.id.layout_update);
        layoutSearch = (ScrollView) findViewById(R.id.layout_search);
        textResult = (TextView) findViewById(R.id.text_search_result);
        editEmail = (EditText) findViewById(R.id.edit_current_email);
        editPasswordOld = (EditText) findViewById(R.id.edit_old_password);
        editPasswordNew = (EditText) findViewById(R.id.edit_new_password);
        editPasswordNewConfirm = (EditText) findViewById(R.id.edit_new_password_confirm);
        editSearch = (EditText) findViewById(R.id.edit_search_email);
        findViewById(R.id.button_update_account).setOnClickListener(this);
        findViewById(R.id.button_logout).setOnClickListener(this);
        findViewById(R.id.button_search_account).setOnClickListener(this);
        findViewById(R.id.button_search).setOnClickListener(this);
        findViewById(R.id.button_search_cancel).setOnClickListener(this);
        database = new DatabaseManager(this);
        preference = new Preference(this);
        layoutUpdate.setVisibility(View.VISIBLE);
        layoutSearch.setVisibility(View.GONE);
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
            case R.id.button_search_account:
                layoutUpdate.setVisibility(View.GONE);
                layoutSearch.setVisibility(View.VISIBLE);
                break;
            case R.id.button_search:
                List<User> usersBySearch = database.searchEmail(editSearch.getText().toString());

                String builder = "";
                for (User x : usersBySearch) {
                    builder += "Id: " + x.getId() + "\n";
                    builder += "Email: " + x.getEmail() + "\n";
                    builder += "Password: " + x.getPassword() + "\n";
                    builder += "-------------------------\n";
                }
                textResult.setText(builder);
                break;
            case R.id.button_search_cancel:
                layoutUpdate.setVisibility(View.VISIBLE);
                layoutSearch.setVisibility(View.GONE);
                break;
        }
    }
}
