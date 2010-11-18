package edu.gatech.mfa;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import edu.gatech.barcodemanager.IntentIntegrator;
import edu.gatech.barcodemanager.IntentResult;
import edu.gatech.mfautils.Credentials;
import edu.gatech.mfautils.CredentialsInterface;
import edu.gatech.mfautils.GenerateKey;

public class MFAClientActivity extends Activity {
	
	private static final String TAG = "MFAMobile";
	private Button genIDButton;
	private Button scanBCButton;
	Context mContext;
	CredentialsInterface credentials;
	

	
	String uniqueID;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.i(TAG, "Application Launched");
        
        mContext = this;
        
        genIDButton = (Button) findViewById(R.id.id_auth);
        scanBCButton = (Button) findViewById(R.id.barcode_auth);
        
        genIDButton.setOnClickListener(genIDButtonListener);
		scanBCButton.setOnClickListener(scanBCButtonListener); 
		
		setExtras();
		
      
    }
    
    
    /**
     * Method to get values from the intent bundle
     * */

    private void setExtras() {
		// TODO Auto-generated method stub
    	
    	//First Extract the bundle from intent
    	Bundle bundle = getIntent().getExtras();
    	
    	if(bundle != null){
    	//Next extract the values using the key as
    	uniqueID = bundle.getString("PwdHash");
    	Log.i(TAG, uniqueID);
    	}
    	
	}


	/**
     * Button listener event for the generate id button
     * invokes the function to generate unique id and  
     * shows it to the user in a alert dialog
     * */
    
    private Button.OnClickListener genIDButtonListener = new Button.OnClickListener() {
    	   
		@Override
		public void onClick(View v) {
			

		}
	};	

    /**
     * Button listener event for the scan barcode button
     * invokes barcode scanner and returns scan results
     * */ 
	
    private Button.OnClickListener scanBCButtonListener = new Button.OnClickListener() {
  	   
		@Override
		public void onClick(View v) {
			SessionInfo.barcodeInvoked = true;
			IntentIntegrator.initiateScan(MFAClientActivity.this, "Barcode Application Error", 
						"The application does not exists on your phone. \nWould you like to downoad it.", 
						"Yes", "NO", "QR_CODE");	
		}
	};	
	
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(!SessionInfo.barcodeInvoked)
			SessionInfo.reset();
	}


	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		if(!(SessionInfo.isLoggedIn))
		{
			Intent myIntent = new Intent(MFAClientActivity.this, LoginActivity.class);
			MFAClientActivity.this.startActivity(myIntent);  
		}
		
	}


	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		
		SessionInfo.barcodeInvoked =false;
		credentials = new Credentials(mContext);
		
		try{
			
		   IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
		   if (scanResult != null) {
			   
			   Log.i(TAG, "Scan Results not null"+scanResult.getContents().toString() );
			   System.out.println(credentials.getDeviceID()+ 
					  SessionInfo.passwordHash+ 
					   scanResult.getContents().toString());
			   
			   String key = GenerateKey.GenerateUniqueID(credentials.getDeviceID(), 
					   SessionInfo.passwordHash, 
					   scanResult.getContents().toString());
			   
			   System.out.println(key);
			   launchDialog("Barcode Scan Results",key );
			   
		    // else continue with any other code you need in the method
		 
		  }} catch(Exception e){
			  Log.e(TAG, "Barcode Error:"+e.toString());
		  }
	}

    private void launchDialog(String title,String message){
    	
    	Dialog dialog = new Dialog(mContext);

    	dialog.setContentView(R.layout.custom_dialog);
    	dialog.setTitle(title);

    	TextView text = (TextView) dialog.findViewById(R.id.text);
    	text.setText(message);
    	ImageView image = (ImageView) dialog.findViewById(R.id.image);
    	image.setImageResource(R.drawable.mfaicon);
    	
    	dialog.show();
    	
    }
    
}