package com.selftaught.mrede003.androidcalculator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessageUpper(View view)
    {
        EditText editText=(EditText) findViewById(R.id.edit_message_upper);

        editText.setText(editText.getText().toString().toUpperCase());
    }

}
