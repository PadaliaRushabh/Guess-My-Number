package android.game.guessmynumber;


import java.util.List;
import java.util.Random;

import android.support.v4.app.Fragment;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;


public class GameActivityPageFragment extends Fragment {
	
	 GridView gridView;
	 private static int mPageNumber;
	 public static final String ARG_PAGE = "page";
	 private ViewGroup rootView;
	 String isNumber;
	 SecretNumber number = new SecretNumber();
	 Random random = new Random();
	 Settings settings;

	 
	 
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
		
		settings = new Settings(getActivity());
	 }
	 /**Called after onCreate
	  	return GrideView with our ABCD**/
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		 List<String> numbers2 ; 
		// TODO Auto-generated method stub		
		 rootView = (ViewGroup) inflater
	                .inflate(R.layout.activity_game, container, false);
		 if(mPageNumber == NumberGenerator.NUM_OF_CARDS){
			 
			if(settings.getCardMode().equals("0")){
		     	/*((TextView) rootView.findViewById(R.id.textViewHint))
		     		.setText("Thinking....");*/
		    }
			else{
				((TextView) rootView.findViewById(R.id.pageNumber))
					.setText("Enter the Number");	 
			 
				/*((TextView) rootView.findViewById(R.id.textViewHint))
					.setText("");*/
			}
		 }
		 else{	
			numbers2 = NumberGenerator.SplitCardValues(mPageNumber);
			java.util.Collections.shuffle(numbers2, random);
			String[] stockArr = new String[numbers2.size()];
			stockArr = numbers2.toArray(stockArr);
			GridView grid=(GridView)rootView.findViewById(R.id.gridViewNumbers);
		 
		 	ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
					R.layout.customgrideview, stockArr);
		 	grid.setAdapter(adapter);
		 	// Set the title view to show the page number.
		 	int total_cards = NumberGenerator.NUM_OF_CARDS + 1;
	     	((TextView) rootView.findViewById(R.id.pageNumber))
	     							.setText("Card " 
	     									+ Integer.toString(mPageNumber + 1)  
	     									+ " of "
	     									+ total_cards);
	     	/*if(settings.getCardMode().equals("0")){
	     		((TextView) rootView.findViewById(R.id.textViewHint))
				.setText("Is your secret number on this card?");
	     	}
	     	else if(numbers2.contains(number.getNumber())){
	     		((TextView) rootView.findViewById(R.id.textViewHint))
				.setText("Secret number is on this card");
	     	}
	     	else{
	     		((TextView) rootView.findViewById(R.id.textViewHint))
				.setText("Secret number is NOT on this card");
	     	}*/
	     	
		 }
		 return rootView;
	}
	 @Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();	
	}
	 @Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);	 
	}
	public int getPageNumber() {
	        return mPageNumber;
	}
}
