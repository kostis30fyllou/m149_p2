package gr.uoa.di.m149_p2;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import gr.uoa.di.m149_p2.dal.RequestRepository;
import gr.uoa.di.m149_p2.models.Request;
import gr.uoa.di.m149_p2.models.RodentBaiting;
import gr.uoa.di.m149_p2.models.Sanitation;
import org.springframework.beans.factory.annotation.Autowired;
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


@SpringBootApplication
public class PopulateDb implements CommandLineRunner {

    private static final String path = "/home/john/Documents/databases/chicago-311-service-requests/";
    private static final String split = ",";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private RequestRepository requestRepository;

    public static void main(String[] args) {
        SpringApplication.run(PopulateDb.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        requestRepository.deleteAll();
        String csv = path + "311-service-requests-street-lights-one-out.csv";
        String line;
//        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
//            line = br.readLine();
//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] fields = line.split(split);
////                System.out.print(fields.length);
////                for (int i=0; i<fields.length; i++)
////                {
////                    System.out.println(fields[i]);
////                }
//                Date creationDate = sdf.parse(fields[0]);
//                Date completionDate = null;
//                if (!fields[2].equalsIgnoreCase("")) { completionDate = sdf.parse(fields[2]);}
//                String streetAddress = null;
//                if (fields.length > 5) {
//                    streetAddress = fields[5];
//                }
//                Integer zip = null;
//                if (fields.length > 6 && !fields[6].equalsIgnoreCase("")) { zip = Integer.parseInt(fields[6]);}
//                BigDecimal x = null;
//                BigDecimal y = null;
//                Integer ward = null;
//                Integer policeDistrict = null;
//                Integer communityArea = null;
//                BigDecimal lat = null;
//                BigDecimal longit = null;
//                Point point = null;
//                String location = null;
//                if (fields.length > 7 && !fields[7].equalsIgnoreCase("") && !fields[8].equalsIgnoreCase("")) {
//                    x = new BigDecimal(fields[7]);
//                    y = new BigDecimal(fields[8]);
//                    if (!fields[9].equalsIgnoreCase("")) { ward = Integer.parseInt(fields[9]); }
//                    if (!fields[10].equalsIgnoreCase("")) { policeDistrict = Integer.parseInt(fields[10]); }
//                    if (!fields[11].equalsIgnoreCase("")) { communityArea = Integer.parseInt(fields[11]); }
//                    lat = new BigDecimal(fields[12]);
//                    longit = new BigDecimal(fields[13]);
//                    List<Double> values = new ArrayList<>();
//                    values.add(lat.doubleValue());
//                    values.add(longit.doubleValue());
//                    point = new Point(new Position(values));
//                    location = fields[14] + "," + fields[15];
//                }
//                Request request = new Request(creationDate, fields[1], completionDate, fields[3], "Street Light Out",
//                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point);
//                requestRepository.save(request);
//
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        csv = path + "311-service-requests-sanitation-code-complaints.csv";

//        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
//            line = br.readLine();
//            while ((line = br.readLine()) != null) {
//
//                // use comma as separator
//                String[] fields = line.split(split);
////                System.out.print(fields.length);
////                for (int i=0; i<fields.length; i++)
////                {
////                    System.out.println(fields[i]);
////                }
//                Date creationDate = sdf.parse(fields[0]);
//                Date completionDate = null;
//                if (!fields[2].equalsIgnoreCase("")) {
//                    completionDate = sdf.parse(fields[2]);
//                }
//                String codeViolation = fields[5];
//                String streetAddress = null;
//                if (fields.length > 6) {
//                    streetAddress = fields[6];
//                }
//                Integer zip = null;
//                if (fields.length > 7 && !fields[7].equalsIgnoreCase("")) {
//                    zip = Integer.parseInt(fields[7]);
//                }
//                BigDecimal x = null;
//                BigDecimal y = null;
//                Integer ward = null;
//                Integer policeDistrict = null;
//                Integer communityArea = null;
//                BigDecimal lat = null;
//                BigDecimal longit = null;
//                Point point = null;
//                String location = null;
//                if (fields.length > 8 && !fields[8].equalsIgnoreCase("") && !fields[9].equalsIgnoreCase("")) {
//                    x = new BigDecimal(fields[8]);
//                    y = new BigDecimal(fields[9]);
//                    if (!fields[10].equalsIgnoreCase("")) {
//                        ward = Integer.parseInt(fields[10]);
//                    }
//                    if (!fields[11].equalsIgnoreCase("")) {
//                        policeDistrict = Integer.parseInt(fields[11]);
//                    }
//                    if (!fields[12].equalsIgnoreCase("")) {
//                        communityArea = Integer.parseInt(fields[12]);
//                    }
//                    lat = new BigDecimal(fields[13]);
//                    longit = new BigDecimal(fields[14]);
//                    List<Double> values = new ArrayList<>();
//                    values.add(lat.doubleValue());
//                    values.add(longit.doubleValue());
//                    point = new Point(new Position(values));
//                    location = fields[15] + "," + fields[16] + "," + fields[17];
//                }
//                Sanitation request = new Sanitation(creationDate, fields[1], completionDate, fields[3], "Street Light Out",
//                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point, codeViolation);
//                requestRepository.save(request);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        csv = path + "311-service-requests-rodent-baiting.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            line = br.readLine();
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
                Float premisesBaited = null;
                if (!fields[5].equalsIgnoreCase("")) {premisesBaited = Float.parseFloat(fields[5]);}
                Integer premisesWithGarbage = null;
                if (!fields[6].equalsIgnoreCase("")) {premisesWithGarbage = Integer.parseInt(fields[6]);}
                Float premisesWithRats = null;
                if (!fields[7].equalsIgnoreCase("")) {premisesWithRats = Float.parseFloat(fields[7]);}
                String currentActivity = fields[8];
                String mostRecentAction = fields[9];

                String streetAddress = null;
                if (fields.length > 10) {
                    streetAddress = fields[10];
                }
                Integer zip = null;
                if (fields.length > 11 && !fields[11].equalsIgnoreCase("")) {
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
                String location = null;
                if (fields.length > 12 && !fields[12].equalsIgnoreCase("") && !fields[13].equalsIgnoreCase("")) {
                    x = new BigDecimal(fields[12]);
                    y = new BigDecimal(fields[13]);
                    if (!fields[14].equalsIgnoreCase("")) {
                        ward = Integer.parseInt(fields[14]);
                    }
                    if (!fields[15].equalsIgnoreCase("")) {
                        policeDistrict = Integer.parseInt(fields[15]);
                    }
                    if (!fields[16].equalsIgnoreCase("")) {
                        communityArea = Integer.parseInt(fields[16]);
                    }
                    lat = new BigDecimal(fields[17]);
                    longit = new BigDecimal(fields[18]);
                    List<Double> values = new ArrayList<>();
                    values.add(lat.doubleValue());
                    values.add(longit.doubleValue());
                    point = new Point(new Position(values));
                    location = fields[19] + "," + fields[20] + "," + fields[21];
                }
                RodentBaiting request = new RodentBaiting(creationDate, fields[1], completionDate, fields[3], "Street Light Out",
                        streetAddress, zip, x, y, ward, policeDistrict, communityArea, lat, longit, location, point, premisesBaited,
                        premisesWithGarbage, premisesWithRats, currentActivity, mostRecentAction);
                requestRepository.save(request);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
