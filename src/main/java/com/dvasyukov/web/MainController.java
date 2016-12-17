package com.dvasyukov.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.dvasyukov.services.InstrumentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private InstrumentService instrumentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("instruments", instrumentService.getAll());
        modelAndView.addObject("siteText", getTextFromSite("http://example.com/"));
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addInstrument(HttpServletRequest request, HttpServletResponse response) {
        instrumentService.addRecordInDb(request);
        return "redirect:/";
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
