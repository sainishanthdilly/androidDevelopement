package com.example.nishanth.inclass2b;

import android.content.Context;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class WeightEstimator extends AppCompatActivity {

    private Button b;
    private EditText editTextF,editTextI;
    private RadioGroup rg;
    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_estimator);

        b = (Button)findViewById(R.id.buttonCalculateWeight);
        editTextF = (EditText)findViewById(R.id.editTextHFeet);
        editTextI = (EditText)findViewById(R.id.editTextHInches);
        rg = (RadioGroup)findViewById(R.id.RadioGroupBMIRange);
        res = (TextView)findViewById(R.id.textViewResult);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Double low=0.0;
              Double high = 0.0;
              try {
                  double heightF = Double.parseDouble(editTextF.getText().toString());
                  double heightI = Double.parseDouble(editTextI.getText().toString());
                  double height = heightF * 12 + heightI;

                  if(heightF <= 0 || heightI <= 0)
                      throw new Exception();

                  if (rg.getCheckedRadioButtonId() == R.id.radioButtonLow) {

                      low = (18.5 * (height * height)) / 703.0;
                      double l = Math.round(low * 100) / 100;


                      res.setText("Your Weight Should be less than " + (l+"") + " lb");

                  } else if (rg.getCheckedRadioButtonId() == R.id.radioButtonMed) {
                      low = (18.5 * (height * height)) / 703.0;
                      high = (24.9 * (height * height)) / 703.0;
                      double l =  Math.round(low * 100d) / 100d;
                      double h = Math.round(high * 100d) / 100d;
                      res.setText("Your Weight Should be in between " + (l+"") + " to " + (h+"") + " lb");

                  } else if (rg.getCheckedRadioButtonId() == R.id.radioButtonL) {
                      low = (25.0 * (height * height)) / 703.0;
                      high = (29.9 * (height * height)) / 703.0;
                      double l =  Math.round(low * 100d) / 100d;
                      double h = Math.round(high * 100d) / 100d;
                      res.setText("Your Weight Should be in between " + (l+"") + " to " + (h+"") + " lb");
                  } else if (rg.getCheckedRadioButtonId() == R.id.radioButtonXL) {
                      high = (29.9 * (height * height)) / 703.0;
                      double h = Math.round(high * 100d) / 100d;
                      res.setText("Your Weight Should be greater than " + (h+"") + " lb");
                  } else {
                      Context context = getApplicationContext();
                      CharSequence text = "Invalid Inputs.";
                      int duration = Toast.LENGTH_SHORT;

                      Toast toast = Toast.makeText(context, text, duration);
                      toast.show();
                  }
              }
              catch (Exception e){
                  Context context = getApplicationContext();
                  CharSequence text = "Invalid Inputs.";
                  int duration = Toast.LENGTH_SHORT;

                  Toast toast = Toast.makeText(context, text, duration);
                  toast.show();
              }




            }
        });


    }
}
