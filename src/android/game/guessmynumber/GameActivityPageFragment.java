package android.game.guessmynumber;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;


public class GameActivityPageFragment extends Fragment{
	
	 GridView gridView;
	 private int mPageNumber;
	 
	 static final String[] numbers = new String[] { 
		"A", "B", "C", "D", "E",
		"F", "G", "H", "I", "J",
		"K", "L", "M", "N", "O",
		"P", "Q", "R", "S", "T",
		"U", "V", "W", "X", "Y", "Z"};
	
	 public static final String ARG_PAGE = "page";
	
	 /**Gets call from GameActivity class to create a new fragment as the
	  user swips**/
	 public static GameActivityPageFragment create(int pageNumber) {
		 	//create new fragment
		 	GameActivityPageFragment fragment = new GameActivityPageFragment();
		 	//[optional] set arguments for example here I put pagenumber
	        Bundle args = new Bundle();
	        args.putInt(ARG_PAGE, pageNumber);
	        fragment.setArguments(args);
	        Log.d("create", fragment.toString());
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
		Log.d("onCreate", "onCreate");
		
	 }
	 /**Called after onCreate
	  	return GrideView with our ABCD**/
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 ViewGroup rootView = (ViewGroup) inflater
	                .inflate(R.layout.activity_game, container, false);
		 GridView grid=(GridView)rootView.findViewById(R.id.gridViewNumbers);
		 
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, numbers);
		 
		 grid.setAdapter(adapter);
		 Log.d("oncreateView", "onCreateView");
		 return rootView;
	}


}
