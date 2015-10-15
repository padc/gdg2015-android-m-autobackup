package com.deubaka.gdg.autobackup;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mInputField;
    private Button mSaveButton;
    private TextView mDataFromBackup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mInputField = (EditText) findViewById(R.id.et_data);
        mSaveButton = (Button) findViewById(R.id.btn_save);
        mSaveButton.setOnClickListener(this);

        mDataFromBackup = (TextView) findViewById(R.id.tv_data_backed);
    }

    @Override
    public void onClick(View v) {
        saveData(mInputField.getText().toString());
        mInputField.setText("");
    }

    @Override
    protected void onResume() {
        super.onResume();

        mDataFromBackup.setText(getSavedData());
    }

    private static final String PREFS = "data_pref";
    private static final String KEY_DATA = "key_data";
    private void saveData(String data) {
        getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_DATA, data)
                .commit();
    }

    private String getSavedData() {
        return getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY_DATA, "");
    }
}
