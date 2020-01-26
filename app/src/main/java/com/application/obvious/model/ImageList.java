package com.application.obvious.model;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.library.baseAdapters.BR;

import com.application.obvious.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.google.gson.annotations.SerializedName;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.List;

public class ImageList extends BaseObservable {

    @SerializedName("Data")
    private List<Image> data;

    @Bindable
    public List<Image> getData() {
        return data;
    }

    public void setData(List<Image> data) {
        this.data = data;
        notifyPropertyChanged(BR.data);
    }

    public static class Image extends BaseObservable {

        private String copyright;

        private String date;

        private String explanation;

        private String hdurl;

        private String url;

        @SerializedName("service_version")
        private String version;

        private String title;

        @SerializedName("media_type")
        private String type;

        @Bindable
        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
            notifyPropertyChanged(BR.copyright);
        }

        @Bindable
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
            notifyPropertyChanged(BR.date);
        }

        @Bindable
        public String getExplanation() {
            return explanation;
        }

        public void setExplanation(String explanation) {
            this.explanation = explanation;
            notifyPropertyChanged(BR.explanation);

        }

        @Bindable
        public String getHdurl() {
            return hdurl;
        }

        public void setHdurl(String hdurl) {
            this.hdurl = hdurl;
            notifyPropertyChanged(BR.hdurl);
        }

        @Bindable
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
            notifyPropertyChanged(BR.url);
        }

        @Bindable
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
            notifyPropertyChanged(BR.type);
        }


        @Bindable
        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
            notifyPropertyChanged(BR.version);
        }

        @Bindable
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
            notifyPropertyChanged(BR.title);
        }
    }

    @BindingAdapter({"bind:url", "bind:spinner"})
    public static void loadImage(ImageView view, String url, AVLoadingIndicatorView spinner)
    {
        spinner.show();
        spinner.setVisibility(View.VISIBLE);
        Glide.with(view.getContext())
                .load(url)
                .thumbnail(0.1f)
                .apply(new RequestOptions().error(R.mipmap.no_image))
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        spinner.hide();
                        spinner.setVisibility(View.GONE);
                        view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        spinner.hide();
                        spinner.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(view);
    }
}
