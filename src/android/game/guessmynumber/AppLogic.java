package android.game.guessmynumber;

import java.util.ArrayList;
import java.util.List;

public class AppLogic {
	
	List<String> NumberList;
	List<Integer> IntList = new ArrayList<Integer>();
	int  number = 0;
	
	public AppLogic(List<String> NumberList){
		this.NumberList = NumberList;
	}
	
	public void sortList(){
		for (String str : NumberList) {
			IntList.add(Integer.parseInt(str));
		}
		java.util.Collections.sort(IntList);
	}
	
	public int getSmallest(){
		sortList();
		return IntList.get(0);
		
		//System.out.println("Get:" + IntList.get(0));
	}
	
	public void add(int number){
		this.number = this.number + number;  
		System.out.println("APP:" + this.number);
	}
	
	/*public int getSum(){
		return this.number;
	}*/
}
