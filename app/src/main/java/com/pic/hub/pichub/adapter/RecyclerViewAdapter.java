package com.pic.hub.pichub.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.pic.hub.pichub.OnPicClickListener;
import com.pic.hub.pichub.R;
import com.pic.hub.pichub.model.Photo;

public class RecyclerViewAdapter extends PagedListAdapter<Photo, RecyclerViewAdapter.PhotoViewHolder> {
    private Context context;
    private OnPicClickListener listener;

    public RecyclerViewAdapter(Context context, OnPicClickListener listener) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.listener = listener;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view_item);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.PhotoViewHolder holder, int position) {
        holder.bind(getItem(position));
    }


    public class PhotoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        Gson gson = new Gson();
        Photo photo;
        RequestOptions options;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.recyclerView_photo);
            options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.appbar_back_ground).error(R.drawable.appbar_back_ground);
        }

        void bind(Photo photo) {
            if (photo != null)
                this.photo = photo;
            Glide.with(context).setDefaultRequestOptions(options)
                    .load(photo.getLargeImageURL())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);

//            ViewGroup.LayoutParams params =imageView.getLayoutParams();
//            if (params instanceof FlexboxLayoutManager.LayoutParams){
//                ((FlexboxLayoutManager.LayoutParams) params).setFlexGrow(1f);
//            }

        }

        @Override
        public void onClick(View v) {
            String json = gson.toJson(photo);
            listener.onPicClick(json);
        }
    }


    public static final DiffUtil.ItemCallback<Photo> DIFF_CALLBACK = new DiffUtil.ItemCallback<Photo>() {
        @Override
        public boolean areItemsTheSame(Photo oldItem, Photo newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(Photo oldItem, Photo newItem) {
            return oldItem.equals(newItem);
        }
    };


}

