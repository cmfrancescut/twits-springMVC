package config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * This initializes my web application. When this runs, it registers my
 * dispatcher servlet and maps URLs appropriately.
 * NOTE: You use this instead of your web.xml file! Hooray for pure Java!
 * @author Carly Francescut 000710713
 */
public class Initializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebAppConfig.class);
         
        context.setServletContext(sc);  
         
        ServletRegistration.Dynamic servlet = sc.addServlet("dispatcher", new DispatcherServlet(context));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
    }

}
