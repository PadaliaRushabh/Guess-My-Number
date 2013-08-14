package android.game.guessmynumber;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

//TODO: Stop and Destroy Music
public class MainActivity extends Activity {
	
	//MediaPlayer bab;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//bab = MediaPlayer.create(MainActivity.this , R.raw.music);
		//bab.setLooping(true);
		//bab.start();
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
