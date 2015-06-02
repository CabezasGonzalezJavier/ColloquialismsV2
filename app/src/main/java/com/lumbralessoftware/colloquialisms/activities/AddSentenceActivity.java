package com.lumbralessoftware.colloquialisms.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.lumbralessoftware.colloquialisms.R;

public class AddSentenceActivity extends ActionBarActivity {

    private EditText mEditTextOrigin;
    private EditText mEditTextDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sentence);

        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroundactionbarr));

        mEditTextOrigin = (EditText) findViewById(R.id.activity_add_sentence_editText_origin);
        mEditTextDestination = (EditText) findViewById(R.id.activity_add_sentence_editText_destination);

    }

    public void showAlertView(){

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(getString(R.string.fragment_test_eng_solution));
        alert.setMessage("");
        alert.setCancelable(false);
        alert.setPositiveButton(getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                }
        );
        AlertDialog alert11 = alert.create();
        alert11.show();
        TextView messageText = (TextView) alert11.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        messageText.setText(R.string.activity_add_sentence_review);

    }

}
