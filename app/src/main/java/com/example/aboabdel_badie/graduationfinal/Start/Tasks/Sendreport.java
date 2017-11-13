package com.example.aboabdel_badie.graduationfinal.Start.Tasks;

import android.os.AsyncTask;

import com.example.aboabdel_badie.graduationfinal.Start.Masterp.Health;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Abo Abdel-Badie on 5/31/2017.
 */

public class Sendreport extends AsyncTask<String,Void,String> {
    Health health ;

    public Sendreport(Health health) {
        this.health = health;
    }

    @Override
    protected String doInBackground(String... params) {
        String id = params[0];
        String pres = params[1];
        String suger = params[2];
        String report = params[3];
      String report_url = params[4];
   try {
            URL url = new URL(report_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter =new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

            String data = URLEncoder.encode("suger_rate","UTF-8")+"="+URLEncoder.encode(suger,"UTF-8")+"&"
                    +URLEncoder.encode("pressure_rate","UTF-8")+"="+URLEncoder.encode(pres,"UTF-8")+"&"
                    +URLEncoder.encode("user_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
    }
}
