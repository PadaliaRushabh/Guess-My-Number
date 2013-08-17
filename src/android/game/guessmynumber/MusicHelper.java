package android.game.guessmynumber;

public class MusicHelper {
	
	private static final int MAX_ACTIVITY = 4;
	public static boolean isPlaying = true; 
	MyMusic music = new MyMusic();
	
	
	public static boolean musicManagerLogic(){
		
		if(!isPlaying)
			return false;

		return true;
	}

}
