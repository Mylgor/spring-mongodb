package com.dvasyukov.services;

import com.dvasyukov.web.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by DVasyukov on 23.12.2016.
 */
public class OtherService implements Runnable{

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private volatile String text;
    private String Url;

    public void setUrl(String url){
        this.Url = url;
    }

    public String getText(){
        return text;
    }

    @Override
    public void run(){
        text = getTextFromSite(this.Url);
    }

    private String getTextFromSite(String url){
        StringBuilder sb = new StringBuilder();
        try{
            URL pageUrl = new URL(url);
            URLConnection uc = pageUrl.openConnection();
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(uc.getInputStream())
            );
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null){
                sb.append(inputLine);
            }

            //парсим страничку
            String text = parsePage(sb.toString());
            return text;
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
        return null;
    }

    private String parsePage(String sb){
        String[] split = sb.split("<p>", -1);
        String str = split[1].substring(0, split[1].length() - 4);
        return str;
    }
}
