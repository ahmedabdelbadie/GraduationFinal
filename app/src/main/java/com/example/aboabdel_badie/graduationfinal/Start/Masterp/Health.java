package com.example.aboabdel_badie.graduationfinal.Start.Masterp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Shared;
import com.example.aboabdel_badie.graduationfinal.Start.Master.Main;
import com.example.aboabdel_badie.graduationfinal.Start.Tasks.Sendreport;

public class Health extends AppCompatActivity {
    EditText tem , pres , suger ,report ;
    RequestQueue requestQueue;
    Shared shared ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        shared = new Shared(this);



        tem = (EditText) findViewById(R.id.et_tem);
        pres = (EditText) findViewById(R.id.et_pres);
        suger = (EditText) findViewById(R.id.et_suger);
        report = (EditText) findViewById(R.id.et_report);
    }

    public void sendreport(View view) {
        new Sendreport(this).execute(shared.getId(),pres.getText().toString(),suger.getText().toString(),
                report.getText().toString(),getString(R.string.connect)+"add_health_report.php");
        //addhealth(tem.getText().toString(), suger.getText().toString(), pres.getText().toString(), report.getText().toString(), "3");
        Toast.makeText(this," Insert Success ",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, Main.class));
    }

}
