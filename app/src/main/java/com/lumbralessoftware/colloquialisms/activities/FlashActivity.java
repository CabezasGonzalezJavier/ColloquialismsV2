package com.lumbralessoftware.colloquialisms.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.lumbralessoftware.colloquialisms.R;
import com.lumbralessoftware.colloquialisms.dao.SentenceDAO;
import com.lumbralessoftware.colloquialisms.models.Sentence;
import com.lumbralessoftware.colloquialisms.utils.Constants;
import com.lumbralessoftware.colloquialisms.utils.Utils;
import com.lumbralessoftware.colloquialisms.webservice.Client;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FlashActivity extends ActionBarActivity {

    private SentenceDAO mSentenceDAO;
    private List<Sentence> mSentenceList;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        mSentenceDAO = new SentenceDAO(this);
        mProgress = new ProgressDialog(this,R.style.Transparent);

        getData();
    }

    public void getData(){
        if(Utils.isOnline(this)){
            getSentences();
        }else if(exitsDB()){
            mSentenceList=mSentenceDAO.readAllAsc();
            gotActivity();
        }else {
            Toast.makeText(this,R.string.no_connection,Toast.LENGTH_LONG);
        }
    }

    public void gotActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean exitsDB(){
        int sentence = mSentenceDAO.getCount();

        return sentence > 0;
    }

    public void insertDataBase(){
        mSentenceDAO.createAll(mSentenceList);
        mProgress.cancel();
        gotActivity();
    }

    public void getSentences(){
        mProgress.show();
        Callback<List<Sentence>> callback = new Callback<List<Sentence>>() {
            @Override
            public void success(List<Sentence> sentences, Response response) {
                Log.v("Client", "success");
                mSentenceList = sentences;
                insertDataBase();
            }

            @Override
            public void failure(RetrofitError error) {
                mProgress.cancel();
                Log.v("Client", "failure");
            }
        };
        Client.initRestAdapter().getSentences(Constants.LANGUAJE_ORIGIN,Constants.LANGUAJE_DESTINATION,callback);
    }

}
