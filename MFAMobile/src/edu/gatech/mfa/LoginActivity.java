package edu.gatech.mfa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gatech.mfautils.Credentials;
import edu.gatech.mfautils.CredentialsInterface;
import edu.gatech.mfautils.CryptiUtils;

public class LoginActivity extends Activity {
	
	private static final String TAG = "MFAMobile";
	
	private Button loginButton;
	private TextView name;
	private TextView password;
	CredentialsInterface credentials;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.login_layout);
		 
		 context = this;
		 
		 ImageView mfaLogo = (ImageView) findViewById(R.id.imagelogo);
		 mfaLogo.setImageResource(R.drawable.mfared);
		 
		 loginButton = (Button) findViewById(R.id.loginbutton);
		 loginButton.setOnClickListener(loginButtonListener);
		 
		 name = (TextView) findViewById(R.id.uidtext);
		 password = (TextView) findViewById(R.id.pwtext);
	}
	
	
	/**
	 * Click event listener  for the login button
	 * */
	
	 private Button.OnClickListener loginButtonListener = new Button.OnClickListener() {
	  	   
			@Override
			public void onClick(View v) {
				
				if(isValided() && isAuthenticated()){
				Intent myIntent = new Intent(LoginActivity.this, MFAClientActivity.class);
				LoginActivity.this.startActivity(myIntent);  
				}
				
			}
		};	
		
		
		
		@Override
		public void onBackPressed() {
		// do something on back.

		return;
		}
		

	/**
	 * Funtion that authenticates a user by matching input credentials
	 * with datastore credentials
	 * 
	 *  @return boolean ture on successful match or false if unsuccessful 
	 * */
	private boolean isAuthenticated() {
			// TODO Auto-generated method stub
		
		credentials = new Credentials(context);
		String pwd = CryptiUtils.getMD5Hash(password.getText().toString());
		
		
		if(!pwd.equals(credentials.getPasswordHashFromDataSource(name.getText().toString())))
		{
			showAlert("Login Error", "Credentials do not match. \nPlease try again");
			return false;
		}
		
		// Continue if match is found 
		SessionInfo.setInfo(name.getText().toString(), pwd);
		return true;
		}


	/**
	 * Funtion to validate the input by the user
	 * 
	 * @return boolean true or false depending on user input
	 * */
	
	private boolean isValided() {
			// TODO Auto-generated method stub
		
			if (name.getText().length() ==0 && password.getText().length() == 0){
				showAlert("Login Error", "Invalid Input. \nPlease enter username & password");
				return false;
			}
		
			if(name.getText().length() ==0)
			{
				showAlert("Login Error", "Invalid Input. \nPlease enter username");
				return false;
			}
			
			if(password.getText().length() == 0){
				
				showAlert("Login Error", "Invalid Input. \nPlease enter password");
				return false;
				
			}
			
			return true;
			
				
		}

	/**
	 * Dialog alert to show appropriate error to the user
	 * */
	
	protected void showAlert(String title, String message) {
		// TODO Auto-generated method stub
		new AlertDialog.Builder(LoginActivity.this)
		.setTitle(title)
		.setMessage(message)
		.setNeutralButton("Ok",
		new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog,
		int which) {
		}}).show();
		
	}
		
	
	
	
}
