package com.pic.hub.pichub.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.json.JSONObject;

// https://pixabay.com/api/docs/
@Entity(tableName = "photos")
public class Photo {
    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String previewURL;
    @NonNull
    private String webformatURL;
    private String imageURL;
    private String largeImageURL;
    private String fullHDURL;
    private String tags;
    private int likes;
    private int favourites;
    private double imageHeight;
    private double imageWidth;
    private long downloads;
    private long comments;
    private String user;
    private String userImageURL;
    // order whether its popular or latest, adding a new column instead of creating a new table
    @NonNull
    private String order;

    public Photo(String id, String previewURL, String webformatURL, String imageURL, String largeImageURL,
                 String fullHDURL, String tags, int likes, int favourites, double imageHeight, double imageWidth,
                 long downloads, long comments, String user, String userImageURL, String order) {

        this.id = id;
        this.previewURL = previewURL;
        this.webformatURL = webformatURL;
        this.imageURL = imageURL;
        this.largeImageURL = largeImageURL;
        this.fullHDURL = fullHDURL;
        this.tags = tags;
        this.likes = likes;
        this.favourites = favourites;
        this.imageHeight = imageHeight;
        this.imageWidth = imageWidth;
        this.downloads = downloads;
        this.comments = comments;
        this.user = user;
        this.userImageURL = userImageURL;
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public String getFullHDURL() {
        return fullHDURL;
    }

    public String getTags() {
        return tags;
    }

    public int getLikes() {
        return likes;
    }

    public long getComments() {
        return comments;
    }

    public String getUser() {
        return user;
    }

    public String getUserImageURL() {
        return userImageURL;
    }

    public int getFavourites() {
        return favourites;
    }

    public double getImageHeight() {
        return imageHeight;
    }

    public double getImageWidth() {
        return imageWidth;
    }

    public long getDownloads() {
        return downloads;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id='" + id + '\'' +
                ", previewURL='" + previewURL + '\'' +
                ", webformatURL='" + webformatURL + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", largeImageURL='" + largeImageURL + '\'' +
                ", fullHDURL='" + fullHDURL + '\'' +
                ", tags='" + tags + '\'' +
                ", likes=" + likes +
                ", favourites=" + favourites +
                ", imageHeight=" + imageHeight +
                ", imageWidth=" + imageWidth +
                ", downloads=" + downloads +
                ", comments=" + comments +
                ", user='" + user + '\'' +
                ", userImageURL='" + userImageURL + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
