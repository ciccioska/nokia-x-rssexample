package info.francescoscalise.rssexample.app;

import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Francesco on 07/04/14.
 */
public class FlickrParser {

    public static List<Entry> parse(InputStream is) {
        List<Entry> items = null;
        try {
            XMLReader xmlReader = SAXParserFactory.newInstance().newSAXParser()
                    .getXMLReader();
            FlickrHandler fHandler = new FlickrHandler();
            xmlReader.setContentHandler(fHandler);
            xmlReader.parse(new InputSource(is));
            items = fHandler.getItems();
        } catch (Exception ex) {
            Log.d("XML", "Parse failed");
        }

        return items;
    }
}
