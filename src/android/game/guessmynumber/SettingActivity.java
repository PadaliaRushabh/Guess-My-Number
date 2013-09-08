package android.game.guessmynumber;

import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class SettingActivity extends Activity{
	
	Switch switchMusic;
	TextView range_txt;
	SeekBar seekbar;
	String rangeDisplay = "Range: 1 - ";
	int range , card_mode ,game_mode ;
	RadioGroup card , game;
	Settings setting = new Settings(SettingActivity.this);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		//Find seekbar
		seekbar = (SeekBar)findViewById(R.id.seekBarNumberRange);
		//find switch 
		switchMusic = (Switch) findViewById(R.id.switchMusic);
		//init textviews and radio button
		init();
		//set listener on seekbar
		seekbar.setOnSeekBarChangeListener( new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				//change display of textview to shoe range value
				range = progress + 10; // adding ten because there is no max in android progress bar
				range_txt.setText(rangeDisplay + Integer.toString(progress + 10));
			}
		});
		
	}
	
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub		
		switch(item.getItemId()){
		case android.R.id.home:
			//Intent upIntent = new Intent(this, MainActivity.class);
			SaveSettings();
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
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
	public void SaveSettings(){
		String value = new String(); 
		
		Intent data = new Intent();
		data.putExtra("settings", value);
		setResult(RESULT_OK , data);
		//get radio group
		card_mode = card.getCheckedRadioButtonId();
		//check which radio button was pressed and set the correct integer
		switch(card_mode){
		case R.id.radioAppGuess:
			card_mode = 0;
			break;
		case R.id.radioUserGuess:
			card_mode = 1;
			break;
		case R.id.radiofreePlay:
			card_mode = 2;
		}
		
		//same with other radio group
		game_mode = game.getCheckedRadioButtonId();
		switch(game_mode){
		case R.id.radioFibonacci:
			game_mode = 0;
			break;
		case R.id.radioBinary:
			game_mode = 1;
			break;
		case R.id.radioPrime:
			game_mode = 2;
		}
		
		//get seek value of seekbar
		int actual_range = seekbar.getProgress();
		//call constructor of setting activity to save preference in sharedpreferences
		Settings settings = new Settings(
						SettingActivity.this 
						, actual_range, card_mode
						, game_mode
						, switchMusic.isChecked()
					);
		//MusicHelper.isPlaying = switchMusic.isChecked();
		//Show Toast that setting are saved
		Toast toast = Toast.makeText(this,"Preferences Saved", Toast.LENGTH_SHORT);
		toast.show();
	}
		
	//set the values from shared preferences
	protected void init(){
		Settings setting = new Settings(SettingActivity.this);
		HashMap<String, String> Hash_pref = new HashMap<String, String>();
		Hash_pref = setting.getSettings();//get settings from shared preferences
		
		//set range textview
		range_txt = (TextView) findViewById(R.id.textViewNumberRange);
		range = Integer.parseInt(Hash_pref.get("range")) + 10;
		range_txt.setText(rangeDisplay + Integer.toString(range));
		
		//set seekbar seek value
		seekbar.setProgress(Integer.parseInt(Hash_pref.get("range")));
		
		//get radio group 
		card = (RadioGroup) findViewById(R.id.radioGroupCardMode);
		game = (RadioGroup) findViewById(R.id.radioGroupGameMode);
		
		//set both radio group
		switch(Integer.parseInt(Hash_pref.get("cardMode"))){
		case 0:
			card.check(R.id.radioAppGuess);	
			break;
		case 1:
			card.check(R.id.radioUserGuess);
			break;
		case 2:
			card.check(R.id.radiofreePlay);
		}
		
		switch(Integer.parseInt(Hash_pref.get("gameMode"))){
		case 0:
			game.check(R.id.radioFibonacci);
			break;
		case 1:
			game.check(R.id.radioBinary);
			break;
		case 2:
			game.check(R.id.radioPrime);
		}
		
		if(Boolean.parseBoolean(Hash_pref.get("isPlaying"))){
			switchMusic.setChecked(true);
		}
		else{
			switchMusic.setChecked(false);
		}
		
	}
	
}
