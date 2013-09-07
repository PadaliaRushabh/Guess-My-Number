package android.game.guessmynumber;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


//TODO: Only pause music if all activities invisible
public class MainActivity extends Activity {
	String name;
	EditText Name;
	Settings setting = new Settings(MainActivity.this);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Name = (EditText)findViewById(R.id.txt_UserName);
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
		case R.id.menu_score:
			Intent score = new Intent(this , Score.class);
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
		Intent intent = new Intent(MainActivity.this, GameActivity.class);
		intent.putExtra("name", name);
		startActivity(intent);
	}

}
