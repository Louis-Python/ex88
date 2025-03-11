package com.example.ex88;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText firstInput = findViewById(R.id.FirstInputNum);
        EditText secondInput = findViewById(R.id.SecondInputNum);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);
        Button buttonMul = findViewById(R.id.buttonMul);
        Button buttonDivide = findViewById(R.id.buttonDivide);
        TextView textResult = findViewById(R.id.textResult);


        View.OnClickListener calculateListener = v -> {
            String num1Str = firstInput.getText().toString().trim();
            String num2Str = secondInput.getText().toString().trim();

            if (num1Str.isEmpty() || num2Str.isEmpty()) {
                textResult.setText("Please enter both numbers");
                return;
            }

            double num1, num2;
            try {
                num1 = Double.parseDouble(num1Str);
                num2 = Double.parseDouble(num2Str);
            } catch (NumberFormatException e) {
                textResult.setText("Invalid input");
                return;
            }

            double result = 0;
            if (v.getId() == R.id.buttonPlus) {
                result = num1 + num2;
            } else if (v.getId() == R.id.buttonMinus) {
                result = num1 - num2;
            } else if (v.getId() == R.id.buttonMul) {
                result = num1 * num2;
            } else if (v.getId() == R.id.buttonDivide) {
                if (num2 == 0) {
                    textResult.setText("Cannot divide by zero");
                    return;
                }
                result = num1 / num2;
            }

            textResult.setText(String.valueOf(result));
        };


        buttonPlus.setOnClickListener(calculateListener);
        buttonMinus.setOnClickListener(calculateListener);
        buttonMul.setOnClickListener(calculateListener);
        buttonDivide.setOnClickListener(calculateListener);
    }
}