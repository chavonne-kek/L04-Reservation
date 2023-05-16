package com.example.l04reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etName,etNo,etPax;
    DatePicker dp;
    TimePicker tp;
    RadioGroup rg;
    Button btnReset,btnConfirm;
    TextView tvDisplay,tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.editTextName);
        etNo = findViewById(R.id.editTextPhone);
        etPax = findViewById(R.id.editTextPax);
        dp = findViewById(R.id.datePicker);
        tp = findViewById(R.id.timePicker);
        rg = findViewById(R.id.radioGroup);
        btnConfirm = findViewById(R.id.buttonConfirm);
        btnReset = findViewById(R.id.buttonReset);
        tvDisplay = findViewById(R.id.textViewDisplay);
        tvError = findViewById(R.id.textViewError);

        dp.updateDate(2023,05,01);
        tp.setHour(19);
        tp.setMinute(30);

        btnConfirm.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {

            if (etName.getText().toString().trim().length() != 0 && etNo.getText().toString().trim().length() != 0 &&
                etPax.getText().toString().trim().length() != 0){
            String name = etName.getText().toString();
            String no = etNo.getText().toString();
            String pax = etPax.getText().toString();



                int hour = tp.getHour();
                int minute = tp.getMinute();
                String time = String.format("%d:%d", hour, minute);
                if (minute < 10) {
                    time = String.format("%d:0%d", hour, minute);
                }

                String date = "";
                int day = dp.getDayOfMonth();
                int month = dp.getMonth() + 1;
                int year = dp.getYear();
                if (day < 10 && month < 10) {
                    date = String.format("0%d/0%d/%d", day, month, year);
                } else if (day < 10) {
                    date = String.format("0%d/%d/%d", day, month, year);
                } else if (month < 10) {
                    date = String.format("%d/0%d/%d", day, month, year);
                }

                int radioID = rg.getCheckedRadioButtonId();
                String type = "";
                if (radioID != -1) {
                    if (rg.getCheckedRadioButtonId() == R.id.radioNoSmoking) {
                        type = "No Smoking";
                    } else {
                        type = "Smoking";
                    }

                String output = String.format("Name: %s \nNumber: %s \nPax: %s \nDate: %s \nTime: %s \nTable Type: %s",
                        name, no, pax, date, time, type);

                Toast.makeText(getApplicationContext(), output, Toast.LENGTH_LONG).show();
                tvDisplay.setText(output);


                }else {
                    Toast.makeText(getApplicationContext(), "Table type not selected", Toast.LENGTH_LONG).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "Particulars not filled", Toast.LENGTH_LONG).show();
            }
        }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dp.updateDate(2023,05,04);
                tp.setHour(19);
                tp.setMinute(30);
                tvDisplay.setText("");
                tvError.setText("");
                etName.setText("");
                etNo.setText("");
                etPax.setText("");
                rg.clearCheck();
            }
        });

    }
}