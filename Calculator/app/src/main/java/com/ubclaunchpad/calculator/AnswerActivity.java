package com.ubclaunchpad.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {
    TextView mAnswerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent intent = getIntent();
        String answer = intent.getStringExtra(CalculatorActivity.EXTRA_CALCULATOR_ANSWER);
        mAnswerView = (TextView) findViewById(R.id.calculator_answer);
        mAnswerView.setText(answer);
    }
}