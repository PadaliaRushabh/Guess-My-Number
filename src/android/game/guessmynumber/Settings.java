package android.game.guessmynumber;

import java.util.HashMap;


import android.content.Context;

public class Settings {

	protected static int range;
	protected static int card_mode;
	protected static int game_mode;
	protected static boolean music_isPlaying;
	protected static boolean new_game = true;
	
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
		//HashMap<String, String> Hash_pref = new HashMap<String, String>();
		Settings.Hash_pref = PrefObj.getAllPreferences();
		
		/*	Iterator it = Hash_pref.entrySet().iterator();
		
		while(it.hasNext()) {
			 HashMap.Entry pairs = (HashMap.Entry)it.next();
			 Settings.music_isPlaying = pairs.getValue().toString();
		 }*/
	}
	
   //get music from shared preferences 
   public boolean getMusic(){
	   
	   getUpdate();
	   if (Settings.Hash_pref.get("isPlaying").equals("true"))
		   return true;
	   
	   return false;
	   
   }
    
}
