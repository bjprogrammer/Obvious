package com.application.obvious.main;

import androidx.appcompat.app.AppCompatActivity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.application.obvious.R;
import com.application.obvious.utils.ConnectivityReceiver;

import es.dmoral.toasty.Toasty;

import static android.net.ConnectivityManager.CONNECTIVITY_ACTION;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    private boolean isInternetAvailable = true;
    private IntentFilter intentFilter;
    private ConnectivityReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content, MainFragment.newInstance())
                .commit();

        //Initialize Connectivity receiver to update internet status
        intentFilter = new IntentFilter();
        intentFilter.addAction(CONNECTIVITY_ACTION);
        receiver = new ConnectivityReceiver();
    }

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(receiver, intentFilter);
        ConnectivityReceiver.connectivityReceiverListener = this;
    }

    //Checking internet isInternetAvailable using broadcast receiver
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if(isInternetAvailable != isConnected)
        {
            if(isConnected){
                Toasty.success(this, "Connected to internet", Toast.LENGTH_SHORT, true).show();
            }
            else
            {
                Toasty.error(getApplicationContext(), "Not connected to internet", Toast.LENGTH_LONG, true).show();
            }
        }
        isInternetAvailable = (isConnected);
    }

    @Override
    protected void onStop() {
        if (receiver != null) {
            try {
                unregisterReceiver(receiver);
            } catch (Exception e) { }
        }
        super.onStop();
    }
}
