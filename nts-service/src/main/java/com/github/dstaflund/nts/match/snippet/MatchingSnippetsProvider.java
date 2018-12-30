package com.github.dstaflund.nts.match.snippet;

import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;

import java.util.List;

import static com.github.dstaflund.nts.search.NtsMap.MatchingSnippetsContract.PARAM_QUERY;
import static com.github.dstaflund.nts.search.NtsMap.MatchingSnippetsContract.QUERY_NAME;

final class MatchingSnippetsProvider {

    private MatchingSnippetsProvider(){
    }

    static List<String> findMatchingSnippets(String query) {
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_QUERY, query)
        );
    }
}
