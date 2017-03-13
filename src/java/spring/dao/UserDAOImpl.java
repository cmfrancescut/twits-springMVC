package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import spring.obj.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Carly Francescut 000710713
 */
public class UserDAOImpl implements UserDAO {

    private final JdbcTemplate jdbcTemplate;
    
    //Inject the datasource being configured in WebAppConfig (@see config.WebAppConfig) into the constructor
    public UserDAOImpl(DataSource datasource)
    {
        jdbcTemplate = new JdbcTemplate(datasource);
    }
    
    @Override
    public void registerUser(String username, String password) {
        jdbcTemplate.update("CALL proc_register(?,?)", new Object[] { username, password } );
    }

    @Override
    public void deleteUser(String username) {
        jdbcTemplate.update("CALL proc_delete_user(?)", new Object[] { username } );
    }

    @Override
    public void resetPassword(String username) {
        jdbcTemplate.update("CALL proc_reset_password(?)", new Object[] { username } );
    }

    @Override
    public boolean validateUser(String username, String password) {
     
        List<User> users = jdbcTemplate.query("SELECT * FROM twits_users WHERE username = ? AND password = ?", new Object[]{ username, password }, new UserDAOImpl.UserRowMapper());
        
        if(users.size() == 1)
        {
            return !users.get(0).isLocked();
        }
        
        return false;
    }

    @Override
    public void toggleAdmin(String username) {
        jdbcTemplate.update("CALL proc_toggle_admin(?)", new Object[] { username } );
    }

    @Override
    public void toggleLocked(String username) {
        jdbcTemplate.update("CALL proc_toggle_locked(?)", new Object[] { username } );
    }

    @Override
    public User getUser(String username) {
        return (User) jdbcTemplate.query("SELECT * FROM twits_users WHERE username = ?", new Object[]{ username }, new UserDAOImpl.UserRowMapper());
    }

    @Override
    public boolean isChannelOwner(String username, int channelId) {
       List<User> users = jdbcTemplate.query("SELECT * FROM twits_channels WHERE username = ? AND channel_id = ?", new Object[]{ username, channelId }, new UserDAOImpl.UserRowMapper());
       return users.size() == 1;
    }

    @Override
    public boolean isUser(String username) {
       List<User> users = jdbcTemplate.query("SELECT * FROM twits_users WHERE username = ?", new Object[]{ username }, new UserDAOImpl.UserRowMapper());
       return users.size() >= 1;
    }

    @Override
    public boolean isAdmin(String username) {
       List<User> users = jdbcTemplate.query("SELECT * FROM twits_users WHERE username = ? AND is_admin=1", new Object[]{ username }, new UserDAOImpl.UserRowMapper());
       return users.size() >= 1;
    }
    
    @Override
    public List<User> allUsers()
    {
        List<User> users = jdbcTemplate.query("SELECT * from twits_users", new UserDAOImpl.UserRowMapper());
        return users;
    }
    
      /*****************MAPPER AND RESULTS SET EXTRACTOR**********************/
    
    /**
     * Extracts the data from one row of the
     * result set for a query into a user object.
     */
   public class UserExtractor implements ResultSetExtractor
   {
       
       @Override
       public User extractData(ResultSet rs) throws SQLException
       {
           User user = new User();
           user.setUsername(rs.getString(1));
           user.setPassword(rs.getString(2));
           user.setAdmin(rs.getBoolean(3));
           user.setLocked(rs.getBoolean(4));
           
           return user;
       }
   }
   
   /**
    * Maps the result set for a query to the appropriate 
    * object.
    */
   public class UserRowMapper implements RowMapper
   {
       @Override
       public User mapRow(ResultSet rs, int line) throws SQLException
       {
           UserExtractor ue = new UserExtractor();
           return ue.extractData(rs);
       }
   }
    
}
