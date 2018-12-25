package com.github.dstaflund.nts.search.area;

import com.github.dstaflund.nts.listener.SessionFactoryListener;
import com.github.dstaflund.nts.search.NtsMap;
import com.github.dstaflund.nts.search.NtsMap.AreaQueryContract;
import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AreaSearchProvider {
    private static final int sTimeoutInSeconds = 5;
    private static final boolean sCacheable = true;
    private static final boolean sReadOnlyInd = true;

    public List<NtsMap> findMapsByArea(AreaSearchParams ctx) {
        SessionFactory sessionFactory = SessionFactoryListener.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return QueryExecuter.executeQuery(session, () ->
            session.getNamedQuery(AreaQueryContract.QUERY_NAME)
                .setParameter(AreaQueryContract.PARAM_NAME, NtsMap.formatName(ctx.getName()))
                .setParameter(AreaQueryContract.PARAM_SNIPPET, NtsMap.formatSnippet(ctx.getSnippet()))
                .setParameter(AreaQueryContract.PARAM_PARENT, NtsMap.formatParent(ctx.getParent()))
                .setParameter(AreaQueryContract.PARAM_NORTH, ctx.getNorth())
                .setParameter(AreaQueryContract.PARAM_SOUTH, ctx.getSouth())
                .setParameter(AreaQueryContract.PARAM_EAST, ctx.getEast())
                .setParameter(AreaQueryContract.PARAM_WEST, ctx.getWest())
                .setTimeout(sTimeoutInSeconds)
                .setReadOnly(sReadOnlyInd)
                .setCacheable(sCacheable)
                .setFetchSize(ctx.getLimit() + 1)
                .setFirstResult(ctx.getOffset())
                .setMaxResults(ctx.getLimit() + 1)
        );
    }

    public static AreaSearchProvider getInstance(){
        return InstanceHelper.INSTANCE;
    }

    private static class InstanceHelper {
        private static final AreaSearchProvider INSTANCE = new AreaSearchProvider();
    }
}
