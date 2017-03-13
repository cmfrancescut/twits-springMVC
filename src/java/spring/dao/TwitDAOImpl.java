package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import spring.obj.Twit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * 
 * @author Carly Francescut 000710713
 */
public class TwitDAOImpl implements TwitDAO {

    private final JdbcTemplate jdbcTemplate;
    
    //Inject the datasource being configured in WebAppConfig (@see config.WebAppConfig) into the constructor
    public TwitDAOImpl(DataSource datasource)
    {
        jdbcTemplate = new JdbcTemplate(datasource);
    }
    
    @Override
    public void createTwit(int channelId, String twit) {
        jdbcTemplate.update("CALL proc_new_twit(?,?)", new Object[] { twit, channelId } );
    }

    @Override
    public void deleteTwit(int twitId) {
        jdbcTemplate.update("CALL proc_delete_twit(?)", new Object[] { twitId } );
    }

    @Override
    public List<Twit> getUserTwits(String username) {
       return jdbcTemplate.query("SELECT * FROM twits_twits WHERE username = ? ORDER BY twit_date DESCENDING", new Object[]{ username }, new TwitDAOImpl.TwitRowMapper());
    }

    @Override
    public List<Twit> getChannelTwits(int channelId) {
       return jdbcTemplate.query("SELECT * FROM twits_twits WHERE channel_id = ?", new Object[]{ channelId }, new TwitDAOImpl.TwitRowMapper());
    }
    
    /*****************MAPPER AND RESULTS SET EXTRACTOR**********************/
    
    
    /**
     * Extracts the data from one row of the
     * result set for a query into a user object.
     */
   public class TwitExtractor implements ResultSetExtractor
   {
       
       @Override
       public Twit extractData(ResultSet rs) throws SQLException
       {
           Twit twit = new Twit();
           twit.setTwitID(rs.getInt(1));
           twit.setTwitText(rs.getString(2));
           twit.setDate(rs.getDate(3));
           twit.setChannelID(rs.getInt(4));
           
           return twit;
       }
   }
   
   /**
    * Maps the result set for a query to the appropriate 
    * object.
    */
   public class TwitRowMapper implements RowMapper
   {
       @Override
       public Twit mapRow(ResultSet rs, int line) throws SQLException
       {
           TwitExtractor te = new TwitExtractor();
           return te.extractData(rs);
       }
   }

}
