package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.queries.*;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.Date;
import java.util.List;

public interface RequestDal {

    public List<TotalTypeRequests> getTotalTypeRequests(Date startDate, Date endDate);

    public List<DailyRequests> getDailyRequests(String type, Date startDate, Date endDate);

    public List<MostCommonTypes> getMostCommonTypes(Date date);

    public List<LeastCommonWards> getLeastCommonWards(String type);

    public List<AvgRequestCompletion> getAvgRequestCompletion(Date startDate, Date endDate);

    public TotalTypeRequests getMostCommonRequest(Date date, GeoJsonPoint p1, GeoJsonPoint p2);
}
