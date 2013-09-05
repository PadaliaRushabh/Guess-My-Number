package android.game.guessmynumber;

import java.util.Date;

public class ScoreHelper {
	
	protected String name;
	protected String score;
	protected String when;
	
	public ScoreHelper(String name , String score , String when){
		this.name = name;
		this.score = score;
		this.when = when;
	}
	
	public String toString(){
		return name +"\n" + score +" Sec" + "\n" +"On " +when;
	}
}
