package com.cookandroid.universitylifeisworth;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SubActivity6_Contect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_layout6_contect);
    }

    public void onClick(View view){
        Intent intent = null;
        switch (view.getId()){
            case R.id.button1:
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(+82)03180410011"));
                break;
            case R.id.button2:
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(+82)03180410021"));
                break;
            case R.id.button3:
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(+82)03180410071"));
                break;
            case R.id.button4:
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(+82)03180410101"));
                break;
            case R.id.button5:
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(+82)03180410082"));
                break;
            case R.id.button6:
                intent = new Intent(Intent.ACTION_DIAL,
                        Uri.parse("tel:(+82)03180410510"));
                break;
        }
        if(intent != null) {
            startActivity(intent);
        }
    }
}
