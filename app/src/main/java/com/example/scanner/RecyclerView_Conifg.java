//package com.example.scanner;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class RecyclerView_Conifg {
//    private Context mContext;
//    private ProductAdapter mProcutsAdapetr;
//
//    public void setConfig(RecyclerView recyclerView, Context context, List<Product> products, List<String> keys){
//        mContext = context;
//        mProcutsAdapetr = new ProductAdapter(products, keys);
//        recyclerView.setLayoutManager(new LinearLayoutManager(context));
//        recyclerView.setAdapter(mProcutsAdapetr);
//    }
//
//    class ProductItemView extends RecyclerView.ViewHolder{
//        private TextView mName;
//        private TextView mCode;
//        private TextView mTax;
//
//        private String key;
//
//        public ProductItemView(ViewGroup parent) {
//            super(LayoutInflater.from(mContext).inflate(R.layout.alts_list_item, parent, false));
//
//            mCode = itemView.findViewById(R.id.ProductCode);
//            mName = itemView.findViewById(R.id.ProductName);
//            mTax = itemView.findViewById(R.id.ProductPrice);
//
//        }
//        public void bind(Product product, String key){
//            mCode.setText(product.getBarcode().toString());
//            mName.setText(product.getProductname());
//            mTax.setText(product.getPinktax().toString());
//            this.key = key;
//        }
//    }
//
//    class ProductAdapter extends RecyclerView.Adapter<ProductItemView>{
//
//        private List<Product> mProductList;
//        private List<String> mKeys;
//
//        public ProductAdapter(List<Product> mProductList, List<String> mKeys) {
//            this.mProductList = mProductList;
//            this.mKeys = mKeys;
//        }
//
//        @Override
//        public ProductItemView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            return new ProductItemView(parent);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ProductItemView holder, int position) {
//            holder.bind(mProductList.get(position), mKeys.get(position));
//        }
//
//        @Override
//        public int getItemCount() {
//            return mProductList.size();
//        }
//    }
//}
