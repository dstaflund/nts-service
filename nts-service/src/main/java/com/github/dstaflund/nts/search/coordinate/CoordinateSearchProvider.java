package com.github.dstaflund.nts.search.coordinate;

import com.github.dstaflund.nts.listener.SessionFactoryListener;
import com.github.dstaflund.nts.search.NtsMap;
import com.github.dstaflund.nts.search.NtsMap.CoordinateQueryContract;
import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CoordinateSearchProvider {
    private static final int sTimeoutInSeconds = 5;
    private static final boolean sCacheable = true;
    private static final boolean sReadOnlyInd = true;

    public List<NtsMap> findMapsByCoordinate(CoordinateSearchParams ctx) {
        SessionFactory sessionFactory = SessionFactoryListener.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return QueryExecuter.executeQuery(session, () ->
            session.getNamedQuery(CoordinateQueryContract.QUERY_NAME)
                .setParameter(CoordinateQueryContract.PARAM_NAME, NtsMap.formatName(ctx.getName()))
                .setParameter(CoordinateQueryContract.PARAM_SNIPPET, NtsMap.formatSnippet(ctx.getSnippet()))
                .setParameter(CoordinateQueryContract.PARAM_PARENT, NtsMap.formatParent(ctx.getParent()))
                .setParameter(CoordinateQueryContract.PARAM_LATITUDE, ctx.getLatitude())
                .setParameter(CoordinateQueryContract.PARAM_LONGITUDE, ctx.getLongitude())
                .setTimeout(sTimeoutInSeconds)
                .setReadOnly(sReadOnlyInd)
                .setCacheable(sCacheable)
                .setFetchSize(ctx.getLimit() + 1)
                .setFirstResult(ctx.getOffset())
                .setMaxResults(ctx.getLimit() + 1)
        );
    }

    public static CoordinateSearchProvider getInstance(){
        return InstanceHelper.INSTANCE;
    }

    private static class InstanceHelper {
        private static final CoordinateSearchProvider INSTANCE = new CoordinateSearchProvider();
    }
}
