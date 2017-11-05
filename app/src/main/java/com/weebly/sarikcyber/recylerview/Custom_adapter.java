package com.weebly.sarikcyber.recylerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dustu on 10/25/2017.
 */

public class Custom_adapter extends BaseAdapter {

    Context c;
    ArrayList<UserAdd> arrayList;

    public Custom_adapter(Context c, ArrayList<UserAdd> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        View v= inflater.inflate(R.layout.layout_custom_adapter, null);

        TextView tvtitle= (TextView)v.findViewById(R.id.customtitle);
        TextView tvlocation= (TextView)v.findViewById(R.id.customlocation);
        TextView tvdate= (TextView)v.findViewById(R.id.customdate);

        tvtitle.setText(arrayList.get(position).getTitle());
        tvlocation.setText(arrayList.get(position).getLocation());
        tvdate.setText(arrayList.get(position).getDateofrent());




        return v;
    }
}
