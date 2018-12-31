package com.github.dstaflund.nts.match.latitude;

import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

import static com.github.dstaflund.nts.NtsMap.MatchingLatitudesContract.QUERY_NAME;

final class MatchingLatitudesProvider {

    private MatchingLatitudesProvider(){
    }

    @SuppressWarnings("Duplicates")
    static List<Float> findMatchingLatitudes(MatchingLatitudesParams ctx) {
        if (ctx == null || ctx.getLatitude() == null || ctx.getLatitude().isEmpty()) return Collections.emptyList();
        return QueryExecuter.executeQuery((Session session) ->
            session.getNamedNativeQuery(QUERY_NAME)
                .setParameter(1, ctx.getLatitude().trim().replace("+", "") + "%")
                .setParameter(2, ctx.getLatitude().trim().replace("+", "") + "%")
        );
    }
}
