package com.github.dstaflund.nts.match.title;

import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.Collections;
import java.util.List;

final class MatchingTitlesProvider {

    private MatchingTitlesProvider(){
    }

    static List<String> findMatchingTitles(MatchingTitlesParams ctx) {
        if (ctx == null || ctx.getTitle() == null || ctx.getTitle().isEmpty()) return Collections.emptyList();
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery("NtsMap.Query.MatchingTitles")
                .setParameter("query", ctx.getTitle().trim().toUpperCase() + "%")
        );
    }
}
