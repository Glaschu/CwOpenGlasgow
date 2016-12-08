package com.Jamesglasgow.Cw.OpenGlasgow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by jamesglasgow on 08/12/2016.
 */

public class AboutDialogue  extends DialogFragment {

    @Override
    /**
     * this create a dialog box with the info to the string to build it with an button to close it
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder mcAboutDialog = new AlertDialog.Builder(getActivity());
        mcAboutDialog.setMessage(R.string.dialog_About)
                .setPositiveButton(R.string.dialog_About_OK_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        mcAboutDialog.setTitle("About");
        mcAboutDialog.setIcon(R.drawable.carpark_img);
        // Create the AlertDialog object and return it
        return mcAboutDialog.create();
    }
}
