package spring.obj;

/**
 * A user object for the Twits MVC demo.
 * @author Carly Francescut 000710713
 */
public class User {

    private String username;
    private String password;
    private boolean admin;
    private boolean locked;

    public User(String username, String password, boolean admin, boolean locked) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.locked = locked;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }
    
    
}
