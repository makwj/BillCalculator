package com.example.billcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class CalculatePage extends AppCompatActivity {

    double totalBillAmt=0, totalPerPax=0, tipAmt=0, billPerPax=0, tipPerPax=0;
    int noOfPax=0,error=0;
    DecimalFormat currency = new DecimalFormat("$###,###.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_page);
        final TextView selTip = findViewById(R.id.selTip);
        Button tip1 = findViewById(R.id.tip1);
        tip1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipAmt=0.1;
                selTip.setText("Tip Selected: " + tipAmt*100 + "%");
            }
        });

        Button tip2 = findViewById(R.id.tip2);
        tip2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipAmt=0.2;
                selTip.setText("Tip Selected: " + tipAmt*100 + "%");
            }
        });

        Button tip3 = findViewById(R.id.tip3);
        tip3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipAmt=0;
                selTip.setText("Tip Selected: No Tip");
            }
        });

        Button calculate = findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            final TextView result1 = findViewById(R.id.result1);
            final TextView result2 = findViewById(R.id.result2);
            final TextView result3 = findViewById(R.id.result3);
            @Override
            public void onClick(View v) {
                EditText noPax = findViewById(R.id.noPax);
                EditText dollarField = findViewById(R.id.billAmt);
                if (noPax.getText().toString().isEmpty()){
                    error++;
                }else{
                    noOfPax = Integer.parseInt(noPax.getText().toString());
                }
                if (dollarField.getText().toString().isEmpty()){
                    error++;
                }else{
                    totalBillAmt = Double.parseDouble(dollarField.getText().toString());
                }
                if (error>0){
                    result1.setText(currency.format(0));
                    result2.setText(currency.format(0));
                    result3.setText(currency.format(0));
                    Toast.makeText(getApplicationContext(),
                            "Ensure there are no empty fields", Toast.LENGTH_SHORT).show();
                    error=0;
                }else {
                    billPerPax = (totalBillAmt / noOfPax);
                    tipPerPax = (totalBillAmt * tipAmt) / noOfPax;
                    totalPerPax = billPerPax + tipPerPax;
                    result1.setText(currency.format(totalPerPax));
                    result2.setText(currency.format(billPerPax));
                    if (tipAmt == 0){
                        result3.setText("No Tip");
                    }else {
                        result3.setText(currency.format(tipPerPax));
                    }
                }
            }
        });
    }
}