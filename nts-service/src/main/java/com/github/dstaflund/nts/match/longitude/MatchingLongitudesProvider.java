package com.github.dstaflund.nts.match.longitude;

import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

final class MatchingLongitudesProvider {

    private MatchingLongitudesProvider(){
    }

    @SuppressWarnings("Duplicates")
    static List<Float> findMatchingLongitudes(MatchingLongitudesParams ctx) {
        if (ctx == null || ctx.getLongitude() == null || ctx.getLongitude().isEmpty()) return Collections.emptyList();
        List<BigDecimal> results = QueryExecuter.executeQuery((Session session) ->
            session.getNamedNativeQuery("NtsMap.NativeQuery.MatchingLongitudes")
                .setParameter(1, ctx.getLongitude().trim().replace("+", "") + "%")
                .setParameter(2, ctx.getLongitude().trim().replace("+", "") + "%")
        );
        return results == null
            ? Collections.emptyList()
            : results.parallelStream()
            .map(BigDecimal::floatValue)
            .collect(Collectors.toList());
    }
}
