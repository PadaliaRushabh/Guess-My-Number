package android.game.guessmynumber;

import java.util.Random;


public class DisplayResult {

	public DisplayResult(){
		
	}
	public String returnbackgroundName(){
		return switchBackground();
	}
	
	private String switchBackground(){
		Random rand = new Random();
		int number = rand.nextInt(4);
		String num = null;
		switch(number){
		case 0:
			num =  "w1";
			break;
		case 1:
			num = "w2";
			break;
		case 2:
			num =  "w3";
			break;
		case 3:
			num = "w4";
			break;
		}
		return num;
	}
	private String switchResultBackground(){
		Random rand = new Random();
		int number = rand.nextInt(2);
		String num = null;
		switch(number){
		case 0:
			num =  "n1";
			break;
		case 1:
			num = "n2";
			break;
		case 2:
			num =  "n3";
			break;
		}
		return num;
	}
	
	public String returnbackResultgroundName(){
		return switchResultBackground();
	}
	
	
}
