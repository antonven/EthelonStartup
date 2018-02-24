package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 2/21/2018.
 */

public class FoundationModel {

    private String foundation_name;
    private String foundation_image;
    private String foundation_about;
    private String foundation_location;
    private String foundation_email;
    private String foundation_contact;

    public FoundationModel() {
    }

    public FoundationModel(String foundation_name, String foundation_image, String foundation_about, String foundation_location, String foundation_email, String foundation_contact) {
        this.foundation_name = foundation_name;
        this.foundation_image = foundation_image;
        this.foundation_about = foundation_about;
        this.foundation_location = foundation_location;
        this.foundation_email = foundation_email;
        this.foundation_contact = foundation_contact;
    }

    public String getFoundation_name() {
        return foundation_name;
    }

    public void setFoundation_name(String foundation_id) {
        this.foundation_name = foundation_id;
    }

    public String getFoundation_image() {
        return foundation_image;
    }

    public void setFoundation_image(String foundation_image) {
        this.foundation_image = foundation_image;
    }

    public String getFoundation_about() {
        return foundation_about;
    }

    public void setFoundation_about(String foundation_about) {
        this.foundation_about = foundation_about;
    }

    public String getFoundation_location() {
        return foundation_location;
    }

    public void setFoundation_location(String foundation_location) {
        this.foundation_location = foundation_location;
    }

    public String getFoundation_email() {
        return foundation_email;
    }

    public void setFoundation_email(String foundation_email) {
        this.foundation_email = foundation_email;
    }

    public String getFoundation_contact() {
        return foundation_contact;
    }

    public void setFoundation_contact(String foundation_contact) {
        this.foundation_contact = foundation_contact;
    }
}
