package com.narola.bookmyseat.location.controller;

import com.narola.bookmyseat.entity.City;
import com.narola.bookmyseat.entity.GetPlaces;
import com.narola.bookmyseat.entity.State;
import com.narola.bookmyseat.exception.ApplicationException;
import com.narola.bookmyseat.location.service.ILocationService;
import com.narola.bookmyseat.utility.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Autowired
    private Validator stateValidator;
    @Autowired
    private Validator cityValidator;
    @Autowired
    private MessageSource messageSource;

    @Qualifier("locationServiceImpl")
    @Autowired
    private ILocationService iLocationService;

    @InitBinder("state")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(stateValidator);
    }

    @InitBinder("city")
    public void initBinder1(WebDataBinder binder) {
        binder.addValidators(cityValidator);
    }

    @GetMapping("/")
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/place", method = {RequestMethod.GET})
    public ModelAndView place(HttpServletRequest request) {
        GetPlaces getPlaces = iLocationService.getAllPlace();
        ModelAndView placeModelAndView = new ModelAndView("AdminPage/Place");
        placeModelAndView.addObject("Statedata", getPlaces.getStateData());
        placeModelAndView.addObject("Citydata", getPlaces.getCityData());
        String errorO = request.getParameter(Constant.ERROR_OCCURS);
        String errTxtMsg = request.getParameter(Constant.ERROR_TEXTMSG);

        if (errorO != null && errTxtMsg != null) {
            placeModelAndView.addObject(Constant.ERROR_OCCURS, request.getParameter(Constant.ERROR_OCCURS));
            placeModelAndView.addObject(Constant.ERROR_TEXTMSG, request.getParameter(Constant.ERROR_TEXTMSG));
        }
        return placeModelAndView;
    }

    @RequestMapping(value = {"/insertstate", "/updatestate"}, method = {RequestMethod.POST})
    public String insertUpState(@Validated State state, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes attribute) {
        Map<String, String> errorList = new HashMap<>();
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError e : errors
            ) {
                errorList.put("validErrorStateName",messageSource.getMessage(e.getCode(), null, Locale.getDefault()));
                throw new ApplicationException("redirect:/location/place", errorList);
            }
        } else {
            state.setCountryId(1);
            iLocationService.insertUpdateState(state, request);
            if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_STATE_INSERT)) {
                attribute.addAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
                attribute.addAttribute(Constant.ERROR_TEXTMSG, "State " + Constant.SUCCESS_TEXT_INSERTMSG);
            } else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_STATE_UPDATE)) {
                attribute.addAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
                attribute.addAttribute(Constant.ERROR_TEXTMSG, "State " + Constant.SUCCESS_TEXT_UPDATEMSG);
            }
        }
        return "redirect:/location/place";
    }

    @RequestMapping(value = "/deletestate", method = {RequestMethod.GET})
    public String deleteState(@RequestParam("stateId") int stateId, RedirectAttributes attribute) {
        iLocationService.deleteState(stateId);
        attribute.addAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
        attribute.addAttribute(Constant.ERROR_TEXTMSG, "State " + Constant.SUCCESS_TEXT_DELETEMSG);
        return "redirect:/location/place";
    }

    //city

    @RequestMapping(value = {"/insertcity", "/updatecity"}, method = {RequestMethod.POST})
    public String insertUpCity(@Validated City city, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes attribute) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errorList = new HashMap<>();
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError e : errors) {
                if (e.getCode().equals("city.empty") || e.getCode().equals("city.invalid") || e.getCode().equals("city.exist")) {

                    errorList.put("validErrorCityName",messageSource.getMessage(e.getCode(), null, Locale.getDefault()));
                }
                if (e.getCode().equals("stateId.empty")) {

                    errorList.put("validErrorSelectState",messageSource.getMessage(e.getCode(), null, Locale.getDefault()));

                }
                throw new ApplicationException("redirect:/location/place", errorList);
            }
        } else {
            iLocationService.insertUpdateCity(city, request);
            if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CITY_INSERT)) {
                attribute.addAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
                attribute.addAttribute(Constant.ERROR_TEXTMSG, "City " + Constant.SUCCESS_TEXT_INSERTMSG);
            } else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_CITY_UPDATE)) {
                attribute.addAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
                attribute.addAttribute(Constant.ERROR_TEXTMSG, "City " + Constant.SUCCESS_TEXT_UPDATEMSG);
            }
        }
        return "redirect:/location/place";
    }

    @RequestMapping(value = "/deletecity", method = {RequestMethod.GET})
    public String deleteCity(@RequestParam("cityID") int cityID, RedirectAttributes attribute) {
        iLocationService.deleteCity(cityID);
        attribute.addAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
        attribute.addAttribute(Constant.ERROR_TEXTMSG, "City " + Constant.SUCCESS_TEXT_DELETEMSG);
        return "redirect:/location/place";
    }
    //jpa practice

    @PostMapping("/jpa-persist")
    public ResponseEntity<String> jpaPersistState(@RequestParam("name") String name){
        try{
            EntityManager entityManager = this.entityManagerFactory.createEntityManager();
            EntityTransaction transaction =  entityManager.getTransaction();
            transaction.begin();
            State state = new State();
            state.setStateName(name);
            state.setCountryId(1);
            state.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            state.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
            entityManager.persist(state);
            transaction.commit();
            entityManager.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/jpa-persistcity")
    public ResponseEntity<String> jpaPersistCity(@RequestParam("name") String name,@RequestParam("stateId") int stateId){
        try{
            EntityManager entityManager = this.entityManagerFactory.createEntityManager();
            EntityTransaction transaction =  entityManager.getTransaction();
            transaction.begin();
            City city = new City();
            city.setCityName(name);
            city.setStateId(stateId);
            city.setCreatedTime(new Timestamp(System.currentTimeMillis()));
            city.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
            entityManager.persist(city);
            transaction.commit();
            entityManager.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/jpa-find")
    public ResponseEntity<String> jpaFindState(@RequestParam("stateId") int stateId){
        State state = new State();
        try{
            EntityManager entityManager = this.entityManagerFactory.createEntityManager();
            EntityTransaction transaction =  entityManager.getTransaction();
            transaction.begin();
            state = entityManager.find(State.class,stateId);
            transaction.commit();
            entityManager.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(state.getStateName());
    }
    @GetMapping("/jpa-remove")
    public ResponseEntity<String> jpaRemoveState(@RequestParam("stateId") int stateId){
        try{
            EntityManager entityManager = this.entityManagerFactory.createEntityManager();
            EntityTransaction transaction =  entityManager.getTransaction();
            transaction.begin();
            State state = entityManager.find(State.class,stateId);
            entityManager.remove(state);
            transaction.commit();
            entityManager.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/jpa-update")
    public ResponseEntity<String> jpaUpdateState(@RequestParam("stateId") int stateId, @RequestParam("name") String name){
        try{
            EntityManager entityManager = this.entityManagerFactory.createEntityManager();
            EntityTransaction transaction =  entityManager.getTransaction();
            transaction.begin();
            State state = entityManager.find(State.class,stateId);
            state.setStateName(name);
            transaction.commit();
            entityManager.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("Updated...");
    }
    @PostMapping("/jpa-merge")
    public ResponseEntity<String> jpaMergeState(){
        try{
            EntityManager entityManager = this.entityManagerFactory.createEntityManager();
            EntityTransaction transaction =  entityManager.getTransaction();
            transaction.begin();
            State state = entityManager.find(State.class,4);
            state.setStateName("jspmergeUpdate");
            entityManager.merge(state);
            transaction.commit();
            entityManager.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok("merged...");
    }
    //City delete from State obj

    @PostMapping("/jpa-remove-cityfrom-state")
    public ResponseEntity<String> jpaRemoveCityFromState(@RequestParam("stateId") int stateId){
        State state = new State();
        try{
            EntityManager entityManager = this.entityManagerFactory.createEntityManager();
            EntityTransaction transaction =  entityManager.getTransaction();
            transaction.begin();
            state = entityManager.find(State.class,stateId);
            state.getCityList().remove(0);
            transaction.commit();
            entityManager.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(state.getStateName());
    }
}
