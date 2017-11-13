package com.example.aboabdel_badie.graduationfinal.Start.Masterp;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Shared;
import com.example.aboabdel_badie.graduationfinal.Start.Model.TreatModel;
import com.example.aboabdel_badie.graduationfinal.Start.Tasks.Treattask;

import com.example.aboabdel_badie.graduationfinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Treat extends AppCompatActivity {
    Shared shared ;


    ListView listView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treat);
        shared = new Shared(this);

        listView1 = (ListView)findViewById(R.id.listView1);
        if (isc())
            new Treattask(this).execute(shared.getId(),getString(R.string.connect)+"treat_time.php");
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
    public void parsetreat(String s) {
        ArrayList<TreatModel> treatModels=new ArrayList<>();
        TreatModel treatModel;

        JSONObject jsonObject ;
        try {
            jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0 ;
            while (count<jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);
                String id = jo.getString("id") ;
                String name = jo.getString("name");
                String time = jo.getString("time");
                treatModel=new TreatModel(id,name,time);
                treatModels.add(treatModel);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Toast.makeText(this,treatModels.get(0).getName(),Toast.LENGTH_LONG).show();
        //rv_treat.setAdapter(new Treatadapter(this,R.layout.row_list_treat,(List)treatModels));
        Treatadapter adapter = new Treatadapter(this,
                R.layout.row_list_treat, treatModels);




        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        listView1.addHeaderView(header);

        listView1.setAdapter(adapter);
    }
}
