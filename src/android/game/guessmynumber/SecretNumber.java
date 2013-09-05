package android.game.guessmynumber;

public class SecretNumber {

	static String Number;
	
	public SecretNumber(String number){
		SecretNumber.Number = number;
	}
	
	public SecretNumber(){
		
	}
	
	public String getNumber(){
		return Number;
	}
}
