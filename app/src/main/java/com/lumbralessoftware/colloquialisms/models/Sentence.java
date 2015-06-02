package com.lumbralessoftware.colloquialisms.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import garin.artemiy.sqlitesimple.library.annotations.Column;

/**
 * Created by javiergonzalezcabezas on 23/5/15.
 */
public class Sentence {

    public transient static final String COLUMN_ID = "_id";      //sentence_origin
    public transient static final String COLUMN_SENTENCE_ORIGIN = "sentence_origin";
    public transient static final String COLUMN_SENTENCE_DESTINATION = "sentence_destination";
    public transient static final String COLUMN_LANGUAGE_ORIGIN = "language_origin";
    public transient static final String COLUMN_LANGUAGE_DESTINATION = "language_destination";

    @Expose
    @Column(name = COLUMN_ID, isPrimaryKey = true)
    private String id;

    @SerializedName("sentence_origin")
    @Expose
    @Column(name = COLUMN_SENTENCE_ORIGIN)
    private String sentenceOrigin;

    @SerializedName("sentence_destination")
    @Expose
    @Column(name = COLUMN_SENTENCE_DESTINATION)
    private String sentenceDestination;

    @SerializedName("language_origin")
    @Expose
    @Column(name = COLUMN_LANGUAGE_ORIGIN)
    private String languageOrigin;

    @SerializedName("language_destination")
    @Expose
    @Column(name = COLUMN_LANGUAGE_DESTINATION)
    private String languageDestination;

    @Expose
    private String created;

//    @Expose
//    private Integer id;
//    @SerializedName("sentence_origin")
//    @Expose
//    private String sentenceOrigin;
//    @SerializedName("sentence_destination")
//    @Expose
//    private String sentenceDestination;
//    @SerializedName("language_origin")
//    @Expose
//    private String languageOrigin;
//    @SerializedName("language_destination")
//    @Expose
//    private String languageDestination;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSentenceOrigin() {
        return sentenceOrigin;
    }

    public void setSentenceOrigin(String sentenceOrigin) {
        this.sentenceOrigin = sentenceOrigin;
    }

    public String getSentenceDestination() {
        return sentenceDestination;
    }

    public void setSentenceDestination(String sentenceDestination) {
        this.sentenceDestination = sentenceDestination;
    }

    public String getLanguageOrigin() {
        return languageOrigin;
    }

    public void setLanguageOrigin(String languageOrigin) {
        this.languageOrigin = languageOrigin;
    }

    public String getLanguageDestination() {
        return languageDestination;
    }

    public void setLanguageDestination(String languageDestination) {
        this.languageDestination = languageDestination;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
