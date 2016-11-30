package com.selftaught.mrede003.androidcalculator;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

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
        if(subDisplay.getText().toString().equals("")) {
            mainDisplay.setText(b.getText().toString()+mainDisplay.getText().toString());//if mainDisplay is blank print char and return
            subDisplay.setText(b.getText().toString()+subDisplay.getText().toString());
            return;
        }
        if (isOperand((subDisplay.getText().toString()).substring(
                subDisplay.getText().toString().length() - 1))) {        //if operand is at end of subDisplay
            subDisplay.setText(subDisplay.getText().toString() +" "+
                        (b.getText()).toString());
            mainDisplay.setText(b.getText().toString());
        }else {
            subDisplay.setText(subDisplay.getText().toString() + b.getText().toString());
            mainDisplay.setText(mainDisplay.getText().toString()+b.getText().toString());
        }

    }
    public void clear(View view)
    {
        TextView subDisplay=(TextView) findViewById(R.id.sub_display);
        TextView mainDisplay=(TextView) findViewById(R.id.main_display);
        subDisplay.setText(null);
        mainDisplay.setText(null);
    }
    public void equals(View view)
    {
        TextView subDisplay=(TextView) findViewById(R.id.sub_display);
        String answer=subDisplay.getText().toString();
        answer=answer.replaceAll("\\s","");
        subDisplay.setText(answer);
        
    }
    public String analyzeEquation(String equation)
    {
        int num1,num2;
        num1=equation.toCharArray()[0];


        return equation;
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

        if(!subDisplay.getText().toString().equals("")&&!(
                isOperand((subDisplay.getText().toString()).substring(
                        subDisplay.getText().toString().length() - 1))))
        {
            subDisplay.setText(subDisplay.getText()+" "+b.getText().toString());
        }
    }
    public boolean isOperand(String t)
    {
        if(t.equals(getString(R.string.divide))||
                t.equals(getString(R.string.multiply))||
                    t.equals(getString(R.string.add))||
                            t.equals(getString(R.string.subtract)))
            return true;
        return false;
    }
    public static String infixToPostfix(String equation)
    {
        final String ops="-+/*^";
        StringBuilder sb=new StringBuilder();
        Stack<Integer> stack=new Stack();

        for(String token: equation.split("//s"))        //split equation at space into string array
        {                                               //for every string in the array.....
            if(token.isEmpty())                         //if string is "" continue
                continue;
            char c=token.charAt(0);                     //char c is the char we're working with
            int index=ops.indexOf(c);                   //index is the pos of the operator. is -1 if its an operand

            if(index!=-1)                               //if index is an operator
            {
                if(stack.isEmpty())
                {
                    stack.push(index)
                }else{
                    while(!stack.isEmpty())
                    {

                    }
                }
            }


        }
    }

}
