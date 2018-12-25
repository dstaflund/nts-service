package com.github.dstaflund.nts.search.coordinate;

import com.github.dstaflund.nts.listener.SessionFactoryListener;
import com.github.dstaflund.nts.search.NtsMap;
import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static com.github.dstaflund.nts.search.NtsMap.CoordinateQueryContract.PARAM_LATITUDE;
import static com.github.dstaflund.nts.search.NtsMap.CoordinateQueryContract.PARAM_LONGITUDE;
import static com.github.dstaflund.nts.search.NtsMap.CoordinateQueryContract.PARAM_NAME;
import static com.github.dstaflund.nts.search.NtsMap.CoordinateQueryContract.PARAM_PARENT;
import static com.github.dstaflund.nts.search.NtsMap.CoordinateQueryContract.PARAM_SNIPPET;
import static com.github.dstaflund.nts.search.NtsMap.CoordinateQueryContract.QUERY_NAME;
import static com.github.dstaflund.nts.search.NtsMap.formatName;
import static com.github.dstaflund.nts.search.NtsMap.formatParent;
import static com.github.dstaflund.nts.search.NtsMap.formatSnippet;

final class CoordinateSearchProvider {
    private static final int sTimeoutInSeconds = 5;
    private static final boolean sCacheable = true;
    private static final boolean sReadOnlyInd = true;

    private CoordinateSearchProvider(){
    }

    static List<NtsMap> findMapsByCoordinate(CoordinateSearchParams ctx) {
        SessionFactory factory = SessionFactoryListener.getSessionFactory();
        Session session = factory.getCurrentSession();
        return QueryExecuter.executeQuery(session, () ->
            session.getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_NAME, formatName(ctx.getName()))
                .setParameter(PARAM_SNIPPET, formatSnippet(ctx.getSnippet()))
                .setParameter(PARAM_PARENT, formatParent(ctx.getParent()))
                .setParameter(PARAM_LATITUDE, ctx.getLatitude())
                .setParameter(PARAM_LONGITUDE, ctx.getLongitude())
                .setTimeout(sTimeoutInSeconds)
                .setReadOnly(sReadOnlyInd)
                .setCacheable(sCacheable)
                .setFetchSize(ctx.getLimit() + 1)
                .setFirstResult(ctx.getOffset())
                .setMaxResults(ctx.getLimit() + 1)
        );
    }
}
