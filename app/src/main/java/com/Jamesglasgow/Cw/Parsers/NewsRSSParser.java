package com.Jamesglasgow.Cw.Parsers;

import android.util.Log;

import com.Jamesglasgow.Cw.models.NewsRSSitem;

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
 * Created by jamesglasgow on 14/11/2016.
 */

public class NewsRSSParser {
    private NewsRSSitem RSSDataItem;

    public void setRSSDataItem(String sItemData)
    {
        RSSDataItem.setItemName(sItemData);
        RSSDataItem.setItemDesc(sItemData);
        RSSDataItem.setIconId(sItemData);


    }

    public NewsRSSitem getRSSDataItem()
    {
        return this.RSSDataItem;
    }

    public NewsRSSParser()
    {
        this.RSSDataItem =  new NewsRSSitem();
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
                    if (theParser.getName().equalsIgnoreCase("title"))
                    {
                        // Now just get the associated text
                        String temp =theParser.nextText();
                        // store data in class
                        RSSDataItem.setItemName(temp);
                        Log.e("Newstitle"," "+temp);
                    }
                    else
                        // Check which Tag we have
                        if (theParser.getName().equalsIgnoreCase("Link"))
                        {
                            // Now just get the associated text
                            String temp = theParser.nextText();
                            // store data in class
                            RSSDataItem.setitemWeb(temp);


                        }
                        else
                            // Check which Tag we have
                            if (theParser.getName().equalsIgnoreCase("description"))
                            {
                                // Now just get the associated text
                                String temp = theParser.nextText();
                                // store data in class
                                RSSDataItem.setItemDesc(temp);

                            }

                            else
                                // Check which Tag we have
                                if (theParser.getName().equalsIgnoreCase("enclosure"))
                                {
                                    // Now just get the associated text
                                    String temp = theParser.getAttributeValue(null,"url");
                                    // store data in class
                                    RSSDataItem.setIconId(temp);
                                    Log.e("Icon"," "+temp);

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
