package com.example.zveri.telemedicine.api.entities;

/**
 * Created by deDwarf on 12/16/2017.
 */

public class DoctorProfile {
    private Long docId;
    private String fullName;
    private String specs;
    private String address;
    private String about;
    private Double stars;
    private String photo;

    public DoctorProfile(Long docId, String fullName, String specs, String address, String about, Double stars, String photo) {
        this.docId = docId;
        this.fullName = fullName;
        this.specs = specs;
        this.address = address;
        this.about = about;
        this.stars = stars;
        this.photo = photo;
    }

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Double getStars() {
        return stars;
    }

    public void setStars(Double stars) {
        this.stars = stars;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
