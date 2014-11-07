package com.example.gcm_tester;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	ShareExternalServer appUtil;
	String regId;
	Context context;
	@SuppressWarnings("rawtypes")
	AsyncTask shareRegidTask;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        appUtil = new ShareExternalServer();

		regId = getIntent().getStringExtra("regId");
		Log.d("MainActivity", "regId: " + regId);
		
		context = this;
		
		shareRegidTask = new GetNotification().execute();
		
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    class GetNotification extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... arg0) {
			String result = appUtil.shareRegIdWithAppServer(context, regId);
			return result;
		}
		
		@Override
		protected void onPostExecute(String result) {
			shareRegidTask = null;
			Toast.makeText(getApplicationContext(), result,
					Toast.LENGTH_LONG).show();
		}
		
	}

}
