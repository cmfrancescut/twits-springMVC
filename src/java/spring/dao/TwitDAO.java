package spring.dao;

import java.util.List;
import spring.obj.Twit;

/**
 * The DAO interface for all Twit CRUD operations.
 * @author Carly Francescut 000710713
 */
public interface TwitDAO {

    /**
     * Post a twit on a specific channel.
     * @param channelId the channel the twit is to be posted to.
     * @param twit the content of the twit.
     */
    public void createTwit(int channelId, String twit);
    
    /**
     * Delete a twit from a channel.
     * @param twitId the twit to be deleted.
     */
    public void deleteTwit(int twitId);
    
    /**
     * Returns all twits posted by the specified user.
     * @param username the user to get twits for.
     * @return arraylist of all twits for a particular user.
     */
    public List<Twit> getUserTwits(String username);
    
    /**
     * Returns all twits posted to a specific channel.
     * @param channelId the channel to get all twits for.
     * @return arraylist of all twits for a particular channel.
     */
    public List<Twit> getChannelTwits(int channelId);

}
