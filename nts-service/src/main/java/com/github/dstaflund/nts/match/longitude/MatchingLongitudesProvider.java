package com.github.dstaflund.nts.match.longitude;

import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

import static com.github.dstaflund.nts.NtsMap.MatchingLongitudesContract.QUERY_NAME;

final class MatchingLongitudesProvider {

    private MatchingLongitudesProvider(){
    }

    @SuppressWarnings("Duplicates")
    static List<Float> findMatchingLongitudes(MatchingLongitudesParams ctx) {
        if (ctx == null || ctx.getLongitude() == null || ctx.getLongitude().isEmpty()) return Collections.emptyList();
        return QueryExecuter.executeQuery((Session session) ->
            session.getNamedNativeQuery(QUERY_NAME)
                .setParameter(1, ctx.getLongitude().trim().replace("+", "") + "%")
                .setParameter(2, ctx.getLongitude().trim().replace("+", "") + "%")
        );
    }
}
