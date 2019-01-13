package gr.uoa.di.m149_p2.dto;

import java.math.BigDecimal;

public class NewIncident {
    private String typeOfServiceRequest;
    private String streetAddress;
    private Integer zipCode;
    private BigDecimal x;
    private BigDecimal y;
    private Integer ward;
    private Integer policeDistrict;
    private Integer communityArea;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String plate;
    private String model;
    private String color;
    private String currentActivity;
    private String mostRecentAction;
    private Double daysParked;
    private String ssa;
    private Double numberOfCarts;
    private Float potholes;
    private Float premisesBaited;
    private Integer premisesWithGarbage;
    private Float premisesWithRats;
    private String codeViolation;
    private String debrisLocations;
    private String treeLocation;

    public String getTypeOfServiceRequest() {
        return typeOfServiceRequest;
    }

    public void setTypeOfServiceRequest(String typeOfServiceRequest) {
        this.typeOfServiceRequest = typeOfServiceRequest;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public Integer getWard() {
        return ward;
    }

    public void setWard(Integer ward) {
        this.ward = ward;
    }

    public Integer getPoliceDistrict() {
        return policeDistrict;
    }

    public void setPoliceDistrict(Integer policeDistrict) {
        this.policeDistrict = policeDistrict;
    }

    public Integer getCommunityArea() {
        return communityArea;
    }

    public void setCommunityArea(Integer communityArea) {
        this.communityArea = communityArea;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }

    public String getMostRecentAction() {
        return mostRecentAction;
    }

    public void setMostRecentAction(String mostRecentAction) {
        this.mostRecentAction = mostRecentAction;
    }

    public Double getDaysParked() {
        return daysParked;
    }

    public void setDaysParked(Double daysParked) {
        this.daysParked = daysParked;
    }

    public String getSsa() {
        return ssa;
    }

    public void setSsa(String ssa) {
        this.ssa = ssa;
    }

    public Double getNumberOfCarts() {
        return numberOfCarts;
    }

    public void setNumberOfCarts(Double numberOfCarts) {
        this.numberOfCarts = numberOfCarts;
    }

    public Float getPotholes() {
        return potholes;
    }

    public void setPotholes(Float potholes) {
        this.potholes = potholes;
    }

    public Float getPremisesBaited() {
        return premisesBaited;
    }

    public void setPremisesBaited(Float premisesBaited) {
        this.premisesBaited = premisesBaited;
    }

    public Integer getPremisesWithGarbage() {
        return premisesWithGarbage;
    }

    public void setPremisesWithGarbage(Integer premisesWithGarbage) {
        this.premisesWithGarbage = premisesWithGarbage;
    }

    public Float getPremisesWithRats() {
        return premisesWithRats;
    }

    public void setPremisesWithRats(Float premisesWithRats) {
        this.premisesWithRats = premisesWithRats;
    }

    public String getCodeViolation() {
        return codeViolation;
    }

    public void setCodeViolation(String codeViolation) {
        this.codeViolation = codeViolation;
    }

    public String getDebrisLocations() {
        return debrisLocations;
    }

    public void setDebrisLocations(String debrisLocations) {
        this.debrisLocations = debrisLocations;
    }

    public String getTreeLocation() {
        return treeLocation;
    }

    public void setTreeLocation(String treeLocation) {
        this.treeLocation = treeLocation;
    }
}
