package com.example.nishanth.currencyconverter;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rg1,rg2;
    private TextView result;
    private EditText input;
    final double USDTOAUD = 1.34;
    final double USDTOCAD = 1.32;
    final double USDTOINR = 68.14;
    final double USDTOGBP = 0.83;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         rg1 = (RadioGroup) findViewById(R.id.radioGroup1);
         rg2 = (RadioGroup) findViewById(R.id.radioGroup2);

        Button b = (Button) findViewById(R.id.buttonconvert);
        result = (TextView)findViewById(R.id.textViewDecimal1);
        input = (EditText)findViewById(R.id.editTextDecimal1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double amounttodivide;
                double amountconvert;
                double inp;
                if(rg1.getCheckedRadioButtonId() == R.id.radioButtonAUD)
                    amounttodivide = 1.34;
                else if(rg1.getCheckedRadioButtonId() == R.id.radioButtonCAD)
                    amounttodivide = 1.32;
                else
                    amounttodivide = 68.14;


                 if(rg2.getCheckedRadioButtonId() == R.id.radioButtonUSD)
                     amountconvert = 1.0;
                 else
                     amountconvert = 0.83;

                try {
                    inp = Double.parseDouble(String.valueOf(input.getText()));

                    result.setText(Double.toString(inp*amountconvert/amounttodivide));
                }
                catch (Exception e){
                    Context context = getApplicationContext();
                    CharSequence text = "Invalid Input";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }

            }
        });

    }

    public void onClear(View v){
        input.setText("");
        input.setHint(getString(R.string.input_number));

        rg1.check(R.id.radioButtonAUD);
        rg2.check(R.id.radioButtonUSD);
        result.setText("");


    }
}
