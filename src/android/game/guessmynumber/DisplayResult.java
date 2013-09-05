package android.game.guessmynumber;


public class DisplayResult {

	char Number1 , Number2;
	
	public DisplayResult(String Number){
		if(Number.length() == 1){
			this.Number1 = '0';
			this.Number2 = Number.charAt(0);
		}
		else{
			this.Number1 = Number.charAt(0);
			this.Number2 = Number.charAt(1);
		}
		
	}
	
	public String[] returnName(){
		String numbers[] = new String[2];
		
		numbers[0] = switchNumber(Character.getNumericValue(this.Number1)); 
		numbers[1] = switchNumber(Character.getNumericValue(this.Number2));
		
		return numbers;
	}
	
	private String switchNumber(int number){
		String num = null;
		switch(number){
		case 0:
			num =  "zero";
			break;
		case 1:
			num = "one";
			break;
		case 2:
			num =  "two";
			break;
		case 3:
			num = "three";
			break;
		case 4:
			num =  "four";
			break;
		case 5:
			num = "five";
			break;
		case 6:
			num =  "six";
			break;
		case 7:
			num = "seven";
			break;
		case 8:
			num =  "eight";
			break;
		case 9:
			num = "nine";
			break;
		
		}
		return num;
	}
	
}