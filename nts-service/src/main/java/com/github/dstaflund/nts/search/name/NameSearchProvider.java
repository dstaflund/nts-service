package com.github.dstaflund.nts.search.name;

import com.github.dstaflund.nts.listener.SessionFactoryListener;
import com.github.dstaflund.nts.search.NtsMap;
import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static com.github.dstaflund.nts.search.NtsMap.NameQueryContract.PARAM_NAME;
import static com.github.dstaflund.nts.search.NtsMap.NameQueryContract.PARAM_PARENT;
import static com.github.dstaflund.nts.search.NtsMap.NameQueryContract.PARAM_SNIPPET;
import static com.github.dstaflund.nts.search.NtsMap.NameQueryContract.QUERY_NAME;
import static com.github.dstaflund.nts.search.NtsMap.formatName;
import static com.github.dstaflund.nts.search.NtsMap.formatParent;
import static com.github.dstaflund.nts.search.NtsMap.formatSnippet;

public final class NameSearchProvider {
    private static final int sTimeoutInSeconds = 5;
    private static final boolean sCacheable = true;
    private static final boolean sReadOnlyInd = true;

    private NameSearchProvider(){
    }

    public static List<NtsMap> findMapsByName(NameSearchParams ctx) {
        SessionFactory factory = SessionFactoryListener.getSessionFactory();
        Session session = factory.getCurrentSession();
        return QueryExecuter.executeQuery(session, () ->
            session.getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_NAME, formatName(ctx.getName()))
                .setParameter(PARAM_SNIPPET, formatSnippet(ctx.getSnippet()))
                .setParameter(PARAM_PARENT, formatParent(ctx.getParent()))
                .setTimeout(sTimeoutInSeconds)
                .setReadOnly(sReadOnlyInd)
                .setCacheable(sCacheable)
                .setFetchSize(ctx.getLimit() + 1)
                .setFirstResult(ctx.getOffset())
                .setMaxResults(ctx.getLimit() + 1)
        );
    }
}
