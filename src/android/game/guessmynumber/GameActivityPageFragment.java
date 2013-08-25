package android.game.guessmynumber;


import java.util.List;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;


public class GameActivityPageFragment extends Fragment{
	
	 GridView gridView;
	 private static int mPageNumber;
	 public static final String ARG_PAGE = "page";
	
	 /**Gets call from GameActivity class to create a new fragment as the
	  user swips**/
	 public static GameActivityPageFragment create(int pageNumber) {
		 	//create new fragment
		 	GameActivityPageFragment fragment = new GameActivityPageFragment();
		 	//[optional] set arguments for example here I put pagenumber
		 	//mPageReferenceMap.put(pageNumber , fragment);
	        Bundle args = new Bundle();
	        args.putInt(ARG_PAGE, pageNumber);
	        fragment.setArguments(args);
	        //return fragment to GameActivity
	        return fragment;
	    }
	 /**Need an empty constructor**/
	 public GameActivityPageFragment(){
		 
	 }
	 
	
	 
	 /**Called when new object is made by the create method from above**/
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt(ARG_PAGE); // [optional]Get page number
			//System.out.print("page" + mPageNumber + ": ");
		
	 }
	 /**Called after onCreate
	  	return GrideView with our ABCD**/
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 List<String> numbers2 ; 
		// TODO Auto-generated method stub		
	    
		 ViewGroup rootView = (ViewGroup) inflater
	                .inflate(R.layout.activity_game, container, false);
		 if(mPageNumber + 1 <= 5){
			 
			numbers2 = NumberGenerator.SplitCardValues(mPageNumber);
			
			String[] stockArr = new String[numbers2.size()];
			stockArr = numbers2.toArray(stockArr);
			GridView grid=(GridView)rootView.findViewById(R.id.gridViewNumbers);
		 
		 	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, stockArr);
		 	grid.setAdapter(adapter);
		 	// Set the title view to show the page number.
	     	((TextView) rootView.findViewById(R.id.pageNumber))
	     							.setText("Card " 
	     									+ Integer.toString(mPageNumber + 1)  
	     									+ " of "
	     									+ "5");
		 }
		 else{
			 ((TextView) rootView.findViewById(R.id.pageNumber))
				.setText("Enter the Number");
		 }
		 return rootView;
	}
	public int getPageNumber() {
	        return mPageNumber;
	}
}
