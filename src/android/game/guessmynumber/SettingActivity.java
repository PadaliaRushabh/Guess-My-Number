package android.game.guessmynumber;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class SettingActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		return super.onMenuItemSelected(featureId, item);
	}
	
	@Override
	/**Diaplay back button only**/
	public boolean onCreateOptionsMenu(Menu menu) {
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		return true;
	}
	
	/**set Result as OK and pass the control to the calling activity(main activity)**/
	public void onOkClick(View view){
		String value = new String(); 
		
		Intent data = new Intent();
		data.putExtra("settings", value);
		setResult(RESULT_OK , data);
		finish();
	}
}
