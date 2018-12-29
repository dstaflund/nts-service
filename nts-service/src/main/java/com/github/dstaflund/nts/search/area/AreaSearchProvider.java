package com.github.dstaflund.nts.search.area;

import com.github.dstaflund.nts.search.NtsMap;
import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;

import java.util.List;

import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_EAST;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_NORTH;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_SOUTH;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_WEST;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.QUERY_NAME;

final class AreaSearchProvider {
    private static final int sTimeoutInSeconds = 5;
    private static final boolean sCacheable = true;
    private static final boolean sReadOnlyInd = true;

    private AreaSearchProvider(){
    }

    static List<NtsMap> findMapsByArea(AreaSearchParams ctx) {
        return QueryExecuter.executeQuery((Session session) ->
            session.getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_NORTH, ctx.getNorth())
                .setParameter(PARAM_SOUTH, ctx.getSouth())
                .setParameter(PARAM_EAST, ctx.getEast())
                .setParameter(PARAM_WEST, ctx.getWest())
                .setTimeout(sTimeoutInSeconds)
                .setReadOnly(sReadOnlyInd)
                .setCacheable(sCacheable)
                .setFetchSize(ctx.getLimit() + 1)
                .setFirstResult(ctx.getOffset())
                .setMaxResults(ctx.getLimit() + 1)
        );
    }
}
