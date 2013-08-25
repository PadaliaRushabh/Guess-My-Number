package android.game.guessmynumber;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class GameActivity extends FragmentActivity
							implements QuitGameDialogFragment.QuitDialogListener{
	
	private static final int NUM_PAGES = 6; //Number of pages we have
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	final Context context = this;
	Settings setting = new Settings(GameActivity.this);

	/**get the pager from activity_game_developer and set it with adapter**/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_viewpager);
		
		NumberGenerator.PrimeGenerator();
		
		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setOffscreenPageLimit(0);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
            	
            	if(position + 1 == 6){
            		// get prompts.xml view
    				LayoutInflater li = LayoutInflater.from(context);
    				View promptsView = li.inflate(R.layout.dialog_enter_number, null);
     
    				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
    						context);
     
    				// set prompts.xml to alertdialog builder
    				alertDialogBuilder.setView(promptsView);
    				
    				// set dialog message
    				alertDialogBuilder
    					.setCancelable(false)
    					.setPositiveButton("OK",
    					  new DialogInterface.OnClickListener() {
    					    public void onClick(DialogInterface dialog,int id) {
    						// get user input and set it to result
    						// edit text
    					    	finish();
    					    }
    					  })
    					.setNegativeButton("I don't Know",
    					  new DialogInterface.OnClickListener() {
    					    public void onClick(DialogInterface dialog,int id) {
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

		//Called when ever user swipes and goes to new fragment
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
}
