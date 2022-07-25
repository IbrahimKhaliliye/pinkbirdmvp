package com.example.scanner;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ProductArrayAdapter extends ArrayAdapter<Product> {
    private Context context;
    private int resource;


    public ProductArrayAdapter(@NonNull Context context, int resource, ArrayList<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(this.context).inflate(this.resource, parent, false);
        }
        // Code goes here
        Product product = getItem(position);
        if (product!=null){
           // TextView user_name = convertView.findViewById(R.id.user_name);
           // TextView user_email = convertView.findViewById(R.id.user_email);
            //user_name.setText(user.getName());
            //user_email.setText(user.getEmail());
        }
        return convertView;

    }
}

