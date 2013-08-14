package android.game.guessmynumber;

public class MusicHelper {
	
	private static final int MAX_ACTIVITY = 4;
	private static boolean continuePlaying = true; 
	
	
	
	public static boolean musicManagerLogic(){
		
		if(!continuePlaying)
			return false;

		return true;
	}
	/*public static void manipulateDestroyed(int operation){
		switch(operation){
		case 0:
			destroyed = destroyed - 1;
			break;
		case 1:
			destroyed = destroyed + 1;
			break;
		}
		isVisible();
	}
	
	public static void manipulateVisible(int operation){
		if(visible > 0){
			switch(operation){
			case 0:
				visible = visible - 1;
				break;
			case 1:
				visible = visible + 1;
				break;
			}
		}
	}
	
	public static void manipulatePause(int operation){
		if(paused > 0){
			switch(operation){
			case 0:
				paused = paused - 1;
				break;
			case 1:
				paused = paused + 1;
				break;
			}
		}
	}
	
	public static int getPause(){
		return paused;
	}
	
	public static int getVisible(){
		return visible;
	}
	
	public static int getDestroyed(){
		return destroyed;
	}
	
	public static boolean isVisible(){
		
		if (visible == 0){
			return false;
		}
			return true;
	}*/
	
}
