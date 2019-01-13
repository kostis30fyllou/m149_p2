package gr.uoa.di.m149_p2;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import gr.uoa.di.m149_p2.dal.RequestRepository;
import gr.uoa.di.m149_p2.models.*;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;


@SpringBootApplication
public class PopulateDb implements CommandLineRunner {


    private String split = ",";
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private RequestRepository requestRepository;

    @Value("${csv.path}")
    private String path;

    public static void main(String[] args) {
        SpringApplication.run(PopulateDb.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        requestRepository.deleteAll();
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

                // use comma as separator
                String[] fields = line.split(split,-1);
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase(""))
                    completionDate = sdf.parse(fields[2]);
                String streetAddress = fields[5];
                Integer zip = null;
                if (!fields[6].equalsIgnoreCase(""))
                    zip = Integer.parseInt(fields[6]);
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;
                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;
                String location = null;
                if (!fields[7].equalsIgnoreCase(""))
                    x = new BigDecimal(fields[7]);
                if (!fields[8].equalsIgnoreCase(""))
                    y = new BigDecimal(fields[8]);
                if (!fields[9].equalsIgnoreCase(""))
                    ward = Integer.parseInt(fields[9]);
                if (!fields[10].equalsIgnoreCase(""))
                    policeDistrict = Integer.parseInt(fields[10]);
                if (!fields[11].equalsIgnoreCase(""))
                    communityArea = Integer.parseInt(fields[11]);
                if (!fields[12].equalsIgnoreCase(""))
                    lat = new BigDecimal(fields[12]);
                if (!fields[13].equalsIgnoreCase(""))
                    longit = new BigDecimal(fields[13]);
                if (!fields[12].equalsIgnoreCase("") && !fields[13].equalsIgnoreCase("")) {
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                }
                location = fields[14];
                StreetLightOut request = new StreetLightOut(creationDate, fields[1], completionDate, fields[3], "Street Light Out",
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point);
                requestRepository.save(request);

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

                // use comma as separator
                String[] fields = line.split(split);
//                System.out.print(fields.length);
//                for (int i=0; i<fields.length; i++)
//                {
//                    System.out.println(fields[i]);
//                }
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase("")) {
                    completionDate = sdf.parse(fields[2]);
                }
                String codeViolation = fields[5];
                String streetAddress = null;
                if (fields.length > 6) {
                    streetAddress = fields[6];
                }
                Integer zip = null;
                if (fields.length > 7 && !fields[7].equalsIgnoreCase("")) {
                    zip = Integer.parseInt(fields[7]);
                }
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;
                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;
                String location = null;
                if (fields.length > 8 && !fields[8].equalsIgnoreCase("") && !fields[9].equalsIgnoreCase("")) {
                    x = new BigDecimal(fields[8]);
                    y = new BigDecimal(fields[9]);
                    if (!fields[10].equalsIgnoreCase("")) {
                        ward = Integer.parseInt(fields[10]);
                    }
                    if (!fields[11].equalsIgnoreCase("")) {
                        policeDistrict = Integer.parseInt(fields[11]);
                    }
                    if (!fields[12].equalsIgnoreCase("")) {
                        communityArea = Integer.parseInt(fields[12]);
                    }
                    lat = new BigDecimal(fields[13]);
                    longit = new BigDecimal(fields[14]);
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                    location = fields[15] + "," + fields[16] + "," + fields[17];
                }
                Sanitation request = new Sanitation(creationDate, fields[1], completionDate, fields[3], fields[4],
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point, codeViolation);
                requestRepository.save(request);
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
                /*System.out.print(fields.length);
                for (int i=0; i<fields.length; i++)
                {
                    System.out.println(fields[i]);
                }*/
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase("")) {
                    completionDate = sdf.parse(fields[2]);
                }

                Float premisesBaited = null;
                if (!fields[5].equalsIgnoreCase(""))
                    premisesBaited = Float.parseFloat(fields[5]);
                Integer premisesWithGarbage = null;
                if (!fields[6].equalsIgnoreCase(""))
                    premisesWithGarbage = Integer.parseInt(fields[6]);
                Float premisesWithRats = null;
                if (!fields[7].equalsIgnoreCase(""))
                    premisesWithRats = Float.parseFloat(fields[7]);
                String currentActivity = fields[8];
                String mostRecentAction = fields[9];

                String streetAddress = fields[10];
                Integer zip = null;
                if (!fields[11].equalsIgnoreCase("")) {
                    zip = Integer.parseInt(fields[11]);
                }
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;
                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;
                if (!fields[12].equalsIgnoreCase(""))
                    x = new BigDecimal(fields[12]);
                if (!fields[13].equalsIgnoreCase(""))
                    y = new BigDecimal(fields[13]);
                if (!fields[14].equalsIgnoreCase(""))
                    ward = Integer.parseInt(fields[14]);
                if (!fields[15].equalsIgnoreCase(""))
                    policeDistrict = Integer.parseInt(fields[15]);
                if (!fields[16].equalsIgnoreCase(""))
                    communityArea = Integer.parseInt(fields[16]);
                if (!fields[17].equalsIgnoreCase(""))
                    lat = new BigDecimal(fields[17]);
                if (!fields[18].equalsIgnoreCase(""))
                    longit = new BigDecimal(fields[18]);
                if (!fields[17].equalsIgnoreCase("") && !fields[12].equalsIgnoreCase("")) {
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                }
                String location = fields[19];
                RodentBaiting request = new RodentBaiting(creationDate, fields[1], completionDate, fields[3], fields[4],
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point, premisesBaited,
                        premisesWithGarbage, premisesWithRats, currentActivity, mostRecentAction);
                requestRepository.save(request);
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

                // use comma as separator
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//                System.out.println(fields.length);
//                for (int i=0; i<fields.length; i++)
//                {
//                    System.out.println(fields[i]);
//                }
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase("")) {
                    completionDate = sdf.parse(fields[2]);
                }
                String surface = fields[5];
                String graffitiLocated = fields[6];

                String streetAddress = fields[7];
                Integer zip = null;
                if (!fields[8].equalsIgnoreCase("")) {
                    zip = Integer.parseInt(fields[8]);
                }
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;

                String ssa = null;

                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;

                if (!fields[9].equalsIgnoreCase(""))
                    x = new BigDecimal(fields[9]);
                if (!fields[10].equalsIgnoreCase(""))
                    y = new BigDecimal(fields[10]);
                if (!fields[11].equalsIgnoreCase(""))
                    ward = Integer.parseInt(fields[11]);
                if (!fields[12].equalsIgnoreCase(""))
                    policeDistrict = Integer.parseInt(fields[12]);
                if (!fields[13].equalsIgnoreCase(""))
                    communityArea = Integer.parseInt(fields[13]);

                ssa = fields[14];

                if (!fields[15].equalsIgnoreCase(""))
                    lat = new BigDecimal(fields[15]);
                if (!fields[16].equalsIgnoreCase(""))
                    longit = new BigDecimal(fields[16]);
                if (!fields[15].equalsIgnoreCase("") && !fields[16].equalsIgnoreCase("")) {                        List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                }
                String location = fields[17];

                Graffiti request = new Graffiti(creationDate, fields[1], completionDate, fields[3], fields[4], streetAddress,
                        zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point,
                        surface, graffitiLocated, ssa);
                requestRepository.save(request);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Graffiti");
    }

    public void insertPotholes() throws Exception {
        String csv = path + "311-service-requests-pot-holes-reported.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                /*System.out.println(fields.length);
                for (int i=0; i<fields.length; i++)
                {
                    System.out.println(fields[i]);
                }*/
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase(""))
                    completionDate = sdf.parse(fields[2]);

                String currentActivity = fields[5];
                String mostRecentAction = fields[6];
                Float potHoles = null;
                if (!fields[7].equalsIgnoreCase(""))
                    potHoles = Float.parseFloat(fields[7]);

                String streetAddress = fields[8];
                Integer zip = null;
                if (!fields[9].equalsIgnoreCase("")) {
                    zip = Integer.parseInt(fields[9]);
                }
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;

                String ssa = null;

                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;
                String location = null;
                if (!fields[10].equalsIgnoreCase(""))
                    x = new BigDecimal(fields[10]);
                if (!fields[11].equalsIgnoreCase(""))
                    y = new BigDecimal(fields[11]);
                if (!fields[12].equalsIgnoreCase(""))
                    ward = Integer.parseInt(fields[12]);
                if (!fields[13].equalsIgnoreCase(""))
                    policeDistrict = Integer.parseInt(fields[13]);
                if (!fields[14].equalsIgnoreCase(""))
                    communityArea = Integer.parseInt(fields[14]);

                ssa = fields[15];

                if (!fields[16].equalsIgnoreCase(""))
                    lat = new BigDecimal(fields[16]);
                if (!fields[17].equalsIgnoreCase(""))
                    longit = new BigDecimal(fields[17]);
                if (!fields[15].equalsIgnoreCase("") && !fields[16].equalsIgnoreCase("")) {
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                }
                    location = fields[18];

                Potholes request = new Potholes(creationDate, fields[1], completionDate, fields[3], "Pothole in Street",
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point,
                        currentActivity, mostRecentAction, potHoles, ssa);
                requestRepository.save(request);
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

                // use comma as separator
                String[] fields = line.split(split, -1);
