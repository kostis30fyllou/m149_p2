package gr.uoa.di.m149_p2.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
public class Request {


    @Id
    private Long id;
    @Indexed
    private Date creationDate;
    private String status;
    @Indexed
    private Date completionDate;
    private String serviceRequestNumber;
    @Indexed
    private String typeOfServiceRequest;
    private String streetAddress;
    @Indexed
    private Integer zipCode;
    private BigDecimal x;
    private BigDecimal y;
//    @Indexed
    private Integer ward;
    private Integer policeDistrict;
    private Integer communityArea;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String location;
    private GeoJsonPoint point;
    private Integer upVotes;

    public Request() {
    }

    public Request(Long id, Date creationDate, String status, Date completionDate, String serviceRequestNumber, String typeOfServiceRequest,
                   String streetAddress, Integer zipCode, BigDecimal x, BigDecimal y, Integer ward, Integer policeDistrict,
                   Integer communityArea, BigDecimal latitude, BigDecimal longitude, String location, GeoJsonPoint point, Integer upVotes) {
        this.id = id;
        this.creationDate = creationDate;
        this.status = status;
        this.completionDate = completionDate;
        this.serviceRequestNumber = serviceRequestNumber;
        this.typeOfServiceRequest = typeOfServiceRequest;
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
        this.x = x;
        this.y = y;
        this.ward = ward;
        this.policeDistrict = policeDistrict;
        this.communityArea = communityArea;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.point = point;
        this.upVotes = upVotes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getServiceRequestNumber() {
        return serviceRequestNumber;
    }

    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public GeoJsonPoint getPoint() {
        return point;
    }

    public void setPoint(GeoJsonPoint point) {
        this.point = point;
    }

    public Integer getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(Integer upVotes) {
        this.upVotes = upVotes;
    }
}
