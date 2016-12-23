package com.dvasyukov.web;

import com.dvasyukov.services.OtherService;
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
import java.util.concurrent.CountDownLatch;

import com.dvasyukov.model.Instrument;


@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private InstrumentService instrumentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAll() {

        ModelAndView modelAndView = new ModelAndView("index");

        try {
            //Get text from site
            OtherService siteService = new OtherService();
            siteService.setUrl("http://example.com/");
            Thread thread = new Thread(siteService);
            thread.start();

            modelAndView.addObject("instruments", instrumentService.getAll());

            thread.join();
            modelAndView.addObject("siteText", siteService.getText());
        } catch (Exception ex){
          log.error(ex.getMessage());
        }
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addInstrument(final HttpServletRequest request, HttpServletResponse response) {

        //Проверяем корректность данных
        final Instrument intrument = instrumentService.checkData(request);
        if (intrument != null){
            //Пишем!
            new Thread(new Runnable() {
                @Override
                public void run() {
                    instrumentService.add(intrument);
                }
            }).start();
        }

        log.info("End POST request");
        return "redirect:/";
    }
}
