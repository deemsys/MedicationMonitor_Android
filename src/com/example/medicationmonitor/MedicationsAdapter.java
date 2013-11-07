package com.example.medicationmonitor;

import java.util.ArrayList;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;

import android.widget.TextView;

public class MedicationsAdapter extends BaseAdapter {
	 
	 Context context;
private	 ArrayList medname;
	private LayoutInflater inflator;
	 public MedicationsAdapter(Context context, ArrayList medname
	            ) {
		
		inflator = LayoutInflater.from(context);		
	        this.medname=medname;
	 }
	 @Override
	   public int getCount() {
	        return medname.size();
	    }	
	 @Override
	    public Medications getItem(int position) {
	        return null;
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return position;
	    }
	    public View getView(int position, View convertView, ViewGroup parent) {
	    
	  /*  TextView txtmed;
	    inflator = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
        View itemView = inflator.inflate(R.layout.medslist_row, parent, false);
	    
        txtmed = (TextView) itemView.findViewById(R.id.medicine);
        
        
        txtmed.setText(medname[position]);
	    
	    return itemView;*/
	    ViewHolder holder;
        if (convertView == null) {
            convertView = inflator.inflate(R.layout.medslist_row, null);
            holder = new ViewHolder();
            holder.medtitle = (TextView) convertView.findViewById(R.id.medicine);
           
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
 
        AddMed  addItem = (AddMed)medname.get(position);
        holder.medtitle.setText(addItem.getName());
       
         return convertView;
        
	    }
        static class ViewHolder {
            TextView medtitle;
	    
	    }
	
}

