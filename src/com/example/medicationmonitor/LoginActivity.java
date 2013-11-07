package com.example.medicationmonitor;




import com.example.medicationmonitor.LoginDataBaseAdapter;
import com.example.medicationmonitor.SharedPrefManager;


import android.os.Bundle;

import android.app.Activity;

import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.CheckBox;


import android.widget.TextView;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;




public class LoginActivity extends Activity {
	Button signin,signup;
	LoginDataBaseAdapter loginDataBaseAdapter;
	SharedPrefManager sm;
    CheckBox chkbx;
    Boolean checked;
    final Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        SharedPrefManager.Init(this);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
      
	     loginDataBaseAdapter=loginDataBaseAdapter.open();
        signin=(Button)findViewById(R.id.signin);
        signup=(Button)findViewById(R.id.signup);
        chkbx=(CheckBox)findViewById(R.id.checkbox1);
        final TextView username =(TextView)findViewById(R.id.username);
		final TextView password =(TextView)findViewById(R.id.password);
		String uname = username.getText().toString();
		String pass =  password.getText().toString();
		SharedPrefManager.SetName(uname); 
		    SharedPrefManager.SetPass(pass); 
		 SharedPrefManager.StoreToPref();
		 if(chkbx.isChecked()){
				
 			SharedPrefManager.LoadFromPref();
 				 
 			
 				 uname= SharedPrefManager.GetName();
 					username.setText(uname);
 					
 					pass= SharedPrefManager.GetPass();
 			       
 			     password.setText(pass);
 				
 	        
 				 }
        signin.setOnClickListener(new View.OnClickListener() {
            
        	public void onClick(View v) {
        		
        		String uname = username.getText().toString();
        		String pass =  password.getText().toString();
        		SharedPrefManager.SetName(uname); 
        		    SharedPrefManager.SetPass(pass); 
        		 SharedPrefManager.StoreToPref();
    		
    			
    			
    		
        	
        		
               String storedPassword=loginDataBaseAdapter.getSinlgeEntry(uname);
        		if(!uname.equals("")  && !pass.equals("")&&pass.equals(storedPassword))
        			startActivity(new Intent(LoginActivity.this,welcomeActivity.class).putExtra("usr",(CharSequence)uname));
        		 else 
        		 {
        			 final Dialog dialog = new Dialog(context);
        			 dialog.setContentView(R.layout.custom_dialog);
        			 dialog.setTitle("         Oh Snap      ");
        			 TextView txt = (TextView) dialog.findViewById(R.id.alert);
        			  txt.setText("Invalid Username And Password.");
        			  Button dialogButton = (Button) dialog.findViewById(R.id.button1);
        			  dialogButton.setOnClickListener(new OnClickListener() {
        				  public void onClick(View vd) {
        					   dialog.dismiss();
        					   
        				   }
        				  
        				   });
        				  
        				   
        				  
        				                  dialog.show();

        					   

        				  }


        			  }


       
        	
		});

	
       signup.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			
			Intent intentSignUP=new Intent(getApplicationContext(),RegisterActivity.class);
			startActivity(intentSignUP);
		}
		});
	}

    protected void onDestroy() {
		super.onDestroy();
	    
		loginDataBaseAdapter.close();
	}
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                                                        INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        return true;
    }
 
}
