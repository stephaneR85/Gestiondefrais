package com.gestiondefrais.stephanerafflin.gestiondefrais;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by stephane.rafflin on 30/09/2017.
 */

public class ItemAdapter extends ArrayAdapter
    {
        LayoutInflater mInflater;
        Context context;
        List<Frais> data;

       public ItemAdapter(Context context, List<Frais> data)
       {
           super(context, R.layout.item_view, data);
           this.context = context;
           this.data = data;
           this.mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       }

        @Override
        public View getView (int position, View item_view, ViewGroup parent) {
            View v = item_view;
            v = mInflater.inflate(R.layout.item_view, parent, false);
            TextView col1 = (TextView) v.findViewById(R.id.col1);
            TextView col2 = (TextView) v.findViewById(R.id.col2);
            TextView col3 = (TextView) v.findViewById(R.id.col3);
            if (data != null) {
                ConvertDate cd = new ConvertDate();
                String dateS = cd.convertLongToString(data.get(position).getDate());
                col1.setText(dateS);
                col2.setText((String) data.get(position).getType());
                col3.setText(Double.toString(data.get(position).getMontant()));
            }
            return v;
        }
}
