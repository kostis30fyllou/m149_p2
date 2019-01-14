package gr.uoa.di.m149_p2.dal;

import gr.uoa.di.m149_p2.models.queries.TotalTypeRequests;

import java.util.Date;
import java.util.List;

public interface RequestDal {

    public List<TotalTypeRequests> getTotalTypeRequests(Date startDate, Date endDate);
}
