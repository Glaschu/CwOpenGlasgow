package com.Jamesglasgow.Cw.Parsers;

import android.util.Log;

import com.Jamesglasgow.Cw.models.WeatherRSSitem;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by jamesglasgow on 07/11/2016.
 */

public class WeatherRSSParser {
    private WeatherRSSitem RSSDataItem;

    public void setRSSDataItem(String sItemData)
    {
        RSSDataItem.setItemName(sItemData);
        RSSDataItem.setItemDesc(sItemData);
        RSSDataItem.setitemHumidity(sItemData);
        RSSDataItem.setIconId(sItemData);
        RSSDataItem.setitemSunRise(sItemData);
        RSSDataItem.setitemSunSet(sItemData);

    }

    public WeatherRSSitem getRSSDataItem()
    {
        return this.RSSDataItem;
    }

    public WeatherRSSParser()
    {
        this.RSSDataItem =  new WeatherRSSitem();
        setRSSDataItem(null);
    }

    public void parseRSSDataItem(XmlPullParser theParser, int theEventType)
    {
        try
        {
            while (theEventType != XmlPullParser.END_DOCUMENT)
            {
                // Found a start tag
                if(theEventType == XmlPullParser.START_TAG)
                {
                    // Check which Tag has been found
                    if (theParser.getName().equalsIgnoreCase("city"))
                    {
                        // Now just get the associated text
                        String temp =theParser.getAttributeValue(null,"name");
                        // store data in class
                        RSSDataItem.setItemName(temp);
                        //Log.e("cityInfo"," "+temp);
                    }
                    else
                        // Check which Tag we have
                        if (theParser.getName().equalsIgnoreCase("sun"))
                        {
                            // Now just get the associated text
                            String temp = theParser.getAttributeValue(null,"rise");
                            // store data in class
                            RSSDataItem.setitemSunRise(temp);
                            temp = theParser.getAttributeValue(null,"set");
                            RSSDataItem.setitemSunSet(temp);
                            //Log.e("temp"," "+ RSSDataItem.getitemSunRise()+" "+RSSDataItem.getitemSunSet());

                        }
                        else
                        // Check which Tag we have
                        if (theParser.getName().equalsIgnoreCase("temperature"))
                        {
                            // Now just get the associated text
                            String temp = theParser.getAttributeValue(null,"value");
                            // store data in class
                            RSSDataItem.setItemDesc(temp);
                            //Log.e("temp"," "+temp);

                        }
                        else
                            // Check which Tag we have
                            if (theParser.getName().equalsIgnoreCase("humidity"))
                            {
                                // Now just get the associated text
                                String temp = theParser.getAttributeValue(null,"value");
                                // store data in class
                                RSSDataItem.setitemHumidity(temp);
                                //Log.e("hum"," "+temp);
                            }
                            else
                                // Check which Tag we have
                                if (theParser.getName().equalsIgnoreCase("weather"))
                                {
                                    // Now just get the associated text
                                    String temp = theParser.getAttributeValue(null,"icon");
                                    // store data in class
                                    RSSDataItem.setIconId(temp);
                                    //Log.e("hum"," "+temp);
                                }
                }

                // Get the next event
                theEventType = theParser.next();

            } // End of while

        }
        catch (XmlPullParserException parserExp1)
        {
            Log.e("MyTag","Parsing error" + parserExp1.toString());
        }

        catch (IOException parserExp1)
        {
            Log.e("MyTag","IO error during parsing");
        }

    }

    public void parseRSSData(String RSSItemsToParse) throws MalformedURLException {
        URL rssURL = new URL(RSSItemsToParse);
        Log.e("WeatherParse",""+rssURL);
        InputStream rssInputStream;
        try
        {
            XmlPullParserFactory parseRSSfactory = XmlPullParserFactory.newInstance();
            parseRSSfactory.setNamespaceAware(true);
            XmlPullParser RSSxmlPP = parseRSSfactory.newPullParser();
            String xmlRSS = getStringFromInputStream(getInputStream(rssURL), "UTF-8");
            RSSxmlPP.setInput(new StringReader(xmlRSS));
            int eventType = RSSxmlPP.getEventType();

            parseRSSDataItem(RSSxmlPP,eventType);

        }
        catch (XmlPullParserException ae1)
        {
            Log.e("MyTag","Parsing error" + ae1.toString());
        }
        catch (IOException ae1)
        {
            Log.e("MyTag","IO error during parsing");
        }

        Log.e("MyTag","End document");
    }

    public InputStream getInputStream(URL url) throws IOException
    {
        return url.openConnection().getInputStream();
    }

    public static String getStringFromInputStream(InputStream stream, String charsetName) throws IOException
    {
        int n = 0;
        char[] buffer = new char[1024 * 4];
        InputStreamReader reader = new InputStreamReader(stream, charsetName);
        StringWriter writer = new StringWriter();
        while (-1 != (n = reader.read(buffer))) writer.write(buffer, 0, n);
        return writer.toString();
    }
}
