package com.example.aboabdel_badie.graduationfinal.Start.Masterp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Shared;

public class Contact extends AppCompatActivity {

    TextView rr,dp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        dp= (TextView) findViewById(R.id.tv_dp);
        dp.setText(new Shared(this).getdphone());

        /*if (isc())
            new Receivereport(this).execute("1");
        else
            Toast.makeText(this,"check  Internet connection",Toast.LENGTH_LONG).show();
            */
    }

    public void parsetreat(String s) {
        rr.setText(s);

    }
    public boolean isc(){
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(this.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            return true;
        }
        else
            return false;
    }
}
