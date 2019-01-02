package com.github.dstaflund.nts.search.name;

import com.github.dstaflund.nts.NtsMap;
import com.github.dstaflund.nts.PagingData;
import com.github.dstaflund.nts.PagedResponse;
import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.dstaflund.nts.NtsMap.NameQueryContract.COUNT_QUERY_NAME;
import static com.github.dstaflund.nts.NtsMap.NameQueryContract.PARAM_NAME;
import static com.github.dstaflund.nts.NtsMap.NameQueryContract.PARAM_SNIPPET;

final class NameSearchProvider {
    private static final Pattern sFullNamePattern = Pattern.compile("^\\s*([0-9]{1,3})?([A-P])?([01]?[0-9])?\\s*$");

    private NameSearchProvider(){
    }

    static PagedResponse<List<NtsMap>> findMapsByName(PagingData paging, NameSearchParams req) {
        return PagedResponse.newInstance(paging, getCount(req), getData(paging, req));
    }

    private static int getCount(NameSearchParams req){
        return QueryExecuter.getResultCount(
            (Session session) ->
                session
                    .getNamedQuery(COUNT_QUERY_NAME)
                    .setParameter(PARAM_NAME, scrubMapName(req.getName()))
                    .setParameter(PARAM_SNIPPET, scrubSnippet(req.getSnippet()))
        );
    }

    private static List<NtsMap> getData(PagingData paging, NameSearchParams req) {
        return QueryExecuter.executeQuery(
            paging,
            (Session session) ->
            session
                .getNamedQuery(NtsMap.NameQueryContract.DATA_QUERY_NAME)
                .setParameter(PARAM_NAME, scrubMapName(req.getName()))
                .setParameter(PARAM_SNIPPET, scrubSnippet(req.getSnippet()))
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
