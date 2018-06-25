package com.cookandroid.universitylifeisworth;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

//그리드뷰를 상속하여 새로운 뷰를 정의한 클래스
//선택한 일자를 표시하는 기능등을 효율적으로 처리하기 위해 정의한 클래스
public class SubActivity2_CalendarMonthView extends GridView {

    // 일자 선택을 위해 직접 정의한 리스너 객체
    private SubActivity2_OnDataSelectionListener selectionListener;

    SubActivity2_CalendarMonthAdapter adapter;
    //생성자
    public SubActivity2_CalendarMonthView(Context context) {
        super(context);
        init();
    }

    ///생성자
    public SubActivity2_CalendarMonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    // 속성 초기화
    private void init() {
        setBackgroundColor(Color.GRAY);         //배경색=회색
        setVerticalSpacing(1);                  //setVerticalSpacint(int): 그리드 뷰의 셀간의 수직 간격 설정
        setHorizontalSpacing(1);                //setHorizontalSpacing(int):그리드 뷰의 셀 가느이 수평 간격 설정
        setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        //setStretchMode: 항목이 자신의 공간을 채우기 위해 뻗어되는 방법을 제어
        //STRETCH_COLUMN_WIDTH: 열을 뻗는다.

        // 칼럼의 갯수 설정(일주일)
        setNumColumns(7);

        // 그리드뷰의 원래 이벤트 처리 리스너 설정
        setOnItemClickListener(new OnItemClickAdapter());
    }

    // 어댑터 설정
    public void setAdapter(BaseAdapter adapter) {
        super.setAdapter(adapter);

        this.adapter = (SubActivity2_CalendarMonthAdapter) adapter;
    }

    //어댑터 객체 반환
    public BaseAdapter getAdapter() {
        return (BaseAdapter)super.getAdapter();
    }

    // 리스너 설정
    public void setOnDataSelectionListener(SubActivity2_OnDataSelectionListener listener) {
        this.selectionListener = listener;
    }

    // 리스너 객체 반환
    public SubActivity2_OnDataSelectionListener getOnDataSelectionListener() {
        return selectionListener;
    }

    class OnItemClickAdapter implements OnItemClickListener {

        //생성자
        public OnItemClickAdapter() {}

        public void onItemClick(AdapterView parent, View v, int position, long id) {

            if (adapter != null) {
                adapter.setSelectedPosition(position);
                adapter.notifyDataSetInvalidated();
            }

            if (selectionListener != null) {
                selectionListener.onDataSelected(parent, v, position, id);
            }
        }
    }
}

