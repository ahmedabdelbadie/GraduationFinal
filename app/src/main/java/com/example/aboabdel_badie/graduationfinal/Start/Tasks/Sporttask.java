package com.example.aboabdel_badie.graduationfinal.Start.Tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.example.aboabdel_badie.graduationfinal.Start.Masterp.Food;
import com.example.aboabdel_badie.graduationfinal.Start.Masterp.Sport;

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
 * Created by Abo Abdel-Badie on 6/24/2017.
 */

public class Sporttask extends AsyncTask<String,Void,String> {
    Sport sport;
    private ProgressDialog dialog;
    public Sporttask(Sport s) {
        this.sport = s;
        dialog = ProgressDialog.show(s, "Download", "Please Wait ......", true, false);

    }
    @Override
    protected String doInBackground(String... params) {
        String id = params[0];
        String sport_url = params[1];
        URL url ;
        try {
            url = new URL(sport_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String data = URLEncoder.encode("ID", "UTF-8")+"="+URLEncoder.encode(id, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();
            InputStream is = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
            String response ="";
            String line="" ;
            while (((line = bufferedReader.readLine()) != null)) {
                response += line;
            }
            bufferedReader.close();

            is.close();
            httpURLConnection.disconnect();

            return response;
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

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //dialog = new ProgressDialog(clogin);
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
        sport.parsefinish(s);
    }
}
