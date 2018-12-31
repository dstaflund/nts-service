package com.github.dstaflund.nts;

import com.github.dstaflund.nts.listener.SessionFactoryListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Function;

public final class QueryExecuter {
    private static final Logger sLogger = LogManager.getLogger(QueryExecuter.class);
    private static final int sTimeoutInSeconds = 5;
    private static final boolean sCacheable = true;
    private static final boolean sReadOnlyInd = true;

    private QueryExecuter(){
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> executeQuery(Function<Session, Query> supplier){
        try(Session session = SessionFactoryListener.getSessionFactory().getCurrentSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                Query query = supplier.apply(session)
                    .setTimeout(sTimeoutInSeconds)
                    .setReadOnly(sReadOnlyInd)
                    .setCacheable(sCacheable);
                List<T> maps = query.list();
                tx.commit();
                return maps;
            }

            catch (Exception e) {
                sLogger.error("Error:  ", e);
                if (tx != null) {
                    tx.rollback();
                    throw e;
                }
            }
        }

        return null;
    }
}
