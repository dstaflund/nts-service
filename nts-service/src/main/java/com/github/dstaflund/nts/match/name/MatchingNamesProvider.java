package com.github.dstaflund.nts.match.name;

import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;

import java.util.List;

import static com.github.dstaflund.nts.search.NtsMap.MatchingNamesContract.PARAM_QUERY;
import static com.github.dstaflund.nts.search.NtsMap.MatchingNamesContract.QUERY_NAME;

final class MatchingNamesProvider {

    private MatchingNamesProvider(){
    }

    static List<String> findMatchingSnippets(String query) {
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_QUERY, query)
        );
    }
}
