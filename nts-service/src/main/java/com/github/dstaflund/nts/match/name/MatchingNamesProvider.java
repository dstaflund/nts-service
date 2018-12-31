package com.github.dstaflund.nts.match.name;

import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

import static com.github.dstaflund.nts.NtsMap.MatchingNamesContract.PARAM_QUERY;
import static com.github.dstaflund.nts.NtsMap.MatchingNamesContract.QUERY_NAME;

final class MatchingNamesProvider {

    private MatchingNamesProvider(){
    }

    static List<String> findMatchingSnippets(MatchingNamesParams ctx) {
        if (ctx == null || ctx.getName() == null || ctx.getName().isEmpty()) return Collections.emptyList();
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_QUERY, ctx.getName().trim().toUpperCase() + "%")
        );
    }
}
