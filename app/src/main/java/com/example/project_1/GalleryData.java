package com.example.project_1;

import android.net.Uri;

public class GalleryData {

    private Uri iv_photo;

    public GalleryData(Uri iv_photo) {
        this.iv_photo = iv_photo;
    }

    public Uri getIv_photo() {
        return iv_photo;
    }

    public void setIv_photo(Uri iv_photo) {
        this.iv_photo = iv_photo;
    }
}
