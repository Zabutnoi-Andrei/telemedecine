package com.example.zveri.telemedicine.api;

import android.util.Log;

import java.io.IOException;

import retrofit2.Response;
import retrofit2.http.Path;

/**
 * Created by deDwarf on 1/8/2018.
 */

public class RemoteApiUtils {
    public static<T> void logResponse(String tag, Response<T> r){
        Log.d(tag, "Response code: " + r.code() + " " + r.message());
        try {
            if (r.isSuccessful()){
                Log.d(tag, "Response errorBody: " + r.raw().body().string());
            }
            else {
                Log.d(tag, "Response errorBody: " + r.errorBody().string());
            }
        } catch (Exception e) {
            // do nothing
        }
    }

    public static void logResponse(String tag, Throwable t){
        Log.d(tag, "Response error message: " + t.getLocalizedMessage());
    }
}
