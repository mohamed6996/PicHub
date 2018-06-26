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
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.pic.hub.pichub.R;
import com.pic.hub.pichub.model.Photo;

public class RecyclerViewAdapter extends PagedListAdapter<Photo,RecyclerViewAdapter.PhotoViewHolder > {
   private Context context;

    public RecyclerViewAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view_item = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_list_item, parent, false);
        return new PhotoViewHolder(view_item);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.PhotoViewHolder holder, int position) {
        holder.bind(getItem(position));
    }



    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recyclerView_photo);

        }

         void bind(Photo photo) {
            if (photo != null)

                Glide.with(context).setDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.appbar_back_ground).error(R.drawable.ic_launcher_background))
                        .load(photo.getPreviewURL())
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView);

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

