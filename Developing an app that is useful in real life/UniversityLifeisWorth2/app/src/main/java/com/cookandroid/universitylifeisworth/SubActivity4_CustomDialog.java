package com.cookandroid.universitylifeisworth;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity4_CustomDialog  extends Dialog implements View.OnTouchListener{
    private EditText siteName, siteDate, siteTime1, siteTime2;
    private Button addOK, addCancel;
    private String _siteName;
    private String _siteDate;
    private int _siteTime1, _siteTime2;

    public SubActivity4_CustomDialog(Context context) {
        super(context);
    }

    protected void onCreate(Bundle sacedInstanceState){
        super.onCreate(sacedInstanceState);
        setContentView(R.layout.sub_layout4_customdialog);

        siteName=(EditText)findViewById(R.id.name);
        siteDate=(EditText)findViewById(R.id.date1);
        siteTime1=(EditText)findViewById(R.id.time1);
        siteTime2=(EditText) findViewById(R.id.time2);

        addOK=(Button)findViewById(R.id.addOK);
        addOK.setOnTouchListener(this);
        addCancel=(Button)findViewById(R.id.addCancel);
        addCancel.setOnTouchListener(this);
    }

    //과목명을 반환하는 메소드
    public String get_siteName(){return _siteName;}
    public String get_siteDate(){return _siteDate;}
    public int get_siteTime1(){return _siteTime1;}
    public int get_siteTime2(){return _siteTime2;}

    @Override
    public boolean onTouch(View v, MotionEvent event) {
//확인버튼을 눌렀을 경우
//입력한 값을 적절히 설정 후 다이어로그 닫음
        if(v==addOK){
            _siteName = siteName.getText().toString();               //과목명을 얻어옴
            _siteDate = siteDate.getText().toString();               //요일을 얻어옴
            _siteTime1 = Integer.parseInt(""+siteTime1.getText());  //교시1
            _siteTime2 = Integer.parseInt(""+siteTime2.getText());  //교시2
            dismiss();
        }
//취소버튼을 누를 경우
//다이얼로그 닫음
        else if(v == addCancel){
            cancel();
        }
        return false;
    }
}