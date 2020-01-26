package com.application.obvious.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.application.obvious.R;
import com.application.obvious.model.ImageList;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ImageList.Image> data;
    private onPressListener listener;

    public interface onPressListener{
        void onClick(int position, ImageView imageView);
    }

    MainAdapter(onPressListener listener) {
        data = new ArrayList<>();
        this.listener =listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        viewHolder = new ViewHolder(DataBindingUtil.inflate(inflater, R.layout.item_grid, viewGroup, false));
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int i) {
        MainAdapter.ViewHolder viewHolder = (MainAdapter.ViewHolder) holder;
        viewHolder.bind(data.get(i),listener, i);
    }


    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder{
        private ViewDataBinding binding;

        private ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        private void bind(Object obj, onPressListener listener, int position) {
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
        data.add(image);
        notifyItemInserted(data.size() - 1);
    }

    void addAll(ImageList mcList) {
        for (ImageList.Image response: mcList.getData()) {
            add(response);
        }
    }
}
