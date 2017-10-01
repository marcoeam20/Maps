package shopwise.freshcart_driver;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by macbook on 24/09/2017.
 */

public class NewOrdersListAdapter extends ArrayAdapter<NewOrdersInformation> {

    public Activity context;
    public int resource;
    public List<NewOrdersInformation> NOlistOrders;


    public NewOrdersListAdapter(@NonNull Activity context, @LayoutRes int resource, @NonNull List<NewOrdersInformation> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        NOlistOrders = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View v = inflater.inflate(resource, parent, false);

        TextView txtOrderId = (TextView) v.findViewById(R.id.txt_id);
        TextView txtUsername = (TextView) v.findViewById(R.id.txt_name);
        TextView txtAddress = (TextView) v.findViewById(R.id.txt_address);

        return v;
    }
}
