package android.game.guessmynumber;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class QuitGameDialogFragment extends DialogFragment {
	/* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */

	//For passing the result of dialog back to that host activity
	public interface QuitDialogListener {
	    public void onDialogPositiveClick(DialogFragment dialog);
	    public void onDialogNegativeClick(DialogFragment dialog);
	}

	//set the listener
	QuitDialogListener mListener;
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		
		try{
			// Instantiate the NoticeDialogListener so we can send events to the host
			mListener = (QuitDialogListener) activity;

		}
		
		catch(ClassCastException e){
			throw new ClassCastException(activity.toString()
                    + " must implement QuitDialogListener");

		}
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		
		//Set Message
		builder.setMessage(R.string.quit_alert_message)
				.setTitle(R.string.quit_alert_title)
		
		//Add Buttons
				.setPositiveButton(R.string.quit_alert_yes, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//The host activity implements listener so our results can be send back
						mListener.onDialogPositiveClick(QuitGameDialogFragment.this);

						
					}
				})
				.setNegativeButton(R.string.quit_alert_cancel , new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//The host activity implements listener so our results can be send back
						mListener.onDialogNegativeClick(QuitGameDialogFragment.this);
						
					}
				});
		
		return builder.create();

	}	

}
