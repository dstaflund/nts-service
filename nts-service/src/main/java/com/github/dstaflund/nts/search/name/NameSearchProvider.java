package com.github.dstaflund.nts.search.name;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

final class NameSearchProvider {
    private static final Pattern sFullNamePattern = Pattern.compile("^\\s*([0-9]{1,3})?([A-P])?([01]?[0-9])?\\s*$");

    private NameSearchProvider(){
    }

    static PagedResponse<List<NtsMap>> findMapsByName(PagingParams paging, NameSearchParams req) {
        return PagedResponse.newInstance(paging, getCount(req), getData(paging, req));
    }

    @SuppressWarnings("Duplicates")
    private static long getCount(NameSearchParams req){
        return QueryExecuter.getResultCount(
            (Session session) -> {
                CriteriaBuilder b = session.getSessionFactory().getCriteriaBuilder();

                ParameterExpression<String> nameParam = b.parameter(String.class);
                ParameterExpression<String> snippetParam = b.parameter(String.class);

                CriteriaQuery<Long> criteria = b.createQuery(Long.class);
                Root<NtsMap> root = criteria.from(NtsMap.class);
                criteria
                    .select(b.count(root))
                    .where(
                        b.and(
                            b.or(
                                b.isNull(nameParam),
                                b.like(root.get(NtsMap_.searchName), nameParam)
                            ),
                            b.or(
                                b.isNull(snippetParam),
                                b.like(root.get(NtsMap_.snippet), snippetParam)
                            )
                        )
                    );

                return session.createQuery(criteria)
                    .setParameter(nameParam, scrubMapName(req.getName()))
                    .setParameter(snippetParam, scrubSnippet(req.getSnippet()));
            }
        );
    }

    @SuppressWarnings("Duplicates")
    private static List<NtsMap> getData(PagingParams paging, NameSearchParams req) {
        return QueryExecuter.executeQuery(
            paging,
            (Session session) -> {
                CriteriaBuilder b = session.getSessionFactory().getCriteriaBuilder();

                ParameterExpression<String> nameParam = b.parameter(String.class);
                ParameterExpression<String> snippetParam = b.parameter(String.class);

                CriteriaQuery<NtsMap> criteria = b.createQuery(NtsMap.class);
                Root<NtsMap> root = criteria.from(NtsMap.class);
                criteria
                    .select(root)
                    .where(
                        b.and(
                            b.or(
                                b.isNull(nameParam),
                                b.like(root.get(NtsMap_.searchName), nameParam)
                            ),
                            b.or(
                                b.isNull(snippetParam),
                                b.like(root.get(NtsMap_.snippet), snippetParam)
                            )
                        )
                    )
                    .orderBy(CriteriaQueryHelper.getOrderDir(b, root, paging));

                return session.createQuery(criteria)
                    .setParameter(nameParam, scrubMapName(req.getName()))
                    .setParameter(snippetParam, scrubSnippet(req.getSnippet()));
            }
        );
    }

    private static String scrubMapName(String value){
        if (value == null || value.trim().isEmpty()) return null;
        Matcher m = sFullNamePattern.matcher(value.trim().toUpperCase());
        if (! m.matches() || m.group(1) == null) return "X";
        return Integer.parseInt(m.group(1))
             + (m.group(2) == null ? "%" : m.group(2))
             + (m.group(3) == null ? "%" : (m.group(3).length() == 1 ? "0" + m.group(3) : m.group(3)));
    }

    private static String scrubSnippet(String value){
        if (value == null || value.trim().isEmpty()) return null;
        return value.trim().toUpperCase() + "%";
    }
}
