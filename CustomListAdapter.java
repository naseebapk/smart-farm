package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomListAdapter extends ArrayAdapter<String>{
	
	private final UserH context;
	private final String[] id;
	private final String[] name;
	private final String[] description;
	private final Bitmap[] image;
	
	public CustomListAdapter(UserH context, String[] id, String[] name,String[] description,Bitmap[] image) {
		//
		super(context, R.layout.machine, id);
		this.context=context;

		this.id=id;
		this.name=name;
		this.description=description;
		this.image=image;
	}
	public View getView(final int position, View view, ViewGroup parent) {
		LayoutInflater inflater=context.getLayoutInflater();
		View rowView=inflater.inflate(R.layout.machine, null,true);
		
		
		ImageView catimg = (ImageView) rowView.findViewById(R.id.imageView2);
		TextView nm = (TextView) rowView.findViewById(R.id.textView5);
		TextView des=(TextView)rowView.findViewById(R.id.textView6);
		

		nm.setText(name[position]);
		des.setText(description[position]);
		catimg.setImageBitmap((image[position]));
	//	Toast.makeText(this, imgid[position]+"", Toast.LENGTH_SHORT).show();

		
		return rowView;
		
	};
	
	 
}
