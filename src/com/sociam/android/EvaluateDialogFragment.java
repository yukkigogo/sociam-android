package com.sociam.android;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

@SuppressLint("ValidFragment")
public class EvaluateDialogFragment extends DialogFragment {

    private String crime_id;
    private int type;
    public EvaluateDialogFragment(String id, int type) {
            this.crime_id=id;
            this.type=type;
    }
    
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
    	setRetainInstance(true);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Vote this!");

        builder.setNegativeButton("Up Vote", new DialogInterface.OnClickListener() {
                
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        // connect server to evaluate and closed and reload
                        
                           EvaluateAsyncTask evaluate = new EvaluateAsyncTask(getActivity());
                        if(type==0)
                           evaluate.execute(crime_id,"1","0","crime");
                        else if(type==1)
                        	evaluate.execute(crime_id,"1","0","message");
                        ((MainActivity) getActivity()).reloadData();
                        EvaluateDialogFragment.this.getDialog().dismiss();
                }
        });
        
        builder.setNeutralButton("Down Vote", new DialogInterface.OnClickListener() {
                
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        EvaluateAsyncTask evaluate = new EvaluateAsyncTask(getActivity());
                        if(type==0)	evaluate.execute(crime_id,"0","1","crime");
                        else if(type==1) evaluate.execute(crime_id,"0","1","message");
                        ((MainActivity) getActivity()).reloadData();
                        EvaluateDialogFragment.this.getDialog().dismiss();

                }
        });
        
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        EvaluateDialogFragment.this.getDialog().dismiss();
                        
                }
        });
                                
        return builder.create();
    }
    
    @Override
	public void onDestroyView() {
	  if (getDialog() != null && getRetainInstance())
	    getDialog().setOnDismissListener(null);
	  super.onDestroyView();
	}

    
}
