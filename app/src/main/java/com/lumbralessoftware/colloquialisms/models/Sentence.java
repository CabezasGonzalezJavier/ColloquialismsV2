package com.lumbralessoftware.colloquialisms.models;


import garin.artemiy.sqlitesimple.library.annotations.Column;

/**
 * Created by javiergonzalezcabezas on 23/5/15.
 */
public class Sentence {

    public transient static final String COLUMN_ID = "_id";
    public transient static final String COLUMN_SENTENCE_ORIGIN = "sentence_origin";
    public transient static final String COLUMN_SENTENCE_DESTINATION = "sentence_destination";
    public transient static final String COLUMN_LANGUAGE_ORIGIN = "language_origin";
    public transient static final String COLUMN_LANGUAGE_DESTINATION = "language_destination";

    @Column(name = COLUMN_ID, isPrimaryKey = true)
    private String id;
    @Column(name = COLUMN_SENTENCE_ORIGIN)
    private String sentenceOrigin;
    @Column(name = COLUMN_SENTENCE_DESTINATION)
    private String sentenceDestination;
    @Column(name = COLUMN_LANGUAGE_ORIGIN)
    private String language_origin;
    @Column(name = COLUMN_LANGUAGE_DESTINATION)
    private String language_destination;

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

    public String getLanguage_origin() {
        return language_origin;
    }

    public void setLanguage_origin(String language_origin) {
        this.language_origin = language_origin;
    }

    public String getLanguage_destination() {
        return language_destination;
    }

    public void setLanguage_destination(String language_destination) {
        this.language_destination = language_destination;
    }
}
