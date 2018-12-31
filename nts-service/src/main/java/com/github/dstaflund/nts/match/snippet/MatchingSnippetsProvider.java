package com.github.dstaflund.nts.match.snippet;

import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

import static com.github.dstaflund.nts.NtsMap.MatchingSnippetsContract.PARAM_QUERY;
import static com.github.dstaflund.nts.NtsMap.MatchingSnippetsContract.QUERY_NAME;

final class MatchingSnippetsProvider {

    private MatchingSnippetsProvider(){
    }

    static List<String> findMatchingSnippets(MatchingSnippetsParams ctx) {
        if (ctx == null || ctx.getSnippet() == null || ctx.getSnippet().isEmpty()) return Collections.emptyList();
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_QUERY, ctx.getSnippet().trim().toUpperCase() + "%")
        );
    }
}
