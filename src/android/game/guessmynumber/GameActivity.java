package android.game.guessmynumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class GameActivity extends Activity {
	
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
	
}
