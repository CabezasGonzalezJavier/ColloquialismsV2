package com.lumbralessoftware.colloquialisms.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v4.app.Fragment;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lumbralessoftware.colloquialisms.R;
import com.lumbralessoftware.colloquialisms.activities.MainActivity;
import com.lumbralessoftware.colloquialisms.models.Sentence;
import com.lumbralessoftware.colloquialisms.models.SentenceRepley;
import com.lumbralessoftware.colloquialisms.utils.Constants;
import com.lumbralessoftware.colloquialisms.utils.Utils;
import com.lumbralessoftware.colloquialisms.webservice.Client;

import org.json.JSONException;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

/**
 * Use the {@link AddSentenceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddSentenceFragment extends Fragment implements View.OnClickListener,TextView.OnEditorActionListener{

    private EditText mEditTextOrigin;
    private EditText mEditTextDestination;
    private ProgressBar mProgressBar;
    private String mOriginString;
    private String mDestinationString;

    public static AddSentenceFragment newInstance(int sectionNumber) {
        AddSentenceFragment fragment = new AddSentenceFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public AddSentenceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_sentence, container, false);
        mEditTextOrigin = (EditText) view.findViewById(R.id.fragment_add_sentence_editText_origin);
        mEditTextDestination = (EditText) view.findViewById(R.id.fragment_add_sentence_editText_destination);
        TextView sendButton = (TextView) view.findViewById(R.id.fragment_add_sentence_button);
        mProgressBar = (ProgressBar) view.findViewById(R.id.fragment_add_sentence_progress_bar);
        sendButton.setOnClickListener(this);
        mEditTextDestination.setOnEditorActionListener(this);

        if (savedInstanceState != null) {
            mEditTextOrigin.setText(savedInstanceState.getString("origin"));
            mEditTextDestination.setText(savedInstanceState.getString("destination"));
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }


    public void showAlertView() {

        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setTitle(getString(R.string.activity_add_sentence_title_review));
        alert.setMessage("");
        alert.setCancelable(false);
        alert.setPositiveButton(getString(android.R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mEditTextDestination.setText("");
                        mEditTextOrigin.setText("");
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

    public SentenceRepley buildReplay() throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"sentence_origin\":\"");
        stringBuilder.append(mEditTextOrigin.getText());
        stringBuilder.append("\",\"sentence_destination\":\"");
        stringBuilder.append(mEditTextDestination.getText());
        stringBuilder.append("\",\"language_origin\":\"");
        stringBuilder.append(Constants.LANGUAJE_ORIGIN);
        stringBuilder.append("\",\"language_destination\":\"");
        stringBuilder.append(Constants.LANGUAJE_DESTINATION);
        stringBuilder.append("\"}");

        final Gson gson = new Gson();
        final SentenceRepley sentenceRepley = gson.fromJson(stringBuilder.toString(), SentenceRepley.class);

        return sentenceRepley;
    }

    public void sendSentence() throws JSONException {
        mProgressBar.setVisibility(View.VISIBLE);
        Callback<Sentence> callback = new Callback<Sentence>() {
            @Override
            public void success(Sentence sentence, Response response) {
                Log.v("success", String.valueOf(response.getStatus()));
                showAlertView();
                mProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void failure(RetrofitError error) {
                String json = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
                Log.v("failure", json.toString());
                Toast.makeText(getActivity(), R.string.error_retrofit, Toast.LENGTH_LONG).show();
                mProgressBar.setVisibility(View.INVISIBLE);
            }
        };
        Client.initRestAdapter().replyController(buildReplay(), callback);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_add_sentence_button:
                checkError();
                break;
        }
    }

    public void checkError(){
        if (mEditTextOrigin.getText().toString().isEmpty()) {
            mEditTextOrigin.setError(getString(R.string.empty_field));
        } else if (mEditTextDestination.getText().toString().isEmpty()) {
            mEditTextDestination.setError(getString(R.string.empty_field));
        } else {
            if(Utils.isOnline(getActivity()))
                try {
                    sendSentence();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            else{
                Toast.makeText(getActivity(),R.string.no_connection,Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
            checkError();
        }
        return true;
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
//        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putString("origin",mEditTextOrigin.getText().toString());
        savedInstanceState.putString("destination", mEditTextDestination.getText().toString());
    }

}
