package com.example.aboabdel_badie.graduationfinal.Start.Regp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Tasks.Idtask;
import com.example.aboabdel_badie.graduationfinal.Start.Tasks.Logintask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Id extends AppCompatActivity {
    EditText et_id ;
    TextView tv_id ;
    String rid ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.id);
        et_id = (EditText) findViewById(R.id.idcheck);
        tv_id = (TextView) findViewById(R.id.wrongid);

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
    public void idcheck_click(View view) {
        if (isc())
            new Idtask(this).execute(et_id.getText().toString(),getString(R.string.connect)+"check.php") ;
        else
            Toast.makeText(this,"check  Internet connection",Toast.LENGTH_LONG).show();
    }
    public void parsecheck(String response) {


        JSONObject jsonObject ;
        try {
            jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            int count = 0 ;
            while (count<jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);
                rid = jo.getString("id") ;
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(et_id.getText().toString().equals(rid))
        {
            Intent i = new Intent(this,Reg.class) ;
            startActivity(i);

        }else{
            tv_id.setText("please check your ID from IT hospital part");
            tv_id.setVisibility(TextView.VISIBLE);
        }
    }
}
