package spring.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.util.List;

import spring.obj.Channel;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * Implementation of the channel DAO interface.
 * @author Carly Francescut 000710713
 */
public class ChannelDAOImpl implements ChannelDAO {

    private final JdbcTemplate jdbcTemplate;
    
    //Inject the datasource being configured in WebAppConfig (@see config.WebAppConfig) into the constructor
    public ChannelDAOImpl(DataSource datasource)
    {
        jdbcTemplate = new JdbcTemplate(datasource);
    }
    
    /**
     * Adds a new channel
     * @param channelName channel to be added
     * @param username owner of the channel
     */
    @Override
    public void createChannel(String channelName, String username) {
        jdbcTemplate.update("CALL proc_new_channel(?,?)", new Object[] {channelName, username} );
    }

    @Override
    public void deleteChannel(int channelId) {
        jdbcTemplate.update("CALL proc_delete_channel(?)", new Object[] {channelId });
    }

    @Override
    public void followChannel(int channelId, String username) {
        jdbcTemplate.update("CALL proc_follow(?,?)", new Object[] { username, channelId });
    }

    @Override
    public void unfollowChannel(int channelId, String username) {
        jdbcTemplate.update("CALL proc_unfollow(?,?)", new Object[] { username, channelId });
    }

    @Override
    public List<Channel> followingChannels(String username) {
        return jdbcTemplate.query("SELECT * FROM twits_channels WHERE channel_id IN ( SELECT channel_id FROM twits_following WHERE username = ?) OR username = ?", new Object[]{ username, username }, new ChannelRowMapper()); 
    }

    /**
     * Returns the channel or channels that match one or more of the supplied parameters
     * @param username to be searched
     * @param channelName to be searched
     * @return list of all matching channels
     */
    @Override
    public List<Channel> searchChannels(String username, String channelName) {
        return jdbcTemplate.query("SELECT * FROM twits_channels WHERE username = ? OR channel_name = ?", new Object[] { username, channelName }, new ChannelRowMapper());
    }

    /**
     * Returns the channel with the supplied channel id
     * @param channelId of channel to be returned
     * @return the channel with the supplied channel id
     */
    @Override
    public Channel getChannel(int channelId) {       
        
        List<Channel> list = jdbcTemplate.query("SELECT * FROM twits_channels WHERE channel_id = ?", new Object[]{ channelId }, new ChannelRowMapper());
        return list.get(0) ;       
    }

    @Override
    public List<Channel> ownedChannels(String username) {
        return jdbcTemplate.query("SELECT * FROM twits_channels WHERE username = ?", new Object[] { username }, new ChannelRowMapper());
    }
    
    @Override
    public boolean isOwner(String username, int channelId)
    {
        return jdbcTemplate.query("SELECT * FROM twits_channels WHERE username= ? AND channel_id = ?", new Object[]{ username, channelId }, new ChannelRowMapper()).size() >= 1;
    }

    /*****************MAPPER AND RESULTS SET EXTRACTOR**********************/
    
    /**
     * Extracts the data from one row of the
     * result set for a query into a channel object.
     */
   public class ChannelExtractor implements ResultSetExtractor
   {
       
       @Override
       public Channel extractData(ResultSet rs) throws SQLException
       {
           Channel channel = new Channel();
           channel.setChannelId(rs.getInt(1));
           channel.setChannelName(rs.getString(2));
           channel.setUsername(rs.getString(3));
           return channel;
       }
   }
   
   /**
    * Maps the result set for a query to the appropriate 
    * object.
    */
   public class ChannelRowMapper implements RowMapper
   {
       @Override
       public Channel mapRow(ResultSet rs, int line) throws SQLException
       {
           ChannelExtractor ce = new ChannelExtractor();
           return ce.extractData(rs);
       }
   }
      
}
