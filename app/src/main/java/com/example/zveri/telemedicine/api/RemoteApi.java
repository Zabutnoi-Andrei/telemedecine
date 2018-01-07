package com.example.zveri.telemedicine.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by deDwarf on 12/16/2017.
 */

public class RemoteApi {
    public static final String baseUrlString = "http://81.180.72.17/";
    private static IRemoteApi api;
    /**
     * TOKEN received from server on successful login. Save it to use in all future requests
     */
    private static String TOKEN = null;

    static {
        Gson gson = new GsonBuilder()
                .setDateFormat("dd/MM/yyyy")
                .setPrettyPrinting()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrlString)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(IRemoteApi.class);
    }
    public static IRemoteApi getApi() {
        return api;
    }

    public static String getTOKEN() {
        return TOKEN;
    }

    public static void setTOKEN(String TOKEN) {
        RemoteApi.TOKEN = TOKEN;
    }
}
