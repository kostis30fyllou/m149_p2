package gr.uoa.di.m149_p2.models;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
public class Graffiti extends Request{
    private String surface;
    private String graffitiLocated;
    private String ssa;

    public Graffiti(Date creationDate, String status, Date completionDate, String serviceRequestNumber, String typeOfServiceRequest, String streetAddress, Integer zipCode, BigDecimal x, BigDecimal y, Integer ward, Integer policeDistrict, Integer communityArea, BigDecimal latitude, BigDecimal longitude, String location, Point point, Integer upVotes, String surface, String graffitiLocated, String ssa) {
        super(creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude, longitude, location, point, upVotes);
        this.surface = surface;
        this.graffitiLocated = graffitiLocated;
        this.ssa = ssa;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getGraffitiLocated() {
        return graffitiLocated;
    }

    public void setGraffitiLocated(String graffitiLocated) {
        this.graffitiLocated = graffitiLocated;
    }

    public String getSsa() {
        return ssa;
    }

    public void setSsa(String ssa) {
        this.ssa = ssa;
    }
}
