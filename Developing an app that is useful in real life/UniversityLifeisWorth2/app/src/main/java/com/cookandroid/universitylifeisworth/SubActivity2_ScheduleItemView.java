package com.cookandroid.universitylifeisworth;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SubActivity2_ScheduleItemView extends LinearLayout {
    private Context sContext;
    private TextView hour;
    private TextView min;
    private TextView msg;

    public SubActivity2_ScheduleItemView(Context context){
        super(context);
        sContext = context;

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sub_layout2_schdule_list, this, true);

        hour = (TextView)findViewById(R.id.list_hour);       //리스트-시
        min = (TextView)findViewById(R.id.list_min);         //리스트-분
        msg = (TextView)findViewById(R.id.list_msg);         //리스트-일정
    }

    // Text출력
    public void setHour(String hour_str){
        hour.setText(hour_str);
    }
    public void setMin(String min_str){
        min.setText(min_str);
    }
    public void setMsg(String msg_str){
        msg.setText(msg_str);
    }
}
