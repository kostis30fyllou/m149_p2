package gr.uoa.di.m149_p2.service;

import gr.uoa.di.m149_p2.dal.RequestDalImpl;
import gr.uoa.di.m149_p2.dal.RequestRepository;
import gr.uoa.di.m149_p2.dto.CustomPoint;
import gr.uoa.di.m149_p2.dto.NewIncident;
import gr.uoa.di.m149_p2.models.*;
import gr.uoa.di.m149_p2.models.queries.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    RequestDalImpl requestDal;


    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private SimpleDateFormat sdfdo = new SimpleDateFormat("yyyy-MM-dd");

    public void resetUpvotes() {
        requestDal.resetUpvotes();
    }

    public Request upvoteRequest(long id) {
        return requestDal.upvoteRequest(id);
    }

    public Long getCount() {
        return requestRepository.count();
    }

    public Long getUpvotedRequestCount() {
        return requestDal.getUpvotedRequestsCount();
    }

    public List<TotalTypeRequests> getTotalTypeRequests(String startDate, String endDate) throws Exception{
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        return requestDal.getTotalTypeRequests(start, end);
    }

    public List<DailyRequests> getDailyRequests(String type, String startDate, String endDate) throws Exception {
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        return requestDal.getDailyRequests(type, start, end);
    }

    public List<MostCommonTypes> getMostCommonTypes(String date) throws Exception {
        Date dateD = sdfdo.parse(date);
        return requestDal.getMostCommonTypes(dateD);
    }

    public List<LeastCommonWards> getLeastCommonWards(String type) {
        return requestDal.getLeastCommonWards(type);
    }

    public List<AvgRequestCompletion> getAvgRequestCompletion(String startDate, String endDate) throws Exception {
        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);
        return requestDal.getAvgRequestCompletion(start, end);
    }

    public TotalTypeRequests getMostCommonRequest(String date, CustomPoint p1, CustomPoint p2) throws Exception{
        Date dateD = sdfdo.parse(date);
        return requestDal.getMostCommonRequest(dateD, new GeoJsonPoint(p1.getX(), p1.getY()),
                new GeoJsonPoint(p2.getX(), p2.getY()));
    }

    public List<MostVotedRequests> getMostVotedRequests(String date) throws Exception {
        Date day = sdf.parse(date);
        return requestDal.getMostVotedRequests(day);
    }

    public Request addRequest(NewIncident incident) throws Exception{
        if(incident == null) {
            return  null;
        }
        Long id = incident.getId();
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
        GeoJsonPoint point = null;
        Integer upVotes = 0;
        if(id == null) {
            id = requestRepository.count() + 1;
        }
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
            point = new GeoJsonPoint(latitude.doubleValue(), longitude.doubleValue());
        }
        if(typeOfServiceRequest.equals("Abandoned Vehicle Complaint")) {
            String plate = incident.getPlate();
            String model = incident.getModel();
            String color = incident.getColor();
            String currentActivity = incident.getCurrentActivity();
            String mostRecentAction = incident.getMostRecentAction();
            Double daysParked = incident.getDaysParked();
            String ssa = incident.getSsa();
            AbandonedVehicles request = new AbandonedVehicles(id, creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress,
                    zipCode, x, y, ward, policeDistrict, communityArea, latitude, longitude, location, point, upVotes, plate, model, color, currentActivity, mostRecentAction,
                    daysParked, ssa);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Alley Light Out")) {
            AlleyLightsOut request = new AlleyLightsOut(id, creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Garbage Cart Black Maintenance/Replacement")) {
            Double numberOfCarts = incident.getNumberOfCarts();
            String currentActivity = incident.getCurrentActivity();
            String mostRecenetAction = incident.getMostRecentAction();
            String ssa = incident.getSsa();
            GarbageCarts request = new GarbageCarts(id, creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, numberOfCarts, currentActivity, mostRecenetAction, ssa);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Graffiti Removal")) {
            String surface = incident.getSurface();
            String graffitiLocated = incident.getGraffitiLocated();
            String ssa = incident.getSsa();
            Graffiti request = new Graffiti(id, creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, surface, graffitiLocated, ssa);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Pothole in Street")) {
            String currentActivity = incident.getCurrentActivity();
            String mostRecenetAction = incident.getMostRecentAction();
            Float potholes = incident.getPotholes();
            String ssa = incident.getSsa();
            Potholes request = new Potholes(id, creationDate, status, completionDate, serviceRequestNumber,
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
            RodentBaiting request = new RodentBaiting(id, creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, premisesBaited, premisesWithGarbage, premisesWithRats, currentActivity, mostRecenetAction);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Sanitation Code Violation")) {
            String codeViolation = incident.getCodeViolation();
            Sanitation request = new Sanitation(id, creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, codeViolation);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Street Light Out")) {
            StreetLightOut request = new StreetLightOut(id, creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Street Lights - All/Out")) {
            StreetLightsAllOut request = new StreetLightsAllOut(id, creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Tree Debris")) {
            String debrisLocations = incident.getDebrisLocations();
            String currentActivity = incident.getCurrentActivity();
            String mostRecenetAction = incident.getMostRecentAction();
            TreeDebris request = new TreeDebris(id, creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, debrisLocations, currentActivity, mostRecenetAction);
            return requestRepository.save(request);
        }
        else if(typeOfServiceRequest.equals("Tree Trim")) {
            String treeLocation = incident.getTreeLocation();
            TreeTrims request = new TreeTrims(id, creationDate, status, completionDate, serviceRequestNumber,
                    typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude,
                    longitude, location, point, upVotes, treeLocation);
            return requestRepository.save(request);
        }
        else return null;
    }

    public void deleteAll() {
        requestRepository.deleteAll();
    }
}
