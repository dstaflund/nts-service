package com.github.dstaflund.nts.match.longitude;

import java.util.Collections;
import java.util.List;

final class MatchingLongitudesProvider {

    private MatchingLongitudesProvider(){
    }

    static List<Float> findMatchingLongitudes(String query) {
        return Collections.emptyList();
//        return QueryExecuter.executeQuery((Session session) ->
//            session.getNamedQuery(QUERY_NAME)
//                .setParameter(PARAM_NORTH, ctx.getNorth())
//                .setParameter(PARAM_SOUTH, ctx.getSouth())
//                .setParameter(PARAM_EAST, ctx.getEast())
//                .setParameter(PARAM_WEST, ctx.getWest())
//                .setFetchSize(ctx.getLimit() + 1)
//                .setFirstResult(ctx.getOffset())
//                .setMaxResults(ctx.getLimit() + 1)
//        );
    }
}
