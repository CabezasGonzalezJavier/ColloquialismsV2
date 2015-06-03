package com.lumbralessoftware.colloquialisms.activities;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.backgroundactionbarr));
        mSentenceDAO = new SentenceDAO(this);

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
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }

    public boolean exitsDB(){
        int sentence = mSentenceDAO.getCount();

        return sentence > 0;
    }

    public void insertDataBase(){
        mSentenceDAO.createAll(mSentenceList);
        gotActivity();
    }

    public void getSentences(){
        Callback<List<Sentence>> callback = new Callback<List<Sentence>>() {
            @Override
            public void success(List<Sentence> sentences, Response response) {
                Log.v("Client", "success");
                mSentenceList = sentences;
                insertDataBase();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(FlashActivity.this,getString(R.string.error_retrofit),Toast.LENGTH_LONG).show();
                mSentenceList=mSentenceDAO.readAllAsc();
                gotActivity();
            }
        };
        Client.initRestAdapter().getSentences(Constants.LANGUAJE_ORIGIN,Constants.LANGUAJE_DESTINATION,callback);
    }

}
