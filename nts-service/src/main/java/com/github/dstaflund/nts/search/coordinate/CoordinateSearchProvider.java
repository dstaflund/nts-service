package com.github.dstaflund.nts.search.coordinate;

import com.github.dstaflund.nts.CriteriaQueryHelper;
import com.github.dstaflund.nts.NtsMap;
import com.github.dstaflund.nts.NtsMap_;
import com.github.dstaflund.nts.PagingParams;
import com.github.dstaflund.nts.PagedResponse;
import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

final class CoordinateSearchProvider {

    private CoordinateSearchProvider(){
    }

    static PagedResponse<List<NtsMap>> findMapsByCoordinate(PagingParams paging, CoordinateSearchParams req) {
        return PagedResponse.newInstance(paging, getCount(req), getData(paging, req));
    }

    @SuppressWarnings("Duplicates")
    private static long getCount(CoordinateSearchParams req) {
        return QueryExecuter.getResultCount(
            (Session session) -> {
                CriteriaBuilder b = session.getSessionFactory().getCriteriaBuilder();

                ParameterExpression<Float> latParam = b.parameter(Float.class);
                ParameterExpression<Float> lngParam = b.parameter(Float.class);

                CriteriaQuery<Long> criteria = b.createQuery(Long.class);
                Root<NtsMap> root = criteria.from(NtsMap.class);
                criteria
                    .select(b.count(root))
                    .where(
                        b.and(
                            b.isNotNull(latParam),
                            b.isNotNull(lngParam),
                            b.between(latParam, root.get(NtsMap_.south), root.get(NtsMap_.north)),
                            b.between(lngParam, root.get(NtsMap_.west), root.get(NtsMap_.east))
                        )
                    );

                return session.createQuery(criteria)
                    .setParameter(latParam, req.getLatitude())
                    .setParameter(lngParam, req.getLongitude());
            }
        );
    }

    @SuppressWarnings("Duplicates")
    private static List<NtsMap> getData(PagingParams paging, CoordinateSearchParams req) {
        return QueryExecuter.executeQuery(
            paging,
            (Session session) -> {
                CriteriaBuilder b = session.getSessionFactory().getCriteriaBuilder();

                ParameterExpression<Float> latParam = b.parameter(Float.class);
                ParameterExpression<Float> lngParam = b.parameter(Float.class);

                CriteriaQuery<NtsMap> criteria = b.createQuery(NtsMap.class);
                Root<NtsMap> root = criteria.from(NtsMap.class);
                criteria
                    .select(root)
                    .where(
                        b.and(
                            b.isNotNull(latParam),
                            b.isNotNull(lngParam),
                            b.between(latParam, root.get(NtsMap_.south), root.get(NtsMap_.north)),
                            b.between(lngParam, root.get(NtsMap_.west), root.get(NtsMap_.east))
                        )
                    )
                    .orderBy(CriteriaQueryHelper.getOrderDir(b, root, paging));

                return session.createQuery(criteria)
                    .setParameter(latParam, req.getLatitude())
                    .setParameter(lngParam, req.getLongitude());
            }
        );
    }
}
