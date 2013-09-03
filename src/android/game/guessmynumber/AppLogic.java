package android.game.guessmynumber;

import java.util.ArrayList;
import java.util.List;

public class AppLogic {
	
	List<String> NumberList;
	List<Integer> IntList = new ArrayList<Integer>();
	static int  number = 0;
	
	public AppLogic(List<String> NumberList){
		this.NumberList = NumberList;
	}
	
	public void sortList(){
		for (String str : NumberList) {
			IntList.add(Integer.parseInt(str));
		}
		java.util.Collections.sort(IntList);
	}
	
	public void setSmallest(){
		sortList();
		add(IntList.get(0));
	}
	
	public void add(int number){
		AppLogic.number = AppLogic.number + number;  
	}
	
	public int getSum(){
		return AppLogic.number;
	}
}
