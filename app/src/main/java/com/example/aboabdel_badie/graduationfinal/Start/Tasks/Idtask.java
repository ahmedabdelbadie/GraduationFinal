package com.example.aboabdel_badie.graduationfinal.Start.Tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Regp.Id;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Abo Abdel-Badie on 4/18/2017.
 */

public class Idtask extends AsyncTask<String,Void,String> {


    Id cid ;
    public Idtask(Id cid) {
        this.cid = cid;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {


        String check_url = params[1];

        String id = params[0];

        try {
            URL url = new URL(check_url) ;
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            String data = URLEncoder.encode("ID","UTF-8")+"="+URLEncoder.encode(id,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            InputStream is = httpURLConnection.getInputStream() ;

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
            String response = "";
            String line = "";
            while ((( line = bufferedReader.readLine()) !=null)){
                response+=line ;
            }
            bufferedReader.close();

            is.close();
            httpURLConnection.disconnect();
            return response ;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        //Toast.makeText(cid,response,Toast.LENGTH_LONG).show();

        cid.parsecheck(response);

    }
}
