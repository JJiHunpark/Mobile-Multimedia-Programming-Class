package com.cookandroid.universitylifeisworth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SubActivity5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_layout5);
    }

    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.bjwwd:
                intent = new Intent(SubActivity5.this,
                        SubActivity5_JWWeekDay.class);
                startActivity(intent);
                break;
            case R.id.bjwwe:
                intent = new Intent(SubActivity5.this,
                        SubActivity5_JWWeekEnd.class);
                startActivity(intent);
                break;
            case R.id.boiwd:
                intent = new Intent(SubActivity5.this,
                        SubActivity5_OidoWeekDay.class);
                startActivity(intent);
                break;
        }
    }
}
