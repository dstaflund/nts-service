package com.github.dstaflund.nts.search.area;

import com.github.dstaflund.nts.NtsMap;
import com.github.dstaflund.nts.PagingParams;
import com.github.dstaflund.nts.PagedResponse;
import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.List;

import static com.github.dstaflund.nts.NtsMap.AreaQueryContract.COUNT_QUERY_NAME;
import static com.github.dstaflund.nts.NtsMap.AreaQueryContract.DATA_QUERY_NAME;
import static com.github.dstaflund.nts.NtsMap.AreaQueryContract.PARAM_EAST;
import static com.github.dstaflund.nts.NtsMap.AreaQueryContract.PARAM_NORTH;
import static com.github.dstaflund.nts.NtsMap.AreaQueryContract.PARAM_SOUTH;
import static com.github.dstaflund.nts.NtsMap.AreaQueryContract.PARAM_WEST;

final class AreaSearchProvider {

    private AreaSearchProvider(){
    }

    static PagedResponse<List<NtsMap>> findMapsByArea(PagingParams paging, AreaSearchParams req) {
        return PagedResponse.newInstance(paging, getCount(req), getData(paging, req));
    }

    private static int getCount(AreaSearchParams req) {
        return QueryExecuter.getResultCount(
            (Session session) ->
                session
                    .getNamedQuery(COUNT_QUERY_NAME)
                    .setParameter(PARAM_NORTH, req.getNorth())
                    .setParameter(PARAM_SOUTH, req.getSouth())
                    .setParameter(PARAM_EAST, req.getEast())
                    .setParameter(PARAM_WEST, req.getWest())
        );
    }

    private static List<NtsMap> getData(PagingParams paging, AreaSearchParams req) {
        return QueryExecuter.executeQuery(
            paging,
            (Session session) ->
                session
                    .getNamedQuery(DATA_QUERY_NAME)
                    .setParameter(PARAM_NORTH, req.getNorth())
                    .setParameter(PARAM_SOUTH, req.getSouth())
                    .setParameter(PARAM_EAST, req.getEast())
                    .setParameter(PARAM_WEST, req.getWest())
        );
    }
}
