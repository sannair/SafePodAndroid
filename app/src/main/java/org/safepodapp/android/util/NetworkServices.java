package org.safepodapp.android.util;

import android.util.Log;

import org.safepodapp.android.SafePodApplication;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkServices {

    public final static String USER_AGENT = "Mozilla/5.0";

    // HTTP GET request
    public static String sendGet(String url) throws Exception {

        Log.d(SafePodApplication.getDebugTag(), "I came into the fragment");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        Log.d(SafePodApplication.getDebugTag(), "\nSending 'GET' request to URL : " + url);
        Log.d(SafePodApplication.getDebugTag(), "Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
//		Log.d(SafePodApplication.getDebugTag(), response.toString());

        return response.toString();
    }

    // HTTP POST request
    public static String sendPost(String url, String experience) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(experience);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        Log.d(SafePodApplication.getDebugTag(), "\nSending 'POST' request to URL : " + url);
        Log.d(SafePodApplication.getDebugTag(), "Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        Log.d(SafePodApplication.getDebugTag(), response.toString());

        return response.toString();

    }

}
