package com.in.trashcash.activity;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DialerKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.in.trashcash.R;

public class SmsActivity extends AppCompatActivity {

    private EditText edit_otp;
    private Button btn_otp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        btn_otp = (Button) findViewById(R.id.buttonotp);
        edit_otp = (EditText) findViewById(R.id.eTotp);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),"fonts/Raleway-Regular.ttf");
        btn_otp.setTypeface(custom_font);
        edit_otp.setTypeface(custom_font);


        edit_otp.setVisibility(View.GONE);
        edit_otp.setKeyListener(DialerKeyListener.getInstance());

        btn_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_otp.setVisibility(View.VISIBLE);
            }
        });


    }
}
