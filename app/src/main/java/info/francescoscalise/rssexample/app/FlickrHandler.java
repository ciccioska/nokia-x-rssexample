package info.francescoscalise.rssexample.app;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 07/04/14.
 */
public class FlickrHandler extends DefaultHandler {

        private List<Entry> items;
        private StringBuffer chars;
        private Entry item ;
        boolean bTitle = false;
        boolean bAuthor = false;
        boolean bItem = false;
        boolean bLink = false;

        public FlickrHandler() {
            items = new ArrayList<Entry>();
        }

        public List<Entry> getItems() {
            return items;
        }

        // Event Handlers
        public void startElement(String uri, String localName, String qName,Attributes attributes) throws SAXException {
            if (qName.equalsIgnoreCase("entry")) {
                item = new Entry();
                bItem = true;
            }
            if (qName.equalsIgnoreCase("title") && bItem) {
                bTitle = true;
            }
            if (qName.equalsIgnoreCase("name") && bItem) {
                bAuthor = true;
            }
            if (qName.equalsIgnoreCase("link") && bItem) {
                bLink = true;
                if(attributes.getLength() > 0){
                    if(attributes.getValue(0).equals("enclosure")){
                            item.link =  attributes.getValue(2);
                    }
                }

            }
        }

        public void characters(char[] ch, int start, int length) throws SAXException {
            if(bTitle)
            {
                item.title = new String(ch, start, length);
                bTitle = false;
            }
            if(bAuthor)
            {
                item.author = new String(ch, start, length);
                bAuthor = false;
            }

        }

        public void endElement(String uri, String localName, String qName)
                throws SAXException {

            // Check if looking for article, and if article is complete
            if (localName.equalsIgnoreCase("entry")) {
                items.add(item);
                bItem  =false;
                item = null;
            }
        }
    }

