package com.cookandroid.universitylifeisworth;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

class SubActivity3_ListInfo {
    TextView writeDate;
    TextView memo;
}

public class SubActivity3_ListAdapter extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<SubActivity3_MemoInfo> listData;
    SubActivity3_ListInfo info;

    public SubActivity3_ListAdapter( Context context, ArrayList<SubActivity3_MemoInfo> data ) {
        inflater = LayoutInflater.from( context );
        this.context = context;
        listData = data;
    }

    public int getCount() {
        return listData.size();
    }

    public SubActivity3_MemoInfo getItem(int position) {
        return listData.get( position );
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertview, ViewGroup parent) {
        View v = convertview;

        Calendar cal = Calendar.getInstance();
        if( v == null ) {
            info = new SubActivity3_ListInfo();

            v = inflater.inflate( R.layout.sub_layout3_memo_list, null );

            info.writeDate = (TextView)v.findViewById( R.id.writedate );

            cal.setTimeInMillis( listData.get( position ).writeDate );
            info.writeDate.setText( cal.get( Calendar.YEAR ) + "년 " + (cal.get( Calendar.MONTH ) + 1 ) + "월 " + cal.get( Calendar.DAY_OF_MONTH ) + "일" );
            info.memo = (TextView)v.findViewById( R.id.memostring );
            info.memo.setText( listData.get( position ).memo );

            v.setTag( info );
        }
        else if( ((SubActivity3_ListInfo)v.getTag()).memo.getText().toString() != listData.get( position ).memo ) {
            info = new SubActivity3_ListInfo();
            v = inflater.inflate( R.layout.sub_layout3_memo_list, null );
            info.writeDate = (TextView)v.findViewById( R.id.writedate );
            cal.setTimeInMillis( listData.get( position ).writeDate );
            info.writeDate.setText( cal.get( Calendar.YEAR ) + "년 " + ( cal.get( Calendar.MONTH ) + 1 ) + "월 " + cal.get( Calendar.DAY_OF_MONTH ) + "일" );
            info.memo = (TextView)v.findViewById( R.id.memostring );
            info.memo.setText( listData.get( position ).memo );
            v.setTag( info );
        }
        return v;
    }

    public void setArrayList(ArrayList<SubActivity3_MemoInfo> arrays) {
        listData = arrays;
    }

    public ArrayList<SubActivity3_MemoInfo> getArrayList() {
        return listData;
    }
}

