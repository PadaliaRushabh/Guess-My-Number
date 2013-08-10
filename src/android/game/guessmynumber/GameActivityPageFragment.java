package android.game.guessmynumber;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;


public class GameActivityPageFragment extends Fragment{
	
	GridView gridView;
	
	static final String[] numbers = new String[] { 
		"A", "B", "C", "D", "E",
		"F", "G", "H", "I", "J",
		"K", "L", "M", "N", "O",
		"P", "Q", "R", "S", "T",
		"U", "V", "W", "X", "Y", "Z"};
	
	public static final String ARG_PAGE = "page";
	
	private int mPageNumber;
	
	 public static GameActivityPageFragment create(int pageNumber) {
		 GameActivityPageFragment fragment = new GameActivityPageFragment();
	        Bundle args = new Bundle();
	        args.putInt(ARG_PAGE, pageNumber);
	        fragment.setArguments(args);
	        return fragment;
	    }
	 public GameActivityPageFragment(){
		 
	 }
	 
	 @Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mPageNumber = getArguments().getInt(ARG_PAGE);
		
	}
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
		 return rootView;
	}
	public int getPageNumber() {
	    return mPageNumber;
	}


}
