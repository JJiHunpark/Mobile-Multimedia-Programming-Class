package com.cookandroid.universitylifeisworth;

//일자 정보를 담기 위한 클래스
public class SubActivity2_MonthItem {

    private int dayValue;

    public SubActivity2_MonthItem() {}

    public SubActivity2_MonthItem(int day) {
        dayValue = day;
    }

    public int getDay() {
        return dayValue;
    }

    public void setDay(int day) {
        this.dayValue = day;
    }
}