//                System.out.println(fields.length);
//                for (int i=0; i<fields.length; i++)
//                {
//                    System.out.println(fields[i]);
//                }
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase(""))
                    completionDate = sdf.parse(fields[2]);
                String streetAddress = null;
                    streetAddress = fields[5];
                Integer zip = null;
                if (!fields[6].equalsIgnoreCase(""))
                    zip = Integer.parseInt(fields[6]);
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;
                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;
                String location = null;
                if (!fields[7].equalsIgnoreCase(""))
                    x = new BigDecimal(fields[7]);
                if (!fields[8].equalsIgnoreCase(""))
                    y = new BigDecimal(fields[8]);
                if (!fields[9].equalsIgnoreCase(""))
                    ward = Integer.parseInt(fields[9]);
                if (!fields[10].equalsIgnoreCase(""))
                    policeDistrict = Integer.parseInt(fields[10]);
                if (!fields[11].equalsIgnoreCase(""))
                    communityArea = Integer.parseInt(fields[11]);
                if (!fields[12].equalsIgnoreCase(""))
                    lat = new BigDecimal(fields[12]);
                if (!fields[13].equalsIgnoreCase(""))
                    longit = new BigDecimal(fields[13]);
                if (!fields[12].equalsIgnoreCase("") && !fields[13].equalsIgnoreCase("")) {
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                }
                if (fields.length > 15) {
                    location = fields[14] + "," + fields[15] + "," + fields[16];
                }

                AlleyLightsOut request = new AlleyLightsOut(creationDate, fields[1], completionDate, fields[3], fields[4],
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point);
                requestRepository.save(request);

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

                // use comma as separator
                String[] fields = line.split(split, -1);
