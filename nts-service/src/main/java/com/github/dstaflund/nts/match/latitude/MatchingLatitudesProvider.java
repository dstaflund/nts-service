package com.github.dstaflund.nts.match.latitude;

import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

final class MatchingLatitudesProvider {

    private MatchingLatitudesProvider(){
    }

    @SuppressWarnings("Duplicates")
    static List<Float> findMatchingLatitudes(MatchingLatitudesParams ctx) {
        if (ctx == null || ctx.getLatitude() == null || ctx.getLatitude().isEmpty()) return Collections.emptyList();
        List<BigDecimal> results = QueryExecuter.executeQuery((Session session) ->
            session.getNamedNativeQuery("NtsMap.NativeQuery.MatchingLatitudes")
                .setParameter(1, ctx.getLatitude().trim().replace("+", "") + "%")
                .setParameter(2, ctx.getLatitude().trim().replace("+", "") + "%")
        );
        return results == null
            ? Collections.emptyList()
            : results.parallelStream()
                .map(BigDecimal::floatValue)
                .collect(Collectors.toList());
    }
}
