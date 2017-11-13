package com.example.aboabdel_badie.graduationfinal.Start.Regp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Shared;
import com.example.aboabdel_badie.graduationfinal.Start.Tasks.Regtask;

public class Reg extends AppCompatActivity {
    EditText fname, lname, pass,cpass,age,phone,state,address, email,tn1,tn2,tn3;
    RadioGroup radioGroup ;
    int checkedRadioButtonId ;
    String gander ;
    Shared shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        fname = (EditText) findViewById(R.id.et_fname);
        lname = (EditText) findViewById(R.id.et_lname);
        pass = (EditText) findViewById(R.id.et_pass);
        cpass = (EditText) findViewById(R.id.et_cpass);
        email = (EditText) findViewById(R.id.et_email);
        tn1 = (EditText) findViewById(R.id.et_tnum1) ;
        tn2 = (EditText) findViewById(R.id.et_tnum2) ;
        tn3 = (EditText) findViewById(R.id.et_tnum3) ;
        age = (EditText) findViewById(R.id.et_age) ;
        phone = (EditText) findViewById(R.id.et_phone) ;
        state = (EditText) findViewById(R.id.et_state) ;
        address = (EditText) findViewById(R.id.et_address) ;


    }

    public void reg_click(View view) {

        if ((fname.getText().toString().equals(""))&&(lname.getText().toString().equals("") )
                &&(email.getText().toString().equals(""))&&(tn1.getText().toString().equals("") )){
            Toast.makeText(this,"please fill mondatory field",Toast.LENGTH_LONG).show();
        }else {
            if(pass.getText().toString().equals(cpass.getText().toString())){

                Toast.makeText(this,"pass",Toast.LENGTH_LONG).show();



                new Regtask(this).execute(shared.getId(),fname.getText().toString(),lname.getText().toString(),age.getText().toString(),
                            phone.getText().toString(),address.getText().toString()
                            ,email.getText().toString(),pass.getText().toString(),state.getText().toString(),
                            tn1.getText().toString(),tn2.getText().toString(),
                            tn3.getText().toString(),getString(R.string.connect)+"reg.php");
                    finish();
                }
            else {
                Toast.makeText(this,"password not matched",Toast.LENGTH_LONG).show();
            }
            }

        }






    }

