package config;

import spring.dao.ChannelDAO;
import spring.dao.ChannelDAOImpl;
import spring.dao.TwitDAO;
import spring.dao.TwitDAOImpl;
import spring.dao.UserDAO;
import spring.dao.UserDAOImpl;
import javax.sql.DataSource;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * Set up any custom configuration for your web application here. This
 * is in lieu of creating a dispatcher-servlet.xml configuration file (i.e.
 * any specific configuration in your dispatcher servlet would go here.)
 * @author Carly Francescut 000710713
 */

@Configuration //lets Spring know that this is a configuration file
@ComponentScan(basePackages="spring") //tells Spring where to find my controllers
@EnableWebMvc //lets Spring know that this is a Web MVC application.
public class WebAppConfig extends WebMvcConfigurerAdapter{
    
    /**
     * Redirects all URLs from the root of the webpage to index.jsp
     * @param registry 
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:index.htm");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/dashboard").setViewName("dashboard");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/channel").setViewName("channel");
    }
    
    /**
     * Map my resources (css, js, etc. files) so my application can find them.
     * @param registry 
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
    
    /**
     * The below bean customizes the URL mapping. Since all of my
     * .jsp files are in /WEB-INF/jsp/ that is where I want it to look.
     * Additionally, my dispatcher will handle all files in that directory
     * that end in .jsp.
     * @return resolver the view resolver object.
     */
    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class); //enable the use of JSTL views
        
        return resolver;
    }
    
    /**
     * This bean initializes my data source connection.
     * @return the datasource
     */
    @Bean
    public DataSource getDataSource() {
        
        DriverManagerDataSource datasource = new DriverManagerDataSource();
        datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        datasource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        datasource.setUsername("idev");
        datasource.setPassword("password");
        
        return datasource;
    }
    
    /**
     * Returns my channel DAO implementation with connection to
     * database.
     * @return channel DAO implementation.
     */
    @Bean
    public ChannelDAO getChannelDAO()
    {
        return new ChannelDAOImpl(getDataSource());
    }
    
     /**
     * Returns my user DAO implementation with connection to
     * database.
     * @return user DAO implementation.
     */
    @Bean
    public UserDAO getUserDAO()
    {
        return new UserDAOImpl(getDataSource());
    }

    
     /**
     * Returns my twit DAO implementation with connection to
     * database.
     * @return twit DAO implementation.
     */
    @Bean
    public TwitDAO getTwitDAO()
    {
        return new TwitDAOImpl(getDataSource());
    }
}
