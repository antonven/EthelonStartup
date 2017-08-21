package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 8/21/2017.
 */

public class UserCredentials {

    private String api_token;
    private String volunteer_id;

    public UserCredentials() {
    }

    public UserCredentials(String api_token, String volunteer_id) {
        this.api_token = api_token;
        this.volunteer_id = volunteer_id;
    }

    public String getApi_token() {
        return api_token;
    }

    public void setApi_token(String api_token) {
        this.api_token = api_token;
    }

    public String getVolunteer_id() {
        return volunteer_id;
    }

    public void setVolunteer_id(String volunteer_id) {
        this.volunteer_id = volunteer_id;
    }
}
