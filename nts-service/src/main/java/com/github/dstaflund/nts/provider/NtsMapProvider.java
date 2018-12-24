package com.github.dstaflund.nts.provider;

import com.github.dstaflund.nts.listener.SessionFactoryListener;
import com.github.dstaflund.nts.model.AreaSearchContext;
import com.github.dstaflund.nts.model.CoordinateSearchContext;
import com.github.dstaflund.nts.model.NtsMap;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Supplier;

public class NtsMapProvider {
    private static final int sTimeoutInSeconds = 5;
    private static final boolean sCacheable = true;
    private static final boolean sReadOnlyInd = true;

    public List<NtsMap> findMapsByArea(AreaSearchContext req) {
        SessionFactory sessionFactory = SessionFactoryListener.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return executeQuery(session, () ->
            session.getNamedQuery(NtsMap.AreaQueryContract.QUERY_NAME)
                .setParameter(NtsMap.AreaQueryContract.PARAM_NAME, req.getName())
                .setParameter(NtsMap.AreaQueryContract.PARAM_SNIPPET, req.getSnippet())
                .setParameter(NtsMap.AreaQueryContract.PARAM_PARENT, req.getParent())
                .setParameter(NtsMap.AreaQueryContract.PARAM_NORTH, req.getNorth())
                .setParameter(NtsMap.AreaQueryContract.PARAM_SOUTH, req.getSouth())
                .setParameter(NtsMap.AreaQueryContract.PARAM_EAST, req.getEast())
                .setParameter(NtsMap.AreaQueryContract.PARAM_WEST, req.getWest())
                .setCacheable(sCacheable)
                .setFetchSize(req.getLimit() + 1)
                .setFirstResult(req.getOffset())
                .setReadOnly(sReadOnlyInd)
                .setMaxResults(req.getLimit() + 1)
                .setTimeout(sTimeoutInSeconds)
        );
    }

    public List<NtsMap> findMapsByCoordinate(CoordinateSearchContext req) {
        SessionFactory sessionFactory = SessionFactoryListener.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        return executeQuery(session, () ->
            session.getNamedQuery(NtsMap.CoordinateQueryContract.QUERY_NAME)
                .setParameter(NtsMap.CoordinateQueryContract.PARAM_NAME, req.getName())
                .setParameter(NtsMap.CoordinateQueryContract.PARAM_SNIPPET, req.getSnippet())
                .setParameter(NtsMap.CoordinateQueryContract.PARAM_PARENT, req.getParent())
                .setParameter(NtsMap.CoordinateQueryContract.PARAM_LATITUDE, req.getLatitude())
                .setParameter(NtsMap.CoordinateQueryContract.PARAM_LONGITUDE, req.getLongitude())
                .setCacheable(sCacheable)
                .setFetchSize(req.getLimit() + 1)
                .setFirstResult(req.getOffset())
                .setReadOnly(sReadOnlyInd)
                .setMaxResults(req.getLimit() + 1)
                .setTimeout(sTimeoutInSeconds)
        );
    }

    @SuppressWarnings("unchecked")
    private List<NtsMap> executeQuery(Session session, Supplier<Query> supplier){
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query query = supplier.get();
            List<NtsMap> maps = query.list();
            tx.commit();
            return maps;
        }

        catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
        }

        finally {
            session.close();
        }

        return null;
    }

    public static NtsMapProvider getInstance(){
        return InstanceHelper.INSTANCE;
    }

    private static class InstanceHelper {
        private static final NtsMapProvider INSTANCE = new NtsMapProvider();
    }
}
