package com.weebly.sarikcyber.recylerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by dustu on 10/25/2017.
 */

public class Custom_adapter extends BaseAdapter implements Filterable {

    Context c;
    ArrayList<UserAdd> arrayList;

    CustomFilter filter;
    ArrayList<UserAdd> filterList;

    public Custom_adapter(Context c, ArrayList<UserAdd> arrayList) {
        this.c = c;
        this.arrayList = arrayList;
        this.filterList = arrayList;
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

    @Override
    public Filter getFilter() {

        if( filter == null){

            filter = new CustomFilter();
        }
        return filter;
    }

    ///INNER_CLASS

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0){

                ///CONSTRAINT TO UPPER CASE

                constraint = constraint.toString().toUpperCase();

                ArrayList<UserAdd> filtersarray = new ArrayList<>();

                for (int i = 0; i < filterList.size(); i++){

                    ///SEARCHING TITLE

                    if ( filterList.get(i).getLocation().toUpperCase().contains(constraint) ){

                        UserAdd ua = new UserAdd(filterList.get(i).getTitle(),filterList.get(i).getLocation(),filterList.get(i).getDateofrent());
                        filtersarray.add(ua);


                    }
                }
                results.count = filtersarray.size();
                results.values = filtersarray;
            }
            else
            {
                results.count = filterList.size();
                results.values = filterList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            arrayList = (ArrayList<UserAdd>) results.values;
            notifyDataSetChanged();

        }
    }
}
