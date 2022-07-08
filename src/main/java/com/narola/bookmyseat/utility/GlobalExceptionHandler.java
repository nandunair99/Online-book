package com.narola.bookmyseat.utility;

import com.narola.bookmyseat.exception.ApplicationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView numberFormat(ApplicationException e) {
        ModelAndView modelAndView = new ModelAndView();
        if (e.getViewName() == null) {
            modelAndView.setViewName("AdminPage/Error");
            modelAndView.addObject("errormsg",e.getMessage());
        } else {
            modelAndView.setViewName(e.getViewName());
            for (Map.Entry<String, String> entry : e.getErrorList().entrySet()) {
                modelAndView.addObject(entry.getKey(), entry.getValue());
            }
        }
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView numberFormat(Exception e) {
        ModelAndView modelAndView = new ModelAndView("AdminPage/Error");
        e.getStackTrace();
        modelAndView.addObject("errormsg", e.getMessage());
        return modelAndView;
    }
}
