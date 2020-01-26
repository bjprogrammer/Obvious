package com.application.obvious.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.application.obvious.R;
import com.application.obvious.model.ImageList;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ImageList.Image> imageList;
    private onPressListener listener;

    public interface onPressListener{
        void onClick(int position, ImageView imageView);
    }

    MainAdapter(onPressListener listener) {
        imageList = new ArrayList<>();
        this.listener =listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        viewHolder = new ViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_grid, viewGroup, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MainAdapter.ViewHolder viewHolder = (MainAdapter.ViewHolder) holder;
        viewHolder.bind(imageList.get(position),listener, position);
    }


    @Override
    public int getItemCount() {
        return imageList == null ? 0 : imageList.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding binding;

        private ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        private void bind(Object obj, onPressListener listener, int position) {
            ViewCompat.setTransitionName(binding.getRoot().findViewById(R.id.imageView), imageList.get(position).getTitle());

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position,(binding.getRoot().findViewById(R.id.imageView)));
                }
            });

            binding.setVariable(BR.image, obj);
            binding.executePendingBindings();
        }
    }

    private void add(ImageList.Image image) {
        imageList.add(image);
        notifyItemInserted(imageList.size() - 1);
    }

    void addAll(ImageList list) {
        for (ImageList.Image response: list.getData()) {
            add(response);
        }
    }
}
