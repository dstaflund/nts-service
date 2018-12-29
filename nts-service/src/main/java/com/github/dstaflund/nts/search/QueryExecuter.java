package com.github.dstaflund.nts.search;

import com.github.dstaflund.nts.listener.SessionFactoryListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public final class QueryExecuter {
   private static final Logger sLogger = LogManager.getLogger(QueryExecuter.class);

    private QueryExecuter(){
    }

    @SuppressWarnings("unchecked")
    public static List<NtsMap> executeQuery(Function<Session, Query> supplier){
        try(Session session = SessionFactoryListener.getSessionFactory().getCurrentSession()) {
            Transaction tx = null;

            try {
                tx = session.beginTransaction();
                Query query = supplier.apply(session);
                sLogger.debug("Query Start:  " + LocalDateTime.now());
                List<NtsMap> maps = query.list();
                sLogger.debug("Query End:  " + LocalDateTime.now());
                sLogger.debug("Result Count:  " + maps.size());
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

        sLogger.debug("Returning null");
        return null;
    }
}