//                System.out.println(fields.length);
//                for (int i=0; i<fields.length; i++)
//                {
//                    System.out.println(fields[i]);
//                }
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase(""))
                    completionDate = sdf.parse(fields[2]);
                String streetAddress = null;
                streetAddress = fields[5];
                Integer zip = null;
                if (!fields[6].equalsIgnoreCase(""))
                    zip = Integer.parseInt(fields[6]);
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;
                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;
                String location = null;
                if (!fields[7].equalsIgnoreCase(""))
                    x = new BigDecimal(fields[7]);
                if (!fields[8].equalsIgnoreCase(""))
                    y = new BigDecimal(fields[8]);
                if (!fields[9].equalsIgnoreCase(""))
                    ward = Integer.parseInt(fields[9]);
                if (!fields[10].equalsIgnoreCase(""))
                    policeDistrict = Integer.parseInt(fields[10]);
                if (!fields[11].equalsIgnoreCase(""))
                    communityArea = Integer.parseInt(fields[11]);
                if (!fields[12].equalsIgnoreCase(""))
                    lat = new BigDecimal(fields[12]);
                if (!fields[13].equalsIgnoreCase(""))
                    longit = new BigDecimal(fields[13]);
                if (!fields[12].equalsIgnoreCase("") && !fields[13].equalsIgnoreCase("")) {
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                }
                if (fields.length > 15) {
                    location = fields[14] + "," + fields[15] + "," + fields[16];
                }

                StreetLightsAllOut request = new StreetLightsAllOut(creationDate, fields[1], completionDate, fields[3], fields[4],
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point);
                requestRepository.save(request);

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

                // use comma as separator
                String[] fields = line.split(split, -1);
