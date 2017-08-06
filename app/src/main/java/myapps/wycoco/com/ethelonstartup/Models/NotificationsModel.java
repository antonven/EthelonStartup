package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 8/6/2017.
 */

public class NotificationsModel {

    private String userId;
    private String userName;
    private String userImage;
    private String status;
    private String content;
    private String type;
    private String time;

    public NotificationsModel() {
    }

    public NotificationsModel(String userId, String userName, String userImage, String status, String content, String type, String time) {
        this.userId = userId;
        this.userName = userName;
        this.userImage = userImage;
        this.status = status;
        this.content = content;
        this.type = type;
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
