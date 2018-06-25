package com.cookandroid.universitylifeisworth;

import android.content.Context;
import android.graphics.Color;
import android.text.format.Time;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class SubActivity2_CalendarMonthAdapter extends BaseAdapter {

    public static final String TAG = "CalendarMonthAdapter";

    Context mContext;

    public static int oddColor = Color.rgb(225, 225, 225);          //흰색
    public static int headColor = Color.rgb(12, 32, 158);           //파란색

    private int selectedPosition = -1;

    private SubActivity2_MonthItem[] items;          //날짜에 관한 배열

    private int countColumn = 7;

    int mStartDay;
    int startDay;
    int curYear;
    int curMonth;

    int firstDay;
    int lastDay;

    Calendar mCalendar;
    boolean recreateItems = false;

    HashMap<String, ArrayList<SubActivity2_ScheduleItem>> scheduleHash;
    //HashMap: Map인터페이스의 한 종류로써 Key와 Value 값으로 데이터를 저장하는 형태
    //Map: Key(키), Value(값) 값으로 저장하는 List 형태의 조상

    public SubActivity2_CalendarMonthAdapter(Context context) {
        super();
        mContext = context;
        init();
    }

    public SubActivity2_CalendarMonthAdapter(Context context, AttributeSet attrs) {
        super();
        mContext = context;
        init();
    }

    private void init() {
        items = new SubActivity2_MonthItem[7 * 6];                   //일주일 6주

        mCalendar = Calendar.getInstance();
        recalculate();
        resetDayNumbers();

        scheduleHash = new HashMap<String, ArrayList<SubActivity2_ScheduleItem>>();
    }

    public void recalculate() {

        // mCalendar의 날짜를 현재 달의 1일로 설정하여 set
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);

        // get week day
        int dayOfWeek = mCalendar.get(Calendar.DAY_OF_WEEK);    //mCalender의 현재 요일을 얻어옴
        firstDay = getFirstDay(dayOfWeek);
        Log.d(TAG, "firstDay : " + firstDay);

        mStartDay = mCalendar.getFirstDayOfWeek();
        curYear = mCalendar.get(Calendar.YEAR);                 //년도
        curMonth = mCalendar.get(Calendar.MONTH);               //월
        lastDay = getMonthLastDay(curYear, curMonth);           //월의 마지막 날짜 매개변수(년도, 월)

        Log.d(TAG, "curYear : " + curYear + ", curMonth : " + curMonth + ", lastDay : " + lastDay);

        int diff = mStartDay - Calendar.SUNDAY - 1;
        startDay = getFirstDayOfWeek();
        Log.d(TAG, "mStartDay : " + mStartDay + ", startDay : " + startDay);

    }

    // 이전버튼을 눌렀을 때 이벤트
    public void setPreviousMonth() {
        mCalendar.add(Calendar.MONTH, -1);      //현재 월-1
        recalculate();

        resetDayNumbers();
        selectedPosition = -1;
    }

    // 다음버튼을 눌렀을 때 이벤트
    public void setNextMonth() {
        mCalendar.add(Calendar.MONTH, +1);       //현재 월+1
        recalculate();

        resetDayNumbers();
        selectedPosition = -1;
    }

    //날짜 설정
    public void resetDayNumbers() {
        for (int i = 0; i < 42; i++) {              //6*7
            // calculate day number
            int dayNumber = (i+1) - firstDay;
            if (dayNumber < 1 || dayNumber > lastDay) {
                dayNumber = 0;
            }

            // save as a data item
            items[i] = new SubActivity2_MonthItem(dayNumber);
        }
    }

    //달력에에서 첫번째 날짜의 위치를 얻어오는 메서드
    private int getFirstDay(int dayOfWeek) {            //현재 달의 1일의 날짜를 받아옴
        int result = 0;         //0으로 초기화
        if (dayOfWeek == Calendar.SUNDAY) {
            result = 0;
        } else if (dayOfWeek == Calendar.MONDAY) {
            result = 1;
        } else if (dayOfWeek == Calendar.TUESDAY) {
            result = 2;
        } else if (dayOfWeek == Calendar.WEDNESDAY) {
            result = 3;
        } else if (dayOfWeek == Calendar.THURSDAY) {
            result = 4;
        } else if (dayOfWeek == Calendar.FRIDAY) {
            result = 5;
        } else if (dayOfWeek == Calendar.SATURDAY) {
            result = 6;
        }

        return result;
    }


    // 지금년도
    public int getCurYear() {
        return curYear;
    }

    // 지금달
    public int getCurMonth() {
        return curMonth;
    }


    public int getNumColumns() {
        return 7;
    }

    public int getCount() {
        return 7 * 6;       //일주일 6주
    }

    //그리드 뷰 항목에 해당하는 값을 출력하여 돌려주는 메소드
    public Object getItem(int position) {
        return items[position];
    }

    //position을 이용하여 값을 돌려주는 메소드
    public long getItemId(int position) {
        return position;
    }

    //화면을 구성하는 메소드
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView(" + position + ") called.");

        SubActivity2_MonthItemView itemView;
        if (convertView == null) {
            itemView = new SubActivity2_MonthItemView(mContext);
        } else {
            itemView = (SubActivity2_MonthItemView) convertView;
        }

        //그리드뷰 객체 생성
        GridView.LayoutParams params = new GridView.LayoutParams(
                GridView.LayoutParams.MATCH_PARENT, parent.getHeight()/6);

        // calculate row and column
        int rowIndex = position / countColumn;          //position / 7
        int columnIndex = position % countColumn;       //position % 7

        Log.d(TAG, "Index : " + rowIndex + ", " + columnIndex);

        itemView.setItem(items[position]);          //postion set
        itemView.setLayoutParams(params);           //그리드뷰 set
        itemView.setPadding(2, 2, 2, 2);

        itemView.setGravity(Gravity.LEFT);          //Text 왼쪽으로 배치

        //날짜 Text Color
        if (columnIndex == 0) {              //일요일
            itemView.setTextColor(Color.RED);
        } else if(columnIndex == 6){        //토요일
            itemView.setTextColor(Color.BLUE);
        } else{                             //평일
            itemView.setTextColor(Color.BLACK);
        }


        ArrayList<SubActivity2_ScheduleItem> outList = getSchedule(position);
        boolean scheduleExist = false;            //스케줄 리스트 존재? false
        if (outList != null && outList.size() > 0) {    //스케줄 리스트 존재?
            scheduleExist = true;                           //true
        }

        //캘린더의 한 셀의 배경 색
        if (position == getSelectedPosition()) {                //날짜 클릭시
            itemView.setBackgroundColor(Color.LTGRAY);              //배경색 = 회색
        } else {
            if (scheduleExist) {                                //스케줄 리스트가 존재하면?
                itemView.setBackgroundColor(Color.YELLOW);          //배경색 = 노랑
            } else {                                            //존재X ?
                itemView.setBackgroundColor(Color.WHITE);           //배경색 = 흰색
            }
        }
        return itemView;
    }

    //주의 시작요일이 어떤 요일인지 알아내는 메소드
    public static int getFirstDayOfWeek() {
        int startDay = Calendar.getInstance().getFirstDayOfWeek();
        if (startDay == Calendar.SATURDAY) {
            return Time.SATURDAY;
        } else if (startDay == Calendar.MONDAY) {
            return Time.MONDAY;
        } else {
            return Time.SUNDAY;
        }
    }


    // 월의 마지막 날짜를 구함
    private int getMonthLastDay(int year, int month){
        switch (month) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return (31);
            case 3:
            case 5:
            case 8:
            case 10:
                return (30);
            default:
                if(((year%4==0)&&(year%100!=0)) || (year%400==0) ) {
                    return (29);   // 2월 윤년
                } else {
                    return (28);
                }
        }
    }


    /* set selected row*/
    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    /* get selected row*/
    public int getSelectedPosition() {
        return selectedPosition;
    }

    public ArrayList<SubActivity2_ScheduleItem> getSchedule(int year, int month, int position){
        String keyStr = year + "-" + month + "-" + position;

        ArrayList<SubActivity2_ScheduleItem> outList = scheduleHash.get(keyStr);
        return outList;
    }

    public ArrayList<SubActivity2_ScheduleItem> getSchedule(int position){
        String keyStr = curYear+ "-" + curMonth + "-" + position;
        //년도-월-position
        ArrayList<SubActivity2_ScheduleItem> outList = scheduleHash.get(keyStr);
        return outList;
    }

    public void putSchedule(int year, int month, int position, ArrayList<SubActivity2_ScheduleItem> aList){
        String keyStr = year + "-" + month + "-" + position;
        scheduleHash.put(keyStr, aList);
    }

    public void putSchedule(int position, ArrayList<SubActivity2_ScheduleItem> aList){
        String keyStr = curYear + "-" + curMonth + "-" + position;
        scheduleHash.put(keyStr, aList);
    }
}

