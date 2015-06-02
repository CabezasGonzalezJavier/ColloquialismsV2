package com.lumbralessoftware.colloquialisms.webservice;

import com.lumbralessoftware.colloquialisms.models.Sentence;
import com.lumbralessoftware.colloquialisms.utils.Constants;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by javiergonzalezcabezas on 2/6/15.
 */
public class Client {
    public interface ClientInterface{
        @GET("/sentences/")
            void getSentences(@Query("lang_origin") String langOrigin,@Query("lang_dest") String langDest, Callback<List<Sentence>> callback);

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
