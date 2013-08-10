package android.game.guessmynumber;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class GameActivity extends Activity {
	
	private GestureDetectorCompat mDetector; 
	GridView gridView;
	
	static final String[] numbers = new String[] { 
		"A", "B", "C", "D", "E",
		"F", "G", "H", "I", "J",
		"K", "L", "M", "N", "O",
		"P", "Q", "R", "S", "T",
		"U", "V", "W", "X", "Y", "Z"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		mDetector = new GestureDetectorCompat(this, new MyGestureListener());
		
		gridView = (GridView) findViewById(R.id.gridViewNumbers);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, numbers);
		
		gridView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		this.mDetector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
	
	class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
		private static final String DEBUG_TAG = "Gestures"; 
		
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
            Log.d(DEBUG_TAG,"onDown: " + e.toString()); 
			return true;
		}
		
		@Override
		public boolean onFling(MotionEvent start, MotionEvent end, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			Log.d(DEBUG_TAG, "onFling: " + start.toString() + end.toString());
			if(start.getX() > end.getX()){
				Toast.makeText(GameActivity.this, "Left Swipe", Toast.LENGTH_SHORT).show();
				
			}
			else{
				Toast.makeText(GameActivity.this, "Right Swipe", Toast.LENGTH_SHORT).show();
			}
            return true;
		}
	}
	
}
