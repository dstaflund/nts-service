package com.github.dstaflund.nts.search;

import com.github.dstaflund.nts.NtsMap;
import com.github.dstaflund.nts.PagedResponse;
import com.github.dstaflund.nts.PagingParams;
import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.List;

class SearchProvider {

    private SearchProvider(){
    }

    static PagedResponse<List<NtsMap>> findMaps(PagingParams paging, SearchParams req) {
        return PagedResponse.newInstance(paging, req, getCount(req), getData(paging, req));
    }

    @SuppressWarnings({"Duplicates", "unchecked"})
    private static long getCount(SearchParams req){
        return QueryExecuter.getResultCount(
            (Session s) -> {
                String q = SearchQueryBuilder.newInstance()
                    .count(true)
                    .searchParams(req)
                    .build();

                return s.createQuery(q);
            }
        );
    }

    @SuppressWarnings("Duplicates")
    private static List<NtsMap> getData(PagingParams paging, SearchParams req) {
        return QueryExecuter.executeQuery(
            paging,
            (Session s) -> {
                String q = SearchQueryBuilder.newInstance()
                    .count(false)
                    .pagingParams(paging)
                    .searchParams(req)
                    .build();

                return s.createQuery(q);
            }
        );
    }
}
