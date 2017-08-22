package com.ubclaunchpad.calculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    private final static String TAG = CalculatorActivity.class.getSimpleName();
    public final static String EXTRA_CALCULATOR_ANSWER = "com.ubclaunchpad.calculator.CALCULATOR_ANSWER";

    EditText mEditBox1;
    EditText mEditBox2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        mEditBox1 = (EditText) findViewById(R.id.firstInput);
        mEditBox2 = (EditText) findViewById(R.id.secondInput);
    }

    /**
     * This implementation of the click listener is for the view found in res/layout/activity_calculator
     * The functions in general should grab the appropriate inputs, and if they are valid, computes the answer.
     * The answer should be displayed in a second activity labelled "AnswerActivity"
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        double input1;
        double input2;
        double answer;
        boolean shouldShowAnswer = true;

        try {
            String parameter1 = mEditBox1.getText().toString();
            String parameter2 = mEditBox2.getText().toString();

            if(parameter1.equals("") && parameter2.equals("")){
                input1 = 0;
                input2 = 0;
                Toast.makeText(this, "Input 1 and 2 empty, assuming 0", Toast.LENGTH_SHORT).show();
            }
            else {
                if (parameter1.equals("")) {
                    input1 = 0;
                    Toast.makeText(this, "Input 1 empty, assuming 0", Toast.LENGTH_SHORT).show();
                } else {
                    input1 = Double.parseDouble(parameter1);
                }

                if (parameter2.equals("")) {
                    input2 = 0;
                    Toast.makeText(this, "Input 2 empty, assuming 0", Toast.LENGTH_SHORT).show();
                } else {
                    input2 = Double.parseDouble(parameter2);
                }
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Error reading inputs", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "Error parsing user inputs");
            shouldShowAnswer = false;
            input1 = 0;
            input2 = 0;
        }

        switch (v.getId()) {
            case R.id.operation_add: {
                answer = input1 + input2;
                break;
            }
            case R.id.operation_sub: {
                answer = input1 - input2;
                break;
            }
            case R.id.operation_multi: {
                answer = input1 * input2;
                break;
            }
            case R.id.operation_div: {
                answer = input1 / input2;
                break;
            }
            case R.id.operation_exponent: {
                answer = Math.pow(input1, input2);
                break;
            }
            case R.id.operation_log: {
                answer = Math.log(input2) / Math.log(input1);
                Toast.makeText(this, "First input = base\nSecond input = parameter", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.operation_sin: {
                answer = Math.sin(input1);
                Toast.makeText(this, "Only input 1 used", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.operation_cos: {
                answer = Math.cos(input1);
                Toast.makeText(this, "Only input 1 used", Toast.LENGTH_LONG).show();
                break;
            }
            case R.id.operation_tan: {
                answer = Math.tan(input1);
                Toast.makeText(this, "Only input 1 used", Toast.LENGTH_LONG).show();
                break;
            }
            default: {
                answer = 0;
                Toast.makeText(this, "Click not implemented yet", Toast.LENGTH_LONG).show();
                Log.e(TAG, "View id: " + v.getId() + " click not implemented yet");
                shouldShowAnswer = false;
                break;
            }
        }

        if (shouldShowAnswer) {
            Intent intent = new Intent(this, AnswerActivity.class);
            intent.putExtra(EXTRA_CALCULATOR_ANSWER, String.valueOf(answer));
            startActivity(intent);
        }
    }

}