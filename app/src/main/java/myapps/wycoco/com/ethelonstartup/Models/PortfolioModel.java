package myapps.wycoco.com.ethelonstartup.Models;

import java.util.ArrayList;

/**
 * Created by dell on 8/15/2017.
 */

public class PortfolioModel {

    private String activityId;
    private String foundationId;
    private String activityName;
    private String activityImage;
    private String activityQr;
    private String activityDes;
    private String activityDate;
    private String activityLocation;
    private String activityStart;
    private String activityEnd;
    private String activityGroup;
    private String activityLong;
    private String activityLat;
    private String activityPoints;
    private String activityStatus;
    private String activityCreated;
    private String activityUpdated;
    private String contactPerson;
    private String activityContact;
    private String volunteerStatus;
    private String foundationName;
    private int points;
    private int volunteer_count;
    private String foundationImage;
    private ArrayList<String> act_skills;
    private int timeIn;


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public PortfolioModel(String activityId, String foundationId, String activityName, String activityImage,
                          String activityQr, String activityDes, String activityLocation,
                          String activityStart, String activityEnd, String activityDate, String activityGroup, String activityLong,
                          String activityLat,
                          String activityPoints, String activityStatus, String activityCreated, String activityUpdated, String contactPerson,
                          String activityContact, String volunteerStatus, String foundationName, int points, int volunteer_count, String foundationImage,
                          ArrayList<String> act_skills, int timeIn) {
        this.activityId = activityId;
        this.foundationId = foundationId;
        this.activityName = activityName;
        this.activityImage = activityImage;
        this.activityQr = activityQr;
        this.activityDes = activityDes;
        this.activityDate = activityDate;
        this.activityLocation = activityLocation;
        this.activityStart = activityStart;
        this.activityEnd = activityEnd;
        this.activityGroup = activityGroup;
        this.activityLong = activityLong;
        this.activityLat = activityLat;
        this.activityPoints = activityPoints;
        this.activityStatus = activityStatus;
        this.activityCreated = activityCreated;
        this.activityUpdated = activityUpdated;
        this.contactPerson = contactPerson;
        this.activityContact = activityContact;
        this.volunteerStatus = volunteerStatus;
        this.foundationName = foundationName;
        this.points = points;
        this.volunteer_count = volunteer_count;
        this.foundationImage = foundationImage;
        this.act_skills = act_skills;
        this.timeIn = timeIn;
    }


    public String getActivityId() {
        return activityId;
    }

    public void setActvitiyId(String activityId) {
        this.activityId = activityId;
    }

    public String getFoundationId() {
        return foundationId;
    }

    public void setFoundationId(String foundationId) {
        this.foundationId = foundationId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityImage() {
        return activityImage;
    }

    public void setActivityImage(String activityImage) {
        this.activityImage = activityImage;
    }

    public String getActivityQr() {
        return activityQr;
    }

    public void setActivityQr(String activityQr) {
        this.activityQr = activityQr;
    }

    public String getActivityDes() {
        return activityDes;
    }

    public void setActivityDes(String activityDes) {
        this.activityDes = activityDes;
    }

    public String getActivityLocation() {
        return activityLocation;
    }

    public void setActivityLocation(String activityLocation) {
        this.activityLocation = activityLocation;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }


    public String getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(String activityStart) {
        this.activityStart = activityStart;
    }

    public String getActivityEnd() {
        return activityEnd;
    }

    public void setActivityEnd(String activityEnd) {
        this.activityEnd = activityEnd;
    }

    public String getActivityGroup() {
        return activityGroup;
    }

    public void setActivityGroup(String activitySkill) {
        this.activityGroup = activitySkill;
    }



    public String getActivityPoints() {
        return activityPoints;
    }

    public void setActivityPoints(String activityPoints) {
        this.activityPoints = activityPoints;
    }

    public String getActivityLong() {
        return activityLong;
    }

    public void setActivityLong(String activityLong) {
        this.activityLong = activityLong;
    }

    public String getActivityLat() {
        return activityLat;
    }

    public void setActivityLat(String activityLat) {
        this.activityLat = activityLat;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public String getActivityCreated() {
        return activityCreated;
    }

    public void setActivityCreated(String activityCreated) {
        this.activityCreated = activityCreated;
    }

    public String getActivityUpdated() {
        return activityUpdated;
    }

    public void setActivityUpdated(String activityUpdated) {
        this.activityUpdated = activityUpdated;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getActivityContact() {
        return activityContact;
    }

    public void setActivityContact(String activityContact) {
        this.activityContact = activityContact;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getVolunteerStatus() {
        return volunteerStatus;
    }

    public void setVolunteerStatus(String volunteerStatus) {
        this.volunteerStatus = volunteerStatus;
    }

    public String getFoundationName() {
        return foundationName;
    }

    public void setFoundationName(String foundationName) {
        this.foundationName = foundationName;
    }

    public int getVolunteer_count() {
        return volunteer_count;
    }

    public void setVolunteer_count(int volunteer_count) {
        this.volunteer_count = volunteer_count;
    }

    public String getFoundationImage() {
        return foundationImage;
    }

    public void setFoundationImage(String foundationImage) {
        this.foundationImage = foundationImage;
    }

    public ArrayList<String> getAct_skills() {
        return act_skills;
    }

    public void setAct_skills(ArrayList<String> act_skills) {
        this.act_skills = act_skills;
    }

    public int getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(int timeIn) {
        this.timeIn = timeIn;
    }
}
