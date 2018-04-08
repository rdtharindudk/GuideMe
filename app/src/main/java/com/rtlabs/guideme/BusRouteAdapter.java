package com.rtlabs.guideme;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by thari on 2/5/2018.
 */

public class BusRouteAdapter extends ArrayAdapter<BusRoute> {


    public BusRouteAdapter(Activity context, ArrayList<BusRoute> busRoutes) {

        super(context, 0, busRoutes);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        BusRoute busRoute = getItem(position);


        TextView busroutenameTextView = (TextView) listItemView.findViewById(R.id.busroutename);

        busroutenameTextView.setText(busRoute.getBusroutename());


        TextView busrouteTextView = (TextView) listItemView.findViewById(R.id.busroute);

        busrouteTextView.setText(busRoute.getBusroute());

        TextView destinationTextView = (TextView) listItemView.findViewById(R.id.destination);

        destinationTextView.setText(busRoute.getDestination());

        return listItemView;
    }


}
