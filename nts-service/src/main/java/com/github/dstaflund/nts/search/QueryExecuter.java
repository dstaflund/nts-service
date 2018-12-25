package com.github.dstaflund.nts.search;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Supplier;

public final class QueryExecuter {

    private QueryExecuter(){
    }

    @SuppressWarnings("unchecked")
    public static List<NtsMap> executeQuery(Session session, Supplier<Query> supplier){
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
}
