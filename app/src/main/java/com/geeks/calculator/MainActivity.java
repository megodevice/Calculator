package com.geeks.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Activity;
import android.app.Notification;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.hardware.biometrics.BiometricManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.BufferedWriter;

public class MainActivity extends AppCompatActivity {

    private androidx.appcompat.widget.AppCompatTextView resultTextView;
    public static final String KEY1 = "key1";
    public static final String KEY2 = "key2";
    private AppCompatButton buttonGoToSecondActivity;
    private double operandOne;
    private double operandTwo;
    private double result;
    private boolean operationPressed;
    private boolean resultPressed;
    private boolean digitPressed;
    private Operations operation;
    String stringResult;
    ClipboardManager clipboard;
    ClipData clip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent().getBooleanExtra(KEY2, false)) finish();
        clipboard = (ClipboardManager) MainActivity.this.getSystemService(Context.CLIPBOARD_SERVICE);
        resultTextView = findViewById(R.id.result_text_view);
        buttonGoToSecondActivity = findViewById(R.id.button_go_to_second_activity);
        onACClick(null);
    }

    public void onDigitClick(View view) {
        if (view instanceof MaterialButton) {
            if (((MaterialButton) view).getText().toString().equals("+/-")) {
                if (!resultTextView.getText().toString().contains("-")) {
                    if (!resultTextView.getText().toString().equals("0")) {
                        resultTextView.setText("-" + resultTextView.getText().toString());
                    }
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
            digitPressed = true;
            buttonGoToSecondActivity.setVisibility(View.GONE);
        }
    }

    public void onResultClick(View view) {
        if (operation != Operations.NON) {
            if (!resultPressed)
                operandTwo = Double.parseDouble(resultTextView.getText().toString());
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
            digitPressed = false;
            resultPressed = true;
            buttonGoToSecondActivity.setVisibility(View.VISIBLE);
        }
    }

    public void onACClick(View view) {
        resultTextView.setText("0");
        stringResult = "";
        result = 0;
        operandOne = 0;
        operandTwo = 0;
        operation = Operations.NON;
        operationPressed = false;
        resultPressed = false;
        digitPressed = false;
        buttonGoToSecondActivity.setVisibility(View.GONE);
    }

    public void onOperationClick(View view) {
        if (view instanceof MaterialButton) {
            if (!resultPressed && !operationPressed && digitPressed) {
                onResultClick(null);
            }
            if (((MaterialButton) view).getText().toString().equals("+"))
                operation = Operations.SUM;
            else if (((MaterialButton) view).getText().toString().equals("-"))
                operation = Operations.SUBTRACT;
            else if (((MaterialButton) view).getText().toString().equals("*"))
                operation = Operations.MULTIPLY;
            else if (((MaterialButton) view).getText().toString().equals("/"))
                operation = Operations.DIVIDE;
            operandOne = Double.parseDouble(resultTextView.getText().toString());
            operationPressed = true;
            digitPressed = false;
            resultPressed = false;
            buttonGoToSecondActivity.setVisibility(View.GONE);
        }
    }

    public void onPercentClick(View view) {
        if (!resultPressed && !operationPressed && digitPressed && operation != Operations.NON) {
            if (operation == Operations.SUM)
                result = operandOne + (operandOne / 100 * Double.parseDouble(resultTextView.getText().toString()));
            else if (operation == Operations.SUBTRACT)
                result = operandOne - (operandOne / 100 * Double.parseDouble(resultTextView.getText().toString()));
            else if (operation == Operations.MULTIPLY)
                result = operandOne * (operandOne / 100 * Double.parseDouble(resultTextView.getText().toString()));
            else if (operation == Operations.DIVIDE)
                result = operandOne / (operandOne / 100 * Double.parseDouble(resultTextView.getText().toString()));

            stringResult = String.valueOf(result);
            if (stringResult.endsWith(".0")) stringResult = stringResult.replace(".0", "");
            resultTextView.setText(stringResult);
            operation = Operations.NON;
            buttonGoToSecondActivity.setVisibility(View.VISIBLE);
        }
    }

    public void onResultTextViewClick(View view) {
        clip = ClipData.newPlainText("", resultTextView.getText().toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "Результат скопирован", Toast.LENGTH_SHORT).show();
    }

    public void onGoButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(KEY1, resultTextView.getText().toString());
        startActivity(intent);
    }
}