//                System.out.println(fields.length);
//                for (int i=0; i<fields.length; i++)
//                {
//                    System.out.println(fields[i]);
//                }
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase(""))
                    completionDate = sdf.parse(fields[2]);

                String treeLocation = fields[5];

                String streetAddress = fields[6];
                Integer zip = null;
                if (!fields[7].equalsIgnoreCase(""))
                    zip = Integer.parseInt(fields[7]);
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;
                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;
                String location = null;
                if (!fields[8].equalsIgnoreCase(""))
                    x = new BigDecimal(fields[8]);
                if (!fields[9].equalsIgnoreCase(""))
                    y = new BigDecimal(fields[9]);
                if (!fields[10].equalsIgnoreCase(""))
                    ward = Integer.parseInt(fields[10]);
                if (!fields[11].equalsIgnoreCase(""))
                    policeDistrict = Integer.parseInt(fields[11]);
                if (!fields[12].equalsIgnoreCase(""))
                    communityArea = Integer.parseInt(fields[12]);
                if (!fields[13].equalsIgnoreCase(""))
                    lat = new BigDecimal(fields[13]);
                if (!fields[14].equalsIgnoreCase(""))
                    longit = new BigDecimal(fields[14]);
                if (!fields[13].equalsIgnoreCase("") && !fields[14].equalsIgnoreCase("")) {
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                }
                if (fields.length > 16) {
                    location = fields[15] + "," + fields[16] + "," + fields[17];
                }

                TreeTrims request = new TreeTrims(creationDate, fields[1], completionDate, fields[3], fields[4],
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point,
                        treeLocation);
                requestRepository.save(request);

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

                // use comma as separator
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//                System.out.println(fields.length);
//                for (int i=0; i<fields.length; i++)
//                {
//                    System.out.println(fields[i]);
//                }
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase(""))
                    completionDate = sdf.parse(fields[2]);

                String debrisLocation = fields[5];
                String currentActivity = fields[6];
                String mostRecentAction = fields[7];

                String streetAddress = fields[8];
                Integer zip = null;
                if (!fields[9].equalsIgnoreCase(""))
                    zip = Integer.parseInt(fields[9]);
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;
                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;
                if (!fields[10].equalsIgnoreCase(""))
                    x = new BigDecimal(fields[10]);
                if (!fields[11].equalsIgnoreCase(""))
                    y = new BigDecimal(fields[11]);
                if (!fields[12].equalsIgnoreCase(""))
                    ward = Integer.parseInt(fields[12]);
                if (!fields[13].equalsIgnoreCase(""))
                    policeDistrict = Integer.parseInt(fields[13]);
                if (!fields[14].equalsIgnoreCase(""))
                    communityArea = Integer.parseInt(fields[14]);
                if (!fields[15].equalsIgnoreCase(""))
                    lat = new BigDecimal(fields[15]);
                if (!fields[16].equalsIgnoreCase(""))
                    longit = new BigDecimal(fields[16]);
                if (!fields[15].equalsIgnoreCase("") && !fields[16].equalsIgnoreCase("")) {
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                }

                String location = fields[17];

                TreeDebris request = new TreeDebris(creationDate, fields[1], completionDate, fields[3], fields[4],
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point,
                        debrisLocation, mostRecentAction, currentActivity);
                requestRepository.save(request);

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

                // use comma as separator
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                /*System.out.println(fields.length);
                for (int i=0; i<fields.length; i++)
                {
                    System.out.println(fields[i]);
                }*/
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase(""))
                    completionDate = sdf.parse(fields[2]);

                String plate = fields[5];
                String model = fields[6];
                String color = fields[7];
                String currentActivity = fields[8];
                String mostRecentAction = fields[9];
                Double daysParked = null;
                if (!fields[10].equalsIgnoreCase(""))
                    daysParked = Double.parseDouble(fields[10]);

                String streetAddress = fields[11];
                Integer zip = null;
                if (!fields[12].equalsIgnoreCase(""))
                    zip = Integer.parseInt(fields[12]);
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;
                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;
                String location = null;
                if (!fields[13].equalsIgnoreCase(""))
                    x = new BigDecimal(fields[13]);
                if (!fields[14].equalsIgnoreCase(""))
                    y = new BigDecimal(fields[14]);
                if (!fields[15].equalsIgnoreCase(""))
                    ward = Integer.parseInt(fields[15]);
                if (!fields[16].equalsIgnoreCase(""))
                    policeDistrict = Integer.parseInt(fields[16]);
                if (!fields[17].equalsIgnoreCase(""))
                    communityArea = Integer.parseInt(fields[17]);

                String ssa = fields[18];

                if (!fields[19].equalsIgnoreCase(""))
                    lat = new BigDecimal(fields[19]);
                if (!fields[20].equalsIgnoreCase(""))
                    longit = new BigDecimal(fields[20]);
                if (!fields[19].equalsIgnoreCase("") && !fields[20].equalsIgnoreCase("")) {
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                }
                    location = fields[21];

                AbandonedVehicles request= new AbandonedVehicles(creationDate, fields[1], completionDate, fields[3], fields[4],
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point,
                        plate, model, color, currentActivity, mostRecentAction, daysParked, ssa);
                requestRepository.save(request);

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

                // use comma as separator
                String[] fields = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
