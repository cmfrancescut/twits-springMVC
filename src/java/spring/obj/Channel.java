package spring.obj;

/**
 * A channel object for use in the Twits MVC demo.
 * @author Carly Francescut 000710713
 */
public class Channel {

    private int channelId;
    private String channelName;
    private String username;

    public Channel()
    {
        
    }
    
    public Channel(int channelId, String channelName, String username) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.username = username;
    }
    
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
