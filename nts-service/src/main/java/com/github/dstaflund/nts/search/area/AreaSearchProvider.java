package com.github.dstaflund.nts.search.area;

import com.github.dstaflund.nts.listener.SessionFactoryListener;
import com.github.dstaflund.nts.search.NtsMap;
import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_EAST;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_NAME;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_NORTH;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_PARENT;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_SNIPPET;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_SOUTH;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.PARAM_WEST;
import static com.github.dstaflund.nts.search.NtsMap.AreaQueryContract.QUERY_NAME;
import static com.github.dstaflund.nts.search.NtsMap.formatName;
import static com.github.dstaflund.nts.search.NtsMap.formatParent;
import static com.github.dstaflund.nts.search.NtsMap.formatSnippet;

public final class AreaSearchProvider {
    private static final int sTimeoutInSeconds = 5;
    private static final boolean sCacheable = true;
    private static final boolean sReadOnlyInd = true;

    private AreaSearchProvider(){
    }

    public static List<NtsMap> findMapsByArea(AreaSearchParams ctx) {
        SessionFactory factory = SessionFactoryListener.getSessionFactory();
        Session session = factory.getCurrentSession();
        return QueryExecuter.executeQuery(session, () ->
            session.getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_NAME, formatName(ctx.getName()))
                .setParameter(PARAM_SNIPPET, formatSnippet(ctx.getSnippet()))
                .setParameter(PARAM_PARENT, formatParent(ctx.getParent()))
                .setParameter(PARAM_NORTH, ctx.getNorth())
                .setParameter(PARAM_SOUTH, ctx.getSouth())
                .setParameter(PARAM_EAST, ctx.getEast())
                .setParameter(PARAM_WEST, ctx.getWest())
                .setTimeout(sTimeoutInSeconds)
                .setReadOnly(sReadOnlyInd)
                .setCacheable(sCacheable)
                .setFetchSize(ctx.getLimit() + 1)
                .setFirstResult(ctx.getOffset())
                .setMaxResults(ctx.getLimit() + 1)
        );
    }
}
