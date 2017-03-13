package spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public class AdminController {

    @Autowired
    UserDAO user;
    
    @RequestMapping(value="/admin")
    public ModelAndView admin(HttpServletRequest request, HttpSession session, RedirectAttributes redirect)
    {
        ModelAndView view = new ModelAndView();
        
        view.addObject("userList", user.allUsers());
        
        //reg error message
        String error = (String) redirect.getFlashAttributes().get("error");
        if(error != null)
        {
            view.addObject("error", redirect.getFlashAttributes().get("error")); 
        }
        //end reg error message
        
        //Begin navbar
        NavBar navbar = new NavBar();
        //check if user has logged in yet or not; if so, give them the correct navbar
        String username = (String) session.getAttribute("username");
        view.addObject("navbar", navbar.getNavBar(username, user.isAdmin(username)) );
        //end navbar
        
        return view;
    }
    
    @RequestMapping(value="/RegisterUser", method = RequestMethod.POST)
    public RedirectView adminRegister(HttpServletRequest request, HttpSession session, RedirectAttributes redirect)
    {
        RedirectView view = new RedirectView();
        String error;
        view.setContextRelative(true);
        
        if(user.isUser((String) request.getParameter("newUser")) && request.getParameter("password").equals("passConfirm")) //note: this is not how you should be validating user input in an application; done for demo purposes only
        {
            
            error ="<div class=\"alert alert-danger alert-dismissable fade in\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a><b>Invalid username or password!</b>";
            error+=" Please choose a different username or password and try again.</div>";
            
        } else //everything's fine
        {
            error ="<div class=\"alert alert-success alert-dismissable fade in\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a><b>Registration successful!</b>";
            error+=" You've successfully registered a new user with the username " + request.getParameter("newUser") +".</div>";
            user.registerUser( (String) request.getParameter("newUser"), (String) request.getParameter("password"));
        }

       redirect.addFlashAttribute("error", error);
       view.setUrl("/admin");
       return view;

    }
    
    @RequestMapping(value="/ResetPassword", method=RequestMethod.GET)
    public RedirectView passReset(HttpServletRequest request, HttpSession session)
    {
       RedirectView view = new RedirectView();
       view.setContextRelative(true);
       user.resetPassword((String) request.getParameter("user"));
       view.setUrl("/admin");
       return view;
    }
    
    @RequestMapping(value="/ToggleLock", method=RequestMethod.GET)
    public RedirectView toggleLock(HttpServletRequest request, HttpSession session)
    {
       RedirectView view = new RedirectView();
       view.setContextRelative(true);
       user.toggleLocked((String) request.getParameter("user"));
       view.setUrl("/admin");
       return view;
    }
    
    @RequestMapping(value="/ToggleAdmin", method=RequestMethod.GET)
    public RedirectView toggleAdmin(HttpServletRequest request, HttpSession session)
    {
       RedirectView view = new RedirectView();
       view.setContextRelative(true);
       user.toggleAdmin((String) request.getParameter("user"));
       view.setUrl("/admin");
       return view;
    }
    
    @RequestMapping(value="/DeleteUser", method=RequestMethod.GET)
    public RedirectView deleteUser(HttpServletRequest request, HttpSession session)
    {
       RedirectView view = new RedirectView();
       view.setContextRelative(true);
       user.deleteUser((String) request.getParameter("user"));
       view.setUrl("/admin");
       return view;
        
    }
}
