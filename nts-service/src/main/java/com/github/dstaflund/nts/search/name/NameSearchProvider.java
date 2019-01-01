package com.github.dstaflund.nts.search.name;

import com.github.dstaflund.nts.NtsMap;
import com.github.dstaflund.nts.QueryExecuter;
import org.hibernate.Session;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.github.dstaflund.nts.NtsMap.NameQueryContract.PARAM_NAME;
import static com.github.dstaflund.nts.NtsMap.NameQueryContract.PARAM_SNIPPET;
import static com.github.dstaflund.nts.NtsMap.NameQueryContract.QUERY_NAME;

final class NameSearchProvider {
    private static final Pattern sFullNamePattern = Pattern.compile("^\\s*([0-9]{1,3})?([A-P])?([01]?[0-9])?\\s*$");

    private NameSearchProvider(){
    }

    static List<NtsMap> findMapsByName(NameSearchParams ctx) {
        return QueryExecuter.executeQuery((Session session) ->
            session
                .getNamedQuery(QUERY_NAME)
                .setParameter(PARAM_NAME, scrubMapName(ctx.getName()))
                .setParameter(PARAM_SNIPPET, scrubSnippet(ctx.getSnippet()))
        );
    }

    private static String scrubMapName(String value){
        if (value == null || value.trim().isEmpty()) return null;
        Matcher m = sFullNamePattern.matcher(value.trim().toUpperCase());
        if (! m.matches() || m.group(1) == null) return "X";
        String result = Integer.parseInt(m.group(1))
             + (m.group(2) == null ? "%" : m.group(2))
             + (m.group(3) == null ? "%" : (m.group(3).length() == 1 ? "0" + m.group(3) : m.group(3)));
        return result;
    }

    private static String scrubSnippet(String value){
        if (value == null || value.trim().isEmpty()) return null;
        return value.trim().toUpperCase() + "%";
    }
}
