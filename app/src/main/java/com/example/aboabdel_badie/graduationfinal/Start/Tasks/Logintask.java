package com.example.aboabdel_badie.graduationfinal.Start.Tasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Login;
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
 * Created by Abo Abdel-Badie on 4/18/2017.
 */

public class Logintask extends AsyncTask<String,Void,String> {
    Login clogin;
    private ProgressDialog dialog;




    public Logintask(Login clogin) {
        this.clogin = clogin;
        //dialog = new ProgressDialog(clogin);
        dialog = ProgressDialog.show(clogin, "Log In", "Please Wait ........", true, false);
    }

    @Override
    protected void onPreExecute() {
        //super.onPreExecute();
        //this.dialog.setMessage("Progress start");
        this.dialog.show();
    }

    @Override
    protected String doInBackground(String... params) {
        String login_url = params[2];
        String email = params[0];
        String pass = params[1];

        try {
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            String data = URLEncoder.encode("email", "UTF-8")+"="+URLEncoder.encode(email, "UTF-8")+"&"
                    +URLEncoder.encode("pass", "UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
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
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        //Toast.makeText(clogin,response, Toast.LENGTH_LONG).show();

        if (response.equals(null)&&response.equals(""))
            Toast.makeText(clogin," Connection Field ", Toast.LENGTH_LONG).show();
        else
            clogin.parsecheck(response);




    }
}
