package android.game.guessmynumber;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class GameActivity extends FragmentActivity
							implements QuitGameDialogFragment.QuitDialogListener{
	
	private static int NUM_PAGES = 0; //Number of pages we have
	private ViewPager mPager;	
	private PagerAdapter mPagerAdapter;
	final Context context = this;
	Settings setting = new Settings(GameActivity.this);
	Timer timer = new Timer();
	TextView timerView;	
	EditText secretNumber;
	int timeit = 0 ;
	NumberGenerator generator;
	String item;
	String []result = new String[4];
	 
	 /*MyResultReceiver resultReceiver;
	 Intent intent;
	 TextView txtview;*/

	/**get the pager from activity_game_developer and set it with adapter**/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_viewpager);
		//startService(new Intent(GameActivity.this, TimerService.class));
	
		//timer.scheduleAtFixedRate(timerTask, 1000, 1000);
		timerView = (TextView) findViewById(R.id.Timer);
		System.out.println("ss" + setting.getCardMode());
		System.out.println("ss" + setting.getRange());
		//generator.PrimeGenerator();
		generator = new NumberGenerator(setting.getGameMode() 
						,(Integer.parseInt(setting.getRange()) + 10));
		item = generator.selectNumber();		
		Log.d("item" , item);
		SecretNumber number = new SecretNumber(item);
		
		NUM_PAGES = NumberGenerator.NUM_OF_CARDS + 1;
		mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            	
            	if(position == NUM_PAGES - 1){
            		// get prompts.xml view
    				LayoutInflater li = LayoutInflater.from(context);
    				View promptsView = li.inflate(R.layout.dialog_enter_number, null);
    				
    				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    						context);
     
    				// set prompts.xml to alertdialog builder
    				alertDialogBuilder.setView(promptsView);
    				
    				secretNumber = ((EditText)promptsView.findViewById(R.id.editText_SecretNumber));
    				// set dialog message
    				alertDialogBuilder
    					.setCancelable(false)
    					.setPositiveButton("OK",
    					  new DialogInterface.OnClickListener() {
    					    public void onClick(DialogInterface dialog,int id) {
    						// get user input and set it to result
    						// edit text
    					    	String number = secretNumber.getText().toString();
    					    	Log.d("number", number);
    					    	if(number.equals(item)){
    					    		Log.d("you" , "win");
    					    	}
    					    	else
    					    		Log.d("you" , "loser");
    					    	prepareAndStartResultActivity(0);
    					    	finish();
    					    }
    					    
    					  })
    					.setNegativeButton("I don't Know",
    					  new DialogInterface.OnClickListener() {
    					    public void onClick(DialogInterface dialog,int id) {
    					    	prepareAndStartResultActivity(1);
    							finish();
    					    }
    					    
    					  });
    				
    				// create alert dialog
    				AlertDialog alertDialog = alertDialogBuilder.create();
     
    				// show it
    				alertDialog.show();
            	}
                invalidateOptionsMenu();
            }
        });   
        
        /*resultReceiver = new MyResultReceiver(null);
        intent = new Intent(this, TimerService.class);
		intent.putExtra("receiver", resultReceiver);
		startService(intent);*/
        timeIt();
	}
	
	public void prepareAndStartResultActivity(int status){
		Date now = new Date();
		switch(status){
		case 0:
			result[0] = item;
			result[1] = secretNumber.getText().toString();;
			result[2] = Integer.toString(timeit);
			result[3] =  now.toString();
			
			break;
		case 1:
			result[0] = item;
			result[1] = null;
			result[2] = Integer.toString(timeit);
			result[3] = now.toString();
			
			break;
		}
		startResultActivity();
	}
	public void startResultActivity(){
		
		Intent intent = new Intent(GameActivity.this , ResultActivity.class);
		intent.putExtra("result", result);
		startActivity(intent);
	}
	
	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case R.id.menu_quit:
			DialogFragment quitDialog = new QuitGameDialogFragment();
			quitDialog.show(getFragmentManager() , "quitDialog");
		}
		return super.onMenuItemSelected(featureId, item);
	}
	
	public void getPosition(){
		Log.d("Page No" , Integer.toString(mPager.getCurrentItem()));
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.inside_game_menu, menu);
		return true;
	}
	/**adapter**/
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter{

		public ScreenSlidePagerAdapter(android.support.v4.app.FragmentManager fragmentManager) {
			super(fragmentManager);
			// TODO Auto-generated constructor stub
		}

		//Called when ever user "Time"swipes and goes to new fragment
		@Override
		public android.support.v4.app.Fragment getItem(int position) {
			// TODO Auto-generated method stub
			 /*calls the create method from GameActivityPageFragement class
			  and gets the newly created fragment back which was created from the create
			  method*/
			//Log.d("Page No" , Integer.toString(mPager.getCurrentItem()));
			//Fragment fragment = GameActivityPageFragment.ne 
			GameActivityPageFragment fragment = GameActivityPageFragment.create(position);
        	Log.d("Page No" , Integer.toString(fragment.getPageNumber()));
			return GameActivityPageFragment.create(position);
		}
		
		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		//gets called multiple times and returns total pages
		public int getCount() {
			// TODO Auto-generated method stub
			return NUM_PAGES;
		}
		
	}

	/**On quit game dialog click**/
	@Override
	public void onDialogPositiveClick(DialogFragment quitDialog) {
		// TODO Auto-generated method stub
		//If yes start new activity
		finish();
	}

	@Override
	public void onDialogNegativeClick(DialogFragment quitDialog) {
		// TODO Auto-generated method stub
		
	} 
	
	//Onpause pause the music
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		MyMusic MM = new MyMusic();
		if(setting.getMusic() == true)
			MM.pauseSong();
		
	}
	//onResume start the music
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(setting.getMusic())
			startService(new Intent(this, MyMusic.class));
	}
	
	protected void timeIt(){
		timer.schedule(new TimerTask() {
			public void run() {
			    timeit++;
			    runOnUiThread(new Runnable() {

			    @Override
			    public void run() {
			    	timerView.setText("Timer:" + timeit);
			            }
			    });
			        }
		}, 10, 1000);
	}
}
