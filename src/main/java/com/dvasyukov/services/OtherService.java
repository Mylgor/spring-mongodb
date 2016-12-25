package com.dvasyukov.services;

import com.dvasyukov.web.MainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by DVasyukov on 23.12.2016.
 */
public class OtherService{

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    private String Url;

    public OtherService(String url){
        this.Url = url;
    }

    public OtherService(){}

    public void setUrl(String url){
        this.Url = url;
    }

    public String getText() {
        try {
            Future<String> future = getTextFromSite();
            return future.get();
        } catch (Exception ex){
            log.error(ex.getMessage() + " STACK: " + Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }

    @Async("taskExecutor")
    public Future<String> getTextFromSite() throws Exception {
        log.info("START async text operation. Thread: " + Thread.currentThread().getName());
        StringBuilder sb = new StringBuilder();

        URL pageUrl = new URL(this.Url);
        URLConnection uc = pageUrl.openConnection();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(uc.getInputStream())
        );
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            sb.append(inputLine);
        }

        //парсим страничку
        String text = parsePage(sb.toString());
        log.info("END async text operation. Thread: " + Thread.currentThread().getName());
        return new AsyncResult<String>(text);
    }

    private String parsePage(String sb){
        String[] split = sb.split("<p>", -1);
        return split[1].substring(0, split[1].length() - 4);
    }
}
