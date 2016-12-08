package com.Jamesglasgow.Cw.Parsers;

import android.util.Log;

import com.Jamesglasgow.Cw.models.CarParkRSSitem;
import com.Jamesglasgow.Cw.models.RoadWorkRSSitem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

/**
 * Created by jamesglasgow on 23/11/2016.
 */

public class CarParkParser {
    //Open conection
    public LinkedList<CarParkRSSitem> ParseStart(String dataToParse){

        URL url1 = null;
        try {
            url1 = new URL(dataToParse);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.e("Checks", "Website " + url1);
        LinkedList<CarParkRSSitem> alist = null;
        alist=parseData(url1);
        return alist;
    }
    /**
     *This is the parser it parser the data when after open iternet conection and
     * searching inputstream it activates and add the data to the item
     */
    private LinkedList<CarParkRSSitem> parseData(URL dataToParse)
    {
        CarParkRSSitem Info=null;
        LinkedList<CarParkRSSitem> alist = null;

        try
        {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            //xpp.setInput( new StringReader ( dataToParse ) );
            xpp.setInput(getInputStream(dataToParse), "UTF_8");
            boolean stared=false;
            int eventType = xpp.getEventType();
            //boolean insideItem = false;
            alist  = new LinkedList<CarParkRSSitem>();
            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                // Found a start tag
                if(eventType == XmlPullParser.START_TAG)
                {
                    // Check which Tag we have
                    if (xpp.getName().equalsIgnoreCase("situation"))//channel
                    {
                        if(!stared){
                            stared=true;
                        }
                        // Log.e("MyTag", "Parsing error1");
                    }
                    else
                        // Check which Tag we have
                        if (xpp.getName().equalsIgnoreCase("situationRecord")&&stared)
                        {

                            Info = new CarParkRSSitem();
                            //
                            //String temp = xpp.getAttributeValue(null,"id");
                            //Info.setItemName(temp);

                        }
                        else if (xpp.getName().equalsIgnoreCase("latitude")&&stared)
                        {

                            String temp =xpp.nextText();

                            Info.setItemLat(temp);


                        }else if (xpp.getName().equalsIgnoreCase("longitude")&&stared)
                        {
                            String temp =xpp.nextText();
                            Info.setItemLong(temp);

                        }
                        else if (xpp.getName().equalsIgnoreCase("carParkIdentity")&&stared)
                        {
                            String temp =xpp.nextText();
                            Info.setItemName(temp);
                            Log.e("Park",""+temp);

                        }else if (xpp.getName().equalsIgnoreCase("carParkOccupancy")&&stared)
                        {
                            String temp =xpp.nextText();
                            Info.setitemPercent(temp);

                        }
                        else if (xpp.getName().equalsIgnoreCase("occupiedSpaces")&&stared)
                        {
                            String temp =xpp.nextText();
                            Info.setItemOccupied(temp);

                        }
                        else if (xpp.getName().equalsIgnoreCase("totalCapacity")&&stared)
                        {
                            String temp =xpp.nextText();
                            Info.setitemCapacity(temp);

                        }

                }else
                if(eventType == XmlPullParser.END_TAG)
                {
                    if (xpp.getName().equalsIgnoreCase("situation"))
                    {
                        //Log.e("MyTag","widget is " + widget.toString());
                        alist.add(Info);
                    }
                    else
                    if (xpp.getName().equalsIgnoreCase("d2LogicalModel"))
                    {
                        int size;
                        size = alist.size();
                        //Log.e("MyTag", "channel size is " + size);
                    }
                }

                // Get the next event
                eventType = xpp.next();

            } // End of while
        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag", "Parsing error " + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag", "IO error during parsing");
        }
        return alist;

    }

    /**
     * Opens Internet conection
     */
    public InputStream getInputStream(URL url) {
        try {
            return url.openConnection().getInputStream();
        } catch (IOException e) {
            return null;
        }

    }



}

