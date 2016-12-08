package com.Jamesglasgow.Cw.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.Jamesglasgow.Cw.OpenGlasgow.R;
import com.Jamesglasgow.Cw.models.CarParkRSSitem;
import com.Jamesglasgow.Cw.models.RoadWorkRSSitem;

import java.util.List;

/**
 * Created by jamesglasgow on 04/12/2016.
 */

public class ParkingListAdapter extends BaseAdapter {
    private List<CarParkRSSitem> items;
    private Context context;
    private LayoutInflater inflater;




    public ParkingListAdapter(Context context, List<CarParkRSSitem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
    }
    //  @Override
    public int getCount() {
        return items.size();
    }

    /**
     * returns the item at postion
     */
    public Object getItem(int position) {
        return items.get(position);
    }

    /**
     * returns the item postion
     */
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {
        TextView textView1;
        TextView textView2;

        LinearLayout lLayout;
        ListView Lview;
    }
    /**
     * The getview inflates the listview with information we get from the item to fill in the listview
     */
    public View getView(int position, View convertView, ViewGroup parent) {


        ParkingListAdapter.ViewHolder holder = null;
        // RelativeLayout ;
        if (convertView == null) {
            holder = new ParkingListAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.list_park_layout, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.title_name_txtview);
            holder.textView2 = (TextView) convertView.findViewById(R.id.des_name_description);

            holder.Lview =(ListView) convertView.findViewById(R.id.CarParklist);
            holder.lLayout = (LinearLayout) convertView.findViewById(R.id.CarPark_layout);
            convertView.setTag(holder);
        } else {
            holder = (ParkingListAdapter.ViewHolder) convertView.getTag();
        }

        holder.textView1.setText(items.get(position).getItemName());
        holder.textView2.setText(items.get(position).GetCarParkSpace());



        return convertView;
    }

}