package com.github.dstaflund.nts.match.parent;

import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;

import java.util.List;

import static com.github.dstaflund.nts.search.NtsMap.MatchingParentsContract.PARAM_QUERY;
import static com.github.dstaflund.nts.search.NtsMap.MatchingParentsContract.QUERY_NAME;

final class MatchingParentsProvider {

    private MatchingParentsProvider(){
    }

    static List<String> findMatchingSnippets(String query) {
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_QUERY, query)
        );
    }
}
