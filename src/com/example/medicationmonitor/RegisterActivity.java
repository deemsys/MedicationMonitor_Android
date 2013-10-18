package com.example.medicationmonitor;

import com.example.medicationmonitor.LoginDataBaseAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;



 
public class RegisterActivity extends Activity {
	 String []mydata1={"0-10","11-20","21-30","31-40","41-50","51-60","61-70","71-80","81-90","<90"};
	private EditText username;
    private EditText email;
    private EditText mobile;
    private EditText pass;
    private EditText confirmpass;
    
    LoginDataBaseAdapter loginDataBaseAdapter;
    private Button signup;

   
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
        
        
        loginDataBaseAdapter=new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		
          registerValid();
          Spinner spinner = (Spinner) findViewById(R.id.spinner);
          spinner.setAdapter(new MyAdapter(this, R.layout.custom_spinner, mydata1));
          
          
          
          
          
          
          
          
          
          
          
          //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
             //       this, R.array.age_array, android.R.layout.simple_spinner_item);
            // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
          //pinner.setAdapter(adapter);
    }
    
    
    public class MyAdapter extends ArrayAdapter<String>
    {
 
            public MyAdapter(Context context, int textViewResourceId, String[] objects) 
            {
                  super(context, textViewResourceId, objects);
            }
             
             
            @Override
            public View getDropDownView(int position, View convertView,ViewGroup parent)
            {
            return getCustomView(position, convertView, parent);
            }
 
            @Override
            public View getView(int position, View convertView, ViewGroup parent) 
            {
            return getCustomView(position, convertView, parent);
            }
 
        public View getCustomView(int position, View convertView, ViewGroup parent) 
        {
 
            LayoutInflater inflater=getLayoutInflater();
            View row=inflater.inflate(R.layout.custom_spinner, parent, false);
            TextView label=(TextView)row.findViewById(R.id.spintext);
            label.setText(mydata1[position]);
            return row;
            }
        
        }
    
    
    private void registerValid() {
        username = (EditText) findViewById(R.id.username);
        
       
	
        
        
        // TextWatcher would let us check validation error on the fly
        username.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(username);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        pass = (EditText) findViewById(R.id.password);
        // TextWatcher would let us check validation error on the fly
        pass.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(pass);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        
        confirmpass = (EditText) findViewById(R.id.confirmpass);
        // TextWatcher would let us check validation error on the fly
        confirmpass.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.hasText(confirmpass);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });
        
        email = (EditText) findViewById(R.id.email);
        email.addTextChangedListener(new TextWatcher() {
            // after every change has been made to this editText, we would like to check validity
            public void afterTextChanged(Editable s) {
                Validation.isEmailAddress(email, true);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

       mobile = (EditText) findViewById(R.id.mobile);
      mobile.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                Validation.isPhoneNumber(mobile, false);
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
            public void onTextChanged(CharSequence s, int start, int before, int count){}
        });

        signup = (Button) findViewById(R.id.signupreg);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                Validation class will check the error and display the error on respective fields
                but it won't resist the form submission, so we need to check again before submit
                 */
                if ( checkValidation () )
                    submitForm();
                else
                    Toast.makeText(RegisterActivity.this, "Enter All Required fields with Valid Details", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void submitForm() {
    	String userName=username.getText().toString();
		String password=pass.getText().toString();
	//	String confirmPassword=confirmpass.getText().toString();
		//String email1=email.getText().toString();
		//String mob=mobile.getText().toString();
    	loginDataBaseAdapter.insertEntry(userName, password);
	    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
	  // DialogClass dgcls=null;
	    //dgcls = new DialogClass(this,"Green","Account Successfully Created!");
	    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
	    
    }

    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(username)) ret = false;
        if (!Validation.isEmailAddress(email, true)) ret = false;
        if (!Validation.isPhoneNumber(mobile, false)) ret = false;
        if(!Validation.isequal(pass,confirmpass))ret=false;

        return ret;
    }

   @Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		loginDataBaseAdapter.close();
	}

}

     


	
	


