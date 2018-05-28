package com.muzi.frescoexample;

import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.muzi.frescoexample.zoomable.DoubleTapGestureListener;
import com.muzi.frescoexample.zoomable.ZoomableDraweeView;


public class ImageLoaderUtils {
    private static final String TAG = "ImageLoaderUtils";

    /**
     * @param view
     * @param url
     * @param retry //是否支持重试
     */
    public static void loadImage(SimpleDraweeView view, String url, boolean retry) {
        if (TextUtils.isEmpty(url)) return;
        Uri uri = Uri.parse(url);//本地:Uri uri = "file:///mnt/sdcard/MyApp/myfile.jpg";
        ImageRequest request = getImageRequest(uri, 0, 0);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setAutoPlayAnimations(false)
                .setTapToRetryEnabled(retry)
                .setOldController(view.getController())
                .build();
        view.setController(controller);
    }

    /**
     * @param view
     * @param url
     * @param retry //是否支持重试
     */
    public static void loadImage(ZoomableDraweeView view, String url, boolean retry) {
        if (TextUtils.isEmpty(url)) return;
        Uri uri = Uri.parse(url);//本地:Uri uri = "file:///mnt/sdcard/MyApp/myfile.jpg";
        ImageRequest request = getImageRequest(uri, 0, 0);
        view.setAllowTouchInterceptionWhileZoomed(true);
        // needed for double tap to zoom
        view.setIsLongpressEnabled(false);
        view.setTapListener(new DoubleTapGestureListener(view));
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(retry)
                .build();
        view.setController(controller);
    }

    public static void loadImage(ZoomableDraweeView view, String url, boolean retry, int rotate) {
        if (TextUtils.isEmpty(url)) return;
        Uri uri = Uri.parse(url);//本地:Uri uri = "file:///mnt/sdcard/MyApp/myfile.jpg";
        ImageRequest request = getImageRequest(uri, 0, 0, rotate);
        view.setAllowTouchInterceptionWhileZoomed(true);
        // needed for double tap to zoom
        view.setIsLongpressEnabled(false);
        view.setTapListener(new DoubleTapGestureListener(view));
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setTapToRetryEnabled(retry)
                .build();
        view.setController(controller);
    }


    /**
     * @param view
     * @param url
     * @param w
     * @param h
     * @param retry //是否支持重试
     */
    public static void loadImage(SimpleDraweeView view, String url, int w, int h, boolean retry) {
        if (TextUtils.isEmpty(url)) return;
        Uri uri = Uri.parse(url);//本地:Uri uri = "file:///mnt/sdcard/MyApp/myfile.jpg";
        ImageRequest request = getImageRequest(uri, w, h);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setAutoPlayAnimations(false)
                .setTapToRetryEnabled(retry)
                .setOldController(view.getController())
                .build();
        view.setController(controller);
    }


    /**
     * @param uri
     * @param w
     * @param h
     * @return
     */
    private static ImageRequest getImageRequest(Uri uri, int w, int h) {
        return getImageRequest(uri, w, h, RotationOptions.NO_ROTATION);
    }

    /**
     * @param uri
     * @param w
     * @param h
     * @return
     */
    private static ImageRequest getImageRequest(Uri uri, int w, int h, int rotate) {
        ImageRequestBuilder builder = ImageRequestBuilder.newBuilderWithSource(uri)
                .setLocalThumbnailPreviewsEnabled(true)
                .setProgressiveRenderingEnabled(true)
                .setRotationOptions(RotationOptions.autoRotate());
        if (rotate != RotationOptions.NO_ROTATION) {
            RotationOptions rotationOptions = RotationOptions.forceRotation(rotate);
            builder.setRotationOptions(rotationOptions);
        } else {
            builder.setRotationOptions(RotationOptions.autoRotate());
        }
        if (w > 0 && h > 0) {
            builder.setResizeOptions(new ResizeOptions(w, h));
        }
        return builder.build();
    }

}
