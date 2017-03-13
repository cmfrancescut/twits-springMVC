package spring.obj;

import java.util.Date;

/**
 * A "twit" object for use in the Twits MVC demo.
 * @author Carly Francescut 000710713
 */
public class Twit {
    
    private int twitID;
    private String twitText;
    private Date date;
    private int channelID;
    
    public Twit(int twitID, String twitText, Date date, int channelID)
    {
        this.twitID = twitID;
        this.twitText = twitText;
        this.date = date;
        this.channelID = channelID;
    }
    
    public Twit()
    {
        
    }

    public int getTwitID() {
        return twitID;
    }

    public void setTwitID(int twitID) {
        this.twitID = twitID;
    }

    public String getTwitText() {
        return twitText;
    }

    public void setTwitText(String twitText) {
        this.twitText = twitText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getChannelID() {
        return channelID;
    }

    public void setChannelID(int channelID) {
        this.channelID = channelID;
    }
    
}
