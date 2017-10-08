package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 8/29/2017.
 */

public class RateVolunteer {

    private String volunteer_id;
    private String volunteer_name;
    private String volunteer_image;
    private String volunteer_group_id;
    private int number_of_volunteers;
    private String status;
    private int rating;

    public RateVolunteer() {
    }

    public RateVolunteer(String volunteer_id, String volunteer_name, String volunteer_image,
                         String volunteer_group_id, int number_of_volunteers, String status) {
        this.volunteer_id = volunteer_id;
        this.volunteer_name = volunteer_name;
        this.volunteer_image = volunteer_image;
        this.volunteer_group_id = volunteer_group_id;
        this.number_of_volunteers = number_of_volunteers;
        this.status = status;
        this.rating = rating;
    }

    public String getVolunteer_id() {
        return volunteer_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setVolunteer_id(String volunteer_id) {
        this.volunteer_id = volunteer_id;
    }

    public String getVolunteer_name() {
        return volunteer_name;
    }

    public void setVolunteer_name(String volunteer_name) {
        this.volunteer_name = volunteer_name;
    }

    public String getVolunteer_image() {
        return volunteer_image;
    }

    public void setVolunteer_image(String volunteer_image) {
        this.volunteer_image = volunteer_image;
    }

    public String getVolunteer_group_id() {
        return volunteer_group_id;
    }

    public void setVolunteer_group_id(String volunteer_group_id) {
        this.volunteer_group_id = volunteer_group_id;
    }

    public int getNumber_of_volunteers() {
        return number_of_volunteers;
    }

    public void setNumber_of_volunteers(int number_of_volunteers) {
        this.number_of_volunteers = number_of_volunteers;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
