package android.game.guessmynumber;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class MyMusic extends Service {
	
	public static MediaPlayer mp;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
		mp = MediaPlayer.create(MyMusic.this, R.raw.music);
		mp.setLooping(true);
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		mp.start();
		Toast.makeText(this, "Service onstart", Toast.LENGTH_SHORT).show();
		return 1;
		//return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		//super.onDestroy();
		Toast.makeText(this, "Service Stopped", Toast.LENGTH_SHORT).show();
		mp.stop();
		mp.release();
	}
	
	

}
