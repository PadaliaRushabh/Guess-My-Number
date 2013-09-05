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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ResultActivity extends Activity {

	String []result = new String[4];
	ImageView image1 , image2;
	String msg;
	Settings S;
	String cardMode;
	MaintainDatabase maintainDB;
	DisplayResult displayResult;
	String imageName[] = new String[2];
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		S = new Settings(getApplicationContext());

	    image1 = (ImageView) findViewById(R.id.imageView1);
	    image2 = (ImageView) findViewById(R.id.imageView2);
		cardMode = S.getCardMode();
		
		
		
		Bundle extras = getIntent().getExtras();
	    if (extras != null) {
	    	result = extras.getStringArray("result");
	    }
	    
	    displayResult = new DisplayResult(result[0].toString());
	    imageName = displayResult.returnName();
	    int id1 = getResources().getIdentifier(
	    		imageName[0], "drawable", getPackageName() );
	    int id2 = getResources().getIdentifier(
	    		imageName[1], "drawable", getPackageName() );
	    image1.setImageResource(id1);
	    image2.setImageResource(id2);
	    
		if(cardMode.equals("0")){
			 Random random = new Random();
			 int randomNum = random.nextInt((10 - 1) + 1) + 1;
			 if(randomNum < 7){
				 result[0] = Integer.toString(Integer.parseInt(result[0]) + randomNum);
			 }
		}
		else{
			RelativeLayout resultbackground = (RelativeLayout)findViewById(R.id.resultBackground);
	    	if(result[1] == null || !result[1].toString().equals(result[0].toString())){
	    		int idwin = getResources().getIdentifier(
	    	 	    	"try", "drawable", getPackageName());
	    	 	resultbackground.setBackgroundResource(idwin);
	    		
	    	}
	    	else{
	    	 	int idwin = getResources().getIdentifier(
	    	 	    	"firework", "drawable", getPackageName());
	    	 	resultbackground.setBackgroundResource(idwin);
	    		
	    		//Insert into Database
	    	    maintainDB = new MaintainDatabase(getApplicationContext(), result[4], result[2].toString()
	    	    													, result[3].toString());
	    	    maintainDB.insertToDatabase();
	    		
	    	}
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
