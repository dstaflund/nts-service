package com.github.dstaflund.nts.match.latitude;

import java.util.Collections;
import java.util.List;

final class MatchingLatitudesProvider {

    private MatchingLatitudesProvider(){
    }

    static List<Float> findMatchingLatitudes(String query) {
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
