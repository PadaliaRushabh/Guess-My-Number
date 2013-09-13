package android.game.guessmynumber;

import android.content.Context;
import android.media.MediaPlayer;

public class BackgroundMusic {

	static MediaPlayer mediaplayer;
	static boolean pause = true;
		
	public static void initMusic(Context context){
		if(mediaplayer == null){
			mediaplayer = MediaPlayer.create(context, R.raw.number);
			mediaplayer.setLooping(true);
		}
	}
	
	public static void startMusic(){
		if(!mediaplayer.isPlaying()){
			mediaplayer.start();
		}
	}
	
	public static void pauseMusic(){
		if(mediaplayer != null){
			if(mediaplayer.isPlaying()){
				mediaplayer.pause();
			}
		}
	}
	
	public static void stopMusic(){
		if(mediaplayer != null){
			if(mediaplayer.isPlaying()){
				mediaplayer.stop();
				mediaplayer.release();
				mediaplayer = null;
			}
		}
	}
	
	public static void onActivityPause(){
		if(BackgroundMusic.pause){
			BackgroundMusic.pauseMusic();
		}
		else{
			BackgroundMusic.pause = true;
		}
	}
	
	public static void setFalse(){
		BackgroundMusic.pause = false;
	}
	
	public static void setTrue(){
		BackgroundMusic.pause = true;
	}
}

