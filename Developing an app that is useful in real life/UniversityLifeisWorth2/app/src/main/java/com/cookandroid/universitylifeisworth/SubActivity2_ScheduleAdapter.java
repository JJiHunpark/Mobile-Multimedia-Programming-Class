package com.cookandroid.universitylifeisworth;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class SubActivity2_ScheduleAdapter extends BaseAdapter {
    private Context sCotext;

    //스케줄 리스트
    ArrayList<SubActivity2_ScheduleItem> scheduleList;

    public SubActivity2_ScheduleAdapter(Context context) {
        sCotext = context;
        scheduleList = new ArrayList<SubActivity2_ScheduleItem>();
    }

    public void clear() {
        scheduleList.clear();
    }

    public void addItem(SubActivity2_ScheduleItem item) {
        scheduleList.add(item);
    }

    public void removeItem(SubActivity2_ScheduleItem item) {
        scheduleList.remove(item);
    }

    public void addAll(ArrayList<SubActivity2_ScheduleItem> items) {
        scheduleList.addAll(items);
    }

    public int getCount() {
        return scheduleList.size();
    }

    public Object getItem(int position) {
        return scheduleList.get(position);
    }

    public boolean isSelectable(int position) {
        return true;
    }

    public long getItemId(int position){
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        SubActivity2_ScheduleItemView itemView = null;

        if (convertView == null) {
            itemView = new SubActivity2_ScheduleItemView(sCotext);
        } else {
            itemView = (SubActivity2_ScheduleItemView) convertView;
        }

        SubActivity2_ScheduleItem curItem = (SubActivity2_ScheduleItem) scheduleList.get(position);
        itemView.setHour(curItem.getHour());        //시 set
        itemView.setMin(curItem.getMin());          //분 set
        itemView.setMsg(curItem.getMsg());          //일정 set

        return itemView;
    }
}
