package com.example.aboabdel_badie.graduationfinal.Start.Masterp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Shared;
import com.example.aboabdel_badie.graduationfinal.Start.Master.Main;

public class NewPhone extends AppCompatActivity {

    EditText etNewPhone;
    Button btnAddNewPhone;
    RequestQueue requestQueue;
    Shared shared;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_phone);
        shared = new Shared(this);



        requestQueue = Volley.newRequestQueue(getApplicationContext());

        etNewPhone = (EditText) findViewById(R.id.etNewPhone);

        btnAddNewPhone = (Button) findViewById(R.id.btnAddNewPhone);
        btnAddNewPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etNewPhone.getText().toString();
                // this string "1" is the id of user change it with the id of your user
                if(phone.length()>6&&phone.length()<15){
                    newPhone(phone, shared.getId());
                }else
                    Toast.makeText(getApplicationContext()," input from 6 to 15 number",Toast.LENGTH_LONG).show();




            }
        });
    }




    public void newPhone(String phone, String userid) {

        String url = getString(R.string.connect)+"add_phone.php?phone=" + phone + "&pid=" + userid;

        //Toast.makeText(this, "URL : " + url, Toast.LENGTH_LONG).show();

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), Main.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Failed Connection", Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(request);
    }
}
