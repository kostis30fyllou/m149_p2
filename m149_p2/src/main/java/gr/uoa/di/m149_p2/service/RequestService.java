package gr.uoa.di.m149_p2.service;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import gr.uoa.di.m149_p2.dal.RequestRepository;
import gr.uoa.di.m149_p2.dto.NewIncident;
import gr.uoa.di.m149_p2.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public Request addRequest(NewIncident incident) throws Exception{
        if(incident == null) {
            return  null;
        }
        Date creationDate = null;
        String status = null;
        Date completionDate = null;
        String serviceRequestNumber = null;
        String typeOfServiceRequest = null;
        String streetAddress = null;
        Integer zipCode = null;
        BigDecimal x = null;
        BigDecimal y = null;
        Integer ward = null;
        Integer policeDistrict = null;
        Integer communityArea = null;
        BigDecimal latitude = null;
        BigDecimal longitude = null;
        String location = null;
        Point point = null;
        Integer upVotes = 0;
        if(incident.getCreationDate() == null) {
            creationDate = new Date();
        }
        else if(!incident.getCreationDate().equalsIgnoreCase("")) {
            creationDate = sdf.parse(incident.getCreationDate());
        }
        if(incident.getStatus() == null) {
            status = "Open";
        }
        else {
            status = incident.getStatus();
        }
        if(incident.getCompletionDate() == null) {
            completionDate = null;
        }
        else if(!incident.getCompletionDate().equalsIgnoreCase("")){
            completionDate = sdf.parse(incident.getCompletionDate());
        }
        serviceRequestNumber = incident.getServiceRequestNumber();
        typeOfServiceRequest = incident.getTypeOfServiceRequest();
        streetAddress = incident.getStreetAddress();
        zipCode = incident.getZipCode();
        x = incident.getX();
        y = incident.getY();
        ward = incident.getWard();
        policeDistrict = incident.getPoliceDistrict();
        communityArea = incident.getCommunityArea();
        latitude = incident.getLatitude();
        longitude = incident.getLongitude();
        if(incident.getLocation() == null) {
            location = "{'longitude': '" + longitude + "', 'needs_recoding': False, 'latitude': '" + latitude +"'}";
        }
        else location = incident.getLocation();
        if(latitude != null && longitude != null) {
            List<Double> values = new ArrayList<>();
            values.add(latitude.doubleValue());
            values.add(longitude.doubleValue());
            point = new Point(new Position(values));
        }
        if(typeOfServiceRequest.equals("Abandoned Vehicle Complaint")) {
            String plate = incident.getPlate();
            String model = incident.getModel();
            String color = incident.getColor();
            String currentActivity = incident.getCurrentActivity();
            String mostRecentAction = incident.getMostRecentAction();
            Double daysParked = incident.getDaysParked();
            String ssa = incident.getSsa();
            AbandonedVehicles request = new AbandonedVehicles(creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress,
                    zipCode, x, y, ward, policeDistrict, communityArea, latitude, longitude, location, point, upVotes, plate, model, color, currentActivity, mostRecentAction,
                    daysParked, ssa);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Alley Light Out")) {
            AlleyLightsOut request = new AlleyLightsOut(creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Garbage Cart Black Maintenance/Replacement")) {
            Double numberOfCarts = incident.getNumberOfCarts();
            String currentActivity = incident.getCurrentActivity();
            String mostRecenetAction = incident.getMostRecentAction();
            String ssa = incident.getSsa();
            GarbageCarts request = new GarbageCarts(creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, numberOfCarts, currentActivity, mostRecenetAction, ssa);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Graffiti Removal")) {
            String surface = incident.getSurface();
            String graffitiLocated = incident.getGraffitiLocated();
            String ssa = incident.getSsa();
            Graffiti request = new Graffiti(creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, surface, graffitiLocated, ssa);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Pothole in Street")) {
            String currentActivity = incident.getCurrentActivity();
            String mostRecenetAction = incident.getMostRecentAction();
            Float potholes = incident.getPotholes();
            String ssa = incident.getSsa();
            Potholes request = new Potholes(creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, currentActivity, mostRecenetAction, potholes, ssa);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Rodent Baiting/Rat Complaint")) {
            Float premisesBaited = incident.getPremisesBaited();
            Integer premisesWithGarbage = incident.getPremisesWithGarbage();
            Float premisesWithRats = incident.getPremisesWithRats();
            String currentActivity = incident.getCurrentActivity();
            String mostRecenetAction = incident.getMostRecentAction();
            RodentBaiting request = new RodentBaiting(creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, premisesBaited, premisesWithGarbage, premisesWithRats, currentActivity, mostRecenetAction);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Sanitation Code Violation")) {
            String codeViolation = incident.getCodeViolation();
            Sanitation request = new Sanitation(creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, codeViolation);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Street Light Out")) {
            StreetLightOut request = new StreetLightOut(creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Street Lights - All/Out")) {
            StreetLightsAllOut request = new StreetLightsAllOut(creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Tree Debris")) {
            String debrisLocations = incident.getDebrisLocations();
            String currentActivity = incident.getCurrentActivity();
            String mostRecenetAction = incident.getMostRecentAction();
            TreeDebris request = new TreeDebris(creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, debrisLocations, currentActivity, mostRecenetAction);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Tree Trim")) {
            String treeLocation = incident.getTreeLocation();
            TreeTrims request = new TreeTrims(creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, treeLocation);
            return requestRepository.save(request);
        }
        else return null;
    }
}
