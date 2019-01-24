package com.github.dstaflund.nts.match.name;

import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

final class MatchingNamesProvider {

    private MatchingNamesProvider(){
    }

    static List<String> findMatchingSnippets(MatchingNamesParams ctx) {
        if (ctx == null || ctx.getName() == null || ctx.getName().isEmpty()) return Collections.emptyList();
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery("NtsMap.Query.MatchingNames")
                .setParameter("query", ctx.getName().trim().toUpperCase() + "%")
        );
    }
}
