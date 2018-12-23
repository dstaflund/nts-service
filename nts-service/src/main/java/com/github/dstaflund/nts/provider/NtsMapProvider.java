package com.github.dstaflund.nts.provider;

import com.github.dstaflund.nts.listener.SessionFactoryListener;
import com.github.dstaflund.nts.model.NtsMap;
import com.github.dstaflund.nts.model.SearchRequest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class NtsMapProvider {

    public List<NtsMap> GetMaps(SearchRequest req) {
        SessionFactory sessionFactory = SessionFactoryListener.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.beginTransaction();
        NtsMap map = session.get(NtsMap.class, req.getName());

        return Collections.singletonList(map);
    }

    public static NtsMapProvider getInstance(){
        return InstanceHelper.INSTANCE;
    }

    private static class InstanceHelper {
        private static final NtsMapProvider INSTANCE = new NtsMapProvider();
    }
}
