package gr.uoa.di.m149_p2;

import gr.uoa.di.m149_p2.dto.NewIncident;
import gr.uoa.di.m149_p2.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;


@SpringBootApplication
public class PopulateDb implements CommandLineRunner {


    @Autowired
    private RequestService requestService;

    @Value("${csv.path}")
    private String path;

    public static void main(String[] args) {
        SpringApplication.run(PopulateDb.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        //populateDB();
    }

    public void populateDB() throws Exception{
        requestService.deleteAll();
        insertStreetLightOut();//done
        insertSanitation();// done
        insertRodentBaiting();// done
        insertGraffiti();// done
        insertPotholes();// done
        insertAlleyLightsOut();// done
        insertStreetLightsAllOut();// done
        insertTreeTrims();// done
        insertTreeDebris();// done
        insertAbandonedVehicles(); //done
        insertGarbageCarts(); //done
    }

    public void insertStreetLightOut() throws Exception {
        System.out.println("Insert Street Light Out Requests");
        String csv = path + "311-service-requests-street-lights-one-out.csv";
        String line;
       try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
           line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest("Street Light Out");
                incident.setStreetAddress(fields[5]);
                if (!fields[6].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[6]));
                if (!fields[7].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[7]));
                if (!fields[8].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[8]));
                if (!fields[9].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[9]));
                if (!fields[10].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[10]));
                if (!fields[11].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[11]));
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[13]));
                incident.setLocation(fields[14]);
                requestService.addRequest(incident);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Street Light Out Requests");

    }
    public void insertSanitation() throws Exception {
        System.out.println("Insert Sanitation Code Complaints");
        String csv = path + "311-service-requests-sanitation-code-complaints.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest(fields[4]);
                incident.setCodeViolation(fields[5]);
                incident.setStreetAddress(fields[6]);
                if (!fields[7].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[7]));
                if (!fields[8].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[8]));
                if (!fields[9].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[9]));
                if (!fields[10].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[10]));
                if (!fields[11].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[11]));
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[13]));
                if (!fields[14].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[14]));
                incident.setLocation(fields[15]);
                requestService.addRequest(incident);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Sanitation Code Complaints");
    }

    public void insertRodentBaiting() throws Exception {
        System.out.println("Insert Rodent Baiting");
        String csv = path + "311-service-requests-rodent-baiting.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest(fields[4]);
                if (!fields[5].equalsIgnoreCase(""))
                    incident.setPremisesBaited(Float.parseFloat(fields[5]));
                if (!fields[6].equalsIgnoreCase(""))
                    incident.setPremisesWithGarbage(Integer.parseInt(fields[6]));
                if (!fields[7].equalsIgnoreCase(""))
                    incident.setPremisesWithRats(Float.parseFloat(fields[7]));
                incident.setCurrentActivity(fields[8]);
                incident.setMostRecentAction(fields[9]);
                incident.setStreetAddress(fields[10]);
                if (!fields[11].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[11]));
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[13]));
                if (!fields[14].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[14]));
                if (!fields[15].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[15]));
                if (!fields[16].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[16]));
                if (!fields[17].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[17]));
                if (!fields[18].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[18]));
                incident.setLocation(fields[19]);
                requestService.addRequest(incident);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Rodent Baiting");
    }

    public void insertGraffiti() throws Exception {
        System.out.println("Insert Graffiti");
        String csv = path + "311-service-requests-graffiti-removal.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest(fields[4]);
                incident.setSurface(fields[5]);
                incident.setGraffitiLocated(fields[6]);
                incident.setStreetAddress(fields[7]);
                if (!fields[8].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[8]));
                if (!fields[9].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[9]));
                if (!fields[10].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[10]));
                if (!fields[11].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[11]));
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[13]));
                incident.setSsa(fields[14]);
                if (!fields[15].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[15]));
                if (!fields[16].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[16]));
                incident.setLocation(fields[17]);
                requestService.addRequest(incident);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Graffiti");
    }

    public void insertPotholes() throws Exception {
        System.out.println("Insert Pot Holes");
        String csv = path + "311-service-requests-pot-holes-reported.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest(fields[4]);
                incident.setCurrentActivity(fields[5]);
                incident.setMostRecentAction(fields[6]);
                if (!fields[7].equalsIgnoreCase(""))
                    incident.setPotholes(Float.parseFloat(fields[7]));
                incident.setStreetAddress(fields[8]);
                if (!fields[9].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[9]));
                if (!fields[10].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[10]));
                if (!fields[11].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[11]));
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[13]));
                if (!fields[14].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[14]));
                incident.setSsa(fields[15]);
                if (!fields[16].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[16]));
                if (!fields[17].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[17]));
                incident.setLocation(fields[18]);
                requestService.addRequest(incident);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Potholes");
    }

    public void insertAlleyLightsOut() throws Exception {
        System.out.println("Insert Alley Light Out Requests");
        String csv = path + "311-service-requests-alley-lights-out.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest(fields[4]);
                incident.setStreetAddress(fields[5]);
                if (!fields[6].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[6]));
                if (!fields[7].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[7]));
                if (!fields[8].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[8]));
                if (!fields[9].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[9]));
                if (!fields[10].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[10]));
                if (!fields[11].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[11]));
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[13]));
                incident.setLocation(fields[14]);
                requestService.addRequest(incident);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Alley Light Out Requests");
    }

    public void insertStreetLightsAllOut() throws Exception {
        System.out.println("Insert Street Lights All Out Requests");
        String csv = path + "311-service-requests-street-lights-all-out.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest(fields[4]);
                incident.setStreetAddress(fields[5]);
                if (!fields[6].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[6]));
                if (!fields[7].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[7]));
                if (!fields[8].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[8]));
                if (!fields[9].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[9]));
                if (!fields[10].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[10]));
                if (!fields[11].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[11]));
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[13]));
                incident.setLocation(fields[14]);
                requestService.addRequest(incident);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Street Lights All Out Requests");
    }

    public void insertTreeTrims() throws Exception {
        System.out.println("Insert Tree Trims Requests");
        String csv = path + "311-service-requests-tree-trims.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest(fields[4]);
                incident.setTreeLocation(fields[5]);
                incident.setStreetAddress(fields[6]);
                if (!fields[7].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[7]));
                if (!fields[8].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[8]));
                if (!fields[9].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[9]));
                if (!fields[10].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[10]));
                if (!fields[11].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[11]));
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[13]));
                if (!fields[14].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[14]));
                incident.setLocation(fields[15]);
                requestService.addRequest(incident);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Tree Trims Requests");
    }

    public void insertTreeDebris() throws Exception {
        System.out.println("Insert Tree debris Requests");
        String csv = path + "311-service-requests-tree-debris.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest(fields[4]);
                incident.setDebrisLocations(fields[5]);
                incident.setCurrentActivity(fields[6]);
                incident.setMostRecentAction(fields[7]);
                incident.setStreetAddress(fields[8]);
                if (!fields[9].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[9]));
                if (!fields[10].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[10]));
                if (!fields[11].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[11]));
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[13]));
                if (!fields[14].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[14]));
                if (!fields[15].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[15]));
                if (!fields[16].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[16]));
                incident.setLocation(fields[17]);
                requestService.addRequest(incident);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Tree Debris Requests");
    }

    public void insertAbandonedVehicles() throws Exception {
        System.out.println("Insert Abandoned Vehicles Requests");
        String csv = path + "311-service-requests-abandoned-vehicles.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest(fields[4]);
                incident.setPlate(fields[5]);
                incident.setModel(fields[6]);
                incident.setColor(fields[7]);
                incident.setCurrentActivity(fields[8]);
                incident.setMostRecentAction(fields[9]);
                if (!fields[10].equalsIgnoreCase(""))
                    incident.setDaysParked(Double.parseDouble(fields[10]));
                incident.setStreetAddress(fields[11]);
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[13]));
                if (!fields[14].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[14]));
                if (!fields[15].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[15]));
                if (!fields[16].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[16]));
                if (!fields[17].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[17]));
                incident.setSsa(fields[18]);
                if (!fields[19].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[19]));
                if (!fields[20].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[20]));
                incident.setLocation(fields[21]);
                requestService.addRequest(incident);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Abandoned Vehicles Requests");
    }

    public void insertGarbageCarts() throws Exception {
        System.out.println("Insert Garbage Carts Requests");
        String csv = path + "311-service-requests-garbage-carts.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                NewIncident incident = new NewIncident();
                incident.setCreationDate(fields[0]);
                incident.setStatus(fields[1]);
                incident.setCompletionDate(fields[2]);
                incident.setServiceRequestNumber(fields[3]);
                incident.setTypeOfServiceRequest(fields[4]);
                if (!fields[5].equalsIgnoreCase(""))
                    incident.setNumberOfCarts(Double.parseDouble(fields[5]));
                incident.setCurrentActivity(fields[6]);
                incident.setMostRecentAction(fields[7]);
                incident.setStreetAddress(fields[8]);
                if (!fields[9].equalsIgnoreCase(""))
                    incident.setZipCode(Integer.parseInt(fields[9]));
                if (!fields[10].equalsIgnoreCase(""))
                    incident.setX(new BigDecimal(fields[10]));
                if (!fields[11].equalsIgnoreCase(""))
                    incident.setY(new BigDecimal(fields[11]));
                if (!fields[12].equalsIgnoreCase(""))
                    incident.setWard(Integer.parseInt(fields[12]));
                if (!fields[13].equalsIgnoreCase(""))
                    incident.setPoliceDistrict(Integer.parseInt(fields[13]));
                if (!fields[14].equalsIgnoreCase(""))
                    incident.setCommunityArea(Integer.parseInt(fields[14]));
                incident.setSsa(fields[15]);
                if (!fields[16].equalsIgnoreCase(""))
                    incident.setLatitude(new BigDecimal(fields[16]));
                if (!fields[17].equalsIgnoreCase(""))
                    incident.setLongitude(new BigDecimal(fields[17]));
                incident.setLocation(fields[18]);
                requestService.addRequest(incident);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Garbage Carts Requests");
    }

}
