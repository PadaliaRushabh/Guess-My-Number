package android.game.guessmynumber;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;


public class GameActivity extends FragmentActivity {
	
	private static final int NUM_PAGES = 5; //Numbe of pages we have
	private ViewPager mPager;
	private PagerAdapter mPagerAdapter;

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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
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
			 Log.d("getItem", Integer.toString(position));
			 /*calls the create method from GameActivityPageFragement class
			  and gets the newly created fragment back which was created from the create
			  method*/
			 return GameActivityPageFragment.create(position);
			//return null;
		}

		@Override
		//gets called multiple times and returns total pages
		public int getCount() {
			// TODO Auto-generated method stub
			//Log.d("getCount", Integer.toString(NUM_PAGES));
			return NUM_PAGES;
		}
		
	} 
}
