package com.pic.hub.pichub.database;

import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.pic.hub.pichub.model.Photo;

import java.util.List;

@Dao // database access object
public interface PhotoDao {

    // Returns a list of all photos in the database
    @Query("SELECT * FROM photos WHERE `order` LIKe :order")
    DataSource.Factory<Integer,Photo> getAllPhotos(String order);

    // Inserts multiple photos
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<Photo> photos);


    @Query("SELECT * FROM photos WHERE id LIKE :photo_id LIMIT 1")
    Photo findById(String photo_id);
}
