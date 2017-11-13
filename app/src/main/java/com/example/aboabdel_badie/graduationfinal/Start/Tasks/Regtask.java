package com.example.aboabdel_badie.graduationfinal.Start.Tasks;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aboabdel_badie.graduationfinal.Start.Regp.Reg;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Abo Abdel-Badie on 5/22/2017.
 */

public class Regtask extends AsyncTask<String,Void,String> {
    Reg reg ;


    public Regtask(Reg reg) {this.reg = reg ;}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... params) {
        String id = params[0];
        String fname = params[1];
        String lname = params[2];
        String age = params[3];
        String phone = params[4];
        String address = params[5];
        String email = params[6];
        String pass = params[7];
        String state = params[8];
        String tn1 = params[9];
        String tn2 = params[10];
        String tn3 = params[11];
        Toast.makeText(reg,id+fname+lname+age+phone+address+email+pass+state,Toast.LENGTH_LONG).show();
        String reg_url = params[12];
     try {
            URL url = new URL(reg_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

            String data = URLEncoder.encode("id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8")+"&"
                    +URLEncoder.encode("fname","UTF-8")+"="+URLEncoder.encode(fname,"UTF-8")+"&"
                    +URLEncoder.encode("lname","UTF-8")+"="+URLEncoder.encode(lname,"UTF-8")+"&"
                    +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")+"&"
                    +URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                    +URLEncoder.encode("age","UTF-8")+"="+URLEncoder.encode(age,"UTF-8")+"&"
                    +URLEncoder.encode("t_num_1","UTF-8")+"="+URLEncoder.encode(tn1,"UTF-8")+"&"
                    +URLEncoder.encode("t_num_2","UTF-8")+"="+URLEncoder.encode(tn2,"UTF-8")+"&"
                    +URLEncoder.encode("t_num_3","UTF-8")+"="+URLEncoder.encode(tn3,"UTF-8")+"&"
                    +URLEncoder.encode("address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+"&"
                    +URLEncoder.encode("state","UTF-8")+"="+URLEncoder.encode(state,"UTF-8")+"&"
                    +URLEncoder.encode("phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            InputStream is = httpURLConnection.getInputStream();
            is.close();
            httpURLConnection.disconnect();


        } catch (IOException e) {
            e.printStackTrace();
        }



        return null;
    }


}
