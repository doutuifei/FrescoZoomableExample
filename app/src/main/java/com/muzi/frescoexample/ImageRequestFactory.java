package com.muzi.frescoexample;

import android.net.Uri;

import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * 作者: lipeng
 * 时间: 2018/5/25
 * 邮箱: lipeng@moyi365.com
 * 功能:
 */
public class ImageRequestFactory {

    public static ImageRequest getImageRequest(ImageRequestInter imageRequestInter) {
        return imageRequestInter.getImageRequestBuilder().build();
    }

    class SimpleImageRequestBuilder implements ImageRequestInter {

        private Uri uri;
        private int w, h;

        public SimpleImageRequestBuilder(Uri uri, int w, int h) {
            this.uri = uri;
            this.w = w;
            this.h = h;
        }

        @Override
        public ImageRequestBuilder getImageRequestBuilder() {
            ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(getUri())
                    .setLocalThumbnailPreviewsEnabled(true)
                    .setProgressiveRenderingEnabled(true)
                    .setRotationOptions(RotationOptions.autoRotate());
            if (getW() > 0 && getH() > 0) {
                builder.setResizeOptions(new ResizeOptions(getW(), getH()));
            }
            return builder;
        }

        @Override
        public Uri getUri() {
            return uri;
        }

        @Override
        public int getW() {
            return w;
        }

        @Override
        public int getH() {
            return h;
        }
    }

    class RotateImageRequestBuilder extends SimpleImageRequestBuilder {

        private int rotate;

        public RotateImageRequestBuilder(Uri uri, int w, int h) {
            super(uri, w, h);
        }

        public RotateImageRequestBuilder(Uri uri, int w, int h, int rotate) {
            super(uri, w, h);
            this.rotate = rotate;
        }

        @Override
        public ImageRequestBuilder getImageRequestBuilder() {
            if (rotate != RotationOptions.NO_ROTATION) {
                RotationOptions rotationOptions = RotationOptions.forceRotation(rotate);
                return super.getImageRequestBuilder().setRotationOptions(rotationOptions);
            }
            return super.getImageRequestBuilder();
        }
    }


    interface ImageRequestInter {
        ImageRequestBuilder getImageRequestBuilder();

        Uri getUri();

        int getW();

        int getH();
    }

}
