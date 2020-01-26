package com.application.obvious.main;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.obvious.R;
import com.application.obvious.databinding.FragmentRecyclerViewBinding;
import com.application.obvious.model.ImageList;

import es.dmoral.toasty.Toasty;
import static com.application.obvious.utils.Constants.GRID_SIZE;

public class MainFragment extends Fragment implements MainContract.MainView{
    private Activity activity;
    private Context context;

    private FragmentRecyclerViewBinding binding;
    private MainPresenter presenter;

    private MainAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    private TextView emptyList;

    @Override
    public void onAttach(@NonNull Context context) {
        if(context instanceof Activity){
            this.activity = (Activity)context;
        }
        else
        {
            this.activity = getActivity();
        }
        super.onAttach(context);
    }


    public MainFragment() { }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (context == null) {
            context = getContext();
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recycler_view,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new MainPresenter(context,this);

        attachView();
        init();
        setupRecyclerView();

        presenter.getImageList();
    }

    private void attachView(){
        recyclerView = binding.recyclerView;
        emptyList = binding.tvEmpty;
        progressBar = binding.progressBar;
        ((AppCompatActivity)activity).setSupportActionBar(binding.toolbar);
        ((AppCompatActivity)activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.unSubscribe();
        presenter = null;
    }

    private void init(){
        //Configuring customized Toast messages
        Toasty.Config.getInstance()
                .setErrorColor( getResources().getColor(R.color.error) )
                .setSuccessColor(getResources().getColor(R.color.success) )
                .setTextColor(Color.WHITE)
                .tintIcon(true)
                .setTextSize(18)
                .apply();
    }

    private void setupRecyclerView(){
        adapter = new MainAdapter((position,  view) -> {

        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, GRID_SIZE);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showWait() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailure(String appErrorMessage) {
        progressBar.setVisibility(View.GONE);
        showNoListView();
        Toasty.error(context, appErrorMessage, Toast.LENGTH_LONG, true).show();
    }

    @Override
    public void onSuccess(ImageList response) {
        progressBar.setVisibility(View.GONE);

        if(response!=null){
            if(!response.getData().isEmpty()){
                adapter.addAll(response);
                recyclerView.setVisibility(View.VISIBLE);
                emptyList.setVisibility(View.GONE);
            }
            else
            {
                showNoListView();
            }
        }
        else
        {
            showNoListView();
        }
    }

    private void showNoListView(){
        recyclerView.setVisibility(View.GONE);
        emptyList.setVisibility(View.VISIBLE);
    }
}
