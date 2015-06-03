package com.lumbralessoftware.colloquialisms.webservice;

import com.lumbralessoftware.colloquialisms.models.Sentence;
import com.lumbralessoftware.colloquialisms.models.SentenceRepley;
import com.lumbralessoftware.colloquialisms.utils.Constants;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by javiergonzalezcabezas on 2/6/15.
 */
public class Client {
    public interface ClientInterface{
        @GET("/sentences/")
            void getSentences(@Query("lang_origin") String langOrigin,@Query("lang_dest") String langDest, Callback<List<Sentence>> callback);

        @Headers({
                "Content-Type: application/json",
                "User-Agent: curl"
        })
        @POST("/sentences/")
        void replyController(@Body SentenceRepley sentenceRepley, Callback<Sentence> callback);
    }

    public static ClientInterface initRestAdapter()
    {
        OkHttpClient client = new OkHttpClient();

        return (ClientInterface) new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(Constants.URL)
                .build()
                .create(ClientInterface.class);
    }
}
