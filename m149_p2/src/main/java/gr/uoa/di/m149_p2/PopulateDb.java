package gr.uoa.di.m149_p2;

import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;
import gr.uoa.di.m149_p2.dal.RequestRepository;
import gr.uoa.di.m149_p2.models.Request;
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

    private static final String path = "/home/kostis/Desktop/Csv/";
    private static final String split = ",";
    private static final SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Autowired
    private RequestRepository requestRepository;

    public static void main(String[] args) {
        SpringApplication.run(PopulateDb.class, args);
    }

    @Override
    public void run(String... args) throws  Exception{

        requestRepository.deleteAll();
        String csv = path + "311-service-requests-street-lights-one-out.csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csv))) {
            line = br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] fields = line.split(split);
                Date creationDate = sdf.parse(fields[0]);
                Date completionDate = sdf.parse(fields[2]);
                BigDecimal lat = new BigDecimal(fields[12]);
                BigDecimal longit = new BigDecimal(fields[13]);
                List<Double> values = new ArrayList<>();
                values.add(lat.doubleValue());
                values.add(longit.doubleValue());
                Point point = new Point(new Position(values));
                Request request = new Request(creationDate, fields[1], completionDate, fields[3], "Street Light Out", fields[5], Integer.parseInt(fields[6]),
                        new BigDecimal(fields[7]), new BigDecimal(fields[8]),  Integer.parseInt(fields[9]),  Integer.parseInt(fields[10]),  Integer.parseInt(fields[11]),
                        lat, longit, fields[14] + "," + fields[15], point);
                requestRepository.save(request);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
