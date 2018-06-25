package com.cookandroid.universitylifeisworth;


// 스케줄을 담기위한 클래스
public class SubActivity2_ScheduleItem {
    private String hour;
    private String min;
    private String msg;

    //생성자
    public SubActivity2_ScheduleItem(){}

    //생성자
    public SubActivity2_ScheduleItem(String inHour, String inMin, String inMsg){
        hour = inHour;
        min = inMin;
        msg = inMsg;
    }

    //입력받은 값에대해 get, set
    public String getHour(){
        return hour;
    }
    public void setHour(String hour){
        this.hour = hour;
    }
    public String getMin(){
        return min;
    }
    public void setMin(String min){
        this.min = min;
    }
    public String getMsg(){
        return msg;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
}


