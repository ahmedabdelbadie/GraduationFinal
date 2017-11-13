package com.example.aboabdel_badie.graduationfinal.Start.Masterp;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Shared;
import com.example.aboabdel_badie.graduationfinal.Start.Tasks.Sporttask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Sport extends AppCompatActivity {
    TextView sp ;
    String ss ;
    Shared shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shared = new Shared(this);


        setContentView(R.layout.activity_sport);
        sp = (TextView) findViewById(R.id.tv_s);

        if (isc())
            new Sporttask(this).execute(shared.getId(),getString(R.string.connect)+"sport.php");
        else
            Toast.makeText(this,"check  Internet connection",Toast.LENGTH_LONG).show();
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

    public void parsefinish(String s) {
        JSONObject jsonObject ;
        try {
            jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0 ;
            while (count<jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);
                ss = jo.getString("sport") ;
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        sp.setText(ss);
    }
}

