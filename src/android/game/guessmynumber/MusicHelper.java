package android.game.guessmynumber;

public class MusicHelper {
	
	private static final int MAX_ACTIVITY = 4;
	private static boolean continuePlaying = true; 
	
	
	
	public static boolean musicManagerLogic(){
		
		if(!continuePlaying)
			return false;

		return true;
	}
	
}
