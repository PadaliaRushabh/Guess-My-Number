package android.game.guessmynumber;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class GameActivity extends FragmentActivity
							implements QuitGameDialogFragment.QuitDialogListener{
	
	private static final int NUM_PAGES = 5; //Numbe of pages we have
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;
	Settings setting = new Settings(GameActivity.this);

	/**get the pager from activity_game_developer and set it with adapter**/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_viewpager);
		
		mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
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
			 return GameActivityPageFragment.create(position);
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
