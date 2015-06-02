package com.lumbralessoftware.colloquialisms;

import android.app.Application;
import android.graphics.Typeface;

import com.lumbralessoftware.colloquialisms.utils.Constants;

import garin.artemiy.sqlitesimple.library.SQLiteSimple;
import garin.artemiy.sqlitesimple.library.util.SimpleDatabaseUtil;

/**
 * Created by javiergonzalezcabezas on 2/6/15.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        initializeTypefaces();
        // also may use isFirstStartOnAppVersion with your version
        if (SimpleDatabaseUtil.isFirstApplicationStart(this)) {
            SQLiteSimple databaseSimple = new SQLiteSimple(this, Constants.DATABASE_NAME);
//            databaseSimple.rawQuery("CREATE  TABLE "main"."Sentence" ("_id" VARCHAR PRIMARY KEY NOT NULL , "sentence_origin" VARCHAR NOT NULL , "sentence_destination" VARCHAR NOT NULL , "language_origin" VARCHAR NOT NULL, "language_destination" VARCHAR NOT NULL )");
        }

    }

    public static class Fonts {
        public static Typeface PENCIL;
    }

    private void initializeTypefaces() {

        Fonts.PENCIL = Typeface.createFromAsset(getAssets(), Constants.FONT);
    }

}
