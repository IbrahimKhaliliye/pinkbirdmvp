package com.example.scanner;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Activity mContext;
    List<Products> productsList;

    public ListAdapter(Activity mContext, List<Products> productsList){
        super(mContext,R.layout.list_item,productsList);
        this.mContext = mContext;
        this.productsList = productsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = mContext.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item,null,true);

        TextView tvProdName = listItemView.findViewById(R.id.tvProdName);
        TextView tvBarcode = listItemView.findViewById(R.id.tvBarcode);
        TextView tvCategory = listItemView.findViewById(R.id.tvCategory);

        Products products = productsList.get(position);

        tvProdName.setText(products.getProdname());
        tvBarcode.setText(products.getBarcode());
        tvCategory.setText(products.getCategory());

        return listItemView;
    }
}