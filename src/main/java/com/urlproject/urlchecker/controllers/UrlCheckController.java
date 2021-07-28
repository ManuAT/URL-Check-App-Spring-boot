package com.urlproject.urlchecker.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlCheckController {

    private final String isSiteUp = "Yes site is Up";
    private final String isSiteDown = "No site is Down";
    private final String wrongUrl = "Incorrect Url";

    @GetMapping("/check")
    public String getUrlStatusMessege(@RequestParam String url){
        String returnMessege = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int connResponseCode = conn.getResponseCode()/100;
            if (connResponseCode != 2 && connResponseCode != 3)
                returnMessege = isSiteDown;
            else
                returnMessege = isSiteUp;
        } catch (MalformedURLException e) {
            returnMessege = wrongUrl;
        } catch (IOException e) {
            returnMessege = isSiteDown;
        }
        return returnMessege;
    }
}
