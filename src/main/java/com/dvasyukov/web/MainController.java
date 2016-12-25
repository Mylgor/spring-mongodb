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

import com.dvasyukov.model.Instrument;

import java.util.concurrent.Future;


@Controller
public class MainController {

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private InstrumentService instrumentService;
    private OtherService leftSiteService = new OtherService("http://example.com/");

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAll() throws Exception{
        ModelAndView modelAndView = new ModelAndView("index");

        Future<String> asyncText = leftSiteService.getTextFromSite();
        log.info("Get all instruments. Thread: " + Thread.currentThread().getName());
        modelAndView.addObject("instruments", instrumentService.getAll());

        while (!asyncText.isDone()){
            log.info("Sleep thread!");
            Thread.sleep(10);
        }
        modelAndView.addObject("siteText", asyncText.get());
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addInstrument(final HttpServletRequest request, HttpServletResponse response) throws Exception {

        //Проверяем корректность данных
        Instrument instrument = instrumentService.checkData(request);
        Future<Boolean> asyncSave = null;
        if (instrument != null){
            asyncSave = instrumentService.asyncAdd(instrument);
        }

        log.info("Simple comment for check thread. Thread:" + Thread.currentThread().getName());
        if (asyncSave != null) {
            while (!asyncSave.isDone()) {
                log.info("Sleep thread!");
                Thread.sleep(10);
            }
            asyncSave.get();
        }
        return "redirect:/";
    }
}
