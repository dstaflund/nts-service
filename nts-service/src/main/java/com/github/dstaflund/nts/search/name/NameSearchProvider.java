package com.github.dstaflund.nts.search.name;

import com.github.dstaflund.nts.listener.SessionFactoryListener;
import com.github.dstaflund.nts.search.NtsMap;
import com.github.dstaflund.nts.search.NtsMap.NameQueryContract;
import com.github.dstaflund.nts.search.QueryExecuter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class NameSearchProvider {
    private static final int sTimeoutInSeconds = 5;
    private static final boolean sCacheable = true;
    private static final boolean sReadOnlyInd = true;

    public List<NtsMap> findMapsByName(NameSearchParams ctx) {
        SessionFactory sessionFactory = SessionFactoryListener.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return QueryExecuter.executeQuery(session, () ->
            session.getNamedQuery(NameQueryContract.QUERY_NAME)
                .setParameter(NameQueryContract.PARAM_NAME, NtsMap.formatName(ctx.getName()))
                .setParameter(NameQueryContract.PARAM_SNIPPET, NtsMap.formatSnippet(ctx.getSnippet()))
                .setParameter(NameQueryContract.PARAM_PARENT, NtsMap.formatParent(ctx.getParent()))
                .setTimeout(sTimeoutInSeconds)
                .setReadOnly(sReadOnlyInd)
                .setCacheable(sCacheable)
                .setFetchSize(ctx.getLimit() + 1)
                .setFirstResult(ctx.getOffset())
                .setMaxResults(ctx.getLimit() + 1)
        );
    }

    public static NameSearchProvider getInstance(){
        return InstanceHelper.INSTANCE;
    }

    private static class InstanceHelper {
        private static final NameSearchProvider INSTANCE = new NameSearchProvider();
    }
}
