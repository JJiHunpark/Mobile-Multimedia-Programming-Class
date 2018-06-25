package com.cookandroid.universitylifeisworth;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SubActivity4 extends Activity {
    private int gridHeight, gridWidth;
    private RelativeLayout layout;
    private RelativeLayout tmpLayout;
    private static boolean isFirst = true;

    //다이어로그
    Button timeTableAdd;
    SubActivity4_CustomDialog addDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_layout4);
//시간표 관련 객체
        tmpLayout = (RelativeLayout)findViewById(R.id.Monday);

//커스텀다이로그관련 객체
        timeTableAdd=(Button)findViewById(R.id.tableadd);
        addDialog=new SubActivity4_CustomDialog(SubActivity4.this);
        addDialog.setTitle("시간표 추가");

//커스텀 다이어로그의 추가버튼을 눌렀을 때의 동작
        addDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                Toast.makeText(getApplicationContext(),"추가",Toast.LENGTH_LONG).show();
                String text=addDialog.get_siteName();           //과목명
                String date=addDialog.get_siteDate();           //요일
                int Time1 = addDialog.get_siteTime1();          //교시1
                int Time2 = addDialog.get_siteTime2();          //교시2
                int i;
                switch (date){
                    case "월":
                        i=1;
                        addView(i,Time1,Time2,text);
                        break;
                    case "화":
                        i=2;
                        addView(i,Time1,Time2,text);
                        break;
                    case "수":
                        i=3;
                        addView(i,Time1,Time2,text);
                        break;
                    case "목":
                        i=4;
                        addView(i,Time1,Time2,text);
                        break;
                    case "금":
                        i=5;
                        addView(i,Time1,Time2,text);
                        break;
                }
            }
        });
//커스텀 다이어로그의 취소버튼을 눌렀을 때의 동작
        addDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(getApplicationContext(),"취소",Toast.LENGTH_LONG).show();
            }
        });

//커스텀다이어로그 띄우기
        timeTableAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                addDialog.show();
            }
        });
    }

    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(isFirst){
            isFirst = false;
            gridWidth = tmpLayout.getWidth();
            gridHeight = tmpLayout.getHeight()/9;
        }
    }

    //최종적으로 과목을 나타내주는 메소드(교시1, 교시2, 과목명)
    private TextView createTv(int start, int end, String text){
        TextView tv = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gridWidth,gridHeight*(end-start+1));
        tv.setY(gridHeight*(start-1));
        tv.setLayoutParams(params);
        tv.setGravity(Gravity.CENTER);
        tv.setText(text);               //출력
        return tv;
    }
    //시간표의 과목에대한 위치와 색을 지정하는 메소드 (요일, 교시1, 교시2, 과목)
    private void addView(int i, int start, int end, String text){
        TextView tv;
        switch (i){
            case 1:
                layout = (RelativeLayout)findViewById(R.id.Monday);
                break;
            case 2:
                layout = (RelativeLayout)findViewById(R.id.Tuesday);
                break;
            case 3:
                layout = (RelativeLayout)findViewById(R.id.Wednesday);
                break;
            case 4:
                layout = (RelativeLayout)findViewById(R.id.Thursday);
                break;
            case 5:
                layout = (RelativeLayout)findViewById(R.id.Friday);
                break;
        }
        tv = createTv(start, end, text);
        tv.setBackgroundColor(Color.argb(100, start*5, (start+end)*20,0));
        layout.addView(tv);
    }
}
