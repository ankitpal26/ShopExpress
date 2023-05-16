package com.example.shopexpress.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopexpress.R;
import com.example.shopexpress.databinding.ItemCartBinding;
import com.example.shopexpress.databinding.QuantityDialogBinding;
import com.example.shopexpress.model.Product;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    Context context;
    ArrayList<Product> products;

    public  CartAdapter(Context context, ArrayList<Product> products){
        this.context = context;
        this.products = products;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart, parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = products.get(position);
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.image);

        holder.binding.name.setText(product.getName());
        holder.binding.price.setText( "INR" +product.getPrice());
        holder.binding.quantity.setText(product.getQuantity() + "item(s)");

       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @SuppressLint("ResourceAsColor")
           @Override
           public void onClick(View v) {
               QuantityDialogBinding quantityDialogBinding = QuantityDialogBinding.inflate(LayoutInflater.from(context));
               AlertDialog dialog = new AlertDialog.Builder(context)
                       .setView(quantityDialogBinding.getRoot())
                       .create();

               dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));

               quantityDialogBinding.productName.setText(product.getName());
               quantityDialogBinding.productStock.setText("Stock: " +product.getStock());
               quantityDialogBinding.quantity.setText(String.valueOf(product.getQuantity()));
               int stock = product.getStock();

               quantityDialogBinding.plusBtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                   }
               });
               quantityDialogBinding.minusBtn.setOnClickListener(new View.OnClickListener() { 
                   @Override
                   public void onClick(View v) {

                   }
               });
               quantityDialogBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                   }
               });

               dialog.show();
           }
       });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
    ItemCartBinding binding;
    public CartViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = ItemCartBinding.bind(itemView);
       }
    }
}
