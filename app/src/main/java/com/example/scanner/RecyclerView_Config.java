package com.example.scanner;

import android.content.Context;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.analytics.ecommerce.Product;

import java.util.List;


public class RecyclerView_Config {
    private Context mContext;
    private ProductsAdapter mProductsAdapter;
    public void setConfig(RecyclerView recyclerView, Context context, List<Product> products, List<String> keys){
        mContext = context;


    }

    class ProductItemView extends RecyclerView.ViewHolder {
        private TextView mproductname;
        private TextView mbarcode;
        private TextView mpinktax;

        private String key;

        public ProductItemView(ViewGroup parent){
            super (LayoutInflater.from(mContext).
            inflate(R.layout.product_list_item, parent, false));

            mproductname = (TextView) itemView.findViewById(R.id.productname_textView);
            mbarcode = (TextView) itemView.findViewById(R.id.barcode_textView);
            mpinktax = (TextView) itemView.findViewById(R.id.pinktax_textView3);
        }

        public void bind(Product product, String key){
            mproductname.setText(product.get);
            mbarcode.setText(product.getBarcode());
            mpinktax.setText(product.getPinktax());

            this.key = key;

        }
    }
    class ProductsAdapter extends RecyclerView.Adapter<ProductItemView>{
        private List<Product> mproductList;

        private List<String> mKeys;

        public ProductsAdapter(List<Product> mproductList, List<String> mKeys) {
            this.mproductList = mproductList;
            this.mKeys = mKeys;
        }

        public ProductsAdapter() {
            super();
        }

        @NonNull
        @Override
        public ProductItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ProductItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductItemView holder, int position) {
            holder.bind(mproductList.get(position), mKeys.get(position));
        }

        @Override
        public int getItemCount() {
            return mproductList.size();
        }
    }
}
