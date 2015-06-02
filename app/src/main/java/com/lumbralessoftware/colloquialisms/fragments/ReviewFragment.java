package com.lumbralessoftware.colloquialisms.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.lumbralessoftware.colloquialisms.R;
import com.lumbralessoftware.colloquialisms.activities.MainActivity;
import com.lumbralessoftware.colloquialisms.dao.SentenceDAO;
import com.lumbralessoftware.colloquialisms.models.Sentence;
import com.lumbralessoftware.colloquialisms.utils.Constants;
import com.lumbralessoftware.colloquialisms.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link ReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewFragment extends Fragment implements View.OnClickListener {

    private static List<Sentence> mSentenceList;
    private SentenceDAO mSentenceDAO;
    private TextView mSentenceENG;
    private TextView mSentenceEs;
    private ImageButton mPause;
    private final Handler handler = new Handler();
    private Runnable r;
    private boolean mPauseState = false;


    public static ReviewFragment newInstance(int sectionNumber) {
        ReviewFragment fragment = new ReviewFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public ReviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_review, container, false);
        mSentenceENG = (TextView) view.findViewById(R.id.fragment_review_eng);
        mSentenceEs = (TextView) view.findViewById(R.id.fragment_review_es);
        mPause = (ImageButton) view.findViewById(R.id.fragment_review_button);
        mPause.setOnClickListener(this);

        getData();

        return view;
    }

    public void getData(){
        mSentenceDAO = new SentenceDAO(getActivity());
        mSentenceList = new ArrayList<Sentence>();
        if(exitsDB()){
            mSentenceList=mSentenceDAO.readAllAsc();
            showSentence();
            thread();
        }
    }

    public boolean exitsDB(){
        int sentence = mSentenceDAO.getCount();
        return sentence > 0;
    }

    public void showSentence(){
        int position = Utils.randInt(0, mSentenceList.size()-1);

        mSentenceENG.setText(mSentenceList.get(position).getSentenceOrigin().toString());
        mSentenceEs.setText(mSentenceList.get(position).getSentenceDestination().toString());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(Constants.ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void thread(){
        r = new Runnable()
        {
            int x = 1;
            public void run()
            {
                showSentence();
                handler.postDelayed(this, Constants.TIME_REVIEW);
            }
        };

        handler.postDelayed(r, Constants.TIME_REVIEW);
    }

    @Override
    public void onClick(View v) {
        if (mPauseState){
            thread();
            mPauseState=false;
            mPause.setImageResource(android.R.drawable.ic_media_pause);
        }else{
            handler.removeCallbacks(r);
            mPauseState=true;
            mPause.setImageResource(android.R.drawable.ic_media_play);
        }
    }
}
