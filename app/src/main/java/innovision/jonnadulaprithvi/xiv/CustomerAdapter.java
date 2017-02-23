package innovision.jonnadulaprithvi.xiv;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jonnadula Prithvi on 22-Oct-16.
 */
public class CustomerAdapter extends ArrayAdapter<String> {
    private String[] names;
    private Integer[] imageid;
    private Activity context;
    private String[] posts;



    public CustomerAdapter(Activity context, String[] names,String[] posts, Integer[] imageid) {
        super(context, R.layout.contact_list_custom, names);
        this.context = context;
        this.names = names;
        this.imageid = imageid;
        this.posts=posts;


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.contact_list_custom, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewPost=(TextView)listViewItem.findViewById(R.id.textViewPost);
        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.imageView);

        textViewName.setText(names[position]);
        imageView.setImageResource(imageid[position]);
        textViewPost.setText(posts[position]);
        return  listViewItem;
    }
}
