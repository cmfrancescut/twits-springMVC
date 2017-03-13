package spring.dao;

import java.util.List;
import spring.obj.User;

/**
 * The DAO interface for users in the Twits MVC demo.
 * @author Carly Francescut 000710713
 */
public interface UserDAO {

    /**
     * Registers a user using the supplied username and password.
     * @param username for the new user
     * @param password for the new user
     */
    public void registerUser(String username, String password);
    
    /**
     * Deletes the specified user
     * @param username to be deleted
     */
    public void deleteUser(String username);
    
    /**
     * Resets the supplied user's password to 'password'.
     * @param username whose password is to be reset.
     */
    public void resetPassword(String username);
    
    /**
     * Determines if the username and password are correct on login.
     * @param username to be checked
     * @param password to be checked
     * @return true if correct, false otherwise
     */
    public boolean validateUser(String username, String password);
    
    /**
     * Toggles the supplied user's admin status on and off.
     * @param username whose admin status is to be changed.
     */
    public void toggleAdmin(String username);
    
    /**
     * Toggles a user's account lock status on and off.
     * @param username whose account status is to be changed.
     */
    public void toggleLocked (String username);
    
    /**
     * Returns the user object for the supplied username.
     * @param username the username of the user to be returned.
     * @return the user object
     */
    public User getUser(String username);
    
    /**
     * Determines if a user owns a particular channel.
     * @param username the username to check
     * @param channelId the channel id to check
     * @return true if the user owns the channel, false otherwise
     */
    public boolean isChannelOwner(String username, int channelId);
    
    /**
     * Determines if a username is available or not.
     * @param username the username to check
     * @return true if name is available, false otherwise
     */
    public boolean isUser(String username);
    
     /**
     * Determines if a username is an admin or not
     * @param username the username to check
     * @return true if user is admin, false otherwise
     */
    public boolean isAdmin(String username);
    
    /**
     * Returns a list of all users for use by administrator.
     * @return list of all users.
     */
    public List<User> allUsers();
    
}
