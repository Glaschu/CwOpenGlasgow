package com.Jamesglasgow.Cw.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.Jamesglasgow.Cw.OpenGlasgow.R;
import com.Jamesglasgow.Cw.models.NewsRSSitem;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by jamesglasgow on 21/11/2016.
 */

public class NewsListAdapter extends BaseAdapter {
    private List<NewsRSSitem> items;
    private Context context;
    private LayoutInflater inflater;

    static int Load =0;
    static boolean Searching =false;



    public NewsListAdapter(Context context, List<NewsRSSitem> items) {
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
        ImageView ImageView1;
        LinearLayout lLayout;
        ListView Lview;
    }
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;
        // RelativeLayout ;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_news_layout, null);
            holder.textView1 = (TextView) convertView.findViewById(R.id.title_name_txtview);
            holder.textView2 = (TextView) convertView.findViewById(R.id.des_name_description);
            //holder.ImageView1 = (ImageView) convertView.findViewById(R.id.News_icon_imgview);
            holder.Lview =(ListView) convertView.findViewById(R.id.Newslist);
            holder.lLayout = (LinearLayout) convertView.findViewById(R.id.News_layout);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView1.setText(items.get(position).getItemName());
        holder.textView2.setText(items.get(position).getItemDesc());



        return convertView;
    }

}
