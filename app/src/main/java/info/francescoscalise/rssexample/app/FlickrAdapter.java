package info.francescoscalise.rssexample.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Francesco on 07/04/14.
 */
public class FlickrAdapter extends ArrayAdapter<Entry> {

    private int resource;
    private LayoutInflater inflater;

    public FlickrAdapter(Context context, int resId, List<Entry> objects)
    {
        super(context,resId, objects);
        resource = resId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Entry item = getItem(position);
        EntryHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(resource, parent, false);
            holder = new EntryHolder();
            holder.titleView = (TextView) convertView.findViewById(R.id.title);
            holder.authorView = (TextView) convertView.findViewById(R.id.author);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (EntryHolder) convertView.getTag();
        }

        holder.titleView.setText(item.title);
        holder.authorView.setText(item.author);
        Picasso.with(getContext()).load(item.link).centerCrop().resize(80,80).into(holder.imageView);
        holder.authorView.setText(item.author);

        return convertView;
    }

    public static class EntryHolder{
        TextView titleView;
        TextView authorView;
        ImageView imageView;
    }

}
