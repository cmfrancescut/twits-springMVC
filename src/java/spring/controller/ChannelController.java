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
import spring.dao.TwitDAO;
import spring.dao.UserDAO;
import spring.obj.Channel;
import util.NavBar;

/**
 * 
 * @author Carly Francescut 000710713
 */

@Controller
public class ChannelController {

    @Autowired
    UserDAO user;
    
    @Autowired
    TwitDAO twit;
    
    @Autowired
    ChannelDAO channel;
    
    @RequestMapping(value="/channel")
    public ModelAndView channel(HttpServletRequest request, HttpSession session)
    {
        
        ModelAndView view = new ModelAndView();
        view.setViewName("channel");
        
        String channelInfo = channel.getChannel( Integer.parseInt(request.getParameter("id"))).getChannelName();
        view.addObject("channelInfo", channelInfo);
        view.addObject("twits", twit.getChannelTwits(Integer.parseInt(request.getParameter("id"))));
        
        if(channel.isOwner((String) session.getAttribute("username"), Integer.parseInt(request.getParameter("id"))))
        {
            String form = "<form class=\"form-horizontal\" action=\"PostTwit\" method=\"POST\"><input type=\"hidden\" name=\"id\" value=\"" + request.getParameter("id") + "\"/>";
            form += "<div class=\"form form-group-sm\"><label for=\"twit\" class=\"control-label\">New Twit: </label><input type=\"text\" class=\"form-control\" name=\"twit\">";
            form += "<br/><input type=\"submit\" class=\"btn btn-default\" name=\"submit\" value=\"Post twit\"></div></form>";
            view.addObject("postTwit", form);
        }
        
        //Begin navbar
        NavBar navbar = new NavBar();
        //check if user has logged in yet or not; if so, give them the correct navbar
        String username = (String) session.getAttribute("username");
        view.addObject("navbar", navbar.getNavBar(username, user.isAdmin(username)) );
        //end navbar
        
        return view;
    }
    
    @RequestMapping(value="/PostTwit", method = RequestMethod.POST)
    public RedirectView adminRegister(HttpServletRequest request, HttpSession session, RedirectAttributes redirect)
    {
       RedirectView view = new RedirectView();
       view.setContextRelative(true);
       twit.createTwit(Integer.parseInt(request.getParameter("id")), request.getParameter("twit"));
       view.setUrl("/channel?id=" + request.getParameter("id"));
       return view;

    }
    
}
