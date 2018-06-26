package com.pic.hub.pichub;

import android.arch.paging.DataSource;

import com.pic.hub.pichub.model.Photo;
import com.pic.hub.pichub.database.PhotoDao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class PhotoLocalCache {
    PhotoDao photoDao;
    Executor ioExecutor;

    public PhotoLocalCache(PhotoDao photoDao, Executor ioExecutor) {
        this.photoDao = photoDao;
        this.ioExecutor = ioExecutor;
    }

    public void insertPhotos(final List<Photo> photos) {
        ioExecutor.execute(new Runnable() {
            @Override
            public void run() {
                photoDao.bulkInsert(photos);
            }
        });
    }

    //todo remove it
    private List<Photo> updateList(List<Photo> photos) {
        List<Photo> updatedPhotos = new ArrayList<>();
        for (int i = 0; i < photos.size(); i++) {
            Photo photo = photos.get(i);
            photo.setOrder("popular");
            updatedPhotos.add(photo);
        }

        return updatedPhotos;
    }


    public DataSource.Factory<Integer, Photo> getSavedPhotos(String order) {
        return photoDao.getAllPhotos(order);
    }
}
