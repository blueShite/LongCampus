package com.example.longhengyu.longcampus.Circle.ReleaseCircle;

import android.os.Bundle;

import com.example.longhengyu.longcampus.R;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoActivity;
import com.jph.takephoto.compress.CompressConfig;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TakePhotoOptions;

public class ReleaseCircleActivity extends TakePhotoActivity {

    TakePhoto mPhoto;
    CropOptions.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_circle);
       mPhoto = getTakePhoto();
        customTakePhoto();
    }

    private void customTakePhoto(){

        CompressConfig config=new CompressConfig.Builder()
                .setMaxSize(102400)
                .setMaxPixel(800>=800? 800:800)
                .enableReserveRaw(true)
                .create();
        mPhoto.onEnableCompress(config,true);
        builder=new CropOptions.Builder();
        builder.setOutputX(800).setOutputY(800);
        builder.setWithOwnCrop(true);
        builder.create();
        TakePhotoOptions.Builder builderOptions=new TakePhotoOptions.Builder();
        builderOptions.setWithOwnGallery(true);
        mPhoto.setTakePhotoOptions(builderOptions.create());

    }

}
