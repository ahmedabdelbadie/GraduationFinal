package com.example.aboabdel_badie.graduationfinal.Start.Tasks;

import android.os.AsyncTask;
import android.widget.Toast;

import com.example.aboabdel_badie.graduationfinal.Start.Master.Main;
import com.example.aboabdel_badie.graduationfinal.Start.Start.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Abo Abdel-Badie on 6/28/2017.
 */

public class Phonetask  extends AsyncTask<String,Void,String>  {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //Toast.makeText(main,s, Toast.LENGTH_LONG).show();
        main.parsephones(s);
    }

    @Override
    protected String doInBackground(String... params) {
        String phone_url = params[0];
        URL url = null;
        try {
            url = new URL(phone_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Phonetask(Main main) {
        this.main = main;
    }

    Main main;
}
