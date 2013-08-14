package android.game.guessmynumber;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyMusic extends Service {
	
	public static MediaPlayer mp;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//On create get the music and set it to continues loop
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mp = MediaPlayer.create(MyMusic.this, R.raw.music);
		mp.setLooping(true);
		
	}
	
	//on startService(intent), start the music
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		mp.start();
		return 1;
	}
	
	//onDestroy Stop/Destroy the music
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mp.stop();
		mp.release();
	}
	

	public void onPause(){

		mp.pause();
	}
	
	//Pause the music
	public void pauseSong(){
		mp.pause();
	}
		

}
