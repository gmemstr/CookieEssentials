package com.gabrielsimmer.cessentials.api;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class ApiClient {
    public static HttpsURLConnection Request(String path) {
        URL url;
        HttpsURLConnection con = null;
        try {
            url = new URL(path);
            con = (HttpsURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return con;
    }

    public static JSONObject Get(HttpsURLConnection con, String[] urlParameters) {
        try {
            con.setRequestMethod("GET");
            String params = String.join("&", urlParameters);
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            return parseResponse(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static JSONObject Post(HttpsURLConnection con, String[] urlParameters) {
        try {
            con.setRequestMethod("POST");
            String params = String.join("&", urlParameters);
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(params);
            wr.flush();
            wr.close();

            return parseResponse(con);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static JSONObject parseResponse(HttpsURLConnection con) {
        try {
            con.connect();
            BufferedReader buf = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            );
            String input;
            String result = "";
            while ((input = buf.readLine()) != null) {
                result += input;
            }

            JSONParser parser = new JSONParser();
            JSONObject responseObj = (JSONObject) parser.parse(result);
            return responseObj;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
