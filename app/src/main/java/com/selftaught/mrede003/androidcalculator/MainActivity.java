package com.selftaught.mrede003.androidcalculator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void displayNumber(View view)
    {
        TextView mainDisplay=(TextView) findViewById(R.id.main_display);
        TextView subDisplay=(TextView) findViewById(R.id.sub_display);

        Button b=(Button)view;
        subDisplay.setText(b.getText().toString()+subDisplay.getText());
        mainDisplay.setText(b.getText().toString()+mainDisplay.getText());
    }
    public void clear(View view)
    {
        TextView subDisplay=(TextView) findViewById(R.id.sub_display);
        TextView mainDisplay=(TextView) findViewById(R.id.main_display);
        subDisplay.setText(null);
        mainDisplay.setText(null);
    }
    public void backspace(View view)
    {
        TextView mainDisplay=(TextView) findViewById(R.id.main_display);
        TextView subDisplay=(TextView) findViewById(R.id.sub_display);
        if(mainDisplay.getText().toString().equals("")) return;

        mainDisplay.setText((mainDisplay.getText()
                .toString()).substring(0, mainDisplay.getText().toString().length()-1));
        subDisplay.setText((subDisplay.getText()
                .toString()).substring(0, subDisplay.getText().toString().length()-1));
    }
    public void displayOperation(View view)
    {
        TextView subDisplay=(TextView) findViewById(R.id.sub_display);
        Button b=(Button)view;

        if(subDisplay.getText()!=null)
        {
            subDisplay.setText(subDisplay.getText()+" "+b.getText().toString());
        }
    }

}
