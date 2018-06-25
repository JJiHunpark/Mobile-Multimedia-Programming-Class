package com.cookandroid.universitylifeisworth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

//하루 계획표
public class SubActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_layout1);
    }

    public void onClick(View view){
        Intent intent = null;
        switch (view.getId()) {
            case R.id.homebtn:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kpu.ac.kr"));
                break;
            case R.id.phonebtn:
                intent = new Intent(SubActivity1.this,
                        SubActivity6_Contect.class);
                break;
            case R.id.busbtn:
                intent = new Intent(SubActivity1.this,
                        SubActivity5.class);
                break;
            case R.id.mapbtn:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.kpu.ac.kr/kpu/index.html"));
                break;
        }
        if(intent != null) {
            startActivity(intent);
        }
    }
}
