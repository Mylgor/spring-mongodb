package com.dvasyukov.web;

import com.dvasyukov.model.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.dvasyukov.services.InstrumentService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {
    @Autowired private InstrumentService instrumentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showAll() {
        ModelAndView modelAndView = new ModelAndView("all");

        modelAndView.addObject("instruments", instrumentService.getAll());

        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView showAddForm() {
        return new ModelAndView("add_form", "instrument", new Instrument());
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addInstrument(@ModelAttribute("instrument") Instrument instrument) {
        if(instrument.getId() == null) {
            instrumentService.add(instrument);
        } else {
            instrumentService.update(instrument);
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView showEditForm(@RequestParam(required = true) Long id) {
        return new ModelAndView("add_form", "instrument", instrumentService.get(id));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteInstrument(@RequestParam(required = true) Long id) {
        instrumentService.remove(id);

        return "redirect:/";
    }
}
