package com.github.dstaflund.nts.match.parent;

import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

import static com.github.dstaflund.nts.NtsMap.MatchingParentsContract.PARAM_QUERY;
import static com.github.dstaflund.nts.NtsMap.MatchingParentsContract.QUERY_NAME;

final class MatchingParentsProvider {

    private MatchingParentsProvider(){
    }

    static List<String> findMatchingSnippets(MatchingParentsParams ctx) {
        if (ctx == null || ctx.getParent() == null || ctx.getParent().isEmpty()) return Collections.emptyList();
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_QUERY, ctx.getParent().trim().toUpperCase() + "%")
        );
    }
}
