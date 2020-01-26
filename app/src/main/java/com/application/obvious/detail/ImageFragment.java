package com.application.obvious.detail;

import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.application.obvious.R;
import com.application.obvious.databinding.ItemPagerBinding;
import com.application.obvious.model.ImageList;
import com.jsibbold.zoomage.ZoomageView;

import static com.application.obvious.utils.Constants.IMAGE;
import static com.application.obvious.utils.Constants.TRANSITION_NAME;

public class ImageFragment extends Fragment {

    private ItemPagerBinding binding;

    public ImageFragment() { }

    static ImageFragment newInstance(ImageList.Image image, String transitionName) {
        ImageFragment imageFragment = new ImageFragment();

        //Passing image data and shared element transition name to image fragment
        Bundle dataBundle = new Bundle();
        dataBundle.putParcelable(IMAGE, image);
        dataBundle.putString(TRANSITION_NAME, transitionName);
        imageFragment.setArguments(dataBundle);
        return imageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.item_pager, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Passing data for loading data through data binding
        final ImageList.Image image = getArguments().getParcelable(IMAGE);
        binding.setImage(image);


        //Setting shared element transition name in image fragment same as home fragment
        String transitionName = getArguments().getString(TRANSITION_NAME);
        final ZoomageView imageView = view.findViewById(R.id.imageView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageView.setTransitionName(transitionName);
        }
    }
}