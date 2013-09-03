package android.game.guessmynumber;

import java.util.Random;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends Activity {

	String []result = new String[4];
	TextView mode , userinput , userinputdate , message , highscore , highscoredate;
	String msg;
	Settings S;
	String cardMode;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		S = new Settings(getApplicationContext());
		
	    mode = (TextView)findViewById(R.id.TextViewMode);
	    userinput = (TextView)findViewById(R.id.TextViewScore);
	    userinputdate = (TextView)findViewById(R.id.TextViewScoreDate);
	    message = (TextView)findViewById(R.id.TextViewMessage);
	    highscore = (TextView)findViewById(R.id.TextViewHighScore);
	    highscoredate = (TextView)findViewById(R.id.TextViewHighScoreDate);
	    
		cardMode = S.getCardMode();
		
		Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	    	result = extras.getStringArray("result");
	    }
	    
		if(cardMode.equals("0")){
			 Random random = new Random();
			 int randomNum = random.nextInt((10 - 1) + 1) + 1;
			 if(randomNum > 7){
				 result[0] = Integer.toString(Integer.parseInt(result[0]) + randomNum);
			 }
			 mode.setText("App Guess");
			 message.setText("The Secret Number is " + result[0].toString());
			 userinput.setVisibility(View.GONE);
			 userinputdate.setVisibility(View.GONE);
			 highscore.setVisibility(View.GONE);
			 highscoredate.setVisibility(View.GONE);
		}
		else{
			
			mode.setText("User Guess");
	    	userinputdate.setText(result[3].toString());
	    	if(result[1] == null){
	    		msg = "The secret number is " + result[0].toString();
	    		userinput.setText("You didnot enter any answer but took " 
	    				+ result[2].toString() + " Sec");
	    	}
	    	else{
	    	 	userinput.setText("You Entered " + result[1].toString() + " in " 
 					+ result[2].toString() + " Sec");
	    	 	if(result[1].toString().equals(result[0].toString())){
	    		msg = "You input " + result[1].toString() + " and secret number is " 
	    				+ result[0].toString()+"\nYou Win";
	    	
	    		}
	    		else{
	    			msg = "You input " + result[1].toString() + " and secret number is " 
	    			+ result[0].toString()+"\nBetter luck next time";
	    		}
	    	}
	    	message.setText(msg);
	    	highscore.setText("HighScore: 23 Sec");
	    	highscoredate.setText(result[3].toString());
		}
	}
	
	@Override
	/**Diaplay back button only**/
	public boolean onCreateOptionsMenu(Menu menu) {
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		return true;
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub		
		switch(item.getItemId()){
		case android.R.id.home:
			//Intent upIntent = new Intent(this, MainActivity.class);
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onMenuItemSelected(featureId, item);
	}
}
