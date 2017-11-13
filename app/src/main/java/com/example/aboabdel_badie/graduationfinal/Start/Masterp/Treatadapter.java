package com.example.aboabdel_badie.graduationfinal.Start.Masterp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.aboabdel_badie.graduationfinal.R;
import com.example.aboabdel_badie.graduationfinal.Start.Model.TreatModel;

import java.util.ArrayList;

public class Treatadapter extends ArrayAdapter<TreatModel> {
    Context c;
    int layoutResourceId;
    ArrayList<TreatModel> data= null ;

    public Treatadapter(Context context, int layoutResourceId, ArrayList<TreatModel> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.c = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TreatHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)c).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new TreatHolder();

            holder.tvid = (TextView) row.findViewById(R.id.txtid);
            holder.tvname = (TextView) row.findViewById(R.id.txtname);
            holder.tvtime = (TextView) row.findViewById(R.id.txttime);

            row.setTag(holder);
        }
        else
        {
            holder = (TreatHolder) row.getTag();
        }

        TreatModel treatModel = data.get(position);
        holder.tvid.setText(treatModel.getId());
        holder.tvname.setText(treatModel.getName());
        holder.tvtime.setText(treatModel.getTime());

        return row;
    }

    static class TreatHolder
    {
        TextView tvid;
        TextView tvname;
        TextView tvtime;
    }

}



