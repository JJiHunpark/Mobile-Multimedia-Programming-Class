package com.cookandroid.universitylifeisworth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

//캘린더
public class SubActivity2 extends Activity {
    static final int INPUT_FORM_REQUEST_CODE = 100;

    GridView monthView;                         //그리드뷰-캘린더
    // 월별 캘린더 뷰 객체
    /*CalendarMonthView monthView;*/

    SubActivity2_CalendarMonthAdapter monthViewAdapter;     //월별 캘린더 어댑터
    TextView monthText;                         //월표시 텍스트
    Button input_btn;                           //일정추가 버튼
    int curYear;            //현재 년도
    int curMonth;           //현재 월
    int curPosition;

    ArrayList<SubActivity2_ScheduleItem> outPutSchedule;     //스케줄 리스트
    ListView schdulelist;
    SubActivity2_ScheduleAdapter scheduleAdapter;            //스케줄에 관한 어댑터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_layout2);


        // 월별 캘린더 뷰 객체 참조
        monthView = (GridView) findViewById(R.id.monthView);                    //캘린더
        monthViewAdapter = new SubActivity2_CalendarMonthAdapter(this);        //캘린더 어댑터 객체
        monthView.setAdapter(monthViewAdapter);                               //캘린더에 캘린더 어댑터 set

// 캘린더를 클릭했을 경우
        monthView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SubActivity2_MonthItem curItem = (SubActivity2_MonthItem) monthViewAdapter.getItem(position);
                int day = curItem.getDay();

                monthViewAdapter.setSelectedPosition(position);
                monthViewAdapter.notifyDataSetChanged();           //데이터 추가, 삭제

                curPosition = position;

                outPutSchedule = monthViewAdapter.getSchedule(position);
                if (outPutSchedule == null) {
                    outPutSchedule = new ArrayList<SubActivity2_ScheduleItem>();
                }
                scheduleAdapter.scheduleList = outPutSchedule;

                scheduleAdapter.notifyDataSetChanged();
            }
        });

        //월 출력
        monthText = (TextView) findViewById(R.id.monthText);    //월
        setMonthText();

        // 이전 월로 넘어가는 이벤트 처리
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();        //이전버튼을 눌렀을때의 동작실행
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

        // 다음 월로 넘어가는 이벤트 처리
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();           //다음버튼을 눌렀을 때의 동작
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });


        //일정 추가
        input_btn = (Button)findViewById(R.id.input_todo);
        input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInputSchduleForm();
            }
        });

        curPosition = -1;

        schdulelist = (ListView)findViewById(R.id.scheduleList);
        scheduleAdapter = new SubActivity2_ScheduleAdapter(this);
        schdulelist.setAdapter(scheduleAdapter);
    }



    // 월 표시 텍스트 설정
    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth+1) + "월");
    }

    // 일정추가 액티비티 호출
    private void showInputSchduleForm(){
        Intent intent = new Intent(getApplicationContext(), SubActivity2_ScheduleInputFormActivity.class);
        startActivityForResult(intent, INPUT_FORM_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(data != null){
            if(requestCode == INPUT_FORM_REQUEST_CODE){
                // ScheduleInputFormActivity에서 받아온 값들
                String hour = data.getStringExtra("hour");
                String min = data.getStringExtra("min");
                String msg = data.getStringExtra("msg");

                if(msg != null){
                    SubActivity2_ScheduleItem sItem = new SubActivity2_ScheduleItem(hour, min, msg);

                    if(outPutSchedule == null){
                        outPutSchedule = new ArrayList<SubActivity2_ScheduleItem>();
                    }
                    outPutSchedule.add(sItem);

                    monthViewAdapter.putSchedule(curPosition, outPutSchedule);

                    scheduleAdapter.scheduleList = outPutSchedule;
                    scheduleAdapter.notifyDataSetChanged();
                }
            }
        }
    }
}
