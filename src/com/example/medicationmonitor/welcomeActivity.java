package com.example.medicationmonitor;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;


import android.view.View;

import android.widget.TextView;



public class welcomeActivity extends Activity  {
	TextView textview;
	TextView signout; 
	// private FragmentTabHost mTabHost;

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setting default screen to login.xml
        setContentView(R.layout.welcome);
        
     /*   mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.tabFrameLayout);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab1").setIndicator("Tab 1",
                        getResources().getDrawable(android.R.drawable.star_on)),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab2").setIndicator("Tab 2",
                        getResources().getDrawable(android.R.drawable.star_on)),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab3").setIndicator("Tab 3",
                        getResources().getDrawable(android.R.drawable.star_on)),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab4").setIndicator("Tab 4",
                        getResources().getDrawable(android.R.drawable.star_on)),
                FragmentTab.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("tab5").setIndicator("Tab 5",
                        getResources().getDrawable(android.R.drawable.star_on)),
                FragmentTab.class, null);


        mTabHost.addTab(
                mTabHost.newTabSpec("tab7").setIndicator("Tab 7",
                        getResources().getDrawable(android.R.drawable.star_on)),
                FragmentTab.class, null);
       
      
        
      */
        
        Intent in = getIntent();
        if (in.getCharSequenceExtra("usr") != null) {
        	final TextView setmsg = (TextView)findViewById(R.id.welcome);
        	setmsg.setText("Welcome: "+in.getCharSequenceExtra("usr"));	        	
        }
      TextView loginScreen = (TextView) findViewById(R.id.signout);
   	 
        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View arg0) {
            	/// Create Intent for SignUpActivity  and Start The Activity
    			Intent sigout=new Intent(getApplicationContext(),LoginActivity.class);
    			startActivity(sigout);                 // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
	}
}

	        	
	            	