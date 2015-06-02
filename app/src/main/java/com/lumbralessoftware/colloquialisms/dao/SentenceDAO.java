package com.lumbralessoftware.colloquialisms.dao;

import android.content.Context;


import com.lumbralessoftware.colloquialisms.models.Sentence;
import com.lumbralessoftware.colloquialisms.utils.Constants;

import garin.artemiy.sqlitesimple.library.SQLiteSimpleDAO;

/**
 * Created by javiergonzalezcabezas on 23/5/15.
 */
public class SentenceDAO extends SQLiteSimpleDAO<Sentence> {
    public SentenceDAO(Context context) {
        super(Sentence.class, context, Constants.DATABASE_NAME);
    }
}
