package com.lumbralessoftware.colloquialisms.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.lumbralessoftware.colloquialisms.R;
import com.lumbralessoftware.colloquialisms.dao.SentenceDAO;
import com.lumbralessoftware.colloquialisms.models.Sentence;

import java.util.List;

public class FlashActivity extends ActionBarActivity {

        private SentenceDAO mSentenceDAO;
        private List<Sentence> mSentenceList;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_flash);

            mSentenceDAO = new SentenceDAO(this);
            getData();
        }

    public void getData(){
        if(exitsDB()){
            mSentenceList=mSentenceDAO.readAllAsc();
            gotActivity();
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

}
