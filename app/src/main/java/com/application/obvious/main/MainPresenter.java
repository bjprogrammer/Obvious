package com.application.obvious.main;

import android.content.Context;

import com.application.obvious.R;
import com.application.obvious.model.ImageList;
import com.application.obvious.utils.HelperFunctions;
import com.google.gson.Gson;

public class MainPresenter implements MainContract.MainPresenter{
    private MainContract.MainView view;
    private Context context;
    private Gson gson;

    MainPresenter(Context context, MainContract.MainView view) {
        this.view = view;
        this.context = context;
        gson = new Gson();
    }

    public void getImageList() {
        view.showWait();

        String jsonData = HelperFunctions.fetchData(context);

        if(jsonData != null){
           ImageList imageList = gson.fromJson(jsonData, ImageList.class);
           view.onSuccess(imageList);
        }else
        {
            view.onFailure(context.getString(R.string.error_message));
        }
    }

    public void unSubscribe(){
        view = null;
    }
}
