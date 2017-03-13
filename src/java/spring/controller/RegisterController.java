package spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import spring.dao.UserDAO;
import util.NavBar;

/**
 * 
 * @author Carly Francescut 000710713
 */

@Controller
public class RegisterController {
    
    @Autowired
     private UserDAO user;
    
    @RequestMapping("/register")
    public ModelAndView register(HttpServletRequest request, HttpSession session, RedirectAttributes redirect)
    {
        ModelAndView view = new ModelAndView();
        view.setViewName("register");  
        
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
    
    @RequestMapping("/registerUser")
    public RedirectView registerUser(HttpServletRequest request, HttpSession session, RedirectAttributes redirect)
    {
        String error;
        RedirectView view = new RedirectView();
        view.setContextRelative(true);
        
        if(user.isUser((String) request.getParameter("username")) && request.getParameter("password").equals("passConfirm")) //note: this is not how you should be validating user input in an application; done for demo purposes only
        {
            
            error ="<div class=\"alert alert-danger alert-dismissable fade in\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a><b>Invalid username or password!</b>";
            error+=" Please choose a different username or password and try again.</div>";
            
        } else //everything's fine
        {
            error ="<div class=\"alert alert-success alert-dismissable fade in\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a><b>Welcome!</b>";
            error+=" You've successfully registered. Please return to <a href=\"index\">our homepage to log in.</a></div>";
            user.registerUser( (String) request.getParameter("username"), (String) request.getParameter("password"));
        }

       redirect.addFlashAttribute("error", error);
       view.setUrl("/register");
       return view;
    }

}
