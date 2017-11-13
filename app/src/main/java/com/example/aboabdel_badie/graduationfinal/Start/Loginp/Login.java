package com.example.aboabdel_badie.graduationfinal.Start.Loginp;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Master.Main;
import com.example.aboabdel_badie.graduationfinal.Start.Regp.Reg;
import com.example.aboabdel_badie.graduationfinal.Start.Start.MainActivity;
import com.example.aboabdel_badie.graduationfinal.Start.Tasks.Logintask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {
    EditText et_email , et_pass ;
    TextView tv_wrong;
    String id = null, email=null ,fn = null ,dp = null ;
    CheckBox checkBox ;
    Shared shared ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        shared = new Shared(this);

        tv_wrong = (TextView) findViewById(R.id.tv_wrongup);
        et_email = (EditText) findViewById(R.id.et_email);
        et_pass = (EditText) findViewById(R.id.et_lpass);
        checkBox = (CheckBox) findViewById(R.id.cb_rem);

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(this,MainActivity.class));
    }

    public void listnerlogin(View view) {

        //startActivity(new Intent(this,Main.class));
        if (isc())
            new Logintask(this).execute(et_email.getText().toString(),et_pass.getText().toString(),getString(R.string.connect)+"login.php");
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
                email = jo.getString("email") ;
                id = jo.getString("id") ;
                fn = jo.getString("fn");
                dp = jo.getString("doctor_phone");
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Shared shared =new Shared.getInstance(getApplicationContext());
        if(et_email.getText().toString().equals(email))
        {
            if(checkBox.isChecked()){
                shared.save("1",id,fn,dp);
                Toast.makeText(this,shared.is_login(),Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login.this,Main.class));

            }
            shared.save("0",id ,fn,dp);
            startActivity(new Intent(Login.this,Main.class));

        }else{
            tv_wrong.setText("ERROR Email or Password");
        }

    }

}
