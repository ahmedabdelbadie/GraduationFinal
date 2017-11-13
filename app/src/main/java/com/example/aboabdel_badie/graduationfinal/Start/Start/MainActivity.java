package com.example.aboabdel_badie.graduationfinal.Start.Start;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Login;
import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Shared;
import com.example.aboabdel_badie.graduationfinal.Start.Master.Main;
import com.example.aboabdel_badie.graduationfinal.Start.Regp.Id;

public class MainActivity extends AppCompatActivity {
    Shared shared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shared = new Shared(this);


    }
    public void existpateint(View view) {

        //Toast.makeText(this,shared.is_login(),Toast.LENGTH_LONG).show();
        if (shared.is_login().equals("1")) {
            startActivity(new Intent(MainActivity.this, Main.class));
        }else{
            startActivity(new Intent(MainActivity.this, Login.class));
        }}
    public void newpateint(View view) {
        startActivity(new Intent(MainActivity.this, Id.class));
    }


    //public void clear(View view) {
     //   Shared(this).logoutUser();
   // }
}
