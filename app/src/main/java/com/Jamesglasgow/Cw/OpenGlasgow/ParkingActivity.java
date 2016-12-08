package com.Jamesglasgow.Cw.OpenGlasgow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.Jamesglasgow.Cw.Async.CarParkAsync;

import com.Jamesglasgow.Cw.adapters.ParkingListAdapter;
import com.Jamesglasgow.Cw.models.CarParkRSSitem;


import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

/**
 *
 */
public class ParkingActivity extends BaseActivity {
	LinkedList<CarParkRSSitem> alist = null;
	private ListView RssParkListView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		/**
		 * Adding our layout to parent class frame layout.
		 */
		getLayoutInflater().inflate(R.layout.item_3_layout, frameLayout);

		/**
		 * Setting title and itemChecked
		 */
		mDrawerList.setItemChecked(position, true);
		setTitle(listArray[position]);


        //place to store park info
		String RSSParkURL = "https://api.open.glasgow.gov.uk/traffic/carparks";
		CarParkAsync rssCarparkAsyncParse = new CarParkAsync(this,RSSParkURL);

        //start async of parking lots
		try {
			alist = rssCarparkAsyncParse.execute("").get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
        //display information using listadapter
        RssParkListView = (ListView) findViewById(R.id.CarParklist);
        RssParkListView.setAdapter(new ParkingListAdapter(this, alist));

        //to tell where on a list view the item was selected to get the neme
        RssParkListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView parent, View v, int position, long id){

                // DO STUFF HERE
                Log.e("ListView"," "+alist.get(position).getItemName());
                openMap(position);
            }
        });
	}
    //called affter list view is select
    //this save the information for put extra such as extra
    private void openMap(int Pos){
        Intent intent = new Intent(this, MapActivity.class);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_LAT",alist.get(Pos).getitemLat());
        extras.putString("EXTRA_LONG",alist.get(Pos).getitemLong());
        extras.putString("EXTRA_NAME",alist.get(Pos).getItemName());
        extras.putString("EXTRA_FILL",alist.get(Pos).GetCarParkSpace());
        intent.putExtras(extras);
        startActivity(intent);
    }

}
