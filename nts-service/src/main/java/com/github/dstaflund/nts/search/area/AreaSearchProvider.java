package com.github.dstaflund.nts.search.area;

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

final class AreaSearchProvider {

    private AreaSearchProvider(){
    }

    static PagedResponse<List<NtsMap>> findMapsByArea(PagingParams paging, AreaSearchParams req) {
        return PagedResponse.newInstance(paging, getCount(req), getData(paging, req));
    }

    @SuppressWarnings("Duplicates")
    private static long getCount(AreaSearchParams req) {
        return QueryExecuter.getResultCount(
            (Session session) -> {
                CriteriaBuilder b = session.getSessionFactory().getCriteriaBuilder();

                ParameterExpression<Float> northParam = b.parameter(Float.class);
                ParameterExpression<Float> southParam = b.parameter(Float.class);
                ParameterExpression<Float> eastParam = b.parameter(Float.class);
                ParameterExpression<Float> westParam = b.parameter(Float.class);

                CriteriaQuery<Long> criteria = b.createQuery(Long.class);
                Root<NtsMap> root = criteria.from(NtsMap.class);
                criteria
                    .select(b.count(root))
                    .where(
                        b.and(
                            b.isNotNull(northParam),
                            b.isNotNull(southParam),
                            b.isNotNull(eastParam),
                            b.isNotNull(westParam),
                            b.between(root.get(NtsMap_.north), southParam, northParam),
                            b.between(root.get(NtsMap_.south), southParam, northParam),
                            b.between(root.get(NtsMap_.east), westParam, eastParam),
                            b.between(root.get(NtsMap_.west), westParam, eastParam)
                        )
                    );
                return session.createQuery(criteria)
                    .setParameter(northParam, req.getNorth())
                    .setParameter(southParam, req.getSouth())
                    .setParameter(eastParam, req.getEast())
                    .setParameter(westParam, req.getWest());
            }
        );
    }

    @SuppressWarnings("Duplicates")
    private static List<NtsMap> getData(PagingParams paging, AreaSearchParams req) {
        return QueryExecuter.executeQuery(
            paging,
            (Session session) ->
            {
                CriteriaBuilder b = session.getSessionFactory().getCriteriaBuilder();

                ParameterExpression<Float> northParam = b.parameter(Float.class);
                ParameterExpression<Float> southParam = b.parameter(Float.class);
                ParameterExpression<Float> eastParam = b.parameter(Float.class);
                ParameterExpression<Float> westParam = b.parameter(Float.class);

                CriteriaQuery<NtsMap> criteria = b.createQuery(NtsMap.class);
                Root<NtsMap> root = criteria.from(NtsMap.class);
                criteria
                    .select(root)
                    .where(
                        b.and(
                            b.isNotNull(northParam),
                            b.isNotNull(southParam),
                            b.isNotNull(eastParam),
                            b.isNotNull(westParam),
                            b.between(root.get(NtsMap_.north), southParam, northParam),
                            b.between(root.get(NtsMap_.south), southParam, northParam),
                            b.between(root.get(NtsMap_.east), westParam, eastParam),
                            b.between(root.get(NtsMap_.west), westParam, eastParam)
                        )
                    )
                    .orderBy(CriteriaQueryHelper.getOrderDir(b, root, paging));

                return session.createQuery(criteria)
                    .setParameter(northParam, req.getNorth())
                    .setParameter(southParam, req.getSouth())
                    .setParameter(eastParam, req.getEast())
                    .setParameter(westParam, req.getWest());
            }
        );
    }
}
