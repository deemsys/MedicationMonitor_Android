package com.example.medicationmonitor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;



 
public class RegisterActivity extends Activity {
	
	private EditText username;
    private EditText email;
    private EditText mobile;
    private EditText pass;
    private EditText confirmpass;
    
  // LoginDataBaseAdapter patient;
    private Button signup;

   
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.register);
        
        
     //   patient=new LoginDataBaseAdapter(this);
	//	patient=patient.open();
		
          registerValid();
          Spinner spinner = (Spinner) findViewById(R.id.spinner);
          ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.age_array, android.R.layout.simple_spinner_item);
             adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
           spinner.setAdapter(adapter);
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
    	//String userName=username.getText().toString();
		//String password=pass.getText().toString();
		//String confirmPassword=confirmpass.getText().toString();
		//String email1=email.getText().toString();
		//String mob=mobile.getText().toString();
    	//patient.insertEntry(userName, password);
	    Toast.makeText(getApplicationContext(), "Account Successfully Created ", Toast.LENGTH_LONG).show();
	    
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

   // @Override
	//protected void onDestroy() {
		// TODO Auto-generated method stub
		//super.onDestroy();
		
		//patient.close();
	//}

}

     


	
	


