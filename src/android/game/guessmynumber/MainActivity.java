package android.game.guessmynumber;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


//TODO: Only pause music if all activities invisible
public class MainActivity extends Activity {
	String name;
	EditText Name;
	Settings setting; 
	SharedPreferencesHelper pref;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Name = (EditText)findViewById(R.id.txt_UserName);
		pref = new SharedPreferencesHelper(getApplicationContext());
		setting = new Settings(getApplicationContext());	
		BackgroundMusic.initMusic(getApplicationContext());
	}

	/**On setting clicked open settingActivity**/
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		BackgroundMusic.setFalse();
		switch(item.getItemId()){
		case R.id.menu_settings:
			Intent intent = new Intent(this , SettingActivity.class);
			startActivity(intent);
			break;
		case R.id.menu_score:
			Intent score = new Intent(this , ScoreActivity.class);
			startActivity(score);
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
		
		if(Name.getText().toString().equals(null) ||Name.getText().toString().equals("")){
			name = "<No Name>";
		}
		else{
			name = Name.getText().toString();
		}
		BackgroundMusic.setFalse();
		Intent intent = new Intent(MainActivity.this, GameActivity.class);
		intent.putExtra("name", name);
		startActivity(intent);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(setting.getBackGroundMusic()){
			BackgroundMusic.startMusic();
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(setting.getBackGroundMusic()){
			BackgroundMusic.onActivityPause();
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		if(setting.getBackGroundMusic()){
			BackgroundMusic.stopMusic();
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(setting.getBackGroundMusic()){
			if(keyCode == KeyEvent.KEYCODE_BACK){
				BackgroundMusic.stopMusic();
			}
		}
		return super.onKeyDown(keyCode, event);
	}
}
