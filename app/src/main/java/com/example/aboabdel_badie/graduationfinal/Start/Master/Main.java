package com.example.aboabdel_badie.graduationfinal.Start.Master;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Login;
import com.example.aboabdel_badie.graduationfinal.Start.Loginp.Shared;
import com.example.aboabdel_badie.graduationfinal.Start.Masterp.Contact;
import com.example.aboabdel_badie.graduationfinal.Start.Masterp.Food;
import com.example.aboabdel_badie.graduationfinal.Start.Masterp.Health;
import com.example.aboabdel_badie.graduationfinal.Start.Masterp.NewPhone;
import com.example.aboabdel_badie.graduationfinal.Start.Masterp.Sport;
import com.example.aboabdel_badie.graduationfinal.Start.Masterp.Treat;
import com.example.aboabdel_badie.graduationfinal.Start.Tasks.Phonetask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class Main extends AppCompatActivity {

    Button btnHealth, btnFood, btnTreatment, btnSport, btnContact, btnEmergency;
    RequestQueue requestQueue;
    RequestQueue requestQueue1;
    GPSTracker gpsTracker;
    public double latitude;
    public double longitude;
    Shared shared;

    final ArrayList<String> phones = new ArrayList<String>();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mastermain);
        shared = new Shared(this);
        LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        //notification To send report
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
/*to make daily notification
Calendar cal = Calendar.getInstance();
 cal.set(Calendar.HOUR_OF_DAY, 9);
 cal.set(Calendar.MINUTE, 30);
 cal.set(Calendar.SECOND, 0);
 */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 10);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

        //get location from gps
        //check gps is enable
       /* if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
        }else {
            gpsTracker = new GPSTracker(getApplicationContext());

            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();
            } else {
                gpsTracker.showSettingsAlert();
            }
        }*/

        requestQueue = Volley.newRequestQueue(getApplicationContext());



        btnHealth = (Button) findViewById(R.id.btnHealth);
        btnHealth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, Health.class);
                startActivity(i);
            }
        });
        btnFood = (Button) findViewById(R.id.btnFood);
        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, Food.class);
                startActivity(i);
            }
        });
        btnTreatment = (Button) findViewById(R.id.btnTreatment);
        btnTreatment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main.this, Treat.class);
                startActivity(i);
            }
        });
        btnSport = (Button) findViewById(R.id.btnSport);
        btnSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, Sport.class));
            }
        });
        btnContact = (Button) findViewById(R.id.btnContact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main.this, Contact.class));

            }
        });
        btnEmergency = (Button) findViewById(R.id.btnEmergency);

        btnEmergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(Main.this);
                builderSingle.setIcon(R.drawable.contact);
                builderSingle.setTitle("Select Emergency Contact:-");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Main.this, android.R.layout.select_dialog_item);
                // number 1 -> change it with the id of the user
                String url = getString(R.string.connect) + "phone.php?uid=" + shared.getId();
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("result");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String phone = jsonObject.getString("phone");
                                arrayAdapter.add(phone);
                                //phones.add(phone);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                requestQueue.add(jsonObjectRequest);

                arrayAdapter.add("Add New Number");

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);
                        if (strName == "Add New Number") {
                            Intent intent = new Intent(Main.this, NewPhone.class);
                            startActivity(intent);
                        } else {
                            //**** DELETE this comment if you run from real device not emulator ... if you run from emulator you get an error
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + strName));
                            if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                // TODO: Consider calling
                                //    ActivityCompat#requestPermissions
                                // here to request the missing permissions, and then overriding
                                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                //                                          int[] grantResults)
                                // to handle the case where the user grants the permission. See the documentation
                                // for ActivityCompat#requestPermissions for more details.
                                return;
                            }
                            startActivity(intent);
                            //Toast.makeText(getApplicationContext(), "Call " + strName, Toast.LENGTH_LONG).show();
                        }
                    }
                });
                builderSingle.show();
            }
        });
        btnEmergency.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new Phonetask(Main.this).execute(getString(R.string.connect) + "phone.php?uid="
                        +shared.getId());





                /*
                SmsManager smsManager = SmsManager.getDefault();
                String massage = " I'm " + new Shared(getApplicationContext()).getName() + " in location at -->longitude :- "
                        + longitude + ".-->latitude :-" + latitude + "." + ".  help me plz";
                int i;
                for (i = 0; i < phones.size(); i++) {
                    //Toast.makeText(getApplicationContext(),phones.get(i),Toast.LENGTH_LONG).show();
                    //smsManager.sendTextMessage(phones.get(i), null, massage, null, null);
                }*/
                return  true;

            }
        });


    }
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this,Login.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sign_up, menu);

        // return true so that the menu pop up is opened
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.sign_up:
                startActivity(new Intent(Main.this,Login.class));
                break;
        }
        return true;
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it to can send Location?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    public void parsephones(String s) {
        JSONObject jsonObject ;
        try {
            jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            int count = 0 ;
            while (count<jsonArray.length()){
                JSONObject jo = jsonArray.getJSONObject(count);
                String phone = jo.getString("phone");
                phones.add(phone);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LocationManager manager = (LocationManager) getSystemService( Context.LOCATION_SERVICE );
        //Toast.makeText(getApplicationContext(), phones.get(0), Toast.LENGTH_LONG).show();
        SmsManager smsManager = SmsManager.getDefault();
        if ( !manager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            buildAlertMessageNoGps();
           // Toast.makeText(this,"must enable gps to send massage",Toast.LENGTH_LONG).show();
        }else {
            gpsTracker = new GPSTracker(getApplicationContext());

            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();
            } else {
                gpsTracker.showSettingsAlert();
            }
            String massage = " I'm " + shared.getName() + " in location at -->longitude :- "
                    + longitude + ".-->latitude :-" + latitude + "." + ".  help me plz";
            int i;
            for (i = 0; i < phones.size(); i++) {
                //Toast.makeText(getApplicationContext(),phones.get(i),Toast.LENGTH_LONG).show();
                smsManager.sendTextMessage(phones.get(i), null, massage, null, null);
            }
        }
    }
}
