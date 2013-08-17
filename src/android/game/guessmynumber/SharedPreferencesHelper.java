package android.game.guessmynumber;

import java.util.HashMap;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesHelper {
	// Shared Preferences
    SharedPreferences pref;
     
    // Editor for Shared preferences
    Editor editor;
     
    // Context
    Context _context;
     
    // Shared pref mode
    int PRIVATE_MODE = 0;
    
    // Sharedpref file name
    private static final String PREF_NAME = "android.game.guessmynumber.settingspref";
    
    // All Shared Preferences Keys
    public static final String KEY_MUSIC = "isPlaying";
     
    // Card Mode (make variable public to access from outside)
    public static final String KEY_CARDMODE = "cardMode";
     
    // Game Mode (make variable public to access from outside)
    public static final String KEY_GAMEMODE = "gameMode";
    
    // Range (make variable public to access from outside)
    public static final String KEY_RANGE = "range";
    
    // Constructor
    public SharedPreferencesHelper(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    
    public void initPreferences(){
    	
    	if(!pref.contains(KEY_MUSIC)){
    		//add values
    		editor.putBoolean(KEY_MUSIC, true);
    		editor.putInt(KEY_CARDMODE, 0);
    		editor.putInt(KEY_GAMEMODE, 0);
    		editor.putInt(KEY_RANGE, 10);
    	
    		//save changes
    		editor.commit();
    		System.out.println("init");
    	}
    	
    }
    
    public void setPreferences(int range , int card_mode , int game_mode , boolean music){
    	//add values
		editor.putBoolean(KEY_MUSIC, music);
		editor.putInt(KEY_CARDMODE, card_mode);
		editor.putInt(KEY_GAMEMODE, game_mode);
		editor.putInt(KEY_RANGE, range);
	
		//save changes
		editor.commit();
    }
    
    public HashMap<String, String> getAllPreferences(){
    	
    	HashMap<String, String> Hash_pref = new HashMap<String, String>();
    	if(!pref.contains(KEY_MUSIC)){
    		initPreferences();
    	}
    	
    	Hash_pref.put(KEY_MUSIC, Boolean.toString(pref.getBoolean(KEY_MUSIC, false)));
    	Hash_pref.put(KEY_CARDMODE, Integer.toString(pref.getInt(KEY_CARDMODE, 4)));
    	Hash_pref.put(KEY_GAMEMODE, Integer.toString(pref.getInt(KEY_GAMEMODE, 4)));
    	Hash_pref.put(KEY_RANGE,Integer.toString(pref.getInt(KEY_RANGE, 4)));
    	
    	return Hash_pref;
    }

   public void clear(){
    	editor.clear();
    }

}
