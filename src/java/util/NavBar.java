package util;

/**
 * 
 * @author Carly Francescut 000710713
 */
public class NavBar {

    public String getNavBar(String username, boolean admin)
    {
        String bar;
        
        if(username == null)
        {
            bar = "<nav class=\"navbar navbar-inverse\">";
            bar+= "<div class=\"container-fluid\">";
            bar+="<div class=\"navbar-header\">";
            bar+="<a class=\"navbar-brand\" href=\"TwitsMVCDemo/index\"><span class=\"glyphicon glyphicon-leaf\"></span></a>";
            bar+="</div>";
            bar+="<ul class=\"nav navbar-nav navbar-right\">";
            bar+="<li><a href=\"TwitsMVCDemo/index\"><span class=\"glyphicon glyphicon-user\"></span> Log in</a></li>";
            bar+="</ul>";
            bar+="</div>";
            bar+="</nav>";
            
        } else if (admin) //admin
        {
            bar = "<nav class=\"navbar navbar-inverse\">";
            bar+= "<div class=\"container-fluid\">";
            bar+="<div class=\"navbar-header\">";
            bar+="<a class=\"navbar-brand\" href=\"dashboard\"><span class=\"glyphicon glyphicon-leaf\"></span></a>";
            bar+="</div>";
            bar+="<p class=\"navbar-text\">";
            bar+="Welcome, " + username + "!";
            bar+="</p>";
            bar+="<ul class=\"nav navbar-nav\">";
            bar+="<li><a href=\"dashboard\">Channels</a></li>";        
            bar+="<li><a href=\"admin\">Admin Dashboard</a></li>";
            bar+="</ul>";
            bar+="<ul class=\"nav navbar-nav navbar-right\">";
            bar+="<li><a href=\"dashboard\"><span class=\"glyphicon glyphicon-home\"></span> Go Home</a></li>";
            bar+="<li><a href=\"logout\"><span class=\"glyphicon glyphicon-log-out\"></span> Log Out</a></li>";
            bar+="</ul>";
            bar+="</div>";
            bar+="</nav>";
            
        } else
        {
            bar = "<nav class=\"navbar navbar-inverse\">";
            bar+= "<div class=\"container-fluid\">";
            bar+="<div class=\"navbar-header\">";
            bar+="<a class=\"navbar-brand\" href=\"dashboard\"><span class=\"glyphicon glyphicon-leaf\"></span></a>";
            bar+="</div>";
            bar+="<p class=\"navbar-text\">";
            bar+="Welcome, " + username + "!";
            bar+="</p>";
            bar+="<ul class=\"nav navbar-nav navbar-right\">";
            bar+="<li><a href=\"dashboard\"><span class=\"glyphicon glyphicon-home\"></span> Go Home</a></li>";
            bar+="<li><a href=\"logout\"><span class=\"glyphicon glyphicon-log-out\"></span> Log Out</a></li>";
            bar+="</ul>";
            bar+="</div>";
            bar+="</nav>";
            
        }
        
        return bar;
    }
    
}
