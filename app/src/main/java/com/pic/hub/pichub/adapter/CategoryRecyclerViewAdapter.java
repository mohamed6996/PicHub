package com.pic.hub.pichub.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.pic.hub.pichub.R;
import com.pic.hub.pichub.onRecyclerViewClickListener;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder> {
    List<Integer> pic_list;
    String[] pic_list_names;
    Context context;
    onRecyclerViewClickListener listener;

    public CategoryRecyclerViewAdapter(List<Integer> pic_list, String[] pic_list_names, Context context, onRecyclerViewClickListener listener) {
        this.pic_list = pic_list;
        this.context = context;
        this.listener = listener;
        this.pic_list_names = pic_list_names;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view_item);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(pic_list.get(position), pic_list_names[position]);
    }

    @Override
    public int getItemCount() {
        return pic_list.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.category_img);
            textView = itemView.findViewById(R.id.category_name);

        }

        public void bind(Integer integer, String catName) {
            Glide.with(context)
                    .load(integer)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);

            textView.setText(catName);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(getAdapterPosition());
        }
    }
}
