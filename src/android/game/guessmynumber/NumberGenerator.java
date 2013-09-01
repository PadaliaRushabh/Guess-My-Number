package android.game.guessmynumber;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.ClipData.Item;


public class NumberGenerator {

	// Game Mode
	public static int SELECTED_MODE = 3; 	// 1 = Prime, 2 = Fibonacci, 3 = Binary
		
	// Range of Binary values (Vertical Row)
	public static int SELECTED_RANGE = 30;
		
	// Number of Cards
	public static int NUM_OF_CARDS = 0;
		
	// Max and Minimum SELECTED_RANGE
	public int MIN_RANGE = 1;
	public int MAX_RANGE = 50;

	// Binary values (Vertical Row)
	ArrayList<String> binaryValues = new ArrayList<String>();
	ArrayList<String> binaryValuesReversed = new ArrayList<String>();
		
	// Prime/Fib/Binary values (Horizontal Row)
	ArrayList<Integer> mainValues = new ArrayList<Integer>();
		
	// Card values
	static ArrayList<String> cardValues = new ArrayList<String>();
		
	// To calculate number of values on each card
	static ArrayList<Integer> cardSplitting = new ArrayList<Integer>();
		
	// Empty String
	String S = "";
	
	private Random randomGenerator = new Random();
	
	public NumberGenerator(int mode , int range){
		NumberGenerator.SELECTED_MODE = mode;
		NumberGenerator.SELECTED_RANGE = range;
	}
	public static void clear(){
		if(cardValues.size() > 1){
			cardValues.clear();
			cardSplitting.clear();
			
		}
	}
	// Generate SELECTED_RANGE number of prime numbers (Horizontal row) 
	public void PrimeGenerator() {
		clear();
		mainValues.add(1);
		
		for(int i = 2, counter = 1; i < Integer.MAX_VALUE; i++) {
			if(isPrime(i)) {
				counter += i;
				mainValues.add(i);
			}
			if(counter >= SELECTED_RANGE) {
				NUM_OF_CARDS = mainValues.size();
				break;
			}
		}
		GenerateBinaryList();
		
	}
	
	// Helper function for PrimeGenerator()
	public boolean isPrime(int x) {
		for(int i = 2; i < x; i++) {
			if(x % i == 0)
				return false;
		}
		return true;
	}
	
	
	// Generate SELECTED_RANGE number of Fibonacci numbers (Horizontal row) 
	public void FibGenerator() {
		clear();
		System.out.println("FIBONACCI MODE");
		System.out.println("Selected Range: " + SELECTED_RANGE);
		mainValues.add(1);
		mainValues.add(2);
		
		for(int i = 0, j = 1, counter = 1; i < SELECTED_RANGE; i++, j++) {
			counter += mainValues.get(j);
			if(counter >= SELECTED_RANGE) {
				NUM_OF_CARDS = mainValues.size();
				break;
			}
			mainValues.add(mainValues.get(i) + mainValues.get(j));
		}
		GenerateBinaryList();
	}
	
	// Generate SELECTED_RANGE number of Binary numbers (Horizontal row) 
	public void BinGenerator() {
		clear();
		System.out.println("BINARY MODE");
		System.out.println("Selected Range: " + SELECTED_RANGE);
		
		for(int i = 0, j = 0, counter = 0; i <= SELECTED_RANGE; i++) {
			j = (int)(Math.pow(2, i));
			mainValues.add(j);
			counter += j;
			if(counter >= SELECTED_RANGE) {
				NUM_OF_CARDS = mainValues.size();
				break;
			}
		}
		GenerateBinaryList();
	}
	
	// Fill up binaryValues
	public void GenerateBinaryList() {	
		for(int i = 1; i < SELECTED_RANGE + 1; i++) {
			S = "";
			int whichNumber = i;
			for(int j = (mainValues.size() - 1); j > -1; j--) {
				
				// Exact
				if((whichNumber - mainValues.get(j)) == 0) {
					S += "1";
					// Fill up with 0s
					while(mainValues.size() > S.length())
						S += "0";
					break;
				}
				
				// Positive
				else if((whichNumber - mainValues.get(j)) >= 0) {
					S += "1";
					whichNumber -= mainValues.get(j);
				}
					
				// Negative
				else if((whichNumber - mainValues.get(j)) < 0)
					S += "0";
			}
			binaryValues.add(S);
		}
		
		for(int i = 0; i < binaryValues.size(); i++)
			binaryValuesReversed.add(new StringBuffer(binaryValues.get(i)).reverse().toString());
		
		GenerateCardValues();
	}
	
	// Fill card values and print
	public void GenerateCardValues() {		
		for(int i = 0, k = 0; i < NUM_OF_CARDS; i++) {
			k = 0; // For splitting
			for(int j = 0; j < SELECTED_RANGE; j++) {

				if(binaryValuesReversed.get(j).charAt(i) == (char) 49) {
					
					k++;
					cardValues.add(Integer.toString(j + 1));
				}
			}
			cardSplitting.add(k);
		}
		SplitCardValues();
	}
	// Split card values and print
	public void SplitCardValues() {
		for(int i = 0, j = 0; i < NUM_OF_CARDS; i++) {
			System.out.print("Card " + (i + 1) + " : ");
			System.out.println(cardValues.subList(j, j = j + cardSplitting.get(i)));
		}
	}	

	public static List<String> SplitCardValues(int page_no) {
		int j = 0, i = 0;
		if(page_no == 0){
			System.out.println("zero");
			return cardValues.subList(j, j = j + cardSplitting.get(i));
		}
		else{
			for(; i <= page_no - 1; i++) {
				j = j + cardSplitting.get(i);
				//System.out.println("i:" + i);
				//System.out.println("j:" + j);
				//System.out.println(cardSplitting.get(i));
				
			}
			//System.out.println(cardValues.subList(j, j = j + cardSplitting.get(i)));
			//return cardValues.subList(0, 5);
			return cardValues.subList(j, j = j + cardSplitting.get(i));
		}
		
		//return cardValues.subList(previous, next);
	}
	
	public String selectNumber(){
		int index = randomGenerator.nextInt(cardValues.size());
		String item = cardValues.get(index);
		
		return item;
		
	}

}
