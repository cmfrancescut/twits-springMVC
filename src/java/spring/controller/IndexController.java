package spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import spring.dao.UserDAO;
import spring.obj.User;
import util.NavBar;

@Controller
public class IndexController {
 
    //autowire in needed DAO implementation
    @Autowired
    private UserDAO user;
    
    
    @RequestMapping(value="/index")
    public ModelAndView index(HttpSession session, HttpServletRequest request, RedirectAttributes redirect) {
        /* Above I have injected both the session and the request so that I can handle how things are displayed
        * to my users. This replaces doing something like HttpSession session = request.getSession(). Spring is
        * clever enough to find the appropriate session and give it to me.
        * Redirect attributes are another way of passing attributes, but the passed attributes are only
        * stored temporarily and won't be forwarded again! */
        
        ModelAndView view = new ModelAndView();
        view.setViewName("index"); //name of the jsp-file in the "jsp" folder
        
        NavBar navbar = new NavBar();
        //check if user has logged in yet or not; if so, give them the correct navbar
        String username = (String) session.getAttribute("username");
        view.addObject("navbar", navbar.getNavBar(username, user.isAdmin(username)) );
        
        String error = (String) redirect.getFlashAttributes().get("error");

        if(error != null)
        {
            view.addObject("error", redirect.getFlashAttributes().get("error")); 
        }
                
        return view;
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RedirectView login(@ModelAttribute("user") User activeUser, RedirectAttributes redirect, HttpSession session)
    {
        RedirectView view = new RedirectView();
        view.setContextRelative(true);
        
        if(user.validateUser(activeUser.getUsername(), activeUser.getPassword()))
        {
           redirect.addFlashAttribute("activeUser", activeUser);
           session.setAttribute("username", activeUser.getUsername());
           view.setUrl("/dashboard");
           return view;
           
        } else
        {
            String error ="<div class=\"alert alert-danger alert-dismissable fade in\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a><b>Log in failed!</b>";
            error+=" If you are a new user, <a href=\"TwitsMVCDemo\register.jsp\" class=\"alert-link\">please register here</a>.<br/>If you already have an account, please contact an administrator for further assistance.</div>";

            redirect.addFlashAttribute("error", error);
            view.setUrl("/index");
            
            return view;
        }
    }
    
    @RequestMapping(value = "/logout")
    public RedirectView logout(@ModelAttribute("user") User activeUser, RedirectAttributes redirect, HttpSession session)
    {
        RedirectView view = new RedirectView();
        view.setContextRelative(true);
        view.setUrl("/index");
        session.invalidate();
        String error = "<div class=\"alert alert-success alert-dismissable fade in\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>Successfully logged out.</div>";
        redirect.addFlashAttribute("error", error);
        return view;
    }
     
}