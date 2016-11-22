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
import com.Jamesglasgow.Cw.models.RoadWorkRSSitem;

import java.util.List;

/**
 * Created by jamesglasgow on 22/11/2016.
 */

public class RoadListAdapter extends BaseAdapter {
    private List<RoadWorkRSSitem> items;
    private Context context;
    private LayoutInflater inflater;




    public RoadListAdapter(Context context, List<RoadWorkRSSitem> items) {
        inflater = LayoutInflater.from(context);
        this.items = items;
    }
    //  @Override
    public int getCount() {
        return items.size();
    }

    //  @Override
    public Object getItem(int position) {
        return items.get(position);
    }
    //@Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {
        TextView textView1;
        TextView textView2;

        LinearLayout lLayout;
        ListView Lview;
    }
    public View getView(int position, View convertView, ViewGroup parent) {


        RoadListAdapter.ViewHolder holder = null;
        // RelativeLayout ;
        if (convertView == null) {
            holder = new RoadListAdapter.ViewHolder();
            convertView = inflater.inflate(R.layout.list_road_layout, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.title_name_txtview);
            holder.textView2 = (TextView) convertView.findViewById(R.id.des_name_description);

            holder.Lview =(ListView) convertView.findViewById(R.id.Roadworklist);
            holder.lLayout = (LinearLayout) convertView.findViewById(R.id.RoadWork_layout);
            convertView.setTag(holder);
        } else {
            holder = (RoadListAdapter.ViewHolder) convertView.getTag();
        }

        holder.textView1.setText(items.get(position).getItemName());
        holder.textView2.setText(items.get(position).getItemDesc());



        return convertView;
    }

}

