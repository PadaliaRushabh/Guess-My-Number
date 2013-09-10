package android.game.guessmynumber;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class BackgroundMusic extends Service{

	public static  MediaPlayer mediaPlayer;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		BackgroundMusic.mediaPlayer = MediaPlayer.create(this, R.raw.number);
		BackgroundMusic.mediaPlayer.setLooping(true);
		
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		if(!BackgroundMusic.mediaPlayer.isPlaying()){
			BackgroundMusic.mediaPlayer.start();
		}
		return 1;
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if(BackgroundMusic.mediaPlayer.isPlaying()){
			BackgroundMusic.mediaPlayer.stop();
			BackgroundMusic.mediaPlayer.release();
			//BackgroundMusic.mediaPlayer = null;
		}
	}
	
	public static void onPause(){
		if(BackgroundMusic.mediaPlayer.isPlaying()){
			BackgroundMusic.mediaPlayer.pause();
		}
	}
	
	public void onStop(){
		if(BackgroundMusic.mediaPlayer.isPlaying()){
			BackgroundMusic.mediaPlayer.stop();
			BackgroundMusic.mediaPlayer.release();
			BackgroundMusic.mediaPlayer = null;
		}
	}
	
}
