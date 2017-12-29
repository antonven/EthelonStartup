package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 8/6/2017.
 */

public class NotificationsModel {

    private String notification_id;
    private String notification_user_id;
    private String image_url;
    private int isRead;
    private String body;
    private String type;
    private String time;
    private String data;
    private String sender_id;



    public NotificationsModel(String notification_id,
                              String notification_user_id,
                              String image_url,
                              int isRead,
                              String body,
                              String type, String data, String sender_id, String time) {
        this.notification_id = notification_id;
        this.notification_user_id = notification_user_id;
        this.image_url = image_url;
        this.isRead = isRead;
        this.body = body;
        this.type = type;
        this.data = data;
        this.sender_id = sender_id;
        this.time = time;
    }

    public NotificationsModel() {
    }

    public String getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }

    public String getNotification_user_id() {
        return notification_user_id;
    }

    public void setNotification_user_id(String notification_user_id) {
        this.notification_user_id = notification_user_id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int isRead() {
        return isRead;
    }

    public void setRead(int read) {
        isRead = read;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }
}
