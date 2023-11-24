package com.geeks.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private double operandOne;
    private double operandTwo;
    private double result;
    private String stringResult;
    private boolean operationPressed;
    private boolean resultPressed;
    private Operations operation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.result_text_view);
        onACClick(null);
    }

    public void onDigitClick(View view) {
        if (view instanceof MaterialButton) {
            if (((MaterialButton) view).getText().toString().equals("+/-") && !resultTextView.getText().toString().equals("0")) {
                if (!resultTextView.getText().toString().contains("-")) {
                    resultTextView.setText("-" + resultTextView.getText().toString());
                } else {
                    resultTextView.setText(resultTextView.getText().toString().replace("-", ""));
                }
            } else if (((MaterialButton) view).getText().toString().equals(".")) {
                if (!resultTextView.getText().toString().contains(".")) {
                    resultTextView.append(((MaterialButton) view).getText().toString());
                }
            } else if (resultTextView.getText().toString().equals("0") || operationPressed || resultPressed) {
                resultTextView.setText(((MaterialButton) view).getText().toString());
            } else {
                resultTextView.append(((MaterialButton) view).getText().toString());
            }
            operationPressed = false;
            resultPressed = false;
        }
    }

    public void onResultClick(View view) {
        if (!resultPressed) operandTwo = Double.parseDouble(resultTextView.getText().toString());
        else operandOne = result;
        if (operation == Operations.SUM) result = operandOne + operandTwo;
        else if (operation == Operations.SUBTRACT) result = operandOne - operandTwo;
        else if (operation == Operations.MULTIPLY) result = operandOne * operandTwo;
        else if (operation == Operations.DIVIDE) result = operandOne / operandTwo;
        operandOne = result;
        stringResult = String.valueOf(result);
        if (stringResult.endsWith(".0")) stringResult = stringResult.replace(".0", "");
        resultTextView.setText(stringResult);
        operationPressed = false;
        resultPressed = true;
    }

    public void onACClick(View view) {
        resultTextView.setText("0");
        operandOne = 0;
        operandTwo = 0;
        operation = Operations.NON;
        operationPressed = false;
        resultPressed = false;
    }

    public void onOperationClick(View view) {
        if (view instanceof MaterialButton) {
            if (((MaterialButton) view).getText().toString().equals("+"))
                operation = Operations.SUM;
            else if (((MaterialButton) view).getText().toString().equals("-"))
                operation = Operations.SUBTRACT;
            else if (((MaterialButton) view).getText().toString().equals("X"))
                operation = Operations.MULTIPLY;
            else if (((MaterialButton) view).getText().toString().equals("/"))
                operation = Operations.DIVIDE;
            operandOne = Double.parseDouble(resultTextView.getText().toString());
            operationPressed = true;
            resultPressed = false;
        }
    }

    public void onPercentClick(View view) {

    }
}