package com.application.obvious.main;

import com.application.obvious.model.ImageList;

class MainContract {

    interface MainView {
        void showWait();
        void onFailure(String appErrorMessage);
        void onSuccess(ImageList response);
    }

    interface MainPresenter{
         void getImageList();
         void unSubscribe();
    }
}
