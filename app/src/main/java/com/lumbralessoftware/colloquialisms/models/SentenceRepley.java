package com.lumbralessoftware.colloquialisms.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by javiergonzalezcabezas on 3/6/15.
 */
public class SentenceRepley {
    @SerializedName("sentence_origin")
    @Expose
    private String sentenceOrigin;
    @SerializedName("sentence_destination")
    @Expose
    private String sentenceDestination;
    @SerializedName("language_origin")
    @Expose
    private String languageOrigin;
    @SerializedName("language_destination")
    @Expose
    private String languageDestination;

    /**
     *
     * @return
     * The sentenceOrigin
     */
    public String getSentenceOrigin() {
        return sentenceOrigin;
    }

    /**
     *
     * @param sentenceOrigin
     * The sentence_origin
     */
    public void setSentenceOrigin(String sentenceOrigin) {
        this.sentenceOrigin = sentenceOrigin;
    }

    /**
     *
     * @return
     * The sentenceDestination
     */
    public String getSentenceDestination() {
        return sentenceDestination;
    }

    /**
     *
     * @param sentenceDestination
     * The sentence_destination
     */
    public void setSentenceDestination(String sentenceDestination) {
        this.sentenceDestination = sentenceDestination;
    }

    /**
     *
     * @return
     * The languageOrigin
     */
    public String getLanguageOrigin() {
        return languageOrigin;
    }

    /**
     *
     * @param languageOrigin
     * The language_origin
     */
    public void setLanguageOrigin(String languageOrigin) {
        this.languageOrigin = languageOrigin;
    }

    /**
     *
     * @return
     * The languageDestination
     */
    public String getLanguageDestination() {
        return languageDestination;
    }

    /**
     *
     * @param languageDestination
     * The language_destination
     */
    public void setLanguageDestination(String languageDestination) {
        this.languageDestination = languageDestination;
    }

}
