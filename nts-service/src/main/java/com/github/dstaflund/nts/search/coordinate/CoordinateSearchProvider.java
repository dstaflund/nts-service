package com.github.dstaflund.nts.search.coordinate;

import com.github.dstaflund.nts.search.NtsMap;
import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;

import java.util.List;

import static com.github.dstaflund.nts.search.NtsMap.CoordinateQueryContract.PARAM_LATITUDE;
import static com.github.dstaflund.nts.search.NtsMap.CoordinateQueryContract.PARAM_LONGITUDE;
import static com.github.dstaflund.nts.search.NtsMap.CoordinateQueryContract.QUERY_NAME;

final class CoordinateSearchProvider {
    private static final int sTimeoutInSeconds = 5;
    private static final boolean sCacheable = true;
    private static final boolean sReadOnlyInd = true;

    private CoordinateSearchProvider(){
    }

    static List<NtsMap> findMapsByCoordinate(CoordinateSearchParams ctx) {
        return QueryExecuter.executeQuery((Session session) ->
            session.getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_LATITUDE, ctx.getLatitude())
                .setParameter(PARAM_LONGITUDE, ctx.getLongitude())
                .setTimeout(sTimeoutInSeconds)
                .setReadOnly(sReadOnlyInd)
                .setCacheable(sCacheable)
        );
    }
}
