package com.github.dstaflund.nts.search.coordinate;

import com.github.dstaflund.nts.NtsMap;
import com.github.dstaflund.nts.NtsMap.CoordinateQueryContract;
import com.github.dstaflund.nts.PagingData;
import com.github.dstaflund.nts.PagedResponse;
import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.List;

import static com.github.dstaflund.nts.NtsMap.CoordinateQueryContract.COUNT_QUERY_NAME;
import static com.github.dstaflund.nts.NtsMap.CoordinateQueryContract.PARAM_LATITUDE;
import static com.github.dstaflund.nts.NtsMap.CoordinateQueryContract.PARAM_LONGITUDE;

final class CoordinateSearchProvider {

    private CoordinateSearchProvider(){
    }

    static PagedResponse<List<NtsMap>> findMapsByCoordinate(PagingData paging, CoordinateSearchParams req) {
        return PagedResponse.newInstance(paging, getCount(req), getData(paging, req));
    }

    private static int getCount(CoordinateSearchParams req) {
        return QueryExecuter.getResultCount(
            (Session session) ->
                session
                    .getNamedQuery(COUNT_QUERY_NAME)
                    .setParameter(PARAM_LATITUDE, req.getLatitude())
                    .setParameter(PARAM_LONGITUDE, req.getLongitude())
        );
    }

    private static List<NtsMap> getData(PagingData paging, CoordinateSearchParams req) {
        return QueryExecuter.executeQuery(
            paging,
            (Session session) ->
                session
                    .getNamedQuery(CoordinateQueryContract.DATA_QUERY_NAME)
                    .setParameter(PARAM_LATITUDE, req.getLatitude())
                    .setParameter(PARAM_LONGITUDE, req.getLongitude())
        );
    }
}
