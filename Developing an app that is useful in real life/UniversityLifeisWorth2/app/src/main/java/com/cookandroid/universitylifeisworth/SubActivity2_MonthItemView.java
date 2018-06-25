package com.cookandroid.universitylifeisworth;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

// 일자에 표시되는 텍스트뷰 정의
public class SubActivity2_MonthItemView  extends TextView {

    private SubActivity2_MonthItem item;

    //생성자
    public SubActivity2_MonthItemView(Context context) {
        super(context);
        init();
    }

    //생성자
    public SubActivity2_MonthItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    //캘린더 날짜에관한 배경색=흰색
    private void init() {
        setBackgroundColor(Color.WHITE);
    }


    public SubActivity2_MonthItem getItem() {
        return item;
    }

    public void setItem(SubActivity2_MonthItem item) {
        this.item = item;

        int day = item.getDay();
        if (day != 0) {                     //날짜(일)정보가 있다면
            setText(String.valueOf(day));   //날짜(일)입력
        } else {
            setText("");                    //공백
        }
    }
}

