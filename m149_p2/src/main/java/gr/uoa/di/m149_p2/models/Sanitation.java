package gr.uoa.di.m149_p2.models;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
public class Sanitation extends Request {

    private String codeViolation;

    public Sanitation(Integer id, Date creationDate, String status, Date completionDate, String serviceRequestNumber, String typeOfServiceRequest, String streetAddress, Integer zipCode, BigDecimal x, BigDecimal y, Integer ward, Integer policeDistrict, Integer communityArea, BigDecimal latitude, BigDecimal longitude, String location, GeoJsonPoint point, Integer upVotes, String codeViolation) {
        super(id, creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude, longitude, location, point, upVotes);
        this.codeViolation = codeViolation;
    }

    public String getCodeViolation() {
        return codeViolation;
    }

    public void setCodeViolation(String codeViolation) {
        this.codeViolation = codeViolation;
    }
}
