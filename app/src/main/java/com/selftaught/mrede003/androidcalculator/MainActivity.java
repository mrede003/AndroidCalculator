package com.selftaught.mrede003.androidcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Stack;


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
        TextView mainDisplay=(TextView) findViewById(R.id.main_display);
        String equation=subDisplay.getText().toString();
        equation=infixToPostfix(equation);
        Stack<Double> stack=new Stack();
        for(String token: equation.split(" "))
        {
            if(!isOperand(token))
                stack.push(Double.parseDouble(token));
            if(isOperand(token))
            {
                Double a=stack.pop();
                Double b=stack.pop();
                stack.push(evaluate(token,a, b));
            }
        }
        subDisplay.setText(fmt(stack.pop()));
        mainDisplay.setText(subDisplay.getText().toString());
    }
    public Double evaluate(String op, Double a, Double b)
    {
        if(op.equals("-"))
            return (b-a);
        if(op.equals("+"))
            return (b+a);
        if(op.equals("*"))
            return (b*a);
        if(op.equals("/"))
            return (b/a);
        if(op.equals("^"))
            return Math.pow(b, a);
        return -1.0;
    }
    public void backspace(View view)
    {
        TextView mainDisplay=(TextView) findViewById(R.id.main_display);
        TextView subDisplay=(TextView) findViewById(R.id.sub_display);
        if(!mainDisplay.getText().toString().equals(""))
            mainDisplay.setText((mainDisplay.getText()
                .toString()).substring(0, mainDisplay.getText().toString().length()-1));
        int offset=2;
        if(!subDisplay.getText().toString().equals("")) {
            if (subDisplay.getText().toString().length() < 2)
                offset = 1;

            subDisplay.setText((subDisplay.getText()
                    .toString()).substring(0, subDisplay.getText().toString().length() - offset));
        }
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
    public void displayParentheses(View view)
    {
        TextView subDisplay=(TextView) findViewById(R.id.sub_display);
        Button b=(Button)view;
        subDisplay.setText(subDisplay.getText()+" "+b.getText().toString()+" ");
    }
    public boolean isOperand(String t)
    {
        if(t.equals(getString(R.string.divide))||
                t.equals(getString(R.string.multiply))||
                    t.equals(getString(R.string.add))||
                            t.equals(getString(R.string.subtract))||
                                t.equals(getString(R.string.exponent)))
            return true;
        return false;
    }
    public static String infixToPostfix(String equation)
    {
        final String ops="-+/*^";
        StringBuilder sb=new StringBuilder();
        Stack<Integer> stack=new Stack();

        for(String token: equation.split(" "))        //split equation at space into string array
        {                                               //for every string in the array.....
            if(token.isEmpty())                         //if string is "" continue
                continue;
            char c=token.charAt(0);                     //char c is the char we're working with
            int index=ops.indexOf(c);                   //index is the pos of the operator. is -1 if its an operand

            if(index!=-1)                               //if index is an operator
            {
                if(stack.isEmpty())
                {
                    stack.push(index);
                }else{
                    while(!stack.isEmpty()) {
                        int op2 = stack.peek() / 2;
                        int op1 = index / 2;
                        if (op2 > op1 || (op2 == op1 && c != '^'))
                            sb.append(ops.charAt(stack.pop())).append(' ');
                          else break;
                    }
                    stack.push(index);
                }
            }
            else if(c=='('){
                stack.push(-2);
            }
            else if(c==')'){
                while(stack.peek()!=-2)
                    sb.append(ops.charAt(stack.pop())).append(' ');
                stack.pop();
            }
            else{
                sb.append(token).append(' ');
            }
        }
        while(!stack.isEmpty())
            sb.append(ops.charAt(stack.pop())).append(' ');
        return sb.toString();
    }
    //Quick static method to display int stored as double as ints
    //and doubles with minimum precision
    public static String fmt(double d)
    {
        if(d == (long) d)
            return String.format("%d",(long)d);
        else
            return String.format("%s",d);
    }

}
