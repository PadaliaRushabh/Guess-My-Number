package android.game.guessmynumber;
import java.util.ArrayList;
import java.util.List;


public class NumberGenerator {

	// Number of cards
	public static int SELECTED_RANGE = 5;
		
	public static int MIN_RANGE = 3;
	public static int PERFORMANCE_MAX = 12;
	public static int MAX_RANGE = (int) Math.pow(2, SELECTED_RANGE) ;

	static int previous = 0 , next = 0;
	// Binary values (Vertical Row)
	static String[] binaryValues = new String[MAX_RANGE];
	static String[] binaryValuesReversed = new String[MAX_RANGE];
		
	// Prime values (Horizontal Row)
	static ArrayList<Integer> primeValues = new ArrayList<Integer>();
		
	// Card values
	static ArrayList<String> cardValues = new ArrayList<String>();
	
	//Final String
	static String[] cardArray = new String[SELECTED_RANGE];
	
	public static void PrimeGenerator() {
		if(primeValues.size() > 1){
			primeValues.clear();
			cardValues.clear();
			binaryValues = null;
			binaryValues = new String[MAX_RANGE];
			binaryValuesReversed = null;
			binaryValuesReversed = new String[MAX_RANGE];
			
		}
		primeValues.add(1);
	
		for(int i = 2, primesFound = 1; i < Integer.MAX_VALUE; i++) {
			if(isPrime(i)) {
				primesFound++;
				primeValues.add(i);
			}
			if(primesFound == SELECTED_RANGE)
				break;
		}
		//PrintPrimeList(primeValues);
		FillBinaryList();
	}
	// Helper function for PrimeGenerator()
	public static boolean isPrime(int x) {
		for(int i = 2; i < x; i++) {
			if(x % i == 0)
				return false;
		}
			return true;
	}
	
	// Fill up binaryValues
	public static void FillBinaryList() {	
		for(int i = 0; i < MAX_RANGE; i++) {
			binaryValues[i] = Integer.toBinaryString(0x1000 | (i + 1)).substring(1);
			binaryValuesReversed[i] = Integer.toBinaryString(0x1000 | (i + 1)).substring(1);
		}
			
		for(int i = 0; i < MAX_RANGE; i++) 
			binaryValuesReversed[i] = new StringBuffer(binaryValues[i]).reverse().toString();
		FillCardValues();
	}
	// Fill card values and print
	public static void FillCardValues() {
		//System.out.print("Card Values: ");
		
		for(int i = 0; i < SELECTED_RANGE; i++) {
			for(int j = 0; j < MAX_RANGE; j++) {
				if(binaryValuesReversed[j].charAt(i) == (char) 49)
					cardValues.add(Integer.toString(Integer.parseInt(binaryValues[j], 2)));
			}
		}
		//SplitCardValues();
			
		//System.out.println(cardValues);	
	}
	public static List<String> SplitCardValues(int page_no) {
		if(page_no == 0){
			previous = 0;
			next = cardValues.size()/SELECTED_RANGE;
		}else{
			previous = (cardValues.size()/SELECTED_RANGE) * (page_no );
			next = (cardValues.size()/SELECTED_RANGE) * (page_no+1);
		}
		System.out.println("sd:" + cardValues.subList(previous, next));
		return cardValues.subList(previous, next);
	
	}

}
