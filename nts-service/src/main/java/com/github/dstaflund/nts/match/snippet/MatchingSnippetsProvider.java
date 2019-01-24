package com.github.dstaflund.nts.match.snippet;

import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

final class MatchingSnippetsProvider {

    private MatchingSnippetsProvider(){
    }

    static List<String> findMatchingSnippets(MatchingSnippetsParams ctx) {
        if (ctx == null || ctx.getSnippet() == null || ctx.getSnippet().isEmpty()) return Collections.emptyList();
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery("NtsMap.Query.MatchingSnippets")
                .setParameter("query", ctx.getSnippet().trim().toUpperCase() + "%")
        );
    }
}
