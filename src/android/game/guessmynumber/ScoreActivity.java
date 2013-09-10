package android.game.guessmynumber;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScoreActivity extends Activity {
	
	private ArrayAdapter<ScoreHelper> score;
	private MaintainDatabase maintainDB;
	Cursor cursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
		score = new ArrayAdapter<ScoreHelper>(this, android.R.layout.simple_list_item_1);
		ListView listview = (ListView)findViewById(R.id.listViewScore);
		listview.setAdapter(score);
		
		maintainDB = new MaintainDatabase(getApplicationContext());
											
		cursor = maintainDB.selectFromDatabase();
		
		while(cursor.moveToNext()){
			String name = cursor.getString(0);
			String Score = cursor.getString(1);
			String when = cursor.getString(2);
			score.add(new ScoreHelper(name, Score, when));//populate listview
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
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
