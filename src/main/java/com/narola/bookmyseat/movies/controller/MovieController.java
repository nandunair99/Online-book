package com.narola.bookmyseat.movies.controller;

import com.narola.bookmyseat.entity.GetMovieResult;
import com.narola.bookmyseat.entity.GetMoviesResult;
import com.narola.bookmyseat.entity.Movie;
import com.narola.bookmyseat.movies.service.IMovieService;
import com.narola.bookmyseat.utility.Constant;
import com.narola.bookmyseat.utility.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/movies")
public class MovieController {

    @Autowired
    @Qualifier("movieServiceImpl")
    private IMovieService iMovieService;

    @Autowired
    private Validator movieValidator;

    @Autowired
    private MessageSource messageSource;

    @InitBinder("movie")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(movieValidator);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @RequestMapping(value = "/getmovies", method = {RequestMethod.GET})
    public ModelAndView movies(HttpServletRequest request) {
        GetMoviesResult getMoviesResult = iMovieService.getAllMovie();
        ModelAndView modelAndView = new ModelAndView("AdminPage/Movies");

        String errorO = request.getParameter(Constant.ERROR_OCCURS);
        String errTxtMsg = request.getParameter(Constant.ERROR_TEXTMSG);

        if (errorO != null && errTxtMsg != null) {
            modelAndView.addObject(Constant.ERROR_OCCURS, request.getParameter(Constant.ERROR_OCCURS));
            modelAndView.addObject(Constant.ERROR_TEXTMSG, request.getParameter(Constant.ERROR_TEXTMSG));
        }
        modelAndView.addObject("GenreData", getMoviesResult.getGenreData());
        modelAndView.addObject("LanguageData", getMoviesResult.getLanguageData());
        modelAndView.addObject("CastCrewData", getMoviesResult.getCastCrewData());
        modelAndView.addObject("MovieData", getMoviesResult.getMovieData());
        modelAndView.addObject("imgPoster", getMoviesResult.getImgPoster());
        return modelAndView;
    }

    //insert update
    @RequestMapping(value = {"/insertmovie", "/updatemovie"}, method = {RequestMethod.POST})
    public String insertUpMovie(@Validated Movie movie, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes attribute) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError e : errors
            ) {
                System.out.println(messageSource.getMessage(e, Locale.getDefault()));
                //attribute.addAttribute("", messageSource.getMessage(e, Locale.getDefault()));
            }
        } else {

            //code

            /*if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_STATE_INSERT)) {
                attribute.addAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
                attribute.addAttribute(Constant.ERROR_TEXTMSG, "State " + Constant.SUCCESS_TEXT_INSERTMSG);
            } else if (request.getRequestURI().equals(request.getContextPath() + Constant.URL_STATE_UPDATE)) {
                attribute.addAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
                attribute.addAttribute(Constant.ERROR_TEXTMSG, "State " + Constant.SUCCESS_TEXT_UPDATEMSG);
            }*/
        }
        return "redirect:/movies/getmovies";
    }

    @RequestMapping(value = "/getmovie", method = {RequestMethod.GET})
    public ModelAndView viewMovie(@RequestParam("movieId") int movieId) {
        GetMovieResult getMovieResult = iMovieService.getMovie(movieId);
        ModelAndView modelAndView = new ModelAndView("AdminPage/ViewMovie");
        modelAndView.addObject("movieDataById", getMovieResult.getMovieData());
        modelAndView.addObject("imgPosterById", getMovieResult.getImgPoster());
        modelAndView.addObject("imgBannerById", getMovieResult.getImgBanner());
        modelAndView.addObject("castCrewDataById", getMovieResult.getCastCrewData());
        modelAndView.addObject("imgCastCrewById", getMovieResult.getImgCastCrew());
        modelAndView.addObject("genreDataById", getMovieResult.getGenreData());
        modelAndView.addObject("languageDataById", getMovieResult.getLanguageData());
        return modelAndView;
    }

    @RequestMapping(value = "/deletemovie", method = {RequestMethod.GET})
    public String deleteMovie(@RequestParam("movieId") int movieId, RedirectAttributes attribute) {
        iMovieService.deleteMovie(movieId);
        attribute.addAttribute(Constant.ERROR_OCCURS, Constant.ERROR_OCCURS_NO);
        attribute.addAttribute(Constant.ERROR_TEXTMSG, "Movie " + Constant.SUCCESS_TEXT_DELETEMSG);
        return "redirect:/movies/getmovies";
    }

    @RequestMapping(value = "/updatemoviestatus", method = {RequestMethod.GET, RequestMethod.POST})
    public void updateMovieStatus(@RequestParam("movieI d") int movieId, @RequestParam("status") int movieStatus, RedirectAttributes attribute) {
        iMovieService.updateMovieStatus(movieId, movieStatus);
    }
}
