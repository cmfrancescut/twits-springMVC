package spring.dao;

import java.util.List;
import spring.obj.Channel;

/**
 * The DAO interface for all twit channel CRUD operations.
 * @author Carly Francescut 000710713
 */
public interface ChannelDAO {

    /**
     * Creates a new channel.
     * @param channelName the name of the new channel.
     * @param username the owner of the new channel.
     */
    public void createChannel(String channelName, String username);
    
    /**
     * Deletes the specified channel and its associated twits.
     * @param channelId the channel to delete
     */
    public void deleteChannel(int channelId);
    
    /**
     * Adds the specified channel to the specified user's following list.
     * @param channelId channel to follow
     * @param username user following the channel
     */    
    public void followChannel(int channelId, String username);
    
    /**
     * Removes a specified channel from a specified user's following list.
     * @param channelId the channel to be unfollowed.
     * @param username the user unfollowing the channel.
     */
    public void unfollowChannel(int channelId, String username);
    
    /**
     * Returns a list of all channels a specified user is following.
     * @param username the user whose following channels are being returned.
     * @return list of all channels being followed.
     */
    public List<Channel> followingChannels(String username);
    
    /**
     * Searches all channels by a supplied username or channel name.
     * @param username to search channels by
     * @param channelName to search channels by
     * @return list of all channels that meet the search criteria
     */
    public List<Channel> searchChannels (String username, String channelName);
    
    /**
     * Returns a channel object for the specified channel id
     * @param channelId the channel id of the channel to be returned
     * @return the requested channel
     */
    public Channel getChannel(int channelId);
    
    /**
     * Returns a list of channels owned by the supplied username.
     * @param username to list channels for
     * @return list of channels owned
     */
    public List<Channel> ownedChannels(String username);
    
    /**
     * Determines if the current user owns the specified channel.
     * @param username to check
     * @param channelId to check
     * @return true if user owns channel, false otherwise
     */
    public boolean isOwner(String username, int channelId);
}
