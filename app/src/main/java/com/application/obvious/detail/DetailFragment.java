package com.application.obvious.detail;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.application.obvious.R;
import com.application.obvious.databinding.FragmentDetailBinding;
import com.application.obvious.model.ImageList;

import java.util.ArrayList;

import static com.application.obvious.utils.Constants.IMAGE_LIST;
import static com.application.obvious.utils.Constants.POSITION;

public class DetailFragment extends Fragment{
    private Context context;

    private FragmentDetailBinding binding;
    public DetailFragment() { }

    public static DetailFragment newInstance(int current, ArrayList<ImageList.Image> images) {
        DetailFragment detailFragment = new DetailFragment();

        // Passing image List and position of image clicked to detailed fragment
        Bundle dataBundle = new Bundle();
        dataBundle.putInt(POSITION, current);
        dataBundle.putParcelableArrayList(IMAGE_LIST, images);
        detailFragment.setArguments(dataBundle);
        return detailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Shared element animation
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setSharedElementEnterTransition(TransitionInflater.from(context).inflateTransition(android.R.transition.move));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (context == null) {
            context = getContext();
        }

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false);
        return  binding.getRoot();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Setup viewpager
        if(getArguments()!=null) {
            int currentItem = getArguments().getInt(POSITION);
            ArrayList<ImageList.Image> imageList = getArguments().getParcelableArrayList(IMAGE_LIST);
            DetailAdapter adapter = new DetailAdapter(getChildFragmentManager(), imageList);
            ViewPager viewPager = binding.getRoot().findViewById(R.id.view_pager);
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(currentItem);
        }
    }
}
