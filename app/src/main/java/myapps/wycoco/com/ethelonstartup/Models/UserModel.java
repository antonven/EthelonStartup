package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 8/5/2017.
 */

public class UserModel {

    private String userImage;
    private String userFirstName;
    private String userLastName;
    private String userRole;

    public UserModel(String userImage, String userFirstName, String userLastName, String userRole) {
        this.userImage = userImage;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userRole = userRole;
    }

    public UserModel() {

    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
