package com.example.medicationmonitor;



import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;

import android.view.View;
import android.widget.Button;
import android.net.Uri;
import android.database.Cursor;
import android.graphics.Bitmap;

import android.util.Log;


import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.EditText;


import android.view.Gravity;

import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;

import android.widget.PopupWindow;

public  class AddMedications extends Activity implements OnCheckedChangeListener{
	Uri selectedImageUri;
	String  selectedPath;
	EditText medname;
	EditText meddir;
	Button img,save;
	ImageView imgview,photoimg;
	SegmentedRadioGroup segmentText;
	RadioButton once,daily;
	 Button choosephoto,takephoto;
	 private PopupWindow mpopup;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.addmedication);
	    medname=(EditText)findViewById(R.id.medname);
	    meddir=(EditText)findViewById(R.id.meddir);
	   save=(Button)findViewById(R.id.save);
	    final String medidet = medname.getText().toString();
	    
	    segmentText = (SegmentedRadioGroup) findViewById(R.id.radioGroup1);
        segmentText.setOnCheckedChangeListener(this);
	    imgview=(ImageView)findViewById(R.id.imgview);
        once=(RadioButton)findViewById(R.id.once);
        daily=(RadioButton)findViewById(R.id.daily);
	    
        once.setTag(R.drawable.onceedit1);
        daily.setTag(R.drawable.daily);
	    
        
        
     

       

      
	 
        
        
        
        
        
        
	    img=(Button)findViewById(R.id.captureimg);
	    img.setOnClickListener(new OnClickListener()
	    {            
          
			@Override
            public void onClick(View arg0) 
            {
				InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                        INPUT_METHOD_SERVICE);
imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
				
                View popUpView = getLayoutInflater().inflate(R.layout.popoverimg, null); 
                mpopup = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true); 
                mpopup.setAnimationStyle(android.R.style.Animation_Dialog);   
                mpopup.showAtLocation(popUpView, Gravity.CENTER, 0, 0);    // Displaying popup
                
                Button choosephoto = (Button)popUpView.findViewById(R.id.choosephoto);
                choosephoto.setOnClickListener(new OnClickListener() 
                {                    
                	
                	@Override
                    public void onClick(View v) 
                    {
                		mpopup.dismiss();
                    	openGallery(10);
                    	
                    }
                	
                });  
                	   
                	  
                
                Button takephoto = (Button)popUpView.findViewById(R.id.takephoto);
                takephoto.setOnClickListener(new OnClickListener() 
                {   
                	
                	 @Override
              	   public void onClick(View v) {
                	
                		 mpopup.dismiss();
              	    // TODO Auto-generated method stub
              	    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE); 
              	                startActivityForResult(cameraIntent, 100); 
              	           
              	   }
                	
              	  });  
                 	   
                	
                    
            }
        });  
	    
	    
    }
	
	public void openGallery(int req_code){
		 
        Intent intent = new Intent();
 
        intent.setType("image/*");
 
        intent.setAction(Intent.ACTION_GET_CONTENT);
 
        startActivityForResult(Intent.createChooser(intent,"Select file to upload "), req_code);
 
   }
	
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		 mpopup.dismiss();
			if (checkedId == R.id.once) {
				  int imageId = (Integer) once.getTag();
		          
		            imgview.setBackgroundResource(imageId);
			
			} else if (checkedId == R.id.daily) {
				 int imageId = (Integer) daily.getTag();
		        
		            imgview.setBackgroundResource(imageId);
			
			} 
		} 
		
	
	  
	 public void onClickAdd (View btnAdd) {

	        String medicineName = medname.getText().toString();
	        String medicinedir = meddir.getText().toString();

	        if ( medicineName.length() != 0 && medicinedir.length() != 0 ) {

	            Intent newIntent = getIntent();
	            newIntent.putExtra("tag_medicine_name", medicineName);
	           newIntent.putExtra("tag_medicine_dir",medicinedir);

	            this.setResult(RESULT_OK, newIntent);

	            finish();
	        }
	    }


	
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		 
		 
		 
        if (resultCode == RESULT_OK) {
         if(data.getData() != null){
           selectedImageUri = data.getData();
         }else{
          Log.d("selectedPath1 : ","Came here its null !");
       //   Toast.makeText(getApplicationContext(), "failed to get Image!", 500).show();
         }
          
         if (requestCode == 100 && resultCode == RESULT_OK) {  
                Bitmap photo = (Bitmap) data.getExtras().get("data"); 
                selectedPath = getPath(selectedImageUri);
                photoimg.setImageURI(selectedImageUri);
                Log.d("selectedPath1 : " ,selectedPath);
 
            } 
          
            if (requestCode == 10)
 
            {
 
               selectedPath = getPath(selectedImageUri);
               photoimg.setImageURI(selectedImageUri);
               Log.d("selectedPath1 : " ,selectedPath);
 
            }
 
        }
 
    }
 
 
 @SuppressWarnings("deprecation")
public String getPath(Uri uri) {
 
        String[] projection = { MediaStore.Images.Media.DATA };
 
        Cursor cursor = managedQuery(uri, projection, null, null, null);
 
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
 
        cursor.moveToFirst();
 
        return cursor.getString(column_index);
 
    }
 /*@Override
 public boolean onTouchEvent(MotionEvent event) {
     InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                                                     INPUT_METHOD_SERVICE);
     imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
     mpopup.dismiss();
     
     return true;
 }
*/
	
	
}


