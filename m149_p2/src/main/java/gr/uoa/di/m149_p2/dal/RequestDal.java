package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.queries.DailyRequests;
import gr.uoa.di.m149_p2.models.queries.LeastCommonWards;
import gr.uoa.di.m149_p2.models.queries.TotalTypeRequests;

import java.util.Date;
import java.util.List;

public interface RequestDal {

    public List<TotalTypeRequests> getTotalTypeRequests(Date startDate, Date endDate);

    public List<DailyRequests> getDailyRequests(String type, Date startDate, Date endDate);

    public List<LeastCommonWards> getLeastCommonWards(String type);
}
