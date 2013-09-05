package android.game.guessmynumber;

import java.util.HashMap;

import android.content.Context;

public class Settings {
	
	 // All Shared Preferences Keys
    public static final String KEY_MUSIC = "isPlaying";
     
    // Card Mode (make variable public to access from outside)
    public static final String KEY_CARDMODE = "cardMode";
     
    // Game Mode (make variable public to access from outside)
    public static final String KEY_GAMEMODE = "gameMode";
    
    // Range (make variable public to access from outside)
    public static final String KEY_RANGE = "range";
    
	Context _Context;
	SharedPreferencesHelper PrefObj;
	static  HashMap<String, String> Hash_pref = new HashMap<String, String>();
	
	//constructor to be used when to set or update settings
	public Settings(Context _Context ,int range , int card_mode , int game_mode 
																	, boolean music_isPlaying){
		
		PrefObj = new SharedPreferencesHelper(_Context);
		PrefObj.setPreferences(range ,card_mode, game_mode,music_isPlaying);
		
	}
	
	public Settings(Context _Context){
		this._Context = _Context;
	}
	
	//get setting
	public HashMap<String, String> getSettings(){
		
		getUpdate();
		return Settings.Hash_pref;
		
	}
	//get latest settings
	public void getUpdate(){
		
		PrefObj = new SharedPreferencesHelper(_Context);
		Settings.Hash_pref = PrefObj.getAllPreferences();
	}
	
   //get music from shared preferences 
   public boolean getMusic(){
	   
	   getUpdate();
	   if (Settings.Hash_pref.get(KEY_MUSIC).equals("true"))
		   return true;
	   
	   return false;
   }
   public String getCardMode(){
	   
	   getUpdate();
	   return Settings.Hash_pref.get(KEY_CARDMODE);
   }
   public String getGameMode(){
	   
	   getUpdate();
	   return Settings.Hash_pref.get(KEY_GAMEMODE);
   }

   public String getRange(){
	   
	   getUpdate();
	   return Settings.Hash_pref.get(KEY_RANGE);
   }
    
}