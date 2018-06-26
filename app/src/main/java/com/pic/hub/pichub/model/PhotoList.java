package com.pic.hub.pichub.model;

import java.util.List;

public class PhotoList {
    private List<Photo> hits;

    public PhotoList(List<Photo> hits) {
        this.hits = hits;
    }

    public List<Photo> getHits() {
        return hits;
    }
}
