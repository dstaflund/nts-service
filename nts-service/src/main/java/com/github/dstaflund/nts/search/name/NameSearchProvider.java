package com.github.dstaflund.nts.search.name;

import com.github.dstaflund.nts.NtsMap;
import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.List;

import static com.github.dstaflund.nts.NtsMap.NameQueryContract.PARAM_NAME;
import static com.github.dstaflund.nts.NtsMap.NameQueryContract.PARAM_PARENT;
import static com.github.dstaflund.nts.NtsMap.NameQueryContract.PARAM_SNIPPET;
import static com.github.dstaflund.nts.NtsMap.NameQueryContract.QUERY_NAME;
import static com.github.dstaflund.nts.NtsMap.formatName;
import static com.github.dstaflund.nts.NtsMap.formatParent;
import static com.github.dstaflund.nts.NtsMap.formatSnippet;

final class NameSearchProvider {

    private NameSearchProvider(){
    }

    static List<NtsMap> findMapsByName(NameSearchParams ctx) {
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_NAME, formatName(ctx.getName()))
                .setParameter(PARAM_SNIPPET, formatSnippet(ctx.getSnippet()))
                .setParameter(PARAM_PARENT, formatParent(ctx.getParent()))
        );
    }
}