//                System.out.println(fields.length);
//                for (int i=0; i<fields.length; i++)
//                {
//                    System.out.println(fields[i]);
//                }
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = null;
                if (!fields[2].equalsIgnoreCase(""))
                    completionDate = sdf.parse(fields[2]);

                Double numberOfCarts = null;
                if (!fields[5].equalsIgnoreCase(""))
                    numberOfCarts = Double.parseDouble(fields[5]);
                String currentActivity = fields[6];
                String mostRecentAction = fields[7];

                String streetAddress = fields[8];
                Integer zip = null;
                if (!fields[9].equalsIgnoreCase(""))
                    zip = Integer.parseInt(fields[9]);
                BigDecimal x = null;
                BigDecimal y = null;
                Integer ward = null;
                Integer policeDistrict = null;
                Integer communityArea = null;
                BigDecimal lat = null;
                BigDecimal longit = null;
                Point point = null;
                if (!fields[10].equalsIgnoreCase(""))
                    x = new BigDecimal(fields[10]);
                if (!fields[11].equalsIgnoreCase(""))
                    y = new BigDecimal(fields[11]);
                if (!fields[12].equalsIgnoreCase(""))
                    ward = Integer.parseInt(fields[12]);
                if (!fields[13].equalsIgnoreCase(""))
                    policeDistrict = Integer.parseInt(fields[13]);
                if (!fields[14].equalsIgnoreCase(""))
                    communityArea = Integer.parseInt(fields[14]);

                String ssa = fields[15];

                if (!fields[16].equalsIgnoreCase(""))
                    lat = new BigDecimal(fields[16]);
                if (!fields[17].equalsIgnoreCase(""))
                    longit = new BigDecimal(fields[17]);
                if (!fields[16].equalsIgnoreCase("") && !fields[17].equalsIgnoreCase("")) {
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                }
                String location = fields[18];

                GarbageCarts request = new GarbageCarts(creationDate, fields[1], completionDate, fields[3], fields[4],
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point,
                        numberOfCarts, currentActivity, mostRecentAction,ssa);
                requestRepository.save(request);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Insert Garbage Carts Requests");
    }

}
