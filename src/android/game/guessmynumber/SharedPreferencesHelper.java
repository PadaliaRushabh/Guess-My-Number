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
    
    public static final String KEY_MUSIC_BACKGROUND = "isPlayingBackgroundMusic";
     
    // Card Mode (make variable public to access from outside)
    public static final String KEY_CARDMODE = "cardMode";
     
    // Game Mode (make variable public to access from outside)
    public static final String KEY_GAMEMODE = "gameMode";
    
    // Range (make variable public to access from outside)
    public static final String KEY_RANGE = "range";
    
    
    public static final String KEY_MAX_TIME = "time";
    
    public static final String KEY_ATTEMPT = "attempt";
    
    // Constructor
    public SharedPreferencesHelper(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    
    //set preferences when the add opens for the first time
    //or id the shared preferences dosen't exists
    public void initPreferences(){
    	
    	if(!pref.contains(KEY_MUSIC_BACKGROUND)){
    		//add values
    		editor.putBoolean(KEY_MUSIC, true);
    		editor.putBoolean(KEY_MUSIC_BACKGROUND, true);
    		editor.putInt(KEY_CARDMODE, 0);
    		editor.putInt(KEY_GAMEMODE, 0);
    		editor.putInt(KEY_RANGE, 10);
    		editor.putInt(KEY_MAX_TIME, 30);
    		editor.putInt(KEY_ATTEMPT, 1);
    	
    		//save changes
    		editor.commit();
    	}
    	
    }
    
    //save preferences to shared preferences
    public void setPreferences(int range , int card_mode , int game_mode , boolean music 
    												,boolean background_music_isplaying
    												,int time , int attempt){
    	//add values
		editor.putBoolean(KEY_MUSIC, music);
		editor.putBoolean(KEY_MUSIC_BACKGROUND, background_music_isplaying);
		editor.putInt(KEY_CARDMODE, card_mode);
		editor.putInt(KEY_GAMEMODE, game_mode);
		editor.putInt(KEY_RANGE, range);
		editor.putInt(KEY_MAX_TIME, time);
		editor.putInt(KEY_ATTEMPT, attempt);
	
		//save changes
		editor.commit();
    }
    //get all preferences
    public HashMap<String, String> getAllPreferences(){
    	
    	HashMap<String, String> Hash_pref = new HashMap<String, String>();
    	if(!pref.contains(KEY_MUSIC_BACKGROUND)){
    		initPreferences();
    	}
    	
    	Hash_pref.put(KEY_MUSIC, Boolean.toString(pref.getBoolean(KEY_MUSIC, false)));
    	Hash_pref.put(KEY_MUSIC_BACKGROUND, Boolean.toString(pref.getBoolean(KEY_MUSIC_BACKGROUND, false)));
    	Hash_pref.put(KEY_CARDMODE, Integer.toString(pref.getInt(KEY_CARDMODE, 4)));
    	Hash_pref.put(KEY_GAMEMODE, Integer.toString(pref.getInt(KEY_GAMEMODE, 4)));
    	Hash_pref.put(KEY_RANGE,Integer.toString(pref.getInt(KEY_RANGE, 4)));
    	Hash_pref.put(KEY_MAX_TIME, Integer.toString(pref.getInt(KEY_MAX_TIME, 30)));
    	Hash_pref.put(KEY_ATTEMPT, Integer.toString(pref.getInt(KEY_ATTEMPT, 1)));
    	
    	return Hash_pref;
    }
    //clear all preferences
    public void clear(){
    	editor.clear();
    }

}
