package com.example.medicationmonitor;




import com.example.medicationmonitor.LoginDataBaseAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

public class LoginActivity extends Activity {
	Button signin,signup;
	LoginDataBaseAdapter loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
	     loginDataBaseAdapter=loginDataBaseAdapter.open();
        signin=(Button)findViewById(R.id.signin);
        signup=(Button)findViewById(R.id.signup);
        signin.setOnClickListener(new View.OnClickListener() {
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    			final TextView username =(TextView)findViewById(R.id.username);
        		final TextView password =(TextView)findViewById(R.id.password);
        		String uname = username.getText().toString();
        		String pass =  password.getText().toString();
        		String storedPassword=loginDataBaseAdapter.getSinlgeEntry(uname);
        		if(!uname.equals("")  && !pass.equals("")&&pass.equals(storedPassword))
        			startActivity(new Intent(LoginActivity.this,welcomeActivity.class).putExtra("usr",(CharSequence)uname));
        		 else 
        			Toast.makeText(LoginActivity.this,"Invalid UserName or Password", Toast.LENGTH_LONG).show();
    		}
		});
	
	
       
        
    signup.setOnClickListener(new View.OnClickListener() {
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			/// Create Intent for SignUpActivity  and Start The Activity
			Intent intentSignUP=new Intent(getApplicationContext(),RegisterActivity.class);
			startActivity(intentSignUP);
			}
		});
	}

    protected void onDestroy() {
		super.onDestroy();
	    // Close The Database
		loginDataBaseAdapter.close();
	}       
   
}
