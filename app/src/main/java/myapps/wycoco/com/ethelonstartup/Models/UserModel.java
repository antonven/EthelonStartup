package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 8/5/2017.
 */

public class UserModel {

    private String userImage;
    private String userFirstName;
    private String userLastName;
    private String userRole;
    private String user_id;
    private String user_token;
    private int user_points;

    public UserModel(String userImage, String userFirstName, String userLastName, String userRole, String user_id, String user_token, int user_points) {
        this.userImage = userImage;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userRole = userRole;
        this.user_id = user_id;
        this.user_token = user_token;
        this.user_points = user_points;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public int getUser_points(){
        return user_points;
    }

    public void setUser_points(int user_points){
        this.user_points = user_points;
    }
}
