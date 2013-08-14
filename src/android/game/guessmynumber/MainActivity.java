package android.game.guessmynumber;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

//TODO: Only pause music if all activities invisible
public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startService(new Intent(this, MyMusic.class));
		Toast.makeText(this, "oncreate", Toast.LENGTH_SHORT).show();
	}

	/**On setting clicked open settingActivity**/
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.menu_settings:
			Intent intent = new Intent(this , SettingActivity.class);
			startActivity(intent);
			break;
		}
		return super.onMenuItemSelected(featureId, item);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//connect the menu to res/menu/settings.xml
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	/**On start button clicked**/
	public void startGame(View v){
		
		Intent intent = new Intent(MainActivity.this, GameActivity.class);
		startActivity(intent);
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MyMusic MM = new MyMusic();
		MM.pauseSong();
		
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		startService(new Intent(this, MyMusic.class));
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		stopService(new Intent(this, MyMusic.class));
	}

}
