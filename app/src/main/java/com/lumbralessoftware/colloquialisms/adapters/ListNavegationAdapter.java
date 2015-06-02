package com.lumbralessoftware.colloquialisms.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lumbralessoftware.colloquialisms.R;

/**
 * Created by javiergonzalezcabezas on 2/6/15.
 */
public class ListNavegationAdapter extends ArrayAdapter<String> {
    private final Context mContext;
    private final String[] mValues;

    public ListNavegationAdapter(Context context, String[] values) {
        super(context, -1, values);
        this.mContext = context;
        this.mValues = values;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_navigation_list, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.row_navigation_list_textView);
        textView.setText(mValues[position]);


        return rowView;
    }
}
