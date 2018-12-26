package com.example.hp.myapp;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class JsonUtils {

    public static URL buildUrl(String moviesLink) {
        Uri builtUri = Uri.parse(moviesLink).buildUpon()
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static List<String> allPostersData(String json){

        JSONObject data ;
        List<String> moviesData = new ArrayList<String>();
        try{
            data = new JSONObject(json);

            JSONArray results =data.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                moviesData.add(results.getJSONObject(i).toString());
            }

        }catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesData;
    }

    public static Movie parseJson (String json){

        Movie res= null ;
        JSONObject data ;
        try {
            data = new JSONObject(json);

            String title = data.getString("title");

            String poster_path = data.getString("poster_path");

            String overview =data.getString("overview");

            String release_date = data.getString("release_date");

            String vote_average = data.getString("vote_average");

            res = new Movie(poster_path,title, overview,  vote_average,  release_date);

        } catch (JSONException e) {
        e.printStackTrace();
    }
        return res;
    }
}
