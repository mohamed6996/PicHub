package com.pic.hub.pichub.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dinuscxj.progressbar.CircleProgressBar;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.github.florent37.glidepalette.GlidePalette;
import com.google.gson.Gson;
import com.pic.hub.pichub.utils.ImageGetter;
import com.pic.hub.pichub.utils.ImageStorage;
import com.pic.hub.pichub.R;
import com.pic.hub.pichub.model.Photo;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment implements View.OnClickListener {

    ImageView user_image, imageDownload, setWall;
    KenBurnsView kenBurnsView;
    TextView userName, views_number, downloads_number, likes_number, favorites_number, pixabay_txt;
    String user_name_str;
    Photo photo;
    CircleProgressBar downloadProgressBar, setProgressBar;
    NestedScrollView nestedScrollView;
    Button btn_download, btn_setWall;
    ProgressDialog progressDialog;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String photoJson = getActivity().getIntent().getStringExtra("pic_json");
        photo = new Gson().fromJson(photoJson, Photo.class);
        user_name_str = photo.getUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        user_image = view.findViewById(R.id.user_image);
        kenBurnsView = view.findViewById(R.id.detail_image_view);
        //  imageDownload = view.findViewById(R.id.detail_download);
        //  setWall = view.findViewById(R.id.detail_set_wall);
        btn_download = view.findViewById(R.id.btn_download);
        btn_setWall = view.findViewById(R.id.btn_set_wall);
        btn_download.setOnClickListener(this);
        btn_setWall.setOnClickListener(this);


        pixabay_txt = view.findViewById(R.id.detail_pixabay);
        pixabay_txt.setMovementMethod(LinkMovementMethod.getInstance());

        userName = view.findViewById(R.id.user_name);
        views_number = view.findViewById(R.id.detail_views_number);
        downloads_number = view.findViewById(R.id.detail_downloads_number);
        likes_number = view.findViewById(R.id.detail_likes_number);
        favorites_number = view.findViewById(R.id.detail_favorites_number);

        //    downloadProgressBar = view.findViewById(R.id.detail_progress_download);
        //    setProgressBar = view.findViewById(R.id.detail_progress_set);

        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Glide.with(this).load(photo.getUserImageURL()).into(user_image);
        userName.setText(user_name_str);

        views_number.append(" " + photo.getViews());
        downloads_number.append(" " + photo.getDownloads());
        likes_number.append(" " + photo.getLikes());
        favorites_number.append(" " + photo.getFavorites());

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Downloading");
        progressDialog.setMax(100);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        //todo set cancel btn
         /* progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "cancel download", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });*/



        String url = photo.getLargeImageURL();

        RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.appbar_back_ground).error(R.drawable.appbar_back_ground);

        Glide.with(this)
                .setDefaultRequestOptions(options)
                .load(url)
                .listener(GlidePalette.with(url)
                                .use(GlidePalette.Profile.MUTED_DARK)
                                .intoBackground(nestedScrollView, GlidePalette.Swatch.RGB)
                                .crossfade(true, 2000)


//                        .setGlideListener(new RequestListener<Drawable>() {
//                    @Override public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                        return false;
//                    }
//
//                    @Override public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                        return false;
//                    }
//                })
                )
                .into(kenBurnsView);


    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        String imageId = photo.getId();

        switch (id) {
            case R.id.btn_download:

                if (ImageStorage.checkifImageExists(imageId)) // check if it is already there if not then download
                    Toast.makeText(getContext(), "already exists!", Toast.LENGTH_SHORT).show();
                else
                    new ImageGetter(progressDialog,false).execute(photo.getLargeImageURL(), imageId);
                break;
            case R.id.btn_set_wall:
                if (ImageStorage.checkifImageExists(imageId)) // check if it is already there if not then download
                    ImageStorage.setWallpaper(imageId, getContext());
                else
                    new ImageGetter(progressDialog,true).execute(photo.getLargeImageURL(), imageId);
                break;
        }

    }
}
