package com.example.asynctask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<CustomItem> {

    private int layoutResource;

    public CustomAdapter(Context context, int resource, List<CustomItem> objects) {
        super(context, resource, objects);
        layoutResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
        }

        CustomItem item = getItem(position);

        if (item != null) {
            TextView nameTextView = convertView.findViewById(R.id.nameTextView);
            TextView emailTextView = convertView.findViewById(R.id.emailTextView);

            if (nameTextView != null) {
                nameTextView.setText(item.getName());
            }

            if (emailTextView != null) {
                emailTextView.setText(item.getEmail());
            }
        }

        return convertView;
    }
}

