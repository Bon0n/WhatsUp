package com.example.whatsup.resource;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class HttpConnection {
    public void connect(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("apikey", "B6D711FCDE4D4FD5936544120E713976");
        if(conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed: HTTP Error Code: " + conn.getResponseCode());
        }

        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        BufferedReader br = new BufferedReader(in);
        String output;
        while((output = br.readLine()) != null) {
            System.out.println(output);
        }
        conn.disconnect();
    }
}
