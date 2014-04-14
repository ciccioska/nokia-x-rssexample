package info.francescoscalise.rssexample.app;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private String url="https://api.flickr.com/services/feeds/photos_public.gne";
    List<Entry> items = null;
    FlickrAdapter adapter;

    public MainActivity( ) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView list = (ListView)findViewById(R.id.listView);
        adapter = new FlickrAdapter(this, R.layout.row_item, new ArrayList<Entry>());
        list.setAdapter(adapter);

        AsyncData dt =  new AsyncData();
        dt.execute(url);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class AsyncData extends AsyncTask<String, Void, List<Entry>> {


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(List<Entry> res){
            //super.onPostExecute(res);
            adapter.addAll(res);

        }

        @Override
        protected List<Entry> doInBackground(String... params) {

            try {

                URL _url = new URL(params[0]);

                items  = FlickrParser.parse(_url.openStream());

            } catch (IOException e) {
                e.printStackTrace();
            }
            return items;

        }
    }

}
