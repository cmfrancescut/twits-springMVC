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
import spring.dao.ChannelDAO;
import spring.dao.UserDAO;
import util.NavBar;

/**
 * 
 * @author Carly Francescut 000710713
 */

@Controller
public class DashboardController {
    
    @Autowired
    UserDAO user;
    
    @Autowired
    ChannelDAO channel;
    
    @RequestMapping(value="/dashboard")
    public ModelAndView dashboard(HttpServletRequest request, HttpSession session, RedirectAttributes redirect)
    {   
         
        ModelAndView view = new ModelAndView();
        view.setViewName("dashboard");
        view.addObject("following", channel.followingChannels((String) session.getAttribute("username")));
        view.addObject("ownedChannels", channel.ownedChannels((String) session.getAttribute("username")));
        view.addObject("user", session.getAttribute("username"));

        NavBar navbar = new NavBar();
        //check if user has logged in yet or not; if so, give them the correct navbar
        String username = (String) session.getAttribute("username");
        view.addObject("navbar", navbar.getNavBar(username, user.isAdmin(username)) );
        
        //get search results if applicable
        if(redirect.getFlashAttributes().get("searchResults") != null)
        {
            view.addObject("searchResults", redirect.getFlashAttributes().get("searchResults")); 
        }
        
        //get created message if applicable
        if(redirect.getFlashAttributes().get("creationMessage") != null)
        {
            String creation = "<div class=\"alert alert-success alert-dismissable fade in\"><a href=\"#\" class=\"close\" data-dismiss=\"alert\" aria-label=\"close\">&times;</a>Channel successfully created.</div>";
            view.addObject("creationMessage", creation);
        }
        
        return view;
    }
    
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public RedirectView search(HttpServletRequest request, HttpSession session, RedirectAttributes redirect)
    {
        RedirectView view = new RedirectView();
        view.setContextRelative(true);
        
        String channelOwner = request.getParameter("channelOwner");
        String channelName = request.getParameter("channelName");
        
        redirect.addFlashAttribute("searchResults", channel.searchChannels(channelOwner, channelName));
        view.setUrl("/dashboard");
        return view;
    }

    @RequestMapping(value="/addChannel", method = RequestMethod.POST)
    public RedirectView add(HttpServletRequest request, HttpSession session, RedirectAttributes redirect)
    {
        RedirectView view = new RedirectView();
        view.setContextRelative(true);
        view.setUrl("/dashboard");
        
        channel.createChannel((String) request.getParameter("newChannel"), (String) session.getAttribute("username"));
        redirect.addFlashAttribute("creationMessage", "true");
        
        return view;
    }
}
