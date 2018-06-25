package com.cookandroid.universitylifeisworth;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

public class MainActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources res = getResources();
        TabHost tabHost = getTabHost();

        TabHost.TabSpec spec;

        Intent intent;

        intent = new Intent().setClass(this, SubActivity1.class);
        spec = tabHost.newTabSpec("Tab1").setIndicator("홈",
                res.getDrawable(android.R.drawable.ic_menu_agenda)).setContent(intent);
        tabHost.addTab(spec);

        /*
        intent = new Intent().setClass(this, SubActivity1.class);
        spec = tabHost.newTabSpec("Tab1");
        spec.setIndicator("홈",res.getDrawable(android.R.drawable.ic_menu_agenda));
        spec.setContent(intent);
        tabHost.addTab(spec);
        */

        intent = new Intent().setClass(this, SubActivity2.class);
        spec = tabHost.newTabSpec("Tab2").setIndicator("캘린더",
                res.getDrawable(android.R.drawable.ic_menu_edit)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SubActivity3.class);
        spec = tabHost.newTabSpec("Tab3").setIndicator("메모장",
                res.getDrawable(android.R.drawable.ic_menu_help)).setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, SubActivity4.class);
        spec = tabHost.newTabSpec("Tab4").setIndicator("시간표",
                res.getDrawable(android.R.drawable.ic_menu_help)).setContent(intent);
        tabHost.addTab(spec);
    }